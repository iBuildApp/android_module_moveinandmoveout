package com.ibuildapp.moveinandmoveout.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ibuildapp.moveinandmoveout.model.ContainerMove;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerMove;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.model.UpdateItem;
import com.ibuildapp.moveinandmoveout.model.filters.BaseFilterItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by web-developer on 16.03.2017.
 */

public class SqlAdapter {
    private final static String CONTAINER_TABLE_PROP = "CONTAINERPROP";
    private final static String CONTAINER_TABLE_MOVE = "CONTAINERMOVE";
    private final static String ITEMS_TABLE_PROP = "ITEMSPROP";
    private final static String ITEMS_TABLE_MOVE = "ITEMSMOVE";
    private final static String UPDATES_TABLE_PROP = "UPDATESPROP";
    private final static String UPDATES_TABLE_MOVE = "UPDATESMOVE";
    private final static String SIGNATURES_TABLE = "SIGNATURES";

    private static String appId;
    private static int widgetOrder;
    private static Context context;
    private static String databaseName = "moveinandmoveout.db";
    private static SQLiteDatabase db = null;

    public static void init(Context context, String appId, int widgetOrder) {
        SqlAdapter.context = context;
        SqlAdapter.appId = appId;
        SqlAdapter.widgetOrder = widgetOrder;

        if (db == null) {
            db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        }

        if (!existTable(ITEMS_TABLE_PROP))
            createTablesProp();
        if (!existTable(ITEMS_TABLE_MOVE))
            createTablesMove();
    }

    private static String createTableName(String tableName) {
        return String.format("%s_%s_%d", tableName, appId, widgetOrder);
    }

    private static boolean existTable(String tableName) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = ?", new String[]{createTableName(tableName)});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public static void dropTablesProp() {
        if (db == null) {
            db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        }

