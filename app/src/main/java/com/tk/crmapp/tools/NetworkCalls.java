package com.tk.crmapp.tools;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Jeremy Shore on 8/1/14.
 */
public class NetworkCalls
{
    public static Call<String>   getAuthority(String endpoint) {

        OkHttpClient mHttpClient =new OkHttpClient().newBuilder()
                .connectTimeout(600,TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置http
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .build();


        Retrofit retrofhttit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(StringConverterFactory.create())
                .client(mHttpClient)
                .build();
        /*RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)

                .build();*/

        IServices services = retrofhttit.create(IServices.class);
        Call<String> httpscallback = services.getAuthority();
        return httpscallback;
    }

    public static String getAuthoritysync(String endpoint) throws IOException {
        OkHttpClient mHttpClient =new OkHttpClient().newBuilder()
                .connectTimeout(300,TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置http
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .build();


        Retrofit retrofhttit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(StringConverterFactory.create())
                .client(mHttpClient)
                .build();
        IServices services = retrofhttit.create(IServices.class);
        Call<String> httpscallback = services.getAuthority();
        Response<String> response = httpscallback.execute();
        Headers headers = response.headers();
        String Authenticate = "";
        if(headers!=null)
        {
            for(int i=0;i<headers.size();i++) {

                if (headers.name(i).equals("WWW-Authenticate")) {

                    Authenticate =  headers.value(i);


                    break;
                }
            }
        }
        return Authenticate;
    }
}
