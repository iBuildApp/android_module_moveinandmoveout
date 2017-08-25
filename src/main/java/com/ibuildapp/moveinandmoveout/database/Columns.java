package com.ibuildapp.moveinandmoveout.database;


public enum Columns {
    DATEOUT(17,"DATEOUT",                     ColumnType.DATE),
    LRDOORSLOCKSOUT(18, "LRDOORSLOCKSOUT",         ColumnType.TEXT),
    LRWINDOWSSCREENSOUT(19, "LRWINDOWSSCREENSOUT",        ColumnType.TEXT),
    LRCARPETFLOORINGOUT(20, "LRCARPETFLOORINGOUT",        ColumnType.TEXT),
    DRWINDOWSCREENSOUT(21, "DRWINDOWSCREENSOUT",                    ColumnType.TEXT),
    DRCARPETFLOORINGOUT(22, "DRCARPETFLOORINGOUT",                    ColumnType.TEXT),
    HCARPETFLOORINGOUT(23, "HCARPETFLOORINGOUT",                    ColumnType.TEXT),
    HWALLSOUT(24, "HWALLSOUT",                    ColumnType.TEXT),
    HLIGHTSSWITCHESOUT(25, "HLIGHTSSWITCHESOUT",                    ColumnType.TEXT),
    KWINDOWSSCREENSOUT(26, "KWINDOWSSCREENSOUT",                    ColumnType.TEXT),
    KFLOORINGOUT(27, "KFLOORINGOUT",                    ColumnType.TEXT),
    KREFRIGERATOROUT(28, "KREFRIGERATOROUT",                    ColumnType.TEXT),
    KSINKOUT(29, "KSINKOUT",                    ColumnType.TEXT);




    private String columnName;
    private Integer columnId;
    private ColumnType columnType;

    Columns(Integer columnId, String columnName, ColumnType columnType){
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public ColumnType getColumnType() {
        return columnType;
    }
}
