package com.tk.crmapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import com.tk.crmapp.models.CRMConfigBean;



public class SimpleApp extends Application {


    private CRMConfigBean crmconfig;

    public CRMConfigBean getCrmconfig() {
        return crmconfig;
    }

    public void setCrmconfig(CRMConfigBean crmconfig) {
        this.crmconfig = crmconfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        crmconfig  = new CRMConfigBean();



    }



}
