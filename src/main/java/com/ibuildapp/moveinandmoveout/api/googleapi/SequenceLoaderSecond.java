package com.ibuildapp.moveinandmoveout.api.googleapi;

import com.appbuilder.sdk.android.Statics;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiSecond;
import com.ibuildapp.moveinandmoveout.api.ibaapi.IBApiServiceSecond;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.MoveMapper;
import com.ibuildapp.moveinandmoveout.model.ResultObjectMove;
import com.ibuildapp.moveinandmoveout.utils.StaticData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by web-developer on 05.04.2017.
 */

public class SequenceLoaderSecond {
    private static final Integer MIDDLE_PROGRESS_VALUE = 40;
    private static final Integer MAX_PROGRESS_VALUE = 70;
    public static class Builder{
        private MoveMapper mapper;

        private int startPosition;
        private int endPosition;

        private String spreadsheetId;
        private String spreadsheetTitle;

        public Builder(){
        }

        public MoveMapper getMapper() {
            return mapper;
        }

        public SequenceLoaderSecond.Builder setMapper(MoveMapper mapper) {
            this.mapper = mapper;
            return this;
        }

        public int getStartPosition() {
            return startPosition;
        }

        public SequenceLoaderSecond.Builder setStartPosition(int startPosition) {
            this.startPosition = startPosition;
            return this;
        }

        public int getEndPosition() {
            return endPosition;
        }

        public SequenceLoaderSecond.Builder setEndPosition(int endPosition) {
            this.endPosition = endPosition;
            return this;
        }

        public String getSpreadsheetId() {
            return spreadsheetId;
        }

        public SequenceLoaderSecond.Builder setSpreadsheetId(String spreadsheetId) {
            this.spreadsheetId = spreadsheetId;
            return this;
        }

        public String getSpreadsheetTitle() {
            return spreadsheetTitle;
        }

        public SequenceLoaderSecond.Builder setSpreadsheetTitle(String spreadsheetTitle) {
            this.spreadsheetTitle = spreadsheetTitle;
            return this;
        }

        public SequenceLoaderSecond build(){
            return new SequenceLoaderSecond(this);
        }
    }

    private final MoveMapper mapper;

    private final int startPosition;
    private final int endPosition;

    private final String spreadsheetId;
    private final String spreadsheetTitle;

    private final List<String> ranges;
    private EntityManager manager;

    private SequenceLoaderSecond(SequenceLoaderSecond.Builder builder) {

        this.mapper = builder.getMapper();
        this.startPosition = builder.getStartPosition();
        this.endPosition = builder.getEndPosition();

        this.spreadsheetId = builder.getSpreadsheetId();
        this.spreadsheetTitle = builder.getSpreadsheetTitle();

        ranges = createRanges(this.mapper, this.startPosition, this.endPosition);
        manager = EntityManager.getInstance();
    }

    private List<String> createRanges(MoveMapper mapper, int startIndex, int endIndex) {
        List<String> tempRanges = new ArrayList<>();
        tempRanges.add(spreadsheetTitle + "!" + mapper.getPropertyname() + String.valueOf(startIndex) + ":" + mapper.getPropertyname() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getFlatnumber() + String.valueOf(startIndex) + ":" + mapper.getFlatnumber() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getTenantName() + String.valueOf(startIndex) + ":" + mapper.getTenantName() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDateIn() + String.valueOf(startIndex) + ":" + mapper.getDateIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrDoorsLlocksIn() + String.valueOf(startIndex) + ":" + mapper.getLrDoorsLlocksIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrWindowsScreensIn() + String.valueOf(startIndex) + ":" + mapper.getLrWindowsScreensIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrCarpetFlooringIn() + String.valueOf(startIndex) + ":" + mapper.getLrCarpetFlooringIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDrWindowScreensIn() + String.valueOf(startIndex) + ":" + mapper.getDrWindowScreensIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDrCarpetFlooringIn() + String.valueOf(startIndex) + ":" + mapper.getDrCarpetFlooringIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethCarpetFlooringIn() + String.valueOf(startIndex) + ":" + mapper.gethCarpetFlooringIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethWwallsIn() + String.valueOf(startIndex) + ":" + mapper.gethWwallsIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethLightsSwitchesIn() + String.valueOf(startIndex) + ":" + mapper.gethLightsSwitchesIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkWindowsScreensIn() + String.valueOf(startIndex) + ":" + mapper.getkWindowsScreensIn()  + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkFlooringIn() + String.valueOf(startIndex) + ":" + mapper.getkFlooringIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkRefrigeratorIn() + String.valueOf(startIndex) + ":" + mapper.getkRefrigeratorIn()  + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkSinkIn() + String.valueOf(startIndex) + ":" + mapper.getkSinkIn() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDateOut() + String.valueOf(startIndex) + ":" + mapper.getDateOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrDoorsLocksOut() + String.valueOf(startIndex) + ":" + mapper.getLrDoorsLocksOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrWindowsScreensOut() + String.valueOf(startIndex) + ":" + mapper.getLrWindowsScreensOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getLrCFlooringOut() + String.valueOf(startIndex) + ":" + mapper.getLrCFlooringOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDrWindowScreensOut() + String.valueOf(startIndex) + ":" + mapper.getDrWindowScreensOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getDrCarpetFlooringOut() + String.valueOf(startIndex) + ":" + mapper.getDrCarpetFlooringOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethCarpetFlooringOut()+ String.valueOf(startIndex) + ":" + mapper.gethCarpetFlooringOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethWallsOut()+ String.valueOf(startIndex) + ":" + mapper.gethWallsOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.gethLightsSwitchesOut()+ String.valueOf(startIndex) + ":" + mapper.gethLightsSwitchesOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkWindowsScreensOut()+ String.valueOf(startIndex) + ":" + mapper.getkWindowsScreensOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkFlooringOut()+ String.valueOf(startIndex) + ":" + mapper.getkFlooringOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkRefrigeratorOut()+ String.valueOf(startIndex) + ":" + mapper.getkRefrigeratorOut() + endIndex);
        tempRanges.add(spreadsheetTitle + "!" + mapper.getkSinkOut()+ String.valueOf(startIndex) + ":" + mapper.getkSinkOut() + endIndex);

        return tempRanges;
    }

    public void load() throws IOException {
        IBApiSecond api = new IBApiServiceSecond(Statics.BASE_DOMEN).getIBApi(startPosition);
        Response<ResultObjectMove> result = api.getAllData(String.valueOf(StaticData.getWidgetId()), spreadsheetId, ranges, "COLUMNS").execute();
        manager.saveToDbMove(result.body().getResult());
    }

    public void load(ProgressListener listener) throws IOException {
        IBApiSecond api = new IBApiServiceSecond(Statics.BASE_DOMEN).getIBApi(startPosition);
        listener.onProgressUpdate(MIDDLE_PROGRESS_VALUE);
        Response<ResultObjectMove> result = api.getAllData(String.valueOf(StaticData.getWidgetId()), spreadsheetId, ranges, "COLUMNS").execute();
        manager.saveToDbMove(result.body().getResult());
        listener.onProgressUpdate(MAX_PROGRESS_VALUE);
    }
}
