package com.tk.crmapp.models;

import java.io.Serializable;

//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/tk_nationalSet?$select=tk_code,tk_name,tk_nationalId
public class tk_national  implements Serializable {
    private String tk_natinoalId;

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
        tk_natinoalId = id;
    }

    private String tk_code;
    private String tk_name;

    private String Name;
    private  String Id;

    @Override
    public String toString() {
        return tk_name;
    }

    public String getTk_natinoalId() {
        return tk_natinoalId;
    }

    public void setTk_natinoalId(String tk_natinoalId) {
        this.tk_natinoalId = tk_natinoalId;
        Id = tk_natinoalId;
    }

    public String getTk_code() {
        return tk_code;
    }

    public void setTk_code(String tk_code) {
        this.tk_code = tk_code;
    }

    public String getTk_name() {
        return tk_name;
    }

    public void setTk_name(String tk_name) {
        this.tk_name = tk_name;
        Name = tk_name;
    }
}
