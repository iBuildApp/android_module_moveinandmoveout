package com.ibuildapp.moveinandmoveout.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.ibuildapp.moveinandmoveout.model.ContainerMove;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.model.UpdateItem;

import java.util.Date;

/**
 * Created by web-developer on 17.03.2017.
 */

public class DBHelper {
    public static void fillProp(ContentValues contentValues, SpreadsheetItemProp entity){
      //  contentValues.put("SP_ROW_ID", entity.getRowId());
        contentValues.put("PROPERTYNAME", entity.getPropertyname());
        contentValues.put("ADDRESS", entity.getAddress());
        contentValues.put("FLATNUMBER", entity.getFlatnumber());
        contentValues.put("MONTHLYRENT", entity.getMonthlyrent());
    }

    public static void fillMove(ContentValues contentValues, SpreadsheetItemMove entity){
        contentValues.put("SP_ROW_ID", entity.getRowId());
        contentValues.put("PROPERTYNAME", entity.getPropertyname());
        contentValues.put("FLATNUMBER", entity.getFlatnumber());
        contentValues.put("TENANTNAME", entity.getTenantname());
        contentValues.put("DATEIN", entity.getDateIn());
        contentValues.put("LRDOORSLLOCKSIN", entity.getLrDoorsLlocksIn());
        contentValues.put("LRWINDOWSSCREENSIN", entity.getLrWindowsScreensIn());
        contentValues.put("LRCARPETFLOORINGIN", entity.getLrCarpetFlooringIn());
        contentValues.put("DRWINDOWSCREENSIN", entity.getDrWindowScreensIn());
        contentValues.put("DRCARPETFLOORINGIN", entity.getDrCarpetFlooringIn());
        contentValues.put("HCARPETFLOORINGIN", entity.gethCarpetFlooringIn());
        contentValues.put("HWALLSIN", entity.gethWwallsIn());
        contentValues.put("HLIGHTSSWITCHESIN", entity.gethLightsSwitchesIn());
        contentValues.put("KWINDOWSSCREENSIN", entity.getkWindowsScreensIn());
        contentValues.put("KFLOORINGIN", entity.getkFlooringIn());
        contentValues.put("KREFRIGERATORIN", entity.getkRefrigeratorIn());
        contentValues.put("KSKININ", entity.getkSinkIn());
        contentValues.put("DATEOUT", entity.getDateOut());
        contentValues.put("LRDOORSLOCKSOUT", entity.getLrDoorsLocksOut());
        contentValues.put("LRWINDOWSSCREENSOUT", entity.getLrWindowsScreensOut());
        contentValues.put("LRCARPETFLOORINGOUT", entity.getLrCFlooringOut());
        contentValues.put("DRWINDOWSCREENSOUT", entity.getDrWindowScreensOut());
        contentValues.put("DRCARPETFLOORINGOUT", entity.getDrCarpetFlooringOut());
        contentValues.put("HCARPETFLOORINGOUT", entity.gethCarpetFlooringOut());
        contentValues.put("HWALLSOUT", entity.gethWallsOut());
        contentValues.put("HLIGHTSSWITCHESOUT", entity.gethLightsSwitchesOut());
        contentValues.put("KWINDOWSSCREENSOUT", entity.getkWindowsScreensOut());
        contentValues.put("KFLOORINGOUT", entity.getkFlooringOut());
        contentValues.put("KREFRIGERATOROUT", entity.getkRefrigeratorOut());
        contentValues.put("KSINKOUT", entity.getkSinkOut());
    }

