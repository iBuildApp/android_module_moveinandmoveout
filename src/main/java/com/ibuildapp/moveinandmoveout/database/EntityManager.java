package com.ibuildapp.moveinandmoveout.database;

import android.content.Context;
import android.database.Cursor;
import com.ibuildapp.moveinandmoveout.database.SqlAdapter;
import com.ibuildapp.moveinandmoveout.model.ContainerMove;
import com.ibuildapp.moveinandmoveout.model.ContainerProp;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerMove;
import com.ibuildapp.moveinandmoveout.model.ItemsContainerProp;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.model.filters.BaseFilterItemProp;
import com.ibuildapp.moveinandmoveout.utils.ItemUtils;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.util.List;

/**
 * Created by web-developer on 16.03.2017.
 */

public class EntityManager {
                private static  EntityManager entityManager ;

                public static EntityManager newInstance(Context context, String appId, int widgetOrder){
                    if (entityManager == null)
                        entityManager = new EntityManager(context, appId, widgetOrder);
                    return entityManager;
                }

                public static EntityManager getInstance() {
                    return entityManager;
                }

                public EntityManager(Context context, String appId, int widgetOrder) {
                    SqlAdapter.init(context, appId, widgetOrder);
                }

                public Integer getItemsCount() {
                    return SqlAdapter.getItemsCountProp();
                }

                public Integer getItemsCountMove() {
                return SqlAdapter.getItemsCountMove();
            }

                public void saveToDbProp(ItemsContainerProp container) {
                    SqlAdapter.saveToDbProp(container);
                }

                public void saveToDbMove(ItemsContainerMove container) {
                    SqlAdapter.saveToDbMove(container);
                }

                public void clearItemsProp() {
                    SqlAdapter.clearItemsProp();
                }

                public void clearItemsMove() {
                SqlAdapter.clearItemsMove();
            }

                public Cursor getPropDistinct(){
                return SqlAdapter.getPropDist();
            }

                public Cursor getPropDistinctFilter(String qFilter){
                return SqlAdapter.getPropDistFilter(qFilter);
                }

                public int getUpdatesCountMove() {
                    return SqlAdapter.getUpdatesCount();
                }

                public List<SpreadsheetItemMove> getUpdatesMove() {
                    return SqlAdapter.getUpdatesMove();
                }

                public void updateDateCheckedProp(String documentToken, String title, Integer rowsCount){
                    SqlAdapter.updateContainerProp(documentToken, title, rowsCount);
                }

                public void updateDateCheckedMove(String documentToken, String title, Integer rowsCount){
                    SqlAdapter.updateContainerMove(documentToken, title, rowsCount);
                }

                public ContainerProp getContainer(String documentToken) {
                    return SqlAdapter.getContainerProp(documentToken);
                }
                public ContainerMove getContainerMove(String documentToken) {
                    return SqlAdapter.getContainerMove(documentToken);
                }

                public void saveToDbProp(ContainerProp container) {
                    SqlAdapter.saveToDbProp(container);
                }

                public void saveToDbMove(ContainerMove container) {
                    SqlAdapter.saveToDbMove(container);
                }

                public List<SpreadsheetItemProp> getItems() {
                return SqlAdapter.getDataProp();}


                public SpreadsheetItemMove getItemsMovebyFlat(String propertyName, String flatNumber) {
                     return SqlAdapter.getDataMovebyFlat(propertyName,flatNumber);
                }

                public SpreadsheetItemMove getItemsHistoryDetail(String propertyName, String flatNumber, String DateIn) {
                    return SqlAdapter.getHistorydetail(propertyName,flatNumber, DateIn);
                }

                public SpreadsheetItemMove getSignature(String propertyName, String flatNumber, String DateIn) {
                    return SqlAdapter.getSignature(propertyName,flatNumber, DateIn);
                }

                public SpreadsheetItemMove getDataMovebyFlatTenantName(String propertyName, String flatNumber, String tenantName) {
                    return SqlAdapter.getDataMovebyFlatTenantName(propertyName,flatNumber, tenantName);
                }
                public List<SpreadsheetItemMove> getItemsMovebyFlatList(String propertyName, String flatNumber) {
                    return SqlAdapter.getDataMovebyFlatList(propertyName,flatNumber);
                }

                public List<SpreadsheetItemProp> getItemsbyProp(String propName) {
                    return SqlAdapter.getDataProp(propName);
                }

                public void saveUpdateMove(SpreadsheetItemMove cloneData, SpreadsheetItemMove item) {
                SqlAdapter.saveToDbMove(cloneData);
                    if (cloneData.getRowId() < MoveInandMoveOutContants.MIN_ADDED_VALUE)
                           SqlAdapter.saveUpdateMove(cloneData);
                    SqlAdapter.saveSignature(cloneData);
                 }

                public List<SpreadsheetItemMove> getNewsMove() {
                 return SqlAdapter.getNewsMove();
            }

                public void updateNewItems() {
        SqlAdapter.updateNewItems();
    }

}
