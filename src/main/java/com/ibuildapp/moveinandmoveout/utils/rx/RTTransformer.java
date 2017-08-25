package com.ibuildapp.moveinandmoveout.utils.rx;


import android.util.Log;

import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApi;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiService;
import com.ibuildapp.moveinandmoveout.model.IbaResponse;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.restfb.util.StringUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

public class RTTransformer implements Observable.Transformer<Void, Void> {

    public interface OnUpdateAccessTokenListener {
        void accessTokenUpdated(String accessToken);
    }

    private int widgetId;
    private IBApi ibaApi;
    private OnUpdateAccessTokenListener listener;

    public RTTransformer(int widgetId, String baseDomain, OnUpdateAccessTokenListener listener) {
        this.widgetId = widgetId;
        ibaApi = new IBApiService(baseDomain).getIBApi();
        this.listener = listener;
    }

    @Override
    public Observable<Void> call(Observable<Void> itemsContainerObservable) {
        return itemsContainerObservable.retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        Log.e("ERROR", throwable.getMessage());

                        if (MoveInandMoveOutContants.UNAUTHORIZED.equals(throwable.getMessage()))
                            return ibaApi.refreshAccessToken(String.valueOf(widgetId))
                                    .flatMap(new Func1<IbaResponse, Observable<?>>() {
                                        @Override
                                        public Observable<?> call(IbaResponse ibaResponse) {
                                            if (ibaResponse != null && !StringUtils.isBlank(ibaResponse.getAccess_token())) {
                                                listener.accessTokenUpdated(ibaResponse.getAccess_token());
                                                return Observable.timer(1, TimeUnit.MILLISECONDS);
                                            }else if (ibaResponse!= null && !StringUtils.isBlank(ibaResponse.getError()))
                                                return Observable.error(new Exception(ibaResponse.getError()));
                                            else return Observable.error(new Exception("Undefined error"));
                                        }
                                    });
                        else return Observable.error(throwable);
                    }
                });
            }
        }).retry(1);
    }
}
