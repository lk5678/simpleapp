package com.tk.crmapp.UI;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tk.crmapp.HttpServices.ObServerOnErrorListener;
import com.tk.crmapp.HttpServices.ObserverOnNextListener;
import com.tk.crmapp.HttpServices.oDataMethods;
import com.tk.crmapp.SimpleApp;
import com.tk.crmapp.databinding.*;
import com.tk.crmapp.R;
import com.tk.crmapp.models.Customer;
import com.tk.crmapp.progress.ProgressObserver;

import java.util.List;

import retrofit2.adapter.rxjava2.HttpException;

public class CustomerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.customer_detail);
        Customer customer = (Customer)getIntent().getSerializableExtra("customer");
        getCustomerDetail(customer.getAccountId());

    }
    private void showCustomerDetail(Customer customer)
    {
        CustomerDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.customer_detail);
        binding.setCustomer(customer);
    }
    private void getCustomerDetail(String customerid)
    {
        SimpleApp app = (SimpleApp)getApplication();
        ObserverOnNextListener<Customer> nextListener = new ObserverOnNextListener<Customer>() {
            @Override
            public void onNext(Customer customer) {


               showCustomerDetail(customer);
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
               /* if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
                {
                    HttpException e1 = (HttpException)e;
                    if(e1.code()==503)
                    {
                        Snackbar
                                .make(coordinatorLayout, "server internal error:" +e1.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                    if(e1.code()==404)
                    {
                        Snackbar
                                .make(coordinatorLayout, "server not find:" +e1.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                    else
                    {
                        Snackbar
                                .make(coordinatorLayout, "other network error:" +e1.getLocalizedMessage(), Snackbar.LENGTH_LONG)
                                .show();
                    }
                    finish();
                }
                else {

                    if (e instanceof com.alibaba.fastjson.JSONException) { //這個異常是轉向了登錄界面，造成不能解析
                        Intent setup = new Intent(ImageListActivity.this, SetupActivity.class);
                        startActivityForResult(setup, SETUP_ID);
                    }
                }*/
                Log.e("ERROR",e.getMessage());
            }
        };
        String sguid ="(guid\'"+customerid+"\')";
         //       https://crm.tk.com/XRMServices/2011/OrganizationData.svc/AccountSet(guid'1126dc13-8470-e911-80c9-08002710246b'
        //    @GET("/XRMServices/2011/OrganizationData.svc/AccountSet{id}")
        oDataMethods.retrieveAccountbyID(new ProgressObserver<List<Customer>>(this, nextListener,errorListener),sguid,app.getCrmconfig().getCRMEndpoint(),app.getCrmconfig().getTOKEN() );

    }
    public void OnClickCloseButton(View view) {


           finish();

    }
}
