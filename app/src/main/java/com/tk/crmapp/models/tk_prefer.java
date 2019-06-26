package com.tk.crmapp.models;
//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/tk_preferSet?$select=tk_cd,tk_name,tk_preferId

public class tk_prefer {
    String tk_preferId;
    String tk_cd;
    String tk_name;

    @Override
    public String toString() {
        return tk_name;
    }

    public String getTk_preferId() {
        return tk_preferId;
    }

    public void setTk_preferId(String tk_preferId) {
        this.tk_preferId = tk_preferId;
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
    }
}
