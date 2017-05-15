package com.ibuildapp.moveinandmoveout.api.googleapi;


import android.util.Log;

import com.appbuilder.sdk.android.Statics;
import com.google.gson.Gson;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApi;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiSecond;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiService;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiServiceSecond;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.ContainerMove;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.MoveMapper;
import com.ibuildapp.moveinandmoveout.model.PropertyMapper;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetData;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetProperties;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetResponse;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateContainer;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateResponse;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.restfb.util.StringUtils;
import com.ibuildapp.moveinandmoveout.model.appends.AppendContainer;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func0;

public class GoogleApiSecond {
    private static final Integer MAX_PART_ELEMENTS = 5000;
    private final String spreadsheetId;
    private String worksheetId;
    private int startIndex;

    private MoveMapper mapper;

    public GoogleApiSecond(String accessToken, final String spreadsheetId, String worksheetId, int startIndex, final MoveMapper mapper){
        updateAccessToken(accessToken);
        this.spreadsheetId = spreadsheetId;
        this.worksheetId = worksheetId;
        this.startIndex = startIndex;
        this.mapper = mapper;
    }

    public final void updateAccessToken(String accessToken){
    }


    private SheetData getGridProperties() throws Exception {
        IBApi api = new IBApiService(Statics.BASE_DOMEN).getIBApi();
        Response<SheetResponse> sheetResponse = api.getSheetProperties(String.valueOf(StaticData.getWidgetId()), spreadsheetId)
                .execute();

        if (!StringUtils.isBlank(sheetResponse.body().getError()))
            throw new Exception(sheetResponse.body().getError());

        SheetProperties properties = sheetResponse.body().getSheetProperties();

        if (properties.getProperties().containsKey(worksheetId))
            return properties.getProperties().get(worksheetId);
        else  throw new Exception("Not load spreadsheet properties");
    }

    public Observable<Void> getDataFromIba(final ProgressListener listener){
        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    String treadName = Thread.currentThread().getName() + " " + Thread.currentThread().getId();
                    Log.e("prepare", treadName);

                    ContainerMove container = EntityManager.getInstance().getContainerMove(StaticData.getXmlParsedData().getGoogle().getDocumentToken());
                    if (container == null ){
                        container = createContainer();
                        return containerLoading(container, 0, listener);
                    }else if ( container.getLoaded() == 0) {
                        int loadedCount = EntityManager.getInstance().getItemsCountMove();
                        return  containerLoading(container, loadedCount, listener);
                    }else {
                        EntityManager.getInstance().clearItemsMove();
                        container = createContainer();
                        return containerLoading(container, 0, listener);
                    }
                } catch (Exception e) {
                    Log.e("prepare", "error " + e.getMessage());
                    return Observable.error(e);
                }
            }
        });
    }
    private ContainerMove createContainer() throws Exception {
        SheetData properties = getGridProperties();
        if (properties == null)
            throw new Exception("Not load spreadsheet properties");

        ContainerMove container = new ContainerMove();
        container.setDocumentToken(StaticData.getXmlParsedData().getGoogle().getDocumentToken());
        container.setRowsCount(properties.getRowsCount());
        container.setSheetTitle(properties.getTitle());
    //    container.setDateFormat(0);
        container.setLoaded(0);

        EntityManager.getInstance().saveToDbMove(container);

        return container;
    }

    private Observable<Void> containerLoading(ContainerMove container, int alreadyLoaded, final ProgressListener listener) throws Exception {
        int threadsCount = (container.getRowsCount() - alreadyLoaded) / (MAX_PART_ELEMENTS + 1) + 1;

        if (alreadyLoaded > 0){
            float value = (float) alreadyLoaded / container.getRowsCount();
            int progressValue = (int) (value * 100);
            listener.onProgressUpdate(progressValue);
        }

        for (int thread = 0; thread < threadsCount; thread++) {
            SequenceLoaderSecond.Builder builder = new SequenceLoaderSecond.Builder();
            builder.setMapper(mapper)
                    .setSpreadsheetId(spreadsheetId)
                    .setSpreadsheetTitle(container.getSheetTitle());

            if (thread == 0)
                builder.setStartPosition(startIndex + alreadyLoaded);
            else builder.setStartPosition(alreadyLoaded + thread * MAX_PART_ELEMENTS);

            if (thread == threadsCount - 1)
                builder.setEndPosition(container.getRowsCount() + 1);
            else builder.setEndPosition(alreadyLoaded + (thread + 1) * MAX_PART_ELEMENTS);

            if (threadsCount == 1)
                builder.build().load(listener);
            else builder.build().load();

            if (threadsCount != 1) {
                float value = (alreadyLoaded + (float)((thread + 1) * MAX_PART_ELEMENTS)) / container.getRowsCount();
                int progressValue = (int) (value * 100);
                listener.onProgressUpdate(progressValue);
            }
        }

        return  Observable.just(null);
    }

    public Observable<Void> updateWorksheet(final List<SpreadsheetItemMove> items) {

        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    String treadName = Thread.currentThread().getName() + " " + Thread.currentThread().getId();
                    Log.e("prepare", treadName);
                    ItemsConverterSecond converter = new ItemsConverterSecond(Integer.valueOf(worksheetId), mapper);
                    UpdateContainer container = converter.itemToUpdateRequest(items);
                    String request =  new Gson().toJson(container.getRequests());

                    IBApiSecond api = new IBApiServiceSecond(Statics.BASE_DOMEN).getIBApi();
                    Response<UpdateResponse> response = api.updateSpreadsheet(String.valueOf(StaticData.getWidgetId()), spreadsheetId, request).execute();

                    if(!StringUtils.isBlank(response.body().getError()))
                        return Observable.error(new Exception(response.body().getError()));

                    return  Observable.just(null);
                } catch (Exception e) {
                    Log.e("prepare", "error " + e.getMessage());
                    return Observable.error(e);
                }
            }
        });
    }
    public Observable<Void> appendWorksheet() {

        return Observable.defer(new Func0<Observable<Void>>() {
            @Override
            public Observable<Void> call() {
                try {
                    String treadName = Thread.currentThread().getName() + " " + Thread.currentThread().getId();
                    Log.e("prepare", treadName);
                    ItemsConverterSecond converter = new ItemsConverterSecond(Integer.valueOf(worksheetId), mapper);
                    List<SpreadsheetItemMove> newItems = EntityManager.getInstance().getNewsMove();
                    if (newItems.size() == 0)
                        return Observable.just(null);

                    AppendContainer container = converter.itemToAppendRequest(newItems);
                    String request =  new Gson().toJson(container.getRequests());

                    IBApiSecond api = new IBApiServiceSecond(Statics.BASE_DOMEN).getIBApi();
                    Response<UpdateResponse> response = api.updateSpreadsheet(String.valueOf(StaticData.getWidgetId()), spreadsheetId, request).execute();

                    if(!StringUtils.isBlank(response.body().getError()))
                        return Observable.error(new Exception(response.body().getError()));

                    EntityManager.getInstance().updateNewItems();
                    return  Observable.just(null);
                } catch (Exception e) {
                    Log.e("prepare", "error " + e.getMessage());
                    return Observable.error(e);
                }
            }
        });
    }
}
