package com.tk.crmapp.tools;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by JeremyShore on 3/12/15.
 */
interface IServices {

    @Headers({
            "Accept: application/x-www-form-urlencoded",
            "Authorhtization: Bearer"
    })
    @GET("/XRMServices/2011/OrganizationData.svc/web?SdkClientVersion=6.1.0.533")
        //htt@GET("/")
    Call<String> getAuthority();

}
