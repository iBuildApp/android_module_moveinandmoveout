package com.ibuildapp.moveinandmoveout.model.appends;


import com.ibuildapp.moveinandmoveout.model.updates.Rows;

import java.util.List;

public class AppendCells {
    public static class Start {
        private String sheetId;
        private Integer rowIndex;
        private Integer columnIndex;

        public String getSheetId() {
            return sheetId;
        }

        public void setSheetId(String sheetId) {
            this.sheetId = sheetId;
        }

        public Integer getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(Integer rowIndex) {
            this.rowIndex = rowIndex;
        }

        public Integer getColumnIndex() {
            return columnIndex;
        }

        public void setColumnIndex(Integer columnIndex) {
            this.columnIndex = columnIndex;
        }
    }


    private String sheetId;
    private List<Rows> rows;
    private String fields;

    public AppendCells(){
        fields = "*";
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
