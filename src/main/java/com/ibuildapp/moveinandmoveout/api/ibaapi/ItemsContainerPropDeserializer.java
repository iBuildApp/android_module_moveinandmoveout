package com.ibuildapp.moveinandmoveout.api.ibaapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;
import com.ibuildapp.moveinandmoveout.utils.NumberUtils;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.restfb.util.StringUtils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ItemsContainerPropDeserializer implements JsonDeserializer<ItemsContainerProp> {
    private int startPosition;

    public  ItemsContainerPropDeserializer(){
        this(StaticData.getXmlParsedData().getGoogle().getFirstRowProp());
    }
    public ItemsContainerPropDeserializer(int startPosition) {
        this.startPosition = startPosition;
    }

    public JsonArray getArrayAtPosition(JsonArray array, int position){
        try {
            JsonElement tempElement = array.get(position);
            if (tempElement.isJsonArray())
                return (JsonArray) ((JsonArray) tempElement).get(0);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ItemsContainerProp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && json.isJsonArray()){
            JsonArray array = (JsonArray) json;

            JsonArray propertynameArray = getArrayAtPosition(array, 0);
            JsonArray addressArray = getArrayAtPosition(array, 1);
            JsonArray flatnumberArray = getArrayAtPosition(array, 2);
            JsonArray monthlyrentArray = getArrayAtPosition(array, 3);


            List<Integer> maxList = new ArrayList<>();

            maxList.add(propertynameArray == null ? 0 : propertynameArray.size());
            maxList.add(addressArray == null ? 0 :propertynameArray.size());
            maxList.add(flatnumberArray == null ? 0 :propertynameArray.size());
            maxList.add(monthlyrentArray == null ? 0 :propertynameArray.size());

            int size = Collections.max(maxList);
            ItemsContainerProp container = new ItemsContainerProp();

            System.gc();

            for (int index = 0; index < size; index ++){
                SpreadsheetItemProp currentItem = new SpreadsheetItemProp();
           //     currentItem.setRowId(index + startPosition);
                currentItem.setPropertyname((propertynameArray != null) && (index <= propertynameArray.size() - 1) ? propertynameArray.get(index).getAsString():"");
                currentItem.setAddress((addressArray != null) && (index <= addressArray.size() - 1) ? addressArray.get(index).getAsString():"");
                currentItem.setFlatnumber((flatnumberArray != null) && (index <= flatnumberArray.size() - 1) ? flatnumberArray.get(index).getAsString():"");
                currentItem.setMonthlyrent((monthlyrentArray != null) && (index <= monthlyrentArray.size() - 1) ? monthlyrentArray.get(index).getAsString():"" + " ");


/*
                String dateString = (dateArray != null) &&(index <= dateArray.size() - 1) ? dateArray.get(index).getAsString() : null;
                if (dateString != null) {
                    BigDecimal value = NumberUtils.tryParse(dateString);
                    if (value != null) {
                        currentItem.setDate(DateUtils.excelDateToJavaDate(value));
                        currentItem.setDateFormat(DateUtils.DateFormats.SERIAL.ordinal());
                    }
                    else {
                        DateUtils.DateFormats format = DateUtils.determineDateFormat(dateString);
                        if (format.equals(DateUtils.DateFormats.NULL))
                            currentItem.setDateFormat(DateUtils.DateFormats.NULL.ordinal());
                        else {
                            Date parsedDate = DateUtils.tryParse(dateString, format.getDateTemplate());
                            currentItem.setDate(parsedDate);
                            currentItem.setDateFormat(format.ordinal());
                        }
                    }
                }else currentItem.setDateFormat(DateUtils.DateFormats.NULL.ordinal());*/

 /*               String quantity = (quantityArray != null) && (index <= quantityArray.size() - 1) ? quantityArray.get(index).getAsString(): "";
                if (StringUtils.isBlank(quantity))
                    currentItem.setQuantity(0f);
                else if ( NumberUtils.FLOATING_POINT_PATTERN.matcher(quantity).matches()) {
                    currentItem.setQuantity(Float.parseFloat(quantity));
                }else currentItem.setQuantity(0f);*/

                container.getItems().add(currentItem);
            }

            System.gc();
            return container;
        }
        return null;
    }
}
