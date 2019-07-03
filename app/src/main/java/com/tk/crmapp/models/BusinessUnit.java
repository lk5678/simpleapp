package com.tk.crmapp.models;

import java.io.Serializable;

//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/BusinessUnitSet?$select=BusinessUnitId,Name
public class BusinessUnit implements Serializable {
    private String Name;
    private String BusinessUnitId;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
        BusinessUnitId = Id;
    }

    @Override
    public String toString() {
        return Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBusinessUnitId() {
        return BusinessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        BusinessUnitId = businessUnitId;
        Id = businessUnitId;
    }
}
