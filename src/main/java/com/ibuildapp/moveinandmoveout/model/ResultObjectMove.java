package com.ibuildapp.moveinandmoveout.model;

public class ResultObjectMove {
    private ItemsContainerMove result;

    private String error;

    public ItemsContainerMove getResult ()
    {
        return result;
    }

    public void setResult (ItemsContainerMove result)
    {
        this.result = result;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", error = "+error+"]";
    }
}
