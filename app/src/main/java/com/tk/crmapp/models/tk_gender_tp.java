package com.tk.crmapp.models;

import java.io.Serializable;

public class tk_gender_tp  implements Serializable {
    private String Value;
    private String Name;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
        if(Value.equalsIgnoreCase("300000001"))
            Name = "男";
        else {
            if (Value.equalsIgnoreCase("300000002"))
                Name = "女";
            else
                Name = "爱啥啥";
        }
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
