package com.ibuildapp.moveinandmoveout.api.googleapi;


import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.PropertyMapper;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.model.updates.Rows;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateCells;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateContainer;
import com.ibuildapp.moveinandmoveout.model.updates.UpdateRequest;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemsConverter {

    private Integer worksheetId;
    private PropertyMapper mapper;

    public ItemsConverter(Integer worksheetId, PropertyMapper mapper){
        this.worksheetId = worksheetId;
        this.mapper = mapper;
    }

    public UpdateContainer itemToUpdateRequest(List<SpreadsheetItemProp> items){
        UpdateContainer request = new UpdateContainer();
    /*    List<UpdateRequest> requests = new ArrayList<>();
        for (SpreadsheetItemProp item : items){
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getPropertyname(), mapper.getPropertyname()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getAddress(), mapper.getAddress()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getFlatnumber(), mapper.getFlatnumber()));
            requests.add(stringValueToUpdateRequest(item.getRowId()-1, item.getMonthlyrent(), mapper.getMonthlyrent()));
        }
        request.setRequests(requests);*/
        return request;
    }

}
