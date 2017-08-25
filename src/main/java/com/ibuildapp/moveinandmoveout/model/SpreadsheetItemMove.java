package com.ibuildapp.moveinandmoveout.model;

import android.graphics.Color;

import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by web-developer on 16.03.2017.
 */

public class SpreadsheetItemMove implements Serializable {
    private static int newRowId;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    private Integer rowId;
    private String propertyname;
    private String flatnumber;
    private String tenantname;
    private String dateIn;
    private String lrDoorsLlocksIn;
    private String lrWindowsScreensIn;
    private String lrCarpetFlooringIn;
    private String drWindowScreensIn;
    private String drCarpetFlooringIn;
    private String hCarpetFlooringIn;
    private String hWwallsIn;
    private String hLightsSwitchesIn;
    private String kWindowsScreensIn;
    private String kFlooringIn;
    private String kRefrigeratorIn;
    private String kSinkIn;
    private String dateOut;
    private String lrDoorsLocksOut;
    private String lrWindowsScreensOut;
    private String lrCFlooringOut;
    private String drWindowScreensOut;
    private String drCarpetFlooringOut;
    private String hCarpetFlooringOut;
    private String hWallsOut;
    private String hLightsSwitchesOut;
    private String kWindowsScreensOut;
    private String kFlooringOut;
    private String kRefrigeratorOut;
    private String kSinkOut;


    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    private byte[] signature;

