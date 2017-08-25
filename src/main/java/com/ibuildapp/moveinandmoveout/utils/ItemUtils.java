package com.ibuildapp.moveinandmoveout.utils;


import com.ibuildapp.moveinandmoveout.database.Columns;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.UpdateItem;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemUtils {
    public static List<UpdateItem> toUpdates(SpreadsheetItemMove cloneItem){
        List<UpdateItem> resultList = new ArrayList<>();

     //   if (!cloneItem.getDateOut().equals(item.getDateOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.DATEOUT, cloneItem.getDateOut()));
     //   if (!cloneItem.getLrDoorsLocksOut().equals(item.getLrDoorsLocksOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.LRDOORSLOCKSOUT, cloneItem.getLrDoorsLocksOut()));
     //   if (!cloneItem.getLrWindowsScreensOut().equals(item.getLrWindowsScreensOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.LRWINDOWSSCREENSOUT, cloneItem.getLrWindowsScreensOut()));
     //   if (!cloneItem.getLrCFlooringOut().equals(item.getLrCFlooringOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.LRCARPETFLOORINGOUT, cloneItem.getLrCFlooringOut()));

     //   if (!cloneItem.getDrWindowScreensOut().equals(item.getDrWindowScreensOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.DRWINDOWSCREENSOUT, cloneItem.getDrWindowScreensOut()));
    //    if (!cloneItem.getDrCarpetFlooringOut().equals(item.getDrCarpetFlooringOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.DRCARPETFLOORINGOUT, cloneItem.getDrCarpetFlooringOut()));

     //   if (!cloneItem.gethCarpetFlooringOut().equals(item.gethCarpetFlooringOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.HCARPETFLOORINGOUT, cloneItem.gethCarpetFlooringOut()));
     //   if (!cloneItem.gethWallsOut().equals(item.gethWallsOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.HWALLSOUT, cloneItem.gethWallsOut()));
      //  if (!cloneItem.gethLightsSwitchesOut().equals(item.gethLightsSwitchesOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.HLIGHTSSWITCHESOUT, cloneItem.gethLightsSwitchesOut()));
      //  if (!cloneItem.getkWindowsScreensOut().equals(item.getkWindowsScreensOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.KWINDOWSSCREENSOUT, cloneItem.getkWindowsScreensOut()));
      //  if (!cloneItem.getkFlooringOut().equals(item.getkFlooringOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.KFLOORINGOUT, cloneItem.getkFlooringOut()));
     //   if (!cloneItem.getkRefrigeratorOut().equals(item.getkRefrigeratorOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.KREFRIGERATOROUT, cloneItem.getkRefrigeratorOut()));
      //  if (!cloneItem.getkSinkOut().equals(item.getkSinkOut()))
            resultList.add(new UpdateItem(cloneItem.getPropertyname(), cloneItem.getFlatnumber(), cloneItem.getDateIn(), Columns.KSINKOUT, cloneItem.getkSinkOut()));


        return resultList;
    }
}
