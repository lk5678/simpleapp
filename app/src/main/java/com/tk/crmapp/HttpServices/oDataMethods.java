package com.tk.crmapp.HttpServices;

import com.google.gson.JsonElement;
import com.tk.crmapp.models.BusinessUnit;
import com.tk.crmapp.models.Customer;
import com.tk.crmapp.models.DotNetJson;
import com.tk.crmapp.models.country_tp;
import com.tk.crmapp.models.tk_degree_tp;
import com.tk.crmapp.models.tk_national;
import com.tk.crmapp.models.tk_prefer;
import com.tk.crmapp.progress.ProgressObserver;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Body;

public class oDataMethods {
    /**
     * 封装线程管理和订阅的过程
     */
    public static void oDataApiSubscribe(Observable observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void retrieveAccounts(ProgressObserver<List<Customer>> observer, String selfiled, String filter, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveAccounts(selfiled, filter), observer);
    }

    public static void retrieveAccounts1(ProgressObserver<List<Customer>> observer, String querystr, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveAccounts1(querystr), observer);
    }

    public static void retrieveAccountbyID(ProgressObserver<List<Customer>> observer, String guid, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveAccountbyID(guid), observer);
    }

    public static void retrieveCountry_tp(ProgressObserver<List<country_tp>> observer, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveConutry_tp(), observer);
    }


    public static void retrievDegree_tp(ProgressObserver<List<tk_degree_tp>> observer,  String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveDegree_tp(), observer);
    }

    public static void retrievPrefer(ProgressObserver<List<tk_prefer>> observer, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrievePrefer(), observer);
    }
    public static void retrievBusinessUnit(ProgressObserver<List<BusinessUnit>> observer, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrieveBusinessUnit(), observer);
    }

    public static void retrievtk_national(ProgressObserver<List<tk_national>> observer, String endpoint, String token) {
        oDataStrategy ostrategy = new oDataStrategy(endpoint, token);

        oDataApiSubscribe(ostrategy.getoDataService().retrievtk_national(), observer);
    }

    public static void   createAccount(ProgressObserver<Customer> observer, String  jsonbody, String endpoint, String token)
    {
        oDataPostStrategy ostrategy = new oDataPostStrategy(endpoint, token);
        RequestBody  body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonbody);
        oDataApiSubscribe(ostrategy.getoDataService().createAccount(body),observer);

    }
}