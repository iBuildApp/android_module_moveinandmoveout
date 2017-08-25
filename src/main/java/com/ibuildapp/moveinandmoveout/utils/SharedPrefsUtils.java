package com.ibuildapp.moveinandmoveout.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ibuildapp.moveinandmoveout.MoveInandMoveOutPlugin;

/**
 * Created by web-developer on 17.03.2017.
 */

public class SharedPrefsUtils {

    public static void savePropertyName(Context context, String propertyname){
        SharedPreferences configuration = context.getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, Activity.MODE_PRIVATE);
        configuration.edit().putString(MoveInandMoveOutContants.PROPERTYNAME, propertyname).apply();
    }

    public static String getCourier(Context context){
        SharedPreferences configuration = context.getSharedPreferences(MoveInandMoveOutContants.SHARED_PREFERENCES, Activity.MODE_PRIVATE);
        return configuration.getString(MoveInandMoveOutContants.PROPERTYNAME, "");
    }
}
