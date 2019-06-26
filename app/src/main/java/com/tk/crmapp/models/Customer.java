package com.tk.crmapp.models;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class Customer implements Serializable {



    public void Customer()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getTk_address() {
        return tk_address;
    }

    public void setTk_address(String tk_address) {
        this.tk_address = tk_address;
    }

    public country_tp getTk_citizenship_tp() {
        return tk_citizenship_tp;
    }

    public void setTk_citizenship_tp(country_tp tk_citizenship_tp) {

            this.tk_citizenship_tp = tk_citizenship_tp;;
    }

    public String getTk_x_risk_value_01() {
        return tk_x_risk_value_01;
    }

    public void setTk_x_risk_value_01(String tk_x_risk_value_01) {
        if(tk_x_risk_value_01.indexOf("{\"")>=0)  //json
        {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_x_risk_value_01);

            this.tk_x_risk_value_01 = jsonObject.getString("Value");
        }
        else
             this.tk_x_risk_value_01 = tk_x_risk_value_01;
    }

    public tk_degree_tp getTk_degree_tp() {
        return tk_degree_tp;
    }

    public void setTk_degree_tp(tk_degree_tp tk_degree_tp) {

            this.tk_degree_tp = tk_degree_tp;
    }

    public tk_gender_tp getTk_gender_tp() {
        return tk_gender_tp;
    }

    public void setTk_gender_tp(tk_gender_tp tk_gender_tp) {

          this.tk_gender_tp = tk_gender_tp;;
    }

    public String getTk_ahph() {
        return tk_ahph;
    }

    public void setTk_ahph(String tk_ahph) {
        this.tk_ahph = tk_ahph;
    }

    public String getTk_bxph() {
        return tk_bxph;
    }

    public void setTk_bxph(String tk_bxph) {
        this.tk_bxph = tk_bxph;
    }

    public String getTk_tzph() {
        return tk_tzph;
    }

    public void setTk_tzph(String tk_tzph) {
        this.tk_tzph = tk_tzph;
    }

    public String getTk_sales_channel() {
        return tk_sales_channel;
    }

    public void setTk_sales_channel(String tk_sales_channel) {
        this.tk_sales_channel = tk_sales_channel;
    }

    public String getTk_prefer() {
        return tk_prefer;
    }

    public void setTk_prefer(String tk_prefer) {
        if(tk_prefer.indexOf("{\"")>=0) {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_prefer);
            this.tk_prefer = jsonObject.getString("Name");
        }
        else
            this.tk_prefer = tk_prefer;
    }

    public String getTk_income() {
        return tk_income;
    }

    public void setTk_income(String tk_income) {
        if(tk_income.indexOf("{\"")>=0) {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_income);
            this.tk_income = jsonObject.getString("Value");
        }
        else
            this.tk_income = tk_income;;
    }

    public String getTk_org_tp() {
        return tk_org_tp;
    }

    public void setTk_org_tp(String tk_org_tp) {
        if(tk_org_tp.indexOf("{\"")>=0) {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_org_tp);
            this.tk_org_tp = jsonObject.getString("Name");
        }
        else
            this.tk_org_tp = tk_org_tp;
    }

    public String getTk_personal_national() {
        return tk_personal_national;
    }

    public void setTk_personal_national(String tk_personal_national) {
        if(tk_personal_national.indexOf("{\"")>=0) {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_personal_national);
            this.tk_personal_national = jsonObject.getString("Name");
        }
        else
            this.tk_personal_national = tk_personal_national;
    }

    public String getTk_x_occupation_tp() {
        return tk_x_occupation_tp;
    }

    public void setTk_x_occupation_tp(String tk_x_occupation_tp) {
        if(tk_x_occupation_tp.indexOf("{\"")>=0) {
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(tk_x_occupation_tp);
            this.tk_x_occupation_tp = jsonObject.getString("Name");
        }
        else
            this.tk_x_occupation_tp = tk_x_occupation_tp;
    }

    private country_tp tk_citizenship_tp;
    private tk_degree_tp tk_degree_tp;
    private tk_gender_tp tk_gender_tp;  //性别

    private String Name;
    private String AccountId;
    private String tk_address;


    private String tk_x_risk_value_01;
    private String tk_ahph;//愛好偏好
    private String tk_bxph;//保險偏好
    private String tk_tzph;//投資偏好
    private String tk_sales_channel;//銷售渠道
    private String tk_prefer;//服务偏好
    private String tk_income;//收入
    private String tk_org_tp;//所有部門
    private String tk_personal_national;
    private String tk_x_occupation_tp;//職業
}
