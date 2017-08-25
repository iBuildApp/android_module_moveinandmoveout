package com.ibuildapp.moveinandmoveout.api;


import com.appbuilder.sdk.android.Statics;
import com.ibuildapp.moveinandmoveout.MoveInActivity;
import com.ibuildapp.moveinandmoveout.MoveOutActivity;
import com.ibuildapp.moveinandmoveout.SyncActivity;
import com.ibuildapp.moveinandmoveout.api.googleapi.GoogleApi;
import com.ibuildapp.moveinandmoveout.api.googleapi.GoogleApiSecond;
import com.ibuildapp.moveinandmoveout.api.googleapi.ProgressListener;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.rx.RTTransformer;

import java.util.List;

import rx.Observable;

public class ApiWorkerSecond {
    private static ApiWorkerSecond INSTANCE;

    public static ApiWorkerSecond getApiWorker() {
        if (INSTANCE == null)
            INSTANCE = new ApiWorkerSecond();

        return INSTANCE;
    }

    private final GoogleApiSecond googleApi;

    private ApiWorkerSecond() {
        googleApi = new GoogleApiSecond(StaticData.getXmlParsedData().getGoogle().getAccessToken(),
                StaticData.getXmlParsedData().getGoogle().getDocumentToken(),
                StaticData.getXmlParsedData().getGoogle().getSheetIdMove(),
                StaticData.getXmlParsedData().getGoogle().getFirstRowMove(),
                StaticData.getXmlParsedData().getJsonMapMove());
    }

    public Observable<Void> getSpreadsheetData(final RTTransformer.OnUpdateAccessTokenListener listener, ProgressListener progressListener) {
        return googleApi.getDataFromIba(progressListener)
                .compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
                    @Override
                    public void accessTokenUpdated(String accessToken) {
                        googleApi.updateAccessToken(accessToken);
                        listener.accessTokenUpdated(accessToken);
                    }
                }));
    }

    public Observable<Void> updateWorksheetSync(List<SpreadsheetItemMove> items, final SyncActivity.AccessTokenListener listener) {
        return googleApi.updateWorksheet(items).compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
            @Override
            public void accessTokenUpdated(String accessToken) {
                googleApi.updateAccessToken(accessToken);
                listener.accessTokenUpdated(accessToken);
            }
        }));
    }

    public Observable<Void> updateWorksheet(List<SpreadsheetItemMove> items, final MoveOutActivity.AccessTokenListener listener) {
        return googleApi.updateWorksheet(items).compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
            @Override
            public void accessTokenUpdated(String accessToken) {
                googleApi.updateAccessToken(accessToken);
                listener.accessTokenUpdated(accessToken);
            }
        }));
    }

    public Observable<Void> appendWorksheet(final RTTransformer.OnUpdateAccessTokenListener listener) {
        return googleApi.appendWorksheet()
                .compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
                    @Override
                    public void accessTokenUpdated(String accessToken) {
                        googleApi.updateAccessToken(accessToken);
                        listener.accessTokenUpdated(accessToken);
                    }
                }));
    }
}
