package com.ibuildapp.moveinandmoveout.model;

import java.io.Serializable;

/**
 * Created by web-developer on 16.03.2017.
 */

public class SpreadsheetItemProp implements Serializable {
 //   private Integer rowId;
    private String propertyname;
    private String address;
    private String flatnumber;
    private String monthlyrent;
    private String status;

    public SpreadsheetItemProp() {

    }


    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlatnumber() {
        return flatnumber;
    }

    public void setFlatnumber(String flatnumber) {
        this.flatnumber = flatnumber;
    }

    public String getMonthlyrent() {
        return monthlyrent;
    }

    public void setMonthlyrent(String monthlyrent) {
        this.monthlyrent = monthlyrent;
    }

    public SpreadsheetItemProp(SpreadsheetItemProp itemProp){
        this.propertyname = itemProp.getPropertyname();
        this.address = itemProp.getAddress();
        this.flatnumber=itemProp.getFlatnumber();
        this.monthlyrent=itemProp.getMonthlyrent();
        this.status=itemProp.getStatus();

    }
}