    public SpreadsheetItemMove() {

    }

    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }


    public String getFlatnumber() {
        return flatnumber;
    }

    public void setFlatnumber(String flatnumber) {
        this.flatnumber = flatnumber;
    }

    public String getTenantname() {
        return tenantname;
    }

    public void setTenantname(String tenantname) {
        this.tenantname = tenantname;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getLrDoorsLlocksIn() {
        return lrDoorsLlocksIn;
    }

    public void setLrDoorsLlocksIn(String lrDoorsLlocksIn) {
        this.lrDoorsLlocksIn = lrDoorsLlocksIn;
    }

    public String gethWwallsIn() {
        return hWwallsIn;
    }

    public void sethWwallsIn(String hWwallsIn) {
        this.hWwallsIn = hWwallsIn;
    }

    public String getLrWindowsScreensIn() {
        return lrWindowsScreensIn;
    }

    public void setLrWindowsScreensIn(String lrWindowsScreensIn) {
        this.lrWindowsScreensIn = lrWindowsScreensIn;
    }

    public String getLrCarpetFlooringIn() {
        return lrCarpetFlooringIn;
    }

    public void setLrCarpetFlooringIn(String lrCarpetFlooringIn) {
        this.lrCarpetFlooringIn = lrCarpetFlooringIn;
    }

    public String getDrWindowScreensIn() {
        return drWindowScreensIn;
    }

    public void setDrWindowScreensIn(String drWindowScreensIn) {
        this.drWindowScreensIn = drWindowScreensIn;
    }

    public String getDrCarpetFlooringIn() {
        return drCarpetFlooringIn;
    }

    public void setDrCarpetFlooringIn(String drCarpetFlooringIn) {
        this.drCarpetFlooringIn = drCarpetFlooringIn;
    }

    public String gethCarpetFlooringIn() {
        return hCarpetFlooringIn;
    }

    public void sethCarpetFlooringIn(String hCarpetFlooringIn) {
        this.hCarpetFlooringIn = hCarpetFlooringIn;
    }



    public String gethLightsSwitchesIn() {
        return hLightsSwitchesIn;
    }

    public void sethLightsSwitchesIn(String hLightsSwitchesIn) {
        this.hLightsSwitchesIn = hLightsSwitchesIn;
    }

    public String getkWindowsScreensIn() {
        return kWindowsScreensIn;
    }

    public void setkWindowsScreensIn(String kWindowsScreensIn) {
        this.kWindowsScreensIn = kWindowsScreensIn;
    }

    public String getkFlooringIn() {
        return kFlooringIn;
    }

    public void setkFlooringIn(String kFlooringIn) {
        this.kFlooringIn = kFlooringIn;
    }

    public String getkRefrigeratorIn() {
        return kRefrigeratorIn;
    }

    public void setkRefrigeratorIn(String kRefrigeratorIn) {
        this.kRefrigeratorIn = kRefrigeratorIn;
    }

    public String getkSinkIn() {
        return kSinkIn;
    }

    public void setkSinkIn(String kSinkIn) {
        this.kSinkIn = kSinkIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getLrDoorsLocksOut() {
        return lrDoorsLocksOut;
    }

    public void setLrDoorsLocksOut(String lrDoorsLocksOut) {
        this.lrDoorsLocksOut = lrDoorsLocksOut;
    }

    public String getLrWindowsScreensOut() {
        return lrWindowsScreensOut;
    }

    public void setLrWindowsScreensOut(String lrWindowsScreensOut) {
        this.lrWindowsScreensOut = lrWindowsScreensOut;
    }

    public String getLrCFlooringOut() {
        return lrCFlooringOut;
    }

    public void setLrCFlooringOut(String lrCFlooringOut) {
        this.lrCFlooringOut = lrCFlooringOut;
    }

    public String getDrWindowScreensOut() {
        return drWindowScreensOut;
    }

    public void setDrWindowScreensOut(String drWindowScreensOut) {
        this.drWindowScreensOut = drWindowScreensOut;
    }

    public String getDrCarpetFlooringOut() {
        return drCarpetFlooringOut;
    }

    public void setDrCarpetFlooringOut(String drCarpetFlooringOut) {
        this.drCarpetFlooringOut = drCarpetFlooringOut;
    }

    public String gethCarpetFlooringOut() {
        return hCarpetFlooringOut;
    }

    public void sethCarpetFlooringOut(String hCarpetFlooringOut) {
        this.hCarpetFlooringOut = hCarpetFlooringOut;
    }

    public String gethWallsOut() {
        return hWallsOut;
    }

    public void sethWallsOut(String hWallsOut) {
        this.hWallsOut = hWallsOut;
    }

    public String gethLightsSwitchesOut() {
        return hLightsSwitchesOut;
    }

    public void sethLightsSwitchesOut(String hLightsSwitchesOut) {
        this.hLightsSwitchesOut = hLightsSwitchesOut;
    }

    public String getkWindowsScreensOut() {
        return kWindowsScreensOut;
    }

    public void setkWindowsScreensOut(String kWindowsScreensOut) {
        this.kWindowsScreensOut = kWindowsScreensOut;
    }

    public String getkFlooringOut() {
        return kFlooringOut;
    }

    public void setkFlooringOut(String kFlooringOut) {
        this.kFlooringOut = kFlooringOut;
    }

    public String getkRefrigeratorOut() {
        return kRefrigeratorOut;
    }

    public void setkRefrigeratorOut(String kRefrigeratorOut) {
        this.kRefrigeratorOut = kRefrigeratorOut;
    }

    public String getkSinkOut() {
        return kSinkOut;
    }

    public void setkSinkOut(String kSinkOut) {
        this.kSinkOut = kSinkOut;
    }

    public SpreadsheetItemMove(SpreadsheetItemMove itemMove){
        this.rowId = itemMove.getRowId();
        this.propertyname = itemMove.getPropertyname();
        this.flatnumber = itemMove.getFlatnumber();
        this.tenantname=itemMove.getTenantname();
        this.dateIn = itemMove.getDateIn();
        this.lrDoorsLlocksIn = itemMove.getLrDoorsLlocksIn();
        this.lrWindowsScreensIn = itemMove.getLrWindowsScreensIn();
        this.lrCarpetFlooringIn=itemMove.getLrCarpetFlooringIn();
        this.drWindowScreensIn=itemMove.getDrWindowScreensIn();
        this.drCarpetFlooringIn=itemMove.getDrCarpetFlooringIn();
        this.hCarpetFlooringIn=itemMove.gethCarpetFlooringIn();
        this.hWwallsIn=itemMove.gethWwallsIn();
        this.hLightsSwitchesIn=itemMove.gethLightsSwitchesIn();
        this.kWindowsScreensIn=itemMove.getkWindowsScreensIn();
        this.kFlooringIn=itemMove.getkFlooringIn();
        this.kRefrigeratorIn=itemMove.getkRefrigeratorIn();
        this.kSinkIn=itemMove.getkSinkIn();
        this.dateOut=itemMove.getDateOut();
        this.lrDoorsLocksOut=itemMove.getLrDoorsLocksOut();
        this.lrWindowsScreensOut=itemMove.getLrWindowsScreensOut();
        this.lrCFlooringOut=itemMove.getLrCFlooringOut();
        this.drWindowScreensOut=itemMove.getDrWindowScreensOut();
        this.drCarpetFlooringOut=itemMove.getDrCarpetFlooringOut();
        this.hCarpetFlooringOut=itemMove.gethCarpetFlooringOut();
        this.hWallsOut=itemMove.gethWallsOut();
        this.hLightsSwitchesOut=itemMove.gethLightsSwitchesOut();
        this.kWindowsScreensOut=itemMove.getkWindowsScreensOut();
        this.kFlooringOut=itemMove.getkFlooringOut();
        this.kRefrigeratorOut=itemMove.getkRefrigeratorOut();
        this.kSinkOut=itemMove.getkSinkOut();
        this.signature=itemMove.getSignature();
    }

    public static SpreadsheetItemMove newInstance(SpreadsheetItemMove currentItems) {
        int maxExistValue =  MoveInandMoveOutContants.MIN_ADDED_VALUE;
        SpreadsheetItemMove item = new SpreadsheetItemMove();
        if (currentItems != null) {
            if (currentItems.getRowId()!=null) {
                if (currentItems.getRowId() > maxExistValue)
                    maxExistValue = item.getRowId();
                 newRowId = maxExistValue;
            } else  newRowId = maxExistValue+1;
            item.setRowId(newRowId);
            if (currentItems.getPropertyname()!=null) {
                item.setPropertyname(currentItems.getPropertyname());
            } else item.setPropertyname("");
            if (currentItems.getFlatnumber()!=null) {
                item.setFlatnumber(currentItems.getFlatnumber());
            } else item.setFlatnumber("");
            if (currentItems.getTenantname()!=null) {
                item.setTenantname(currentItems.getTenantname());
            } else item.setTenantname("");
            if (currentItems.getDateIn()!=null) {
                item.setDateIn(currentItems.getDateIn());
            } else item.setDateIn("");
        } else
        {
            int newRowId = maxExistValue+1;
            item.setRowId(newRowId);
            item.setPropertyname("");
            item.setFlatnumber("");
            item.setTenantname("");
            item.setDateIn("");

        }
        item.setLrDoorsLlocksIn("");
        item.setkWindowsScreensIn("");
        item.setLrCarpetFlooringIn("");
        item.setDrWindowScreensIn("");
        item.setDrCarpetFlooringIn("");
        item.sethCarpetFlooringIn("");
        item.sethWwallsIn("");
        item.sethLightsSwitchesIn("");
        item.setkWindowsScreensIn("");
        item.setkFlooringIn("");
        item.setkRefrigeratorIn("");
        item.setkSinkIn("");
        item.setDateOut("");
        item.setLrDoorsLocksOut("");
        item.setLrWindowsScreensOut("");
        item.setLrCFlooringOut("");
        item.setDrWindowScreensOut("");
        item.setDrCarpetFlooringOut("");
        item.sethCarpetFlooringOut("");
        item.sethWallsOut("");
        item.sethLightsSwitchesOut("");
        item.setkWindowsScreensOut("");
        item.setkFlooringOut("");
        item.setkRefrigeratorOut("");
        item.setkSinkOut("");
        return item;
    }

    public static SpreadsheetItemMove newInstanceSecond(SpreadsheetItemMove currentItems) {
        int maxExistValue =  MoveInandMoveOutContants.MIN_ADDED_VALUE;
        SpreadsheetItemMove item = new SpreadsheetItemMove();
        if (currentItems != null) {
            if (currentItems.getRowId()!=null) {
                if (currentItems.getRowId() > maxExistValue)
                    maxExistValue = item.getRowId();
                newRowId = maxExistValue;
            }
            int newRowId = maxExistValue;

            item.setRowId(newRowId);
            if (!currentItems.getPropertyname().equals("")) {
                item.setPropertyname(currentItems.getPropertyname());
            } else item.setPropertyname("");
            if (!currentItems.getFlatnumber().equals("")) {
                item.setFlatnumber(currentItems.getFlatnumber());
            } else item.setFlatnumber("");
        } else
        {
            int newRowId = maxExistValue+1;
            item.setRowId(newRowId);
            item.setPropertyname("");
            item.setFlatnumber("");
            item.setTenantname("");
            item.setDateIn("");

        }
        item.setLrDoorsLlocksIn("");
        item.setkWindowsScreensIn("");
        item.setLrCarpetFlooringIn("");
        item.setDrWindowScreensIn("");
        item.setDrCarpetFlooringIn("");
        item.sethCarpetFlooringIn("");
        item.sethWwallsIn("");
        item.sethLightsSwitchesIn("");
        item.setkWindowsScreensIn("");
        item.setkFlooringIn("");
        item.setkRefrigeratorIn("");
        item.setkSinkIn("");
        item.setDateOut("");
        item.setLrDoorsLocksOut("");
        item.setLrWindowsScreensOut("");
        item.setLrCFlooringOut("");
        item.setDrWindowScreensOut("");
        item.setDrCarpetFlooringOut("");
        item.sethCarpetFlooringOut("");
        item.sethWallsOut("");
        item.sethLightsSwitchesOut("");
        item.setkWindowsScreensOut("");
        item.setkFlooringOut("");
        item.setkRefrigeratorOut("");
        item.setkSinkOut("");
        return item;
    }

    public static SpreadsheetItemMove newInstanceAll(SpreadsheetItemMove data, SpreadsheetItemMove dataClone ) {
        int idRow;
        int maxExistValue =  MoveInandMoveOutContants.MIN_ADDED_VALUE;
        if (data!=null) {
            if (data.getRowId()!=null)
            {
                if (data.getRowId()>maxExistValue)
                {
                    idRow=(data.getRowId());
                }
                else {
                     idRow = maxExistValue+1;
                }
                dataClone.setRowId(idRow);
            }
        }
        dataClone.setPropertyname(data.getPropertyname());
        dataClone.setFlatnumber(data.getFlatnumber());
        dataClone.setTenantname(data.getTenantname());
        dataClone.setDateIn(data.getDateIn());
        if (data.getDateOut()!=null) {
            dataClone.setDateOut(data.getDateOut());
        }


        return dataClone;
    }


    public static SpreadsheetItemMove signature(SpreadsheetItemMove data, SpreadsheetItemMove dataClone ) {
          if (dataClone.getSignature()!=null)
              data.setSignature(dataClone.getSignature());
        return data;
    }

    public static SpreadsheetItemMove newInstanceAllUpdate(SpreadsheetItemMove data, SpreadsheetItemMove dataClone ) {
        data.setLrDoorsLocksOut(dataClone.getLrDoorsLocksOut());
        data.setLrWindowsScreensOut(dataClone.getLrWindowsScreensOut());
        data.setLrCFlooringOut(dataClone.getLrCFlooringOut());
        data.setDrWindowScreensOut(dataClone.getDrWindowScreensOut());
        data.setDrCarpetFlooringOut(dataClone.getDrCarpetFlooringOut());
        data.sethCarpetFlooringOut(dataClone.gethCarpetFlooringOut());
        data.sethWallsOut(dataClone.gethWallsOut());
        data.sethLightsSwitchesOut(dataClone.gethLightsSwitchesOut());
        data.setkWindowsScreensOut(dataClone.getkWindowsScreensOut());
        data.setkFlooringOut(dataClone.getkFlooringOut());
        data.setkRefrigeratorOut(dataClone.getkRefrigeratorOut());
        data.setkSinkOut(dataClone.getkSinkOut());
        data.setSignature(dataClone.getSignature());
        return data;
    }

    public boolean cloneEquals(SpreadsheetItemMove clone){
        if (clone.getDateIn() == null){
            if (dateIn!= null) return false;
        }else if (!clone.getDateIn().equals(dateIn))
            return false;

        if (clone.getDateOut() == null){
            if (dateOut!= null) return false;
        }else if (!clone.getDateOut().equals(dateOut))
            return false;

        if (!clone.getPropertyname().equals(propertyname))
            return false;
        if (!clone.getFlatnumber().equals(flatnumber))
            return false;
     return  clone.getPropertyname().equals(propertyname);

    }
}
