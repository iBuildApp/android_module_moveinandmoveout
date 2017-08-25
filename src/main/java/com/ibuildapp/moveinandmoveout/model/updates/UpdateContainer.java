package com.ibuildapp.moveinandmoveout.model.updates;


import java.util.List;

public class UpdateContainer {
    public List<UpdateRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<UpdateRequest> requests) {
        this.requests = requests;
    }

    List<UpdateRequest> requests;
}
