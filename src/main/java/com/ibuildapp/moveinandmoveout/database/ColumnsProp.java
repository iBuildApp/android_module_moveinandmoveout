package com.ibuildapp.moveinandmoveout.database;

/**
 * Created by web-developer on 17.03.2017.
 */

public enum ColumnsProp {
    PROPERTYNAME("PROPERTYNAME"), ADDRESS("ADDRESS"), FLATNUMBER("FLATNUMBER"), MONTHLYRENT("MONTHLYRENT");

    private String columnName;

    ColumnsProp(String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
