package com.ibuildapp.moveinandmoveout.api.googleapi;



import com.appbuilder.sdk.android.Statics;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApi;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiService;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.PropertyMapper;
import com.ibuildapp.moveinandmoveout.model.ResultObjectProp;
import com.ibuildapp.moveinandmoveout.utils.StaticData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SequenceLoader {
    private static final Integer MIDDLE_PROGRESS_VALUE = 40;
    private static final Integer MAX_PROGRESS_VALUE = 70;
    public static class Builder{
        private PropertyMapper mapper;

        private int startPosition;
        private int endPosition;

        private String spreadsheetId;
        private String spreadsheetTitle;

        public Builder(){
        }

        public PropertyMapper getMapper() {
            return mapper;
        }

        public Builder setMapper(PropertyMapper mapper) {
            this.mapper = mapper;
            return this;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public Builder setStartPosition(int startPosition) {
            this.startPosition = startPosition;
            return this;
        }

        public int getEndPosition() {
            return endPosition;
        }

        public Builder setEndPosition(int endPosition) {
            this.endPosition = endPosition;
            return this;
        }

        public String getSpreadsheetId() {
            return spreadsheetId;
        }

        public Builder setSpreadsheetId(String spreadsheetId) {
            this.spreadsheetId = spreadsheetId;
            return this;
        }

        public String getSpreadsheetTitle() {
            return spreadsheetTitle;
        }

        public Builder setSpreadsheetTitle(String spreadsheetTitle) {
            this.spreadsheetTitle = spreadsheetTitle;
            return this;
        }

        public SequenceLoader build(){
            return new SequenceLoader(this);
        }
    }

    private final PropertyMapper mapper;

    private final int startPosition;
    private final int endPosition;

    private final String spreadsheetId;
    private final String spreadsheetTitle;

    private final List<String> ranges;
    private EntityManager manager;

    private SequenceLoader(Builder builder) {

        this.mapper = builder.getMapper();
        this.startPosition = builder.getStartPosition();
        this.endPosition = builder.getEndPosition();

        this.spreadsheetId = builder.getSpreadsheetId();
        this.spreadsheetTitle = builder.getSpreadsheetTitle();

        ranges = createRanges(this.mapper, this.startPosition, this.endPosition);
        manager = EntityManager.getInstance();
    }

    private List<String> createRanges(PropertyMapper mapper, int startIndex, int endIndex) {
        List<String> tempRanges = new ArrayList<>();
        tempRanges.add(spreadsheetTitle + "!" + mapper.getPropertyname() + String.valueOf(startIndex) + ":" + mapper.getPropertyname() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getAddress() + String.valueOf(startIndex) + ":" + mapper.getAddress() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getFlatnumber() + String.valueOf(startIndex) + ":" + mapper.getFlatnumber() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getMonthlyrent() + String.valueOf(startIndex) + ":" + mapper.getMonthlyrent() + endIndex);
        return tempRanges;
    }

    public void load() throws IOException {
        IBApi api = new IBApiService(Statics.BASE_DOMEN).getIBApi(startPosition);
        Response<ResultObjectProp> result = api.getAllData(String.valueOf(StaticData.getWidgetId()), spreadsheetId, ranges, "COLUMNS").execute();
        manager.saveToDbProp(result.body().getResult());
    }

    public void load(ProgressListener listener) throws IOException {
        IBApi api = new IBApiService(Statics.BASE_DOMEN).getIBApi(startPosition);
        listener.onProgressUpdate(MIDDLE_PROGRESS_VALUE);
        Response<ResultObjectProp> result = api.getAllData(String.valueOf(StaticData.getWidgetId()), spreadsheetId, ranges, "COLUMNS").execute();
        manager.saveToDbProp(result.body().getResult());
        listener.onProgressUpdate(MAX_PROGRESS_VALUE);
    }
}
