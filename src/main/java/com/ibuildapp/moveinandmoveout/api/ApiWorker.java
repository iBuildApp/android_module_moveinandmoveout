package com.ibuildapp.moveinandmoveout.api;


import com.appbuilder.sdk.android.Statics;
import com.ibuildapp.moveinandmoveout.MoveInActivity;
import com.ibuildapp.moveinandmoveout.MoveOutActivity;
import com.ibuildapp.moveinandmoveout.api.googleapi.GoogleApi;
import com.ibuildapp.moveinandmoveout.api.googleapi.ProgressListener;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.utils.rx.RTTransformer;
import com.ibuildapp.moveinandmoveout.SyncActivity;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;

import java.util.List;

import rx.Observable;

public class ApiWorker {
    private static ApiWorker INSTANCE;

    public static ApiWorker getApiWorker(){
        if (INSTANCE == null)
            INSTANCE = new ApiWorker();

        return INSTANCE;
    }

    private final GoogleApi googleApi;
  //  private final GoogleApi googleApiSecond;

    private ApiWorker(){
        googleApi = new GoogleApi(StaticData.getXmlParsedData().getGoogle().getAccessToken(),
                StaticData.getXmlParsedData().getGoogle().getDocumentToken(),
                StaticData.getXmlParsedData().getGoogle().getSheetIdProp(),
                StaticData.getXmlParsedData().getGoogle().getFirstRowProp(),
                StaticData.getXmlParsedData().getJsonMapProp());
    }

    public Observable<Void> getSpreadsheetData(final RTTransformer.OnUpdateAccessTokenListener listener, ProgressListener progressListener){
        return googleApi.getDataFromIba(progressListener)
                .compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
                    @Override
                    public void accessTokenUpdated(String accessToken) {
                        googleApi.updateAccessToken(accessToken);
                        listener.accessTokenUpdated(accessToken);
                    }
                }));
    }

    public Observable<Void> updateWorksheet(List<SpreadsheetItemProp> items, final MoveOutActivity.AccessTokenListener listener){
        return googleApi.updateWorksheet(items).compose(new RTTransformer(StaticData.getWidgetId(), Statics.BASE_DOMEN, new RTTransformer.OnUpdateAccessTokenListener() {
            @Override
            public void accessTokenUpdated(String accessToken) {
                googleApi.updateAccessToken(accessToken);
                listener.accessTokenUpdated(accessToken);
            }
        }));
    }
}
