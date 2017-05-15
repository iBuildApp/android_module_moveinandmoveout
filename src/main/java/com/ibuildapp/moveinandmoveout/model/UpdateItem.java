package com.ibuildapp.moveinandmoveout.model;


import com.ibuildapp.moveinandmoveout.database.Columns;

import java.util.Date;

public class UpdateItem {

    private String propertyName;
    private String flatNumber;
    private String dateIn;
    private Integer rowId;
    private Columns column;
    private String stringValue = "";
    private Long integerValue;
    private Date date;
    private Integer dateFormat;

    public UpdateItem(String propertyName, String flatNumber, String dateIn, Columns column, String value) {
        this.propertyName = propertyName;
        this.flatNumber = flatNumber;
        this.dateIn = dateIn;
        this.column = column;
        this.stringValue = value;
    }

    public UpdateItem(String propertyName, String flatNumber, String dateIn, Columns column, Long value) {
        this.propertyName = propertyName;
        this.flatNumber = flatNumber;
        this.dateIn = dateIn;
        this.column = column;
        this.integerValue = value;
    }

    public UpdateItem(String propertyName, String flatNumber, String dateIn, Columns column, Date value) {
        this.propertyName = propertyName;
        this.flatNumber = flatNumber;
        this.dateIn = dateIn;
        this.column = column;
        this.date = value;
    }

    public UpdateItem() {

    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public Columns getColumn() {
        return column;
    }

    public void setColumn(Columns column) {
        this.column = column;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Long integerValue) {
        this.integerValue = integerValue;
    }

    public Integer getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(Integer dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
