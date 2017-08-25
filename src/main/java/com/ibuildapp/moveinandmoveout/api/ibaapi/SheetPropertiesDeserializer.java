package com.ibuildapp.moveinandmoveout.api.ibaapi;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetData;
import com.ibuildapp.moveinandmoveout.model.sheets.SheetProperties;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SheetPropertiesDeserializer implements JsonDeserializer<SheetProperties> {

    @Override
    public SheetProperties deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        Map<String, SheetData> map;

        if (json.isJsonArray() && ((JsonArray)json).size() == 1) {
            map = new HashMap<>();
            map.put("0", gson.fromJson(((JsonArray)json).get(0), SheetData.class));
        }else
            map = gson.fromJson(json, new TypeToken<Map<String, SheetData>>() {}.getType());

        SheetProperties properties = new SheetProperties();
        properties.setProperties(map);
        return properties;
    }
}
