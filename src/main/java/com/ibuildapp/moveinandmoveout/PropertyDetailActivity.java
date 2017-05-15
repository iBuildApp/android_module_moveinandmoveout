package com.ibuildapp.moveinandmoveout;

import android.animation.Animator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.animations.SimpleAnimatorListener;
import com.ibuildapp.moveinandmoveout.adapters.PropertyDetailAdapter;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.view.DetailsViewPager;
import java.util.List;

public class PropertyDetailActivity extends AppBuilderModuleMainAppCompat {
    private String title;
    private int position;
    public View rootView;
    private DetailsViewPager viewPager;
    private View nextLayout;
    private View previousLayout;
    private List<SpreadsheetItemProp> data;
    private PropertyDetailAdapter adapter;
    private List<SpreadsheetItemMove> originalItemsMove;
    public String flagName="Noname";
    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_property_detail);
        initContent();
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarTitle(getString(R.string.moveinandmoveout_propertynametittle));
        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.moveinandmoveout_back), getResources().getColor(android.R.color.black), true, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setTopBarTitleColor(getResources().getColor(android.R.color.black));

        rootView = findViewById(R.id.moveinandmoveout_main_details_root);
        rootView.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        viewPager = (DetailsViewPager) findViewById(R.id.moveinandmoveout_details_view_pager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setScrollDurationFactor(2f);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    hidePreviousButton();

                if (position > 0)
                    showPreviousButton();

                if (position == adapter.getCount() - 1)
                    hideNextButton();

                if (position < adapter.getCount() - 1)
                    showNextButton();
            }
        });
        nextLayout = findViewById(R.id.moveinandmoveout_details_next);
        previousLayout = findViewById(R.id.moveinandmoveout_details_previous);
        nextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
                if (currentPosition < viewPager.getAdapter().getCount() - 1)
                    viewPager.setCurrentItem(currentPosition + 1, true);
            }
        });

        previousLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
                if (currentPosition > 0)
                    viewPager.setCurrentItem(currentPosition - 1, true);
            }
        });
        adapter = new PropertyDetailAdapter(getSupportFragmentManager(), data, this);


        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position, false);
    }
    public void initContent(){
        Intent intent = getIntent();
        Widget widget = (Widget)intent.getSerializableExtra(MoveInandMoveOutContants.EXTRA_WIDGET);

        if(widget == null) {
            return;
        }
        String propertyName = intent.getStringExtra(MoveInandMoveOutContants.PROPERTYNAME);
        title = widget.getTitle();
        flagName="NoName";
        position = intent.getIntExtra(MoveInandMoveOutContants.POSITION, 0);
        //Сделаем набор данных по одному адресу, чтобы не было проблем с перелистовкой
        data = EntityManager.getInstance().getItems();
        //Проставим статусы
        for (int i = 0; i < data.size(); i++) {
            //Получим ключи
            String flatnumber = data.get(i).getFlatnumber();
            String propName = data.get(i).getPropertyname();
           if (propName.equals(flagName)) {
                data.remove(i);
                i=i-1;
            }
             else {
                //Побежали по второй таблице искать нужные данные
                originalItemsMove = EntityManager.getInstance().getItemsMovebyFlatList(propName, flatnumber);
                if (originalItemsMove.size() > 0) {
                    String dateStr = originalItemsMove.get(0).getDateOut();
                    if (!dateStr.equals("")) {
                        data.get(i).setStatus(getResources().getString(R.string.moveinandmoveout_available_text));// ("Available");
                    } else {
                        data.get(i).setStatus(originalItemsMove.get(0).getTenantname());
                    }
                } else {
                    //Проставляем статус свободно
                    data.get(i).setStatus(getResources().getString(R.string.moveinandmoveout_available_text));
                }
                //Запомним имя, если еще раз повториться  - удаляем
                flagName = data.get(i).getPropertyname();
            }
       }
        for (int index = 0; index < data.size(); index++)
            if (data.get(index).getPropertyname().equals(propertyName)) {
                position = index;
                break;
            }
    }


    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();

                if (position < -1) {
                    view.setAlpha(0);

                } else if (position <= 1) {
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                    float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                    if (position < 0) {
                        view.setTranslationX(horzMargin - vertMargin / 2);
                    } else {
                        view.setTranslationX(-horzMargin + vertMargin / 2);
                    }

                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                    view.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                } else view.setAlpha(0);
            }
        }
    }
    private void showNextButton() {
        if (nextLayout.getVisibility() == View.GONE ){
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                nextLayout.setY(nextLayout.getHeight());

                nextLayout.animate().translationY(0)
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(new SimpleAnimatorListener(){
                            @Override
                            public void onAnimationStart(Animator animation) {
                                nextLayout.setVisibility(View.VISIBLE);
                            }
                        });
            } else nextLayout.setVisibility(View.VISIBLE);
        }
    }

    private void hideNextButton() {
        if (nextLayout.getVisibility() == View.VISIBLE)
            if (android.os.Build.VERSION.SDK_INT >= 12) {

                nextLayout.animate().translationY(nextLayout.getHeight())
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new AccelerateInterpolator())
                        .setListener(new SimpleAnimatorListener(){
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                nextLayout.setVisibility(View.GONE);
                            }
                        });
            } else nextLayout.setVisibility(View.GONE);
    }

    private void showPreviousButton() {
        if (previousLayout.getVisibility() == View.GONE ) {
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                previousLayout.setY(previousLayout.getHeight());

                previousLayout.animate().translationY(0)
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(new SimpleAnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                previousLayout.setVisibility(View.VISIBLE);
                            }
                        });
            } else previousLayout.setVisibility(View.VISIBLE);
        }
    }

    private void hidePreviousButton() {
        if (previousLayout.getVisibility() == View.VISIBLE)
            if (android.os.Build.VERSION.SDK_INT >= 12) {

                previousLayout.animate().translationY(previousLayout.getHeight())
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new AccelerateInterpolator())
                        .setListener(new SimpleAnimatorListener(){
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                previousLayout.setVisibility(View.GONE);
                            }
                        });
            } else previousLayout.setVisibility(View.GONE);
    }
    public void formDetailsAddressActivity(String propertyname, String flatnumber) {
        Intent intent = new Intent(this, AddressDetailAtivity.class);
        intent.putExtra(MoveInandMoveOutContants.PROPERTYNAME, propertyname);
        intent.putExtra(MoveInandMoveOutContants.FLATNUMBER, flatnumber);
        startActivityForResult(intent, MoveInandMoveOutContants.DETAILS_ACTIVITY_REQUEST);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
    }
}
