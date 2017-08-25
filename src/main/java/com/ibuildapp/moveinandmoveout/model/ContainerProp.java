package com.ibuildapp.moveinandmoveout.model;

/**
 * Created by web-developer on 17.03.2017.
 */

public class ContainerProp {
    private String documentToken;
    private String sheetTitle;
    private Integer rowsCount;
    private Integer loaded;

    public String getDocumentToken() {
        return documentToken;
    }

    public void setDocumentToken(String documentToken) {
        this.documentToken = documentToken;
    }

    public Integer getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(Integer rowsCount) {
        this.rowsCount = rowsCount;
    }

    public Integer getLoaded() {
        return loaded;
    }

    public void setLoaded(Integer loaded) {
        this.loaded = loaded;
    }


    public String getSheetTitle() {
        return sheetTitle;
    }

    public void setSheetTitle(String sheetTitle) {
        this.sheetTitle = sheetTitle;
    }
}
