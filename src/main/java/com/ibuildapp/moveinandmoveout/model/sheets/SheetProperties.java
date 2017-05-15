package com.ibuildapp.moveinandmoveout.model.sheets;

import java.util.Map;

public class SheetProperties {
    private Map<String, SheetData> properties;

    public Map<String, SheetData> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, SheetData> properties) {
        this.properties = properties;
    }
}