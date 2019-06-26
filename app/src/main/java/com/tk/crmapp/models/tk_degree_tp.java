package com.tk.crmapp.models;

import java.io.Serializable;

public class tk_degree_tp implements Serializable {
    private String tk_degree_tpId;
    private String tk_name;
    private String Id;
    private String Name;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        tk_degree_tpId = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        tk_name = name;
    }

    @Override
    public String toString() {
        return tk_name;
    }

    private String tk_cd;

    public String getTk_degree_tpId() {
        return tk_degree_tpId;
    }

    public void setTk_degree_tpId(String tk_degree_tpId) {
        this.tk_degree_tpId = tk_degree_tpId;
        Id = tk_degree_tpId;
    }

    public String getTk_name() {
        return tk_name;
    }

    public void setTk_name(String tk_name) {
        this.tk_name = tk_name;
        Name = tk_name;
    }

    public String getTk_cd() {
        return tk_cd;
    }

    public void setTk_cd(String tk_cd) {
        this.tk_cd = tk_cd;
    }
}
