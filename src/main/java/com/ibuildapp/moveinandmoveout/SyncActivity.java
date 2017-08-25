package com.ibuildapp.moveinandmoveout;



import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.ibuildapp.moveinandmoveout.api.ApiWorker;
import com.ibuildapp.moveinandmoveout.api.ApiWorkerSecond;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.fragments.SyncProgressFragment;
import com.ibuildapp.moveinandmoveout.model.ContainerMove;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.OnCompletedListener;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.rx.RTTransformer;
import com.ibuildapp.moveinandmoveout.utils.rx.RxUtils;
import com.ibuildapp.moveinandmoveout.utils.rx.SimpleSubscriber;
import com.ibuildapp.moveinandmoveout.view.ShimmerFrameLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class SyncActivity extends AppBuilderModuleMainAppCompat {

    private CompositeSubscription subscription;
    private CompositeSubscription subscriptionSecond;

    private ApiWorker apiWorker;
    private ApiWorkerSecond apiWorkerSecond;

    private View pressedLayout;
    private SyncProgressFragment fragment;

    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_sync);
        hideTopBar();

        apiWorker = ApiWorker.getApiWorker();
        apiWorkerSecond = ApiWorkerSecond.getApiWorker();
        FrameLayout cancelButton = (FrameLayout) findViewById(R.id.moveinandmoveout_sync_cancel_button);
        pressedLayout = findViewById(R.id.moveinandmoveout_sync_button_pressed);

        ShimmerFrameLayout shimmer = (ShimmerFrameLayout) findViewById(R.id.moveinandmoveout_sync_shimmer);
        shimmer.startShimmerAnimation();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressedLayout.setVisibility(View.VISIBLE);
                onBackPressed();
                AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                    @Override
                    public void call() {
                        pressedLayout.setVisibility(View.GONE);
                    }
                }, 200, TimeUnit.MILLISECONDS);
            }
        });

        fragment = (SyncProgressFragment) getSupportFragmentManager().findFragmentById(R.id.moveinandmoveout_sync_fragment);
        subscription = new CompositeSubscription();
        subscriptionSecond = new CompositeSubscription();

        Schedulers.computation().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                initData();
            }
        });
    }

    private void initData() {
        Observable<Void> updateObservable = null;
        Observable<Void> updateObservableSecond = null;
        Observable<Void> loadObservable;
        Observable<Void> loadObservableSecond;
        Observable<Void> appendObservable;
        if (EntityManager.getInstance().getUpdatesCountMove() > 0){
         /*   List<SpreadsheetItemProp> updates = EntityManager.getInstance().getUpdatesProp();
            updateObservable = apiWorker.updateWorksheet(updates, new AccessTokenListener())
                    .compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.computation(), Schedulers.computation()));*/
            List<SpreadsheetItemMove> updatesSecond = EntityManager.getInstance().getUpdatesMove();
            updateObservableSecond = apiWorkerSecond.updateWorksheetSync(updatesSecond, new AccessTokenListener())
                    .compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.computation(), Schedulers.computation()));
        }

        appendObservable = apiWorkerSecond.appendWorksheet(new AccessTokenListener())
                .compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.computation(), Schedulers.computation()));

        loadObservable = apiWorker.getSpreadsheetData(new AccessTokenListener(), new ProgressListener())
                .compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.io(), AndroidSchedulers.mainThread()));
        loadObservableSecond = apiWorkerSecond.getSpreadsheetData(new AccessTokenListener(), new ProgressListener())
                .compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.io(), AndroidSchedulers.mainThread()));

        Observable<Void> resultObservable;
        Observable<Void> resultObservableSecond;

        if (updateObservableSecond != null){
            resultObservable = Observable.concat(loadObservable, updateObservable);
            resultObservableSecond = Observable.concat(appendObservable,  updateObservableSecond, loadObservableSecond, updateDateChecked(),updateDateCheckedSecond() );
        }else { resultObservable = Observable.concat(loadObservable, updateDateChecked());
                resultObservableSecond =  Observable.concat(appendObservable,  loadObservableSecond,  updateDateCheckedSecond()); }
        subscription.add(resultObservable
                .subscribe(new SimpleSubscriber<Void>(){

                    @Override
                    public void onError(Throwable e) {
                        finishWithError(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                fragment.updateToLast(new OnCompletedListener() {
                                    @Override
                                    public void onCompleted() {
                                        finishWithResult();
                                    }
                                });
                            }
                        });
                    }
                }));

        subscriptionSecond.add(resultObservableSecond
                .subscribe(new SimpleSubscriber<Void>(){

                    @Override
                    public void onError(Throwable e) {
                        finishWithErrorSecond(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                            @Override
                            public void call() {
                                fragment.updateToLast(new OnCompletedListener() {
                                    @Override
                                    public void onCompleted() {
                                        finishWithResultSecond();
                                    }
                                });
                            }
                        });
                    }
                }));
    }


    private Observable<Void> updateDateChecked(){
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                ContainerProp container = EntityManager.getInstance().getContainer(StaticData.getXmlParsedData().getGoogle().getDocumentToken());
                EntityManager.getInstance().updateDateCheckedProp(StaticData.getXmlParsedData().getGoogle().getDocumentToken(),
                        container.getSheetTitle(), container.getRowsCount());
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        }).compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.computation(), Schedulers.computation()));
    }

    private Observable<Void> updateDateCheckedSecond(){
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                ContainerMove container = EntityManager.getInstance().getContainerMove(StaticData.getXmlParsedData().getGoogle().getDocumentToken());
                EntityManager.getInstance().updateDateCheckedMove(StaticData.getXmlParsedData().getGoogle().getDocumentToken(),
                        container.getSheetTitle(), container.getRowsCount());
                subscriber.onNext(null);
                subscriber.onCompleted();
            }
        }).compose(RxUtils.<Void>applyCustomSchedulers(Schedulers.computation(), Schedulers.computation()));
    }

    private void finishWithResult(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
      //  finish();
    }
    private void finishWithResultSecond(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
        finishSecond();
    }

    private void finishWithError(String error){
        clearData();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MoveInandMoveOutContants.SYNC_ERROR, error);
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }

    private void finishWithErrorSecond(String error){
        clearData();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(MoveInandMoveOutContants.SYNC_ERROR, error);
        setResult(RESULT_CANCELED, resultIntent);
        finishSecond();
    }
    @Override
    public void stop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription.clear();
            subscription = null;
        }
        super.stop();
    }

    @Override
    public void resume() {
        if (subscription == null || subscription.isUnsubscribed()) {
            subscription = new CompositeSubscription();
            initData();
        }

        super.resume();
    }

    private void finishWithCancel(){
        clearData();
        Intent resultIntent = new Intent();
        setResult(RESULT_CANCELED, resultIntent);
        finish();
    }

    @Override
    public void finish() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription.clear();
        }

        super.finish();
        overridePendingTransition(0, R.anim.moveinandmoveout_exit_to_bottom);
    }
    public void finishSecond() {
        if (!subscriptionSecond.isUnsubscribed()) {
            subscriptionSecond.unsubscribe();
            subscriptionSecond.clear();
        }

        super.finish();
        overridePendingTransition(0, R.anim.moveinandmoveout_exit_to_bottom);
    }

    public void clearData(){
       /* EntityManager.getInstance().clearItems();
        SharedPreferences configuration = getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, MODE_PRIVATE);
        configuration.edit()
                .putString(MoveInandMoveOutContants.DOCUMENT_TOKEN, "")
                .apply();*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishWithCancel();
    }
    public class ProgressListener implements com.ibuildapp.moveinandmoveout.api.googleapi.ProgressListener{

        @Override
        public void onProgressUpdate(final int progressValue) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    fragment.updateTo(progressValue);
                }
            });
        }
    }

    public class AccessTokenListener implements RTTransformer.OnUpdateAccessTokenListener{

        @Override
        public void accessTokenUpdated(final String accessToken) {
            AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                @Override
                public void call() {
                    Toast.makeText(SyncActivity.this, "Access Token updated", Toast.LENGTH_LONG).show();
                    SharedPreferences configuration = getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, MODE_PRIVATE);
                    configuration.edit()
                            .putString(StaticData.getXmlParsedData().getGoogle().getDocumentToken(), accessToken)
                            .apply();
                }
            });
        }
    }
}
