package com.ibuildapp.moveinandmoveout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by web-developer on 17.03.2017.
 */

public class ItemsContainerProp implements Serializable {
    private List<SpreadsheetItemProp> items;

    public ItemsContainerProp(){
        items = new ArrayList<>();
    }

    public List<SpreadsheetItemProp> getItems() {
        return items;
    }

    public void setItems(List<SpreadsheetItemProp> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }
}
