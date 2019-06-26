package com.tk.crmapp.models;

public class CRMConfigBean {

    public String CRMEndpoint = "https://crm.tk.com";
    public String ADFSEndpoint = "https://sts.tk.com";
    public  String username = "tk\\administrator";
    public  String TOKEN = "";
    public  long  EXPIRES_ON = 0;
    public  String REFRESH_TOKEN = "";

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String clientid = "595b53e1-b01b-4143-b6ed-fa83f0855bda";

    public String getCRMEndpoint() {
        return CRMEndpoint;
    }

    public void setCRMEndpoint(String CRMEndpoint) {
        this.CRMEndpoint = CRMEndpoint;
    }

    public String getADFSEndpoint() {
        return ADFSEndpoint;
    }

    public void setADFSEndpoint(String ADFSEndpoint) {
        this.ADFSEndpoint = ADFSEndpoint;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public long getEXPIRES_ON() {
        return EXPIRES_ON;
    }

    public void setEXPIRES_ON(long EXPIRES_ON) {
        this.EXPIRES_ON = EXPIRES_ON;
    }

    public String  getREFRESH_TOKEN() {
        return REFRESH_TOKEN;
    }

    public void setREFRESH_TOKEN( String REFRESH_TOKEN) {
        this.REFRESH_TOKEN = REFRESH_TOKEN;
    }
}
