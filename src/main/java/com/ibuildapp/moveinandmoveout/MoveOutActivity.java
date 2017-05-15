package com.ibuildapp.moveinandmoveout;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.appbuilder.sdk.android.Widget;
import com.appbuilder.sdk.android.animations.SimpleAnimatorListener;
import com.ibuildapp.moveinandmoveout.adapters.MoveOutAdapterFragment;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.fragments.MoveOutFirstFragment;
import com.ibuildapp.moveinandmoveout.fragments.MoveOutSecondFragment;
import com.ibuildapp.moveinandmoveout.fragments.details.OnItemChangedListener;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.rx.RTTransformer;
import com.ibuildapp.moveinandmoveout.view.DetailsViewPager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

public class MoveOutActivity extends AppBuilderModuleMainAppCompat {
    String title;
    SpreadsheetItemMove data;
    SpreadsheetItemMove secondData;
    SpreadsheetItemMove allData;
    public View rootView;
    public String propName;
    public String flatNumber;
    public String tenantName;
    private DetailsViewPager viewPager;
    private View nextLayout;
    private View previousLayout;
    private LinearLayout shareButton;
    private MoveOutAdapterFragment adapter;
    private boolean itemUpdated = false;

    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_moveout_activity);
        initContent();
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarTitle(getString(R.string.moveinandmoveout_moveOut));
        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.moveinandmoveout_back),
                ContextCompat.getColor(this, android.R.color.black), true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        setTopBarTitleColor(ContextCompat.getColor(this, android.R.color.black));
        shareButton = (LinearLayout) getLayoutInflater().inflate(R.layout.moveinandmoveout_save_button, null);
        shareButton.setVisibility(View.VISIBLE);
        setTopBarRightButton(shareButton, getString(R.string.moveinandmoveout_edit_save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveOutFirstFragment firstFragment = adapter.getFirstFragment();
                MoveOutSecondFragment secondFragment = adapter.getSecondFragment();
                data =  firstFragment.getCloneData();
                secondData = secondFragment.getCloneData();
                allData = SpreadsheetItemMove.newInstanceAllUpdate(data,secondData);
                if (!allData.equals(secondData)) {
                    EntityManager.getInstance().saveUpdateMove(allData, secondData);
                    itemUpdated = true;
                    data = allData;
                    AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                        @Override
                        public void call() {
                            tryFinish();
                        }
                    });
                }
                Intent intent = new Intent(MoveOutActivity.this, SyncActivity.class);
                startActivityForResult(intent, MoveInandMoveOutContants.SYNC_ACTIVITY_REQUEST);
                overridePendingTransition(R.anim.moveinandmoveout_enter_from_bottom, 0);
            }
        });

        rootView = findViewById(R.id.moveinandmoveout_moveout_activity_main);
        rootView.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        viewPager = (DetailsViewPager) findViewById(R.id.moveinandmoveout_moveout_view_pager);
        viewPager.setPageTransformer(true, new MoveOutActivity.DepthPageTransformer());
        viewPager.setScrollDurationFactor(2f);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    hidePreviousButton();

                if (position > 0)
                    showPreviousButton();

                if (position == 1)
                    hideNextButton();

                if (position < 1)
                    showNextButton();
            }
        });
        nextLayout = findViewById(R.id.moveinandmoveout_moveout_next);
        previousLayout = findViewById(R.id.moveinandmoveout_moveout_previous);
        nextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentPosition + 1, true);
                nextLayout.setVisibility(View.INVISIBLE);
                previousLayout.setVisibility(View.VISIBLE);
            }
        });

        previousLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentPosition - 1, true);
                nextLayout.setVisibility(View.VISIBLE);
                previousLayout.setVisibility(View.INVISIBLE);
            }
        });
        previousLayout.setVisibility(View.INVISIBLE);
        adapter = new MoveOutAdapterFragment(getSupportFragmentManager(), data, new ItemChangedListener());

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    ;

    public void initContent() {
        Intent intent = getIntent();
        Widget widget = (Widget) intent.getSerializableExtra(MoveInandMoveOutContants.EXTRA_WIDGET);
        propName = intent.getStringExtra(MoveInandMoveOutContants.PROPERTYNAME);
        flatNumber = intent.getStringExtra(MoveInandMoveOutContants.FLATNUMBER);
        tenantName = intent.getStringExtra(MoveInandMoveOutContants.TENANTNAME);
        title = widget.getTitle();
        data = EntityManager.getInstance().getItemsMovebyFlat(propName, flatNumber);
        data.setPropertyname(propName);
        data.setFlatnumber(flatNumber);
        data.setTenantname(tenantName);

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
        if (nextLayout.getVisibility() == View.GONE) {
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                nextLayout.setY(nextLayout.getHeight());

                nextLayout.animate().translationY(0)
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(new SimpleAnimatorListener() {
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
                        .setListener(new SimpleAnimatorListener() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                nextLayout.setVisibility(View.GONE);
                            }
                        });
            } else nextLayout.setVisibility(View.GONE);
    }

    private void showPreviousButton() {
        if (previousLayout.getVisibility() == View.GONE) {
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
                        .setListener(new SimpleAnimatorListener() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                previousLayout.setVisibility(View.GONE);
                            }
                        });
            } else previousLayout.setVisibility(View.GONE);
    }

    public void tryFinish() {

        if (!secondData.equals(data)) {
        //    showDialog();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(MoveInandMoveOutContants.ITEM_UPDATED, itemUpdated);
        intent.putExtra(MoveInandMoveOutContants.POSITION, 1);
        intent.putExtra(MoveInandMoveOutContants.ITEMMOVE, allData);
        setResult(RESULT_OK, intent);
    }


    public class AccessTokenListener implements RTTransformer.OnUpdateAccessTokenListener {

        @Override
        public void accessTokenUpdated(final String accessToken) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    Toast.makeText(MoveOutActivity.this, "Access Token updated", Toast.LENGTH_LONG).show();
                    SharedPreferences configuration = getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, MODE_PRIVATE);
                    configuration.edit()
                            .putString(StaticData.getXmlParsedData().getGoogle().getDocumentToken(), accessToken)
                            .apply();
                }
            });
        }
    }

    public class ProgressListener implements com.ibuildapp.moveinandmoveout.api.googleapi.ProgressListener {

        @Override
        public void onProgressUpdate(final int progressValue) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    //     fragment.updateTo(progressValue);
                }
            });
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN) hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }
    public class ItemChangedListener implements OnItemChangedListener {

        @Override
        public void itemChanged() {
            //   showButton(saveButton);
            //    viewPager.setSwipeEnabled(false);
            //    itemChanged = true;
        }

        @Override
        public void changesCleared() {
            //     hideButton(saveButton);
            viewPager.setSwipeEnabled(true);
            //  itemChanged = false;
        }

        @Override
        public void justLockSwipe() {
                viewPager.setSwipeEnabled(false);
            //  interfaceLock = true;
        }

        @Override
        public void justUnlockSwipe() {
                viewPager.setSwipeEnabled(true);
            //   interfaceLock = false;
        }

        @Override
        public void callPhone(String phone) {

        }

        @Override
        public void showMap(String addressString) {

        }
    }
}