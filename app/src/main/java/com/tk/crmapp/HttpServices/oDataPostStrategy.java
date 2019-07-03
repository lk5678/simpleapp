package com.tk.crmapp.HttpServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tk.crmapp.tools.CRMoDataV7ConverterFactory;
import com.tk.crmapp.tools.SSLSocketClient;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class oDataPostStrategy {
    private String endpoint;
    private String sessionToken;

    public oDataPostStrategy(@NonNull String endpoint, @NonNull String sessionToken) {
        this.endpoint = endpoint;
        this.sessionToken = sessionToken;
    }
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    public  oDataService odataservice;
    public  oDataService getoDataService()
    {
        if(odataservice==null)
        {
            synchronized (oDataPostStrategy.class) {
                if (odataservice == null) {
                    init();
                }
            }
        }
        return odataservice;
    }
    private void init() {
     //   TokenInterceptor tokenInterceptor = new TokenInterceptor();
     //   tokenInterceptor.initParams();
        Interceptor  authinterceptor  = initAuthInterceptor(this.sessionToken);
        OkHttpClient oclient =  new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())

                .addInterceptor(authinterceptor)


                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(oclient)
                .baseUrl(endpoint.endsWith("/") ? endpoint : endpoint + "/")
                .addConverterFactory(CRMoDataV7ConverterFactory.create())
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        odataservice = retrofit.create(oDataService.class);

    }

    private  Interceptor initAuthInterceptor(String sessionToken) {
            return chain -> {
                Request request = chain.request();
                Log.i("Interceptor","token:"+sessionToken);
                Log.i("Interceptor","url:"+request.url());
                Request.Builder builder = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type","application/json; charset=utf-8")
                        //.addHeader("OData-MaxVersion", "4.0")
                       // .addHeader("OData-Version", "4.0")
                        .addHeader("User-Agent", "Microsoft Android Sample")
                        //.addHeader("Prefer", "odata.include-annotations=\"*\"")
                       // .addHeader("Cache-Control","no-cache")
                       // .addHeader("accept-encoding","gzip, deflate")
                        .addHeader("Authorization", "Bearer " + sessionToken.replaceAll("(\\r|\\n)", ""));

                return chain.proceed(builder.build());
            };
        }
}
