package com.ibuildapp.moveinandmoveout.api.googleapi;


import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.MoveMapper;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.appends.AppendCells;
import com.ibuildapp.moveinandmoveout.model.appends.AppendContainer;
import com.ibuildapp.moveinandmoveout.model.appends.AppendRequest;
import com.ibuildapp.moveinandmoveout.model.updates.Rows;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateCells;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateContainer;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateRequest;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ItemsConverterSecond {

    private Integer worksheetId;
    private MoveMapper mapper;
    private String monthStr;
    private String dayStr;
    public ItemsConverterSecond(Integer worksheetId, MoveMapper mapper){
        this.worksheetId = worksheetId;
        this.mapper = mapper;
    }

    public UpdateContainer itemToUpdateRequest(List<SpreadsheetItemMove> items){
        UpdateContainer request = new UpdateContainer();
        List<UpdateRequest> requests = new ArrayList<>();
        for (SpreadsheetItemMove item : items){
         /*   requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getPropertyname(), mapper.getPropertyname()));
        //    requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getFlatnumber(), mapper.getFlatnumber()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getTenantname(), mapper.getTenantName()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getDateIn(), mapper.getDateIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrDoorsLlocksIn(), mapper.getLrDoorsLlocksIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrWindowsScreensIn(), mapper.getLrWindowsScreensIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrCarpetFlooringIn(), mapper.getLrCarpetFlooringIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getDrWindowScreensIn(), mapper.getDrWindowScreensIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getDrCarpetFlooringIn(), mapper.getDrCarpetFlooringIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethCarpetFlooringIn(), mapper.gethCarpetFlooringIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethWwallsIn(), mapper.gethWwallsIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethLightsSwitchesIn(), mapper.gethLightsSwitchesIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkWindowsScreensIn(), mapper.getkWindowsScreensIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkFlooringIn(), mapper.getkFlooringIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkRefrigeratorIn(), mapper.getkRefrigeratorIn()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkSinkIn(), mapper.getkSinkIn()));*/
            try {
                requests.add(dateStrValueToUpdateRequest(item.getRowId()-1, item.getDateOut(), mapper.getDateOut()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrDoorsLocksOut(), mapper.getLrDoorsLocksOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrWindowsScreensOut(), mapper.getLrWindowsScreensOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getLrCFlooringOut(), mapper.getLrCFlooringOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getDrWindowScreensOut(), mapper.getDrWindowScreensOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getDrCarpetFlooringOut(), mapper.getDrCarpetFlooringOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethCarpetFlooringOut(), mapper.gethCarpetFlooringOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethWallsOut(), mapper.gethWallsOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.gethLightsSwitchesOut(), mapper.gethLightsSwitchesOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkWindowsScreensOut(), mapper.getkWindowsScreensOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkFlooringOut(), mapper.getkFlooringOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkRefrigeratorOut(), mapper.getkRefrigeratorOut()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getkSinkOut(), mapper.getkSinkOut()));
        }
        request.setRequests(requests);
        return request;
    }

    private UpdateRequest dateStrValueToUpdateRequest(Integer rowId, String value, String columnName) throws ParseException {
        int columnValue = GoogleUtils.toNumber(columnName) - 1;
        Rows.UserEnteredValue userEnteredValue;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = sdf.parse(value);
        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTime(date);
        int month = dateInfo.get(Calendar.MONTH);
        month = month+1;
        int year = dateInfo.get(Calendar.YEAR);
        int day =dateInfo.get(Calendar.DAY_OF_MONTH);
        String formula = "=DATE("+year+";"+month+";"+day+")";
        userEnteredValue =  new Rows.FormulaValue(formula);

        UpdateRequest request = new UpdateRequest();

        UpdateCells updateCells = new UpdateCells();
        UpdateCells.Start start = new UpdateCells.Start();
        start.setSheetId(String.valueOf(worksheetId));
        start.setRowIndex(rowId);
        start.setColumnIndex(columnValue);
        updateCells.setStart(start);

        Rows rows = new Rows();
        List<Rows.EnteredValue> rowValues = new ArrayList<>();
        rowValues.add(new Rows.EnteredValue(userEnteredValue));
        rows.setValues(rowValues);
        updateCells.setRows(rows);
        request.setUpdateCells(updateCells);
        return request;
    }

    private UpdateRequest stringValueToUpdateRequest(Integer rowId, String value, String columnName){
        UpdateRequest request = new UpdateRequest();

        UpdateCells updateCells = new UpdateCells();
        UpdateCells.Start start = new UpdateCells.Start();
        start.setSheetId(String.valueOf(worksheetId));
        start.setRowIndex(rowId);
        int columnValue = GoogleUtils.toNumber(columnName) - 1;
        start.setColumnIndex(columnValue);
        updateCells.setStart(start);

        Rows rows = new Rows();
        List<Rows.EnteredValue> rowValues = new ArrayList<>();
        rowValues.add(new Rows.EnteredValue(new Rows.StringValue(value)));
        rows.setValues(rowValues);
        updateCells.setRows(rows);

        request.setUpdateCells(updateCells);
        return request;
    }
    private Rows.EnteredValue stringValueAppendRequest( String value, String columnName){
        Rows.EnteredValue row = new Rows.EnteredValue(new Rows.StringValue(value));
        return row;
    }
    private Rows.EnteredValue dateValueToAppendRequest( Date value) {
        Rows.UserEnteredValue userEnteredValue;
        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTime(value);
        int month = dateInfo.get(Calendar.MONTH);
        month = month+1;
        int year = dateInfo.get(Calendar.YEAR);
        int day =dateInfo.get(Calendar.DAY_OF_MONTH);
        String formula = "=DATE("+year+";"+month+";"+day+")";
        userEnteredValue =  new Rows.FormulaValue(formula);
        return new Rows.EnteredValue(userEnteredValue);
    }
    private Rows.EnteredValue floatValueAppendRequest(BigDecimal value, String columnName){
        Rows.EnteredValue row = new Rows.EnteredValue(new Rows.NumberValue(value));
        return row;
    }

    public AppendContainer itemToAppendRequest(List<SpreadsheetItemMove> newItems) {
        AppendContainer request = new AppendContainer();
        List<AppendRequest> requests = new ArrayList<>();
        AppendCells cells = new AppendCells();

        cells.setSheetId(String.valueOf(worksheetId));
        List<Rows> rows = new ArrayList<>();

        HashMap<String, Rows.EnteredValue> ordered = new HashMap<>();

        for (SpreadsheetItemMove item : newItems){
            Rows rowsItem = new Rows();
            List<Rows.EnteredValue> rowValues = new ArrayList<>();
            ordered.put(mapper.getPropertyname(), stringValueAppendRequest( item.getPropertyname(), mapper.getPropertyname()));
            ordered.put(mapper.getFlatnumber(), floatValueAppendRequest(new BigDecimal(item.getFlatnumber()), mapper.getFlatnumber()));
            ordered.put(mapper.getTenantName(), stringValueAppendRequest( item.getTenantname(), mapper.getTenantName()));
            if (item.getDateIn() != null) {
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date date = null;
                try {
                    date = format.parse(item.getDateIn());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ordered.put(mapper.getDateIn(), dateValueToAppendRequest(date));
            }
            else ordered.put(mapper.getDateIn(),stringValueAppendRequest( "", mapper.getDateIn()));
            ordered.put(mapper.getLrDoorsLlocksIn(), stringValueAppendRequest( item.getLrDoorsLlocksIn(), mapper.getLrDoorsLlocksIn()));
            ordered.put(mapper.getLrWindowsScreensIn(), stringValueAppendRequest( item.getLrWindowsScreensIn(), mapper.getLrWindowsScreensIn()));
            ordered.put(mapper.getLrCarpetFlooringIn(), stringValueAppendRequest( item.getLrCarpetFlooringIn(), mapper.getLrCarpetFlooringIn()));
            ordered.put(mapper.getDrWindowScreensIn(), stringValueAppendRequest( item.getDrWindowScreensIn(), mapper.getDrWindowScreensIn()));
            ordered.put(mapper.getDrCarpetFlooringIn(), stringValueAppendRequest( item.getDrCarpetFlooringIn(), mapper.getDrCarpetFlooringIn()));
            ordered.put(mapper.gethCarpetFlooringIn(), stringValueAppendRequest( item.gethCarpetFlooringIn(), mapper.gethCarpetFlooringIn()));
            ordered.put(mapper.gethWwallsIn(), stringValueAppendRequest( item.gethWwallsIn(), mapper.gethWwallsIn()));
            ordered.put(mapper.gethLightsSwitchesIn(), stringValueAppendRequest( item.gethLightsSwitchesIn(), mapper.gethLightsSwitchesIn()));
            ordered.put(mapper.getkWindowsScreensIn(), stringValueAppendRequest( item.getkWindowsScreensIn(), mapper.getkWindowsScreensIn()));
            ordered.put(mapper.getkFlooringIn(), stringValueAppendRequest( item.getkFlooringIn(), mapper.getkFlooringIn()));
            ordered.put(mapper.getkRefrigeratorIn(), stringValueAppendRequest( item.getkRefrigeratorIn(), mapper.getkRefrigeratorIn()));
            ordered.put(mapper.getkSinkIn(), stringValueAppendRequest( item.getkSinkIn(), mapper.getkSinkIn()));
            List<String> sortedMap = new ArrayList<>(ordered.keySet());
            Collections.sort(sortedMap);
            for (String key : sortedMap)
                rowValues.add(ordered.get(key));

            rowsItem.setValues(rowValues);
            rows.add(rowsItem);
        }

        cells.setRows(rows);
        AppendRequest request1 = new AppendRequest();

        request1.setUpdateCells(cells);
        requests.add(request1);
        request.setRequests(requests);

        return request;
    }

}
