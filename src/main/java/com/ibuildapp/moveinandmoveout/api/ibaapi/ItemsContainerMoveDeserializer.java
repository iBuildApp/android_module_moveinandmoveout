package com.ibuildapp.moveinandmoveout.api.ibaapi;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerMove;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;
import com.ibuildapp.moveinandmoveout.utils.NumberUtils;
import com.ibuildapp.moveinandmoveout.utils.StaticData;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemsContainerMoveDeserializer implements JsonDeserializer<ItemsContainerMove> {
    private int startPosition;

    public ItemsContainerMoveDeserializer(){
        this(StaticData.getXmlParsedData().getGoogle().getFirstRowMove());
    }
    public ItemsContainerMoveDeserializer(int startPosition) {
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
    public ItemsContainerMove deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null && json.isJsonArray()){
            JsonArray array = (JsonArray) json;

            JsonArray propertynameArray = getArrayAtPosition(array, 0);
            JsonArray flatnumberArray = getArrayAtPosition(array, 1);
            JsonArray tenantNameArray = getArrayAtPosition(array, 2);
            JsonArray dateInArray = getArrayAtPosition(array, 3);
            JsonArray lrDoorsLlocksInArray = getArrayAtPosition(array, 4);
            JsonArray lrWindowsScreensInArray = getArrayAtPosition(array, 5);
            JsonArray lrCarpetFlooringInArray = getArrayAtPosition(array, 6);
            JsonArray drWindowScreensInArray = getArrayAtPosition(array, 7);
            JsonArray drCarpetFlooringInArray = getArrayAtPosition(array, 8);
            JsonArray hCarpetFlooringInArray = getArrayAtPosition(array, 9);
            JsonArray hWwallsInArray = getArrayAtPosition(array, 10);
            JsonArray hLightsSwitchesInArray = getArrayAtPosition(array, 11);
            JsonArray kWindowsScreensInArray = getArrayAtPosition(array, 12);
            JsonArray kFlooringInArray = getArrayAtPosition(array, 13);
            JsonArray kRefrigeratorInArray = getArrayAtPosition(array, 14);
            JsonArray kSinkInArray = getArrayAtPosition(array, 15);
            JsonArray dateOutArray = getArrayAtPosition(array, 16);
            JsonArray lrDoorsLocksOutArray = getArrayAtPosition(array, 17);
            JsonArray lrWindowsScreensOutArray = getArrayAtPosition(array, 18);
            JsonArray lrCFlooringOutArray = getArrayAtPosition(array, 19);
            JsonArray drWindowScreensOutArray = getArrayAtPosition(array, 20);
            JsonArray drCarpetFlooringOutArray = getArrayAtPosition(array, 21);
            JsonArray hCarpetFlooringOutArray = getArrayAtPosition(array, 22);
            JsonArray hWallsOutArray = getArrayAtPosition(array, 23);
            JsonArray hLightsSwitchesOutArray = getArrayAtPosition(array, 24);
            JsonArray kWindowsScreensOutArray = getArrayAtPosition(array, 25);
            JsonArray kFlooringOutArray = getArrayAtPosition(array, 26);
            JsonArray kRefrigeratorOutArray = getArrayAtPosition(array, 27);
            JsonArray kSinkOutArray = getArrayAtPosition(array, 28);



            List<Integer> maxList = new ArrayList<>();

            maxList.add(propertynameArray == null ? 0 : propertynameArray.size());
            maxList.add(flatnumberArray == null ? 0 :propertynameArray.size());

            int size = Collections.max(maxList);
            ItemsContainerMove container = new ItemsContainerMove();

            System.gc();

            for (int index = 0; index < size; index ++){
                SpreadsheetItemMove currentItem = new SpreadsheetItemMove();
           //     currentItem.setRowId(index + startPosition);
                //&& (index <= propertynameArray.size() - 1)
                currentItem.setRowId(index + startPosition);
                currentItem.setPropertyname((propertynameArray != null) && (index <= propertynameArray.size() - 1) ? propertynameArray.get(index).getAsString():"");
                currentItem.setFlatnumber((flatnumberArray != null)&& (index <= flatnumberArray.size() - 1) ? flatnumberArray.get(index).getAsString():"");
                currentItem.setTenantname((tenantNameArray != null)&& (index <= tenantNameArray.size() - 1) ? tenantNameArray.get(index).getAsString():"");
                currentItem.setDateIn((dateInArray != null) && (index <= dateInArray.size() - 1) ? dateInArray.get(index).getAsString():"");
                currentItem.setLrDoorsLlocksIn((lrDoorsLlocksInArray != null)&& (index <= lrDoorsLlocksInArray.size() - 1) ? lrDoorsLlocksInArray.get(index).getAsString():"");
                currentItem.setLrWindowsScreensIn((lrWindowsScreensInArray != null)&& (index <= lrWindowsScreensInArray.size() - 1) ? lrWindowsScreensInArray.get(index).getAsString():"");
                currentItem.setLrCarpetFlooringIn((lrCarpetFlooringInArray != null)&& (index <= lrCarpetFlooringInArray.size() - 1) ? lrCarpetFlooringInArray.get(index).getAsString():"");
                currentItem.setDrWindowScreensIn((drWindowScreensInArray != null)&& (index <= drWindowScreensInArray.size() - 1) ? drWindowScreensInArray.get(index).getAsString():"");
                currentItem.setDrCarpetFlooringIn((drCarpetFlooringInArray != null)&& (index <= drCarpetFlooringInArray.size() - 1)  ? drCarpetFlooringInArray.get(index).getAsString():"");
                currentItem.sethCarpetFlooringIn((hCarpetFlooringInArray != null)&& (index <= hCarpetFlooringInArray.size() - 1)  ? hCarpetFlooringInArray.get(index).getAsString():"");
                currentItem.sethWwallsIn((hWwallsInArray != null) && (index <= hWwallsInArray.size() - 1)  ? hWwallsInArray.get(index).getAsString():"");
                currentItem.sethLightsSwitchesIn((hLightsSwitchesInArray != null)&& (index <= hLightsSwitchesInArray.size() - 1) ? hLightsSwitchesInArray.get(index).getAsString():"");
                currentItem.setkWindowsScreensIn((kWindowsScreensInArray != null)&& (index <= kWindowsScreensInArray.size() - 1) ? kWindowsScreensInArray.get(index).getAsString():"");
                currentItem.setkFlooringIn((kFlooringInArray != null) && (index <= kFlooringInArray.size() - 1) ? kFlooringInArray.get(index).getAsString():"");
                currentItem.setkRefrigeratorIn((kRefrigeratorInArray != null)&& (index <= kRefrigeratorInArray.size() - 1) ? kRefrigeratorInArray.get(index).getAsString():"");
                currentItem.setkSinkIn((kSinkInArray != null)&& (index <= kSinkInArray.size() - 1) ? kSinkInArray.get(index).getAsString():"");
                currentItem.setDateOut((dateOutArray != null)&& (index <= dateOutArray.size() - 1) ? dateOutArray.get(index).getAsString():"");
                currentItem.setLrDoorsLocksOut((lrDoorsLocksOutArray != null)&& (index <= lrDoorsLocksOutArray.size() - 1)? lrDoorsLocksOutArray.get(index).getAsString():"");
                currentItem.setLrWindowsScreensOut((lrWindowsScreensOutArray != null)&& (index <= lrWindowsScreensOutArray.size() - 1) ? lrWindowsScreensOutArray.get(index).getAsString():"");
                currentItem.setLrCFlooringOut((lrCFlooringOutArray != null) && (index <= lrCFlooringOutArray.size() - 1) ? lrCFlooringOutArray.get(index).getAsString():"");
                currentItem.setDrWindowScreensOut((drWindowScreensOutArray != null)&& (index <= drWindowScreensOutArray.size() - 1) ? drWindowScreensOutArray.get(index).getAsString():"");
                currentItem.setDrCarpetFlooringOut((drCarpetFlooringOutArray != null)&& (index <= drCarpetFlooringOutArray.size() - 1) ? drCarpetFlooringOutArray.get(index).getAsString():"");
                currentItem.sethCarpetFlooringOut((hCarpetFlooringOutArray != null) && (index <= hCarpetFlooringOutArray.size() - 1) ? hCarpetFlooringOutArray.get(index).getAsString():"");
                currentItem.sethWallsOut((hWallsOutArray != null) && (index <= hWallsOutArray.size() - 1)  ? hWallsOutArray.get(index).getAsString():"");
                currentItem.sethLightsSwitchesOut((hLightsSwitchesOutArray != null)&& (index <= hLightsSwitchesOutArray.size() - 1) ? hLightsSwitchesOutArray.get(index).getAsString():"");
                currentItem.setkWindowsScreensOut((kWindowsScreensOutArray != null)&& (index <= kWindowsScreensOutArray.size() - 1) ? kWindowsScreensOutArray.get(index).getAsString():"");
                currentItem.setkFlooringOut((kFlooringOutArray != null)&& (index <= kFlooringOutArray.size() - 1)  ? kFlooringOutArray.get(index).getAsString():"");
                currentItem.setkRefrigeratorOut((kRefrigeratorOutArray != null)&& (index <= kRefrigeratorOutArray.size() - 1)  ? kRefrigeratorOutArray.get(index).getAsString():"");
                currentItem.setkSinkOut((kSinkOutArray != null)&& (index <= kSinkOutArray.size() - 1) ? kSinkOutArray.get(index).getAsString():"" + " ");


 /*               String dateString = (dateInArray != null) &&(index <= dateInArray.size() - 1) ? dateInArray.get(index).getAsString() : null;
                if (dateString != null) {
                    BigDecimal value = NumberUtils.tryParse(dateString);
                    if (value != null) {
                        currentItem.setDateIn(DateUtils.excelDateToJavaDate(value));
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
