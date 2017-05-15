package com.ibuildapp.moveinandmoveout.model;

public class ResultObjectProp {
    private ItemsContainerProp result;

    private String error;

    public ItemsContainerProp getResult ()
    {
        return result;
    }

    public void setResult (ItemsContainerProp result)
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
