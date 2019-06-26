package com.tk.crmapp.models;
//https://crm.tk.com/XRMServices/2011/OrganizationData.svc/BusinessUnitSet?$select=BusinessUnitId,Name
public class BusinessUnit {
    private String Name;
    private String BusinessUnitId;

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
    }
}