        try {
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(ITEMS_TABLE_PROP)));
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(CONTAINER_TABLE_PROP)));
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(UPDATES_TABLE_PROP)));
        } catch (Exception e) {
        }
    }

    public static void dropTablesMove() {
        if (db == null) {
            db = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        }

        try {
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(ITEMS_TABLE_MOVE)));
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(CONTAINER_TABLE_MOVE)));
            db.execSQL(String.format("DROP TABLE IF EXISTS %s", createTableName(UPDATES_TABLE_MOVE)));
        } catch (Exception e) {
        }
    }

    private static void createTableItemsProp() {
        String query = String.format("CREATE TABLE %s ", createTableName(ITEMS_TABLE_PROP))
                + "( "
           //     + " SP_ROW_ID INTEGER PRIMARY KEY, "
                + " PROPERTYNAME TEXT, "
                + " ADDRESS TEXT, "
                + " FLATNUMBER TEXT, "
                + " MONTHLYRENT TEXT "
                + ")";
        db.execSQL(query);
    }

    private static void createTableItemsMove() {
        String query = String.format("CREATE TABLE %s ", createTableName(ITEMS_TABLE_MOVE))
                + "( "
                + " SP_ROW_ID INTEGER PRIMARY KEY, "
                + " PROPERTYNAME TEXT, "
                + " FLATNUMBER TEXT, "
                + " TENANTNAME TEXT, "
                + " DATEIN TEXT, "
                + " LRDOORSLLOCKSIN TEXT, "
                + " LRWINDOWSSCREENSIN TEXT, "
                + " LRCARPETFLOORINGIN TEXT, "
                + " DRWINDOWSCREENSIN TEXT, "
                + " DRCARPETFLOORINGIN TEXT, "
                + " HCARPETFLOORINGIN TEXT, "
                + " HWALLSIN TEXT, "
                + " HLIGHTSSWITCHESIN TEXT, "
                + " KWINDOWSSCREENSIN TEXT, "
                + " KFLOORINGIN TEXT, "
                + " KREFRIGERATORIN TEXT, "
                + " KSKININ TEXT, "
                + " DATEOUT TEXT, "
                + " LRDOORSLOCKSOUT TEXT, "
                + " LRWINDOWSSCREENSOUT TEXT, "
                + " LRCARPETFLOORINGOUT TEXT, "
                + " DRWINDOWSCREENSOUT TEXT, "
                + " DRCARPETFLOORINGOUT TEXT, "
                + " HCARPETFLOORINGOUT TEXT, "
                + " HWALLSOUT TEXT, "
                + " HLIGHTSSWITCHESOUT TEXT, "
                + " KWINDOWSSCREENSOUT TEXT, "
                + " KFLOORINGOUT TEXT, "
                + " KREFRIGERATOROUT TEXT, "
                + " KSINKOUT TEXT "
                + ")";
        db.execSQL(query);
    }

    private static void createTableUpdatesProp() {
        String query = String.format("CREATE TABLE %s ", createTableName(UPDATES_TABLE_PROP))
                + "( "
                + " SP_ROW_ID INTEGER PRIMARY KEY, "
                + " PROPERTYNAME TEXT, "
                + " ADDRESS TEXT, "
                + " FLATNUMBER TEXT, "
                + " MONTHLYRENT TEXT "
                + ")";
        db.execSQL(query);
    }

    private static void createTableUpdatesMove() {
        String query = String.format("CREATE TABLE %s ", createTableName(UPDATES_TABLE_MOVE))
                + "( "
                + " SP_ROW_ID INTEGER PRIMARY KEY, "
                + " PROPERTYNAME TEXT, "
                + " FLATNUMBER TEXT, "
                + " TENANTNAME TEXT, "
                + " DATEIN TEXT, "
                + " LRDOORSLLOCKSIN TEXT, "
                + " LRWINDOWSSCREENSIN TEXT, "
                + " LRCARPETFLOORINGIN TEXT, "
                + " DRWINDOWSCREENSIN TEXT, "
                + " DRCARPETFLOORINGIN TEXT, "
                + " HCARPETFLOORINGIN TEXT, "
                + " HWALLSIN TEXT, "
                + " HLIGHTSSWITCHESIN TEXT, "
                + " KWINDOWSSCREENSIN TEXT, "
                + " KFLOORINGIN TEXT, "
                + " KREFRIGERATORIN TEXT, "
                + " KSKININ TEXT, "
                + " DATEOUT TEXT, "
                + " LRDOORSLOCKSOUT TEXT, "
                + " LRWINDOWSSCREENSOUT TEXT, "
                + " LRCARPETFLOORINGOUT TEXT, "
                + " DRWINDOWSCREENSOUT TEXT, "
                + " DRCARPETFLOORINGOUT TEXT, "
                + " HCARPETFLOORINGOUT TEXT, "
                + " HWALLSOUT TEXT, "
                + " HLIGHTSSWITCHESOUT TEXT, "
                + " KWINDOWSSCREENSOUT TEXT, "
                + " KFLOORINGOUT TEXT, "
                + " KREFRIGERATOROUT TEXT, "
                + " KSINKOUT TEXT "
                + ")";
        db.execSQL(query);
    }
    private static void createTableContainerProp() {
        String query = String.format("CREATE TABLE %s ", createTableName(CONTAINER_TABLE_PROP))
                + "( "
                + " DOCUMENT_TOKEN TEXT PRIMARY KEY, "
                + " SHEET_TITLE TEXT, "
                + " ROWS_COUNT INTEGER, "
                + " LOADED INTEGER "
                + ")";
        db.execSQL(query);
    }
    private static void createTableContainerMove() {
        String query = String.format("CREATE TABLE %s ", createTableName(CONTAINER_TABLE_MOVE))
                + "( "
                + " DOCUMENT_TOKEN TEXT PRIMARY KEY, "
                + " SHEET_TITLE TEXT, "
                + " ROWS_COUNT INTEGER, "
                + " LOADED INTEGER "
                + ")";
        db.execSQL(query);
    }
    private static void createTableSignatures() {
        String query = String.format("CREATE TABLE %s ", createTableName(SIGNATURES_TABLE))
                + "( "
                + " SP_ROW_ID INTEGER PRIMARY KEY, "
                + " PROPERTYNAME TEXT, "
                + " FLATNUMBER TEXT, "
                + " DATEIN TEXT, "
                + " SIGNATURE BLOB "
                + ")";
        db.execSQL(query);
    }
    private static void createTablesProp() {
        dropTablesProp();
        createTableItemsProp();
        createTableContainerProp();
        createTableUpdatesProp();
    }

    private static void createTablesMove() {
        dropTablesMove();
        createTableItemsMove();
        createTableContainerMove();
        createTableUpdatesMove();
        createTableSignatures();
    }
    public static void saveToDbProp(ItemsContainerProp container) {
        try {
            ContentValues contentValues = new ContentValues(container.getItems().size());
            try {
                db.beginTransaction();
                for (SpreadsheetItemProp entity : container.getItems()) {
                    try {
                        DBHelper.fillProp(contentValues, entity);
                        db.insertWithOnConflict(createTableName(ITEMS_TABLE_PROP), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveToDbMove(ItemsContainerMove container) {
        try {
            ContentValues contentValues = new ContentValues(container.getItems().size());
            try {
                db.beginTransaction();
                for (SpreadsheetItemMove entity : container.getItems()) {
                    try {
                        DBHelper.fillMove(contentValues, entity);
                        db.insertWithOnConflict(createTableName(ITEMS_TABLE_MOVE), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveToDbProp(SpreadsheetItemProp item) {
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillProp(contentValues, item);
                    db.insertWithOnConflict(createTableName(ITEMS_TABLE_PROP), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToDbMove(SpreadsheetItemMove item) {
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillMove(contentValues, item);
                    db.insertWithOnConflict(createTableName(ITEMS_TABLE_MOVE), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getItemsCountProp() {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT (*) FROM " + createTableName(ITEMS_TABLE_PROP), null);
            if (cursor != null && cursor.getCount() != 0)
                cursor.moveToNext();
            Integer count =  cursor.getInt(0);
            return count;
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            if (cursor != null)
                cursor.close();
        }
        return 0;
    }

    public static Integer getItemsCountMove() {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT (*) FROM " + createTableName(ITEMS_TABLE_MOVE), null);
            if (cursor != null && cursor.getCount() != 0)
                cursor.moveToNext();
            Integer count =  cursor.getInt(0);
            return count;
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            if (cursor != null)
                cursor.close();
        }
        return 0;
    }

    public static void clearItemsProp() {
        dropTablesProp();
        createTableItemsProp();
        createTableContainerProp();
        createTableUpdatesProp();
    }

    public static void clearItemsMove() {
        dropTablesMove();;
        createTableItemsMove();
        createTableContainerMove();
        createTableUpdatesMove();
    }

    public static Cursor getAllItemsProp() {
        Cursor cursor;
        cursor = db.query(createTableName(ITEMS_TABLE_PROP), null, null, null, null, null, null);
        return cursor;
    }

    public static Cursor getAllItemsPropByPropName(String propName) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM " + createTableName(ITEMS_TABLE_PROP)
                + " WHERE PROPERTYNAME =  CAST ( '"+ propName +"' as TEXT)", null);
        return cursor;
    }

    public static Cursor getPropDist() {
        Cursor cursor;
        cursor = db.rawQuery("SELECT distinct PROPERTYNAME FROM " + createTableName(ITEMS_TABLE_PROP), null);
        return cursor;
    }


    public static Cursor getPropDistFilter(String qFilter ) {
        Cursor cursor;
        cursor = db.rawQuery("SELECT distinct PROPERTYNAME FROM " + createTableName(ITEMS_TABLE_PROP)
                + " WHERE PROPERTYNAME IN ( "+ qFilter + " )", null);
        return cursor;
    }
    public static void saveUpdateMove(SpreadsheetItemMove cloneItem) {
        if (!existTable(UPDATES_TABLE_MOVE))
            createTableUpdatesMove();
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillMove(contentValues, cloneItem);
                    db.insertWithOnConflict(createTableName(UPDATES_TABLE_MOVE), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Integer getUpdatesCount() {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT (*) FROM " + createTableName(UPDATES_TABLE_MOVE), null);
            if (cursor != null && cursor.getCount() != 0)
                cursor.moveToNext();
            Integer count =  cursor.getInt(0);
            return count;
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            if (cursor != null)
                cursor.close();
        }
        return 0;
    }
    public static Cursor getFilteredDataProp(BaseFilterItemProp item) {
        return  db.query(createTableName(ITEMS_TABLE_PROP), null,
                item.getFilterString(), null,
                null, null, null);

    }
    public static List<SpreadsheetItemProp> getUpdatesProp() {
        Cursor cursor = null;
        List<SpreadsheetItemProp> result = new ArrayList<>();

        try {
            cursor = db.query(createTableName(UPDATES_TABLE_PROP), null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseProp(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static List<SpreadsheetItemMove> getUpdatesMove() {
        Cursor cursor = null;
        List<SpreadsheetItemMove> result = new ArrayList<>();

        try {
            cursor = db.query(createTableName(UPDATES_TABLE_MOVE), null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseMove(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }
    public static ContainerProp getContainerProp(String documentToken) {
        Cursor cursor = null;
        ContainerProp result = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + createTableName(CONTAINER_TABLE_PROP), null);
            if (cursor.moveToFirst()) {
                do {
                    result = DBHelper.parseContainerProp(cursor);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SQL ADAPTER", " GET CONTAINER " + e.getMessage());
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static ContainerMove getContainerMove(String documentToken) {
        Cursor cursor = null;
        ContainerMove result = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + createTableName(CONTAINER_TABLE_MOVE), null);
            if (cursor.moveToFirst()) {
                do {
                    result = DBHelper.parseContainerMove(cursor);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SQL ADAPTER", " GET CONTAINER " + e.getMessage());
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }
    public static void saveToDbProp(ContainerProp container) {
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillContainerProp(contentValues, container);
                    db.insertWithOnConflict(createTableName(CONTAINER_TABLE_PROP), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToDbMove(ContainerMove container) {
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillContainerMove(contentValues, container);
                    db.insertWithOnConflict(createTableName(CONTAINER_TABLE_MOVE), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateContainerProp(String documentToken, String title, Integer rowsCount) {
        ContainerProp container = getContainerProp(documentToken);
        container.setLoaded(1);
        saveToDbProp(container);
    }

    public static void updateContainerMove(String documentToken, String title, Integer rowsCount) {
        ContainerMove container = getContainerMove(documentToken);
        container.setLoaded(1);
        saveToDbMove(container);
    }
    public static boolean containerLoadedProp(String documentToken) {
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT LOADED FROM " + createTableName(CONTAINER_TABLE_PROP)
                    + "WHERE DOCUMENT_TOKEN = CAST ( '"+ documentToken + "' as TEXT)", null);
            if (cursor != null && cursor.getCount() != 0)
                cursor.moveToNext();
            Integer count =  cursor.getInt(0);
            return count > 0;
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            if (cursor != null)
                cursor.close();
        }
        return false;
    }
    public static Integer getContainerDateFormat() {

        return null;
    }
    public static List<SpreadsheetItemProp> getDataProp() {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_PROP) ;

        Cursor cursor = null;
        List<SpreadsheetItemProp> result = new ArrayList<>();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseProp(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static List<SpreadsheetItemMove> getDataMove() {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) ;

        Cursor cursor = null;
        List<SpreadsheetItemMove> result = new ArrayList<>();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseMove(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static SpreadsheetItemMove getDataMovebyFlat(String propertyName, String flatNumber) {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) +" WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                " ORDER BY DATEIN DESC  " ;

        Cursor cursor = null;
        SpreadsheetItemMove result = new SpreadsheetItemMove();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                result = DBHelper.parseMove(cursor);
            /*    do {
                    result = DBHelper.parseMove(cursor);
                } while (cursor.moveToNext());*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static SpreadsheetItemMove getHistorydetail(String propertyName, String flatNumber, String dateIn) {
      /*  String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) +" WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                " and DATEIN = CAST ( '"+ dateIn +"' as TEXT)  " ;*/
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) +  " WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                 " and DATEIN = CAST ( '"+ dateIn +"' as TEXT) " ;
        String joinSignature = "SELECT core.*, sign.SIGNATURE AS SIGNATURE FROM (" + coreSql + ") core LEFT JOIN " + createTableName(SIGNATURES_TABLE) +
                " sign ON core.PROPERTYNAME = sign.PROPERTYNAME and  core.FLATNUMBER = sign.FLATNUMBER and core.DATEIN = sign.DATEIN ";

        Cursor cursor = null;
        SpreadsheetItemMove result = new SpreadsheetItemMove();

        try {
            cursor = db.rawQuery(joinSignature, null);
            if (cursor.moveToFirst()) {
                result = DBHelper.parseMove(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static SpreadsheetItemMove getSignature(String propertyName, String flatNumber, String dateIn) {
        String coreSql = "SELECT SIGNATURE AS SIGNATURE  FROM " + createTableName(SIGNATURES_TABLE) +" WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                " and DATEIN = CAST ( '"+ dateIn +"' as TEXT)  " ;

        Cursor cursor = null;
        SpreadsheetItemMove result = new SpreadsheetItemMove();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                result = DBHelper.parseMove(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static List<SpreadsheetItemMove> getSignatureAll() {
        String coreSql = "SELECT * FROM " + createTableName(SIGNATURES_TABLE) ;

        Cursor cursor = null;
        List<SpreadsheetItemMove> result = new ArrayList<>();
        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseMove(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static SpreadsheetItemMove getDataMovebyFlatTenantName(String propertyName, String flatNumber, String tenantName) {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) +" WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                " and TENANTNAME = CAST ( '"+ tenantName +"' as TEXT) ORDER BY DATEIN DESC  " ;

        Cursor cursor = null;
        SpreadsheetItemMove result = new SpreadsheetItemMove();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                result = DBHelper.parseMove(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }



    public static List<SpreadsheetItemMove> getDataMovebyFlatList(String propertyName, String flatNumber) {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) +" WHERE PROPERTYNAME = CAST ( '"+ propertyName +"' as TEXT)  and FLATNUMBER = CAST ( '"+ flatNumber +"' as TEXT) " +
                " ORDER BY DATEIN DESC  " ;

        Cursor cursor = null;
        List<SpreadsheetItemMove> result = new ArrayList<>();
        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseMove(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }
    public static List<SpreadsheetItemProp> getDataProp(String propName) {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_PROP) + " WHERE PROPERTYNAME = CAST ( '"+ propName +"' as TEXT)" ;

        Cursor cursor = null;
        List<SpreadsheetItemProp> result = new ArrayList<>();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseProp(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static SpreadsheetItemProp getDataPropFlat(String propName, String flatnumber) {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_PROP) + " WHERE PROPERTYNAME = CAST ( '"+ propName +"' as TEXT) and FLATNUMBER = CAST ( '"+ flatnumber +"' as TEXT)" ;

        Cursor cursor = null;
        SpreadsheetItemProp result = new SpreadsheetItemProp();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result = DBHelper.parseProp(cursor);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static List<SpreadsheetItemMove> getNewsMove() {
        String coreSql = "SELECT * FROM " + createTableName(ITEMS_TABLE_MOVE) + " WHERE SP_ROW_ID > " +
                MoveInandMoveOutContants.MIN_ADDED_VALUE +"  ORDER BY SP_ROW_ID ASC";

        Cursor cursor = null;
        List<SpreadsheetItemMove> result = new ArrayList<>();

        try {
            cursor = db.rawQuery(coreSql, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseMove(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static void updateNewItems() {
        List<UpdateItem> updates = getAllUpdates();

        try {
            db.beginTransaction();
            for (UpdateItem item : updates) {
                try {
                    StringBuilder sql = new StringBuilder("UPDATE ");
                    sql.append(createTableName(ITEMS_TABLE_MOVE));
                    sql.append(" SET ");
                    sql.append(item.getColumn().getColumnName());
                    sql.append(" = ");

                    if(item.getColumn().getColumnType() == ColumnType.TEXT)
                        sql.append("'").append(item.getStringValue()).append("'");
                    else if(item.getColumn().getColumnType() == ColumnType.NUMBER)
                        sql.append(item.getIntegerValue());
                    else if (item.getColumn().getColumnType() == ColumnType.DATE)
                        sql.append(item.getDate().getTime()/1000);
                    else  sql.append(item.getIntegerValue());

                    sql.append(" WHERE PROPERTYNAME = ").append("'").append(item.getPropertyName()).append("'");
                    sql.append(" AND FLATNUMBER = ").append("'").append(item.getFlatNumber()).append("'");
                    sql.append(" AND DATEIN = ").append("'").append(item.getDateIn()).append("'");
                    db.execSQL(sql.toString());

                    db.execSQL("DELETE FROM " + createTableName(UPDATES_TABLE_MOVE ) + " WHERE PROPERTYNAME = '" + item.getPropertyName() + "'  AND FLATNUMBER =  '" + item.getFlatNumber()+ "' AND DATEIN =  '" + item.getDateIn()+ "'");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            db.setTransactionSuccessful();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static List<UpdateItem> getAllUpdates() {

        String coreSql = "SELECT * FROM " + createTableName(UPDATES_TABLE_MOVE) ;
        String joinSignature = "SELECT core.*, items.SP_ROW_ID AS SP_ROW_ID FROM (" + coreSql + ") core INNER JOIN " +
                createTableName(ITEMS_TABLE_MOVE) +
                " items ON core.PROPERTYNAME = items.PROPERTYNAME and core.FLATNUMBER = items.FLATNUMBER and core.DATEIN=items.DATEIN";

        Cursor cursor = null;
        List<UpdateItem> result = new ArrayList<>();

        try {
            cursor = db.rawQuery(joinSignature, null);
            if (cursor.moveToFirst()) {
                do {
                    result.add(DBHelper.parseUpdate(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(cursor != null)
                cursor.close();
        }
        return result;
    }

    public static void saveSignature(SpreadsheetItemMove cloneItem) {
        if (!existTable(SIGNATURES_TABLE))
            createTableSignatures();
        try {
            ContentValues contentValues = new ContentValues(1);
            try {
                db.beginTransaction();
                try {
                    DBHelper.fillSignature(contentValues, cloneItem);
                    db.insertWithOnConflict(createTableName(SIGNATURES_TABLE), "", contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                db.setTransactionSuccessful();
            }catch (Throwable e){
                e.printStackTrace();
            }
            finally {
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

