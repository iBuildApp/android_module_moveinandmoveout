package com.ibuildapp.moveinandmoveout.model;

public class IbaResponse {
    private String error;
    private String access_token;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "ClassPojo [error = " + error + ", access_token = " + access_token + "]";
    }
}
