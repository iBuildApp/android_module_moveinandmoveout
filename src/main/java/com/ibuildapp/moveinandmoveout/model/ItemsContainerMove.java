package com.ibuildapp.moveinandmoveout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by web-developer on 05.04.2017.
 */

public class ItemsContainerMove implements Serializable {
    private List<SpreadsheetItemMove> items;

    public ItemsContainerMove(){
        items = new ArrayList<>();
    }

    public List<SpreadsheetItemMove> getItems() {
        return items;
    }

    public void setItems(List<SpreadsheetItemMove> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }
}
