package com.tk.crmapp.models;



import com.alibaba.fastjson.annotation.JSONField;


public class DotNetJson {

    @JSONField(name = "d")
    protected String dvalue;



    public DotNetJson() {
        dvalue ="";
    }
    public DotNetJson(String value) {
        dvalue =value;
    }
    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue;
    }
}
