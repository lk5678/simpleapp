package com.tk.crmapp.HttpServices;



import com.tk.crmapp.models.BusinessUnit;
import com.tk.crmapp.models.Customer;
import com.tk.crmapp.models.DotNetJson;
import com.tk.crmapp.models.country_tp;
import com.tk.crmapp.models.tk_degree_tp;
import com.tk.crmapp.models.tk_national;
import com.tk.crmapp.models.tk_prefer;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;



public interface oDataService {


    @GET("/XRMServices/2011/OrganizationData.svc/{logicalName}")
    Observable<Response<DotNetJson>> retrieveMultipleList(@Path("logicalName") String logicalName,
                                                          @QueryMap Map<String, String> query);

      @GET("/XRMServices/2011/OrganizationData.svc/AccountSet")
    Observable<List<Customer>> retrieveAccounts(@Query("$select")String selField, @Query(value = "$filter") String filter);

    @GET("/XRMServices/2011/OrganizationData.svc/{accountquerystr}")
    Observable<List<Customer>> retrieveAccounts1(@Path(value = "accountquerystr",encoded = true) String querystr);

    @GET("/XRMServices/2011/OrganizationData.svc/AccountSet{id}")
    Observable<Customer> retrieveAccountbyID(@Path("id") String id );

    @GET("/XRMServices/2011/OrganizationData.svc/tk_country_tpSet?$select=tk_name,tk_country_tpId,tk_cd")
    Observable<List<country_tp>> retrieveConutry_tp();

    @GET("/XRMServices/2011/OrganizationData.svc/tk_degree_tpSet?$select=tk_name,tk_degree_tpId,tk_cd")
    Observable<List<tk_degree_tp>> retrieveDegree_tp();
    @GET("/XRMServices/2011/OrganizationData.svc/tk_preferSet?$select=tk_cd,tk_name,tk_preferId")
    Observable<List<tk_prefer>> retrievePrefer();

    @GET("/XRMServices/2011/OrganizationData.svc/BusinessUnitSet?$select=BusinessUnitId,Name")
    Observable<List<BusinessUnit>> retrieveBusinessUnit();
    @GET("/XRMServices/2011/OrganizationData.svc/tk_nationalSet?$select=tk_code,tk_name,tk_nationalId")
    Observable<List<tk_national>> retrievtk_national();
}
