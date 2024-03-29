package com.tk.crmapp.models;

//国籍实体，
//实体获取链接：https://crm.tk.com/XRMServices/2011/OrganizationData.svc/tk_country_tpSet
//实体名称：tk_country_tp

import java.io.Serializable;

public class country_tp  implements Serializable{
    private String tk_name;
    private String  tk_country_tpId;
    private String  tk_cd;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        tk_country_tpId = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        tk_name = Name;
    }

    private String Id;
    private String Name;
    public String getTk_name() {
        return tk_name;
    }

    public void setTk_name(String tk_name) {
        this.tk_name = tk_name;
        Name = tk_name;
    }

    public String getTk_country_tpId() {
        return tk_country_tpId;
    }

    public void setTk_country_tpId(String tk_country_tpId) {
        this.tk_country_tpId = tk_country_tpId;
        Id = tk_country_tpId;
    }

    public String getTk_cd() {
        return tk_cd;
    }

    public void setTk_cd(String tk_cd) {
        this.tk_cd = tk_cd;
    }
    @Override
    public String toString()
    {

        return tk_name;
    }
}
