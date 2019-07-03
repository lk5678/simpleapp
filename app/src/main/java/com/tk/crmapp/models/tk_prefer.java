package com.tk.crmapp.models;
//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/tk_preferSet?$select=tk_cd,tk_name,tk_preferId

import java.io.Serializable;

public class tk_prefer  implements Serializable {
    String tk_preferId;
    String tk_cd;
    String tk_name;
    String Name;
    String Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        tk_name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        tk_preferId = id;
    }

    @Override
    public String toString() {
        return tk_name;
    }

    public String getTk_preferId() {
        return tk_preferId;
    }

    public void setTk_preferId(String tk_preferId) {
        this.tk_preferId = tk_preferId;
        Id = tk_preferId;
    }

    public String getTk_cd() {
        return tk_cd;
    }

    public void setTk_cd(String tk_cd) {
        this.tk_cd = tk_cd;
    }

    public String getTk_name() {
        return tk_name;
    }

    public void setTk_name(String tk_name) {
        this.tk_name = tk_name;
        Name = tk_name;
    }
}
