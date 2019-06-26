package com.tk.crmapp.models;
//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/tk_nationalSet?$select=tk_code,tk_name,tk_nationalId
public class tk_national {
    private String tk_natinoalId;
    private String tk_code;
    private String tk_name;

    @Override
    public String toString() {
        return tk_name;
    }

    public String getTk_natinoalId() {
        return tk_natinoalId;
    }

    public void setTk_natinoalId(String tk_natinoalId) {
        this.tk_natinoalId = tk_natinoalId;
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
    }
}
