package com.ibuildapp.moveinandmoveout.model.appends;



import java.util.List;

public class AppendContainer {
    public List<AppendRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<AppendRequest> requests) {
        this.requests = requests;
    }

    List<AppendRequest> requests;
}