    public static SpreadsheetItemMove parseMove(Cursor cursor) {
        SpreadsheetItemMove entity = new SpreadsheetItemMove();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (cursor.getColumnName(i).equals("SP_ROW_ID")) {
                entity.setRowId(cursor.getInt(i));
            } else
            if (cursor.getColumnName(i).equals("PROPERTYNAME")) {
                entity.setPropertyname(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("FLATNUMBER")) {
                entity.setFlatnumber(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("TENANTNAME")) {
                entity.setTenantname(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("DATEIN")) {
                entity.setDateIn(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("LRDOORSLLOCKSIN")) {
                entity.setLrDoorsLlocksIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("LRWINDOWSSCREENSIN")) {
                entity.setLrWindowsScreensIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("LRCARPETFLOORINGIN")) {
                entity.setLrCarpetFlooringIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("DRWINDOWSCREENSIN")) {
                entity.setDrWindowScreensIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("DRCARPETFLOORINGIN")) {
                entity.setDrCarpetFlooringIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HCARPETFLOORINGIN")) {
                entity.sethCarpetFlooringIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HWALLSIN")) {
                entity.sethWwallsIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HLIGHTSSWITCHESIN")) {
                entity.sethLightsSwitchesIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KWINDOWSSCREENSIN")) {
                entity.setkWindowsScreensIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KFLOORINGIN")) {
                entity.setkFlooringIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KREFRIGERATORIN")) {
                entity.setkRefrigeratorIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KSKININ")) {
                entity.setkSinkIn(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("DATEOUT")) {
                entity.setDateOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("LRDOORSLOCKSOUT")) {
                entity.setLrDoorsLocksOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("LRWINDOWSSCREENSOUT")) {
                entity.setLrWindowsScreensOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("LRCARPETFLOORINGOUT")) {
                entity.setLrCFlooringOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("DRWINDOWSCREENSOUT")) {
                entity.setDrWindowScreensOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("DRCARPETFLOORINGOUT")) {
                entity.setDrCarpetFlooringOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HCARPETFLOORINGOUT")) {
                entity.sethCarpetFlooringOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HWALLSOUT")) {
                entity.sethWallsOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("HLIGHTSSWITCHESOUT")) {
                entity.sethLightsSwitchesOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KWINDOWSSCREENSOUT")) {
                entity.setkWindowsScreensOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KFLOORINGOUT")) {
                entity.setkFlooringOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KREFRIGERATOROUT")) {
                entity.setkRefrigeratorOut(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("KSINKOUT")) {
                entity.setkSinkOut(cursor.getString(i));
            }
            else if (cursor.getColumnName(i).equals("SIGNATURE")) {
                entity.setSignature(cursor.getBlob(i));
            }
        }
        return entity;
    }

    public static SpreadsheetItemProp parseProp(Cursor cursor) {
        SpreadsheetItemProp entity = new SpreadsheetItemProp();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
        /*    if (cursor.getColumnName(i).equals("SP_ROW_ID")) {
                entity.setRowId(cursor.getInt(i));
            } else */ if (cursor.getColumnName(i).equals("PROPERTYNAME")) {
                entity.setPropertyname(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("ADDRESS")) {
                entity.setAddress(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("FLATNUMBER")) {
                entity.setFlatnumber(cursor.getString(i));
            }else if (cursor.getColumnName(i).equals("MONTHLYRENT")) {
                entity.setMonthlyrent(cursor.getString(i));
            }
        }
        return entity;
    }

    public static SpreadsheetItemProp parseDistProp(Cursor cursor) {
        SpreadsheetItemProp entity = new SpreadsheetItemProp();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
        /*    if (cursor.getColumnName(i).equals("SP_ROW_ID")) {
                entity.setRowId(cursor.getInt(i));
            } else */ if (cursor.getColumnName(i).equals("PROPERTYNAME")) {
                entity.setPropertyname(cursor.getString(i));
            }
        }
        return entity;
    }

    public static ContainerProp parseContainerProp(Cursor cursor) {
        ContainerProp container = new ContainerProp();

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (cursor.getColumnName(i).equals("DOCUMENT_TOKEN")) {
                container.setDocumentToken(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("SHEET_TITLE")) {
                container.setSheetTitle(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("ROWS_COUNT")) {
                container.setRowsCount(cursor.getInt(i));
            }else if (cursor.getColumnName(i).equals("LOADED")) {
                container.setLoaded(cursor.getInt(i));
            }
        }
        return container;
    }

    public static ContainerMove parseContainerMove(Cursor cursor) {
        ContainerMove container = new ContainerMove();

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (cursor.getColumnName(i).equals("DOCUMENT_TOKEN")) {
                container.setDocumentToken(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("SHEET_TITLE")) {
                container.setSheetTitle(cursor.getString(i));
            } else if (cursor.getColumnName(i).equals("ROWS_COUNT")) {
                container.setRowsCount(cursor.getInt(i));
            }else if (cursor.getColumnName(i).equals("LOADED")) {
                container.setLoaded(cursor.getInt(i));
            }
        }
        return container;
    }

    public static void fillContainerProp(ContentValues contentValues, ContainerProp container) {
        contentValues.put("DOCUMENT_TOKEN", container.getDocumentToken());
        contentValues.put("SHEET_TITLE", container.getSheetTitle());
        contentValues.put("ROWS_COUNT", container.getRowsCount());
        contentValues.put("LOADED", container.getLoaded());
    }

    public static void fillContainerMove(ContentValues contentValues, ContainerMove container) {
        contentValues.put("DOCUMENT_TOKEN", container.getDocumentToken());
        contentValues.put("SHEET_TITLE", container.getSheetTitle());
        contentValues.put("ROWS_COUNT", container.getRowsCount());
        contentValues.put("LOADED", container.getLoaded());
    }

    public static void fillUpdate(ContentValues contentValues, UpdateItem entity){
        contentValues.put("PROPERTYNAME", entity.getPropertyName());
        contentValues.put("FLATNUMBER", entity.getFlatNumber());
        contentValues.put("DATEIN", entity.getDateIn());
        contentValues.put("COLUMN_NUMBER", entity.getColumn().getColumnId());
        contentValues.put("VALUE_INTEGER", entity.getIntegerValue());
        contentValues.put("VALUE_TEXT", entity.getStringValue());
        contentValues.put("VALUE_DATE", entity.getDate() != null ?entity.getDate().getTime()/1000 : null);
    }



    public static UpdateItem parseUpdate(Cursor cursor) {
        UpdateItem entity = new UpdateItem();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            if (cursor.getColumnName(i).equals("SP_ROW_ID"))
                entity.setRowId(cursor.getInt(i));
            else if (cursor.getColumnName(i).equals("PROPERTYNAME"))
                entity.setPropertyName(cursor.getString(i));
            else if (cursor.getColumnName(i).equals("FLATNUMBER"))
                entity.setPropertyName(cursor.getString(i));
            else if (cursor.getColumnName(i).equals("DATEIN"))
                entity.setPropertyName(cursor.getString(i));
            else if (cursor.getColumnName(i).equals("COLUMN_NUMBER"))
                entity.setColumn(Columns.values()[cursor.getInt(i)]);
            else if (cursor.getColumnName(i).equals("VALUE_INTEGER"))
                entity.setIntegerValue(cursor.getLong(i));
            else if (cursor.getColumnName(i).equals("VALUE_TEXT"))
                entity.setStringValue(cursor.getString(i));
            else if (cursor.getColumnName(i).equals("DATE_FORMAT"))
                entity.setDateFormat(cursor.getInt(i));
        }
        entity.setDate(cursor.getLong(cursor.getColumnIndex("VALUE_DATE")) == 0 ?null:new Date(cursor.getLong(cursor.getColumnIndex("VALUE_DATE"))* 1000));

        return entity;
    }

    public static void fillSignature(ContentValues contentValues, SpreadsheetItemMove entity){
        contentValues.put("SP_ROW_ID", entity.getRowId());
        contentValues.put("PROPERTYNAME", entity.getPropertyname());
        contentValues.put("FLATNUMBER", entity.getFlatnumber());
        contentValues.put("DATEIN", entity.getDateIn());
        contentValues.put("SIGNATURE", entity.getSignature());
    }

}
