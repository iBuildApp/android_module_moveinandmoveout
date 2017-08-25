package com.ibuildapp.moveinandmoveout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.appbuilder.sdk.android.Utils;
import com.appbuilder.sdk.android.Widget;
import com.ibuildapp.moveinandmoveout.adapters.PropertyNameAdapter;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.BitmapUtils;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.SimpleTextWatcher;
import com.ibuildapp.moveinandmoveout.xml.XmlParser;
import com.restfb.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;


public class MoveInandMoveOutPlugin extends AppBuilderModuleMainAppCompat {
    private String xml;
    private String title;
    public EntityManager manager;
    private View root;
    private EditText searchText;
    private View clearSearch;
    private View aboutUsLayout;
    private View syncLayout;
    private View availble;
    private View rented;
    private View fullList;
    private RecyclerView mainList;
    private boolean launching = false;
    private List<SpreadsheetItemProp> originalItems;
    private PropertyNameAdapter adapter;
    public  String qFilter="";
    public  Integer flagStatus = 0;
    private ImageView syncImage;
    private TextView syncText;
    private View topPanel;


    public void create() {
        setContentView(R.layout.moveinandmoveout_main);
        root= findViewById(R.id.moveinandmoveout_main_root_view);
        searchText = (EditText) findViewById(R.id.moveinandmoveout_main_search_edit_text);
        clearSearch = findViewById(R.id.moveinandmoveout_main_search_clear_text);
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchText.setText("");
            }
        });
        topPanel = findViewById(R.id.moveinandmoveout_main_toppanel);
        aboutUsLayout = findViewById(R.id.moveinandmoveout_main_about_us_layout);
        aboutUsLayout.setOnClickListener(new AboutUsButtonClickListener());
        syncLayout = findViewById(R.id.moveinandmoveout_main_sync_layout);
        syncLayout.setOnClickListener(new SyncButtonClickListener());
        availble = findViewById(R.id.moveinandmoveout_main_availble_layout);
        rented=findViewById(R.id.moveinandmoveout_main_rented_layout);
        fullList=findViewById(R.id.moveinandmoveout_main_fulllist_layout);
        rented.setOnClickListener(new RentedClickListener());
        fullList.setOnClickListener(new FullListClickListener());
        availble.setOnClickListener(new AvailableClickListener());
        syncImage = (ImageView) findViewById(R.id.moveinandmoveout_main_sync_image);
        syncText = (TextView) findViewById(R.id.moveinandmoveout_main_sync_text);
        mainList = (RecyclerView) findViewById(R.id.moveinandmoveout_main_list);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        mainList.addItemDecoration(new RecyclerView.ItemDecoration() {
            private float itemBottom = getResources().getDimension(R.dimen.moveinandmoveout_main_item_bottom);
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int totalCount = parent.getAdapter().getItemCount();

                if (position == 0)
                    outRect.top = (int) (2 * itemBottom);

                if (position == totalCount - 1)
                    outRect.bottom = (int) (2 * itemBottom);
            }
        });


        searchText.addTextChangedListener(new SimpleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().equals(""))
                    showClearButton();
                else hideClearButton();
                if (s.toString().trim().equals("")) {
                    originalItems=manager.getItems();
                }
                final List<SpreadsheetItemProp> filteredModelList = filter(originalItems, s.toString());
                setContainer(filteredModelList);
            //    adapter.animateTo(filteredModelList);
            }
        });
        initContent();
        Schedulers.computation().createWorker().schedule(new Action0() {
           @Override
            public void call() {
                initWidgetData();
            }
        });

    }

    public void initContent(){
        Intent intent = getIntent();
        Widget widget = (Widget)intent.getSerializableExtra(MoveInandMoveOutContants.EXTRA_WIDGET);

        if(widget == null) {
            handleErrorInit();
            return;
        }
        xml = widget.getPluginXmlData().length() == 0
                ? Utils.readXmlFromFile(widget.getPathToXmlFile())
                : widget.getPluginXmlData();

        StaticData.setWidgetId(widget.getWidgetId());
        if(TextUtils.isEmpty(xml)) {
            handleErrorInit();
            handleErrorInit();
            return;
        }
        title = widget.getTitle();
        manager = EntityManager.newInstance(this,com.appbuilder.sdk.android.Statics.appId,  widget.getOrder());
    }

    private void initWidgetData() {
        StaticData.setXmlParsedData(XmlParser.parse(xml));

        final SharedPreferences configuration = getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, MODE_PRIVATE);
        if (StaticData.getXmlParsedData().getGoogle() == null){
            Toast.makeText(MoveInandMoveOutPlugin.this, R.string.moveinandmoveout_document_not_set, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        String newAccessToken = configuration.getString(StaticData.getXmlParsedData().getGoogle().getDocumentToken(), "");
        if (!StringUtils.isBlank(newAccessToken))
            StaticData.getXmlParsedData().getGoogle().setAccessToken(newAccessToken);

        AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                launchUI();
            }
        });
    }
    private void launchUI() {
        setTopBarTitle(title);
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarLeftButtonTextAndColor( getResources().getString(R.string.moveinandmoveout_home), ContextCompat.getColor(this, android.R.color.black),
                true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

        setTopBarTitleColor(ContextCompat.getColor(this, android.R.color.black));
        root.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        topPanel.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        Schedulers.computation().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                initBackend();
            }
        });
    }


    private void handleErrorInit() {

    }

    private void initBackend() {
        ContainerProp container = manager.getContainer(StaticData.getXmlParsedData().getGoogle().getDocumentToken());
        if (container != null && container.getLoaded()  > 0){
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                        @Override
                        public void call() {
                            setContainer(originalItems);
                        }
                    });
                }
            });
        } else {
            Intent intent = new Intent(MoveInandMoveOutPlugin.this, SyncActivity.class);
            startActivityForResult(intent, MoveInandMoveOutContants.SYNC_ACTIVITY_REQUEST);
            overridePendingTransition(R.anim.moveinandmoveout_enter_from_bottom, 0);
        }
    }

    private void setContainer(List<SpreadsheetItemProp> filteredModelList) {
        originalItems=manager.getItems();
        if (filteredModelList!=null){
            if (filteredModelList.size()>0) {
            qFilter="";
            for (int i = 0; i < filteredModelList.size(); i++) {
                 qFilter = qFilter+ "'"+ filteredModelList.get(i).getPropertyname()+"',";
            }
            qFilter=qFilter.substring(0,qFilter.length()-1);
            adapter = new PropertyNameAdapter(this, manager.getPropDistinctFilter(qFilter), manager, flagStatus);
           } else {
                adapter = null;
            }
          }
        else
        {
            adapter = new PropertyNameAdapter(this, manager.getPropDistinct(), manager, flagStatus);
        }
        mainList.setAdapter(adapter);
    }

    private class SyncButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MoveInandMoveOutPlugin.this, SyncActivity.class);
            startActivityForResult(intent, MoveInandMoveOutContants.SYNC_ACTIVITY_REQUEST);
            overridePendingTransition(R.anim.moveinandmoveout_enter_from_bottom, 0);

        }
    }


    private void showClearButton() {
        if (clearSearch.getVisibility()!= View.VISIBLE)
            ViewCompat.setScaleX(clearSearch, 0);
        ViewCompat.setScaleY(clearSearch, 0);

        ViewCompat.animate(clearSearch).scaleX(1).scaleY(1)
                .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                .setInterpolator(new OvershootInterpolator(2f))
                .setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(View view) {
                        clearSearch.setVisibility(View.VISIBLE);
                    }
                });
    }

    public void hideClearButton() {
        if (android.os.Build.VERSION.SDK_INT >= 12) {

            ViewCompat.animate(clearSearch).scaleX(0).scaleY(0)
                    .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                    .setInterpolator(new AccelerateInterpolator(2f))
                    .setListener(new ViewPropertyAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(View view) {
                            clearSearch.setVisibility(View.INVISIBLE);
                        }
                    });
        } else clearSearch.setVisibility(View.INVISIBLE);
    }

    private List<SpreadsheetItemProp> filter(List<SpreadsheetItemProp> models, String query) {
        query = query.toLowerCase();

        final List<SpreadsheetItemProp> filteredModelList = new ArrayList<>();
        for (SpreadsheetItemProp model : models) {
            final String propertyname = model.getPropertyname().toLowerCase();
            if (propertyname.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    private class AboutUsButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(!launching)
                launching = true;
            else return;

            Intent intent = new Intent(MoveInandMoveOutPlugin.this, AboutUsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
        }
    }
    public void launchDetailsActivity(String propertyname) {
        Intent intent = new Intent(this, PropertyDetailActivity.class);
        intent.putExtra(MoveInandMoveOutContants.PROPERTYNAME, propertyname);
        startActivityForResult(intent, MoveInandMoveOutContants.DETAILS_ACTIVITY_REQUEST);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
    }

    public void launchDetailsAddressActivity(String propertyname, String flatnumber) {
        Intent intent = new Intent(this, AddressDetailAtivity.class);
        intent.putExtra(MoveInandMoveOutContants.PROPERTYNAME, propertyname);
        intent.putExtra(MoveInandMoveOutContants.FLATNUMBER, flatnumber);
        startActivityForResult(intent, MoveInandMoveOutContants.DETAILS_ACTIVITY_REQUEST);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
    }

    private class RentedClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           flagStatus=3;
           create();
        }
    }

    private class FullListClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            flagStatus=1;
            create();
        }
    }

    private class AvailableClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            flagStatus=2;
            create();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        launching = false;
        if (requestCode == MoveInandMoveOutContants.SYNC_ACTIVITY_REQUEST){
            if (resultCode == RESULT_OK) {
                if (originalItems != null)
                    searchText.setText("");
                syncButtonHide();
                setContainer(originalItems);
            }else {
                if (data!= null) {
                    String error = data.getStringExtra(MoveInandMoveOutContants.SYNC_ERROR);
                    if (!StringUtils.isBlank(error))
                        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                }
            }

        } else if (requestCode == MoveInandMoveOutContants.DETAILS_ACTIVITY_REQUEST){
            if (data != null){
                boolean isUpdated = data.getBooleanExtra(MoveInandMoveOutContants.ITEM_UPDATED, false);
                int removedPosition = data.getIntExtra(MoveInandMoveOutContants.ITEM_DELETED, -1);

                if (isUpdated || removedPosition != -1){
                    int firstPosition = ((LinearLayoutManager)mainList.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

                    List<SpreadsheetItemProp> newItems = manager.getItems();
                    List<Integer> changeItems = new ArrayList<>();

                    Log.e("SIZE", String.valueOf(newItems.size()));

                    for (int size = 0; size < newItems.size(); size ++) {
                        if (removedPosition == -1) {
                            if (!newItems.get(size).equals(originalItems.get(size)))
                                changeItems.add(size);
                        }else {
                            if (size < removedPosition){
                                if (!newItems.get(size).equals(originalItems.get(size)))
                                    changeItems.add(size);
                            }else if (size >= removedPosition){
                                if (!newItems.get(size).equals(originalItems.get(size+1)))
                                    changeItems.add(size);
                            }
                        }
                    }

                    if (removedPosition != -1)
                        adapter.notifyItemRemoved(removedPosition);

                    originalItems = newItems;
                    adapter.setItems(new ArrayList<>(originalItems));

                    for (int change : changeItems)
                        adapter.notifyItemChanged(change);

                    mainList.scrollToPosition(firstPosition);
                    searchText.setText(searchText.getText());

                    if(manager.getUpdatesCountMove()> 0 || manager.getNewsMove().size() > 0 )
                        syncButtonShow();
                    else syncButtonHide();
                }
            }

        }else if (requestCode == MoveInandMoveOutContants.EDIT_ACTIVITY_REQUEST){
            if (data != null  && resultCode == RESULT_OK){
                boolean itemUpdated = data.getBooleanExtra(MoveInandMoveOutContants.ITEM_UPDATED, false);
                if (itemUpdated) {
                    List<SpreadsheetItemProp> newItems = manager.getItems();

                    originalItems = newItems;
                    adapter.setItems(new ArrayList<>(originalItems));

                    adapter.notifyItemInserted(newItems.size() - 1);
                    searchText.setText(searchText.getText());
                    syncButtonShow();
                }
            }
        }else
            super.onActivityResult(requestCode, resultCode, data);
    }

    public void syncButtonHide(){
        setSyncDisable();
        ViewCompat.animate(syncLayout)
                .scaleX(1).scaleY(1)
                .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                .setInterpolator(new AccelerateInterpolator());
    }

    public void setSyncEnable(){
        syncText.setTextColor(ContextCompat.getColor(this, R.color.moveinandmoveout_sync_color));

        Bitmap syncBitmap = BitmapUtils.applyColorFilterForResource(this, R.drawable.moveinandmoveout_sync_white,
                ContextCompat.getColor(this, R.color.moveinandmoveout_sync_color), PorterDuff.Mode.MULTIPLY);
        syncImage.setImageBitmap(syncBitmap);
    }

    public void setSyncDisable(){
        syncText.setTextColor(ContextCompat.getColor(this, R.color.moveinandmoveout_black));
        syncImage.setImageResource(R.drawable.moveinandmoveout_sync);
    }
    public void syncButtonShow(){
        ViewCompat.setScaleX(syncLayout, 0);
        ViewCompat.setScaleY(syncLayout, 0);

        setSyncEnable();
        ViewCompat.animate(syncLayout)
                .scaleX(1).scaleY(1)
                .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                .setInterpolator(new DecelerateInterpolator());

    }
}
