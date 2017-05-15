package com.ibuildapp.moveinandmoveout.model.filters;

import com.ibuildapp.moveinandmoveout.database.ColumnsProp;

/**
 * Created by web-developer on 17.03.2017.
 */

public abstract class BaseFilterItemProp {
    private ColumnsProp column;

    public BaseFilterItemProp(ColumnsProp column){
        this.column = column;
    }

    public ColumnsProp getColumn() {
        return column;
    }

    public abstract String getFilterString();
}
