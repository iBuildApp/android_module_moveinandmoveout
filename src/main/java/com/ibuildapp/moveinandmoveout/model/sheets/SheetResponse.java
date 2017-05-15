package com.ibuildapp.moveinandmoveout.model.sheets;


import com.google.gson.annotations.SerializedName;

public class SheetResponse {
    private String error;

    @SerializedName("worksheets")
    private SheetProperties sheetProperties;

    public SheetProperties getSheetProperties() {
        return sheetProperties;
    }

    public String getError() {
        return error;
    }
}
