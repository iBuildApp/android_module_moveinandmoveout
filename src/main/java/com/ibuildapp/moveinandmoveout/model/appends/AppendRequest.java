package com.ibuildapp.moveinandmoveout.model.appends;



public class AppendRequest {
//    private String fields = "*"
    private AppendCells appendCells;

    public AppendCells getUpdateCells() {
        return appendCells;
    }

    public void setUpdateCells(AppendCells appendCells) {
        this.appendCells = appendCells;
    }

}
