package com.tk.crmapp.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.tk.crmapp.HttpServices.ObServerOnErrorListener;
import com.tk.crmapp.HttpServices.ObserverOnNextListener;
import com.tk.crmapp.HttpServices.oDataMethods;
import com.tk.crmapp.R;
import com.tk.crmapp.SimpleApp;
import com.tk.crmapp.databinding.CustomerDetailBinding;
import com.tk.crmapp.databinding.CustomerEditBinding;
import com.tk.crmapp.models.BusinessUnit;
import com.tk.crmapp.models.Customer;
import com.tk.crmapp.models.country_tp;
import com.tk.crmapp.models.tk_degree_tp;
import com.tk.crmapp.models.tk_national;
import com.tk.crmapp.models.tk_prefer;
import com.tk.crmapp.progress.ProgressObserver;

import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava2.HttpException;

public class CustomerEditActivity extends AppCompatActivity {

    private List<country_tp> country_tpList;
    private List<tk_prefer> prefer_tpList;
    private List<tk_degree_tp> degree_tpList;
    private List<String> sex_tpList;
    private List<BusinessUnit> businessUnitList;
    private List<tk_national>  tk_nationalList;
    private SimpleApp m_pApp;
    private Spinner tk_sex_spinner, tk_country_spinner,tk_degree_spinner,tk_prefer_spinner,businessUnit_Spinner,tk_national_Spinner;
    private  Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_edit);
       // Toolbar toolbar = (Toolbar)findViewById(R.id.customereditbar);
      //  setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayShowTitleEnabled(false);
        bindSexSpinner();
        m_pApp = (SimpleApp)getApplication();
        country_tpList = new ArrayList<country_tp>();
        getCountry_tpList(m_pApp.getCrmconfig().CRMEndpoint,m_pApp.getCrmconfig().getTOKEN());
        degree_tpList = new ArrayList<tk_degree_tp>();
        getDegree_tpList(m_pApp.getCrmconfig().CRMEndpoint,m_pApp.getCrmconfig().getTOKEN());
        prefer_tpList = new ArrayList<tk_prefer>();
        getPrefer_tpList(m_pApp.getCrmconfig().CRMEndpoint,m_pApp.getCrmconfig().getTOKEN());
        businessUnitList = new ArrayList<BusinessUnit>();
        getbussionUnitList(m_pApp.getCrmconfig().CRMEndpoint,m_pApp.getCrmconfig().getTOKEN());
        tk_nationalList = new ArrayList<tk_national>();
        gettk_nationalList(m_pApp.getCrmconfig().CRMEndpoint,m_pApp.getCrmconfig().getTOKEN());
        customer  = new Customer();


        customer.setName("崔雪莉");
        CustomerEditBinding binding = DataBindingUtil.setContentView(this,R.layout.customer_edit);
        binding.setCustomer(customer);

    }

    private void bindSexSpinner()
    {
        sex_tpList = new ArrayList<String>();
        sex_tpList.add("男");
        sex_tpList.add("女");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                 CustomerEditActivity.this, android.R.layout.simple_list_item_1,sex_tpList);
        tk_sex_spinner = (Spinner)findViewById(R.id.spin_tk_sex_tp);
        tk_sex_spinner.setAdapter(adapter);


    }

    public void  bindCountrySpinner()
    {

        ArrayAdapter<country_tp> adapter = new ArrayAdapter<country_tp>(
                CustomerEditActivity.this, android.R.layout.simple_list_item_1,country_tpList);
        tk_country_spinner = (Spinner)findViewById(R.id.spin_tk_country_tp);
        tk_country_spinner.setAdapter(adapter);


    }

    public void  bindDegreeSpinner()
    {

        ArrayAdapter<tk_degree_tp> adapter = new ArrayAdapter<tk_degree_tp>(
                CustomerEditActivity.this, android.R.layout.simple_list_item_1,degree_tpList);
        tk_degree_spinner = (Spinner)findViewById(R.id.spin_tk_degree_tp);
        tk_degree_spinner.setAdapter(adapter);


    }

    public void  bindPreferSpinner()
    {

        ArrayAdapter<tk_prefer> adapter = new ArrayAdapter<tk_prefer>(
                CustomerEditActivity.this, android.R.layout.simple_list_item_1,prefer_tpList);
        tk_prefer_spinner = (Spinner)findViewById(R.id.spin_tk_prefer);
        tk_prefer_spinner.setAdapter(adapter);



    }

    public void  bindBusinessUnitSpinner()
    {

        ArrayAdapter<BusinessUnit> adapter = new ArrayAdapter<BusinessUnit>(
                CustomerEditActivity.this, android.R.layout.simple_list_item_1,businessUnitList);
        businessUnit_Spinner = (Spinner)findViewById(R.id.spin_businessUnit);
        businessUnit_Spinner.setAdapter(adapter);



    }

    public void  bindtk_nationalSpinner()
    {

        ArrayAdapter<tk_national> adapter = new ArrayAdapter<tk_national>(
                CustomerEditActivity.this, android.R.layout.simple_list_item_1,tk_nationalList);
        tk_national_Spinner = (Spinner)findViewById(R.id.spin_tk_national);
        tk_national_Spinner.setAdapter(adapter);



    }
    @Override
    protected  void onStart()
    {
        super.onStart();

    }
    private  boolean getCountry_tpList(String CRMEndpoint,String  token)
    {

        boolean bret = false;
        ObserverOnNextListener<List<country_tp>> nextListener = new ObserverOnNextListener<List<country_tp>>() {
            @Override
            public void onNext(List<country_tp> list) {
                country_tpList.clear();
                for(country_tp cp: list)
                    country_tpList.add(cp);
                bindCountrySpinner();
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {

                Log.e("ERROR",e.getMessage());

            }
        };

        oDataMethods.retrieveCountry_tp(new ProgressObserver<List<country_tp>>(this, nextListener,errorListener),CRMEndpoint,token);

        return bret;
    }

    private  boolean getDegree_tpList(String CRMEndpoint,String  token)
    {

        boolean bret = false;
        ObserverOnNextListener<List<tk_degree_tp>> nextListener = new ObserverOnNextListener<List<tk_degree_tp>>() {
            @Override
            public void onNext(List<tk_degree_tp> list) {
               degree_tpList.clear();
                for(tk_degree_tp cp: list)
                    degree_tpList.add(cp);
                bindDegreeSpinner();
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
                {
                   }
                Log.e("ERROR",e.getMessage());

            }
        };


        oDataMethods.retrievDegree_tp(new ProgressObserver<List<tk_degree_tp>>(this, nextListener,errorListener),CRMEndpoint,token);

        return bret;
    }
    private  boolean getPrefer_tpList(String CRMEndpoint,String  token)
    {

        boolean bret = false;
        ObserverOnNextListener<List<tk_prefer>> nextListener = new ObserverOnNextListener<List<tk_prefer>>() {
            @Override
            public void onNext(List<tk_prefer> list) {
                prefer_tpList.clear();
                for(tk_prefer cp: list)
                    prefer_tpList.add(cp);
                bindPreferSpinner();
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
                {
                }
                Log.e("ERROR",e.getMessage());

            }
        };


        oDataMethods.retrievPrefer(new ProgressObserver<List<tk_prefer>>(this, nextListener,errorListener),CRMEndpoint,token);

        return bret;
    }

    private  boolean getbussionUnitList(String CRMEndpoint,String  token)
    {

        boolean bret = false;
        ObserverOnNextListener<List<BusinessUnit>> nextListener = new ObserverOnNextListener<List<BusinessUnit>>() {
            @Override
            public void onNext(List<BusinessUnit> list) {
                businessUnitList.clear();
                for(BusinessUnit cp: list)
                    businessUnitList.add(cp);
               bindBusinessUnitSpinner();
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
                {
                }
                Log.e("ERROR",e.getMessage());

            }
        };


        oDataMethods.retrievBusinessUnit(new ProgressObserver<List<BusinessUnit>>(this, nextListener,errorListener),CRMEndpoint,token);

        return bret;
    }

    private  boolean gettk_nationalList(String CRMEndpoint,String  token)
    {

        boolean bret = false;
        ObserverOnNextListener<List<tk_national>> nextListener = new ObserverOnNextListener<List<tk_national>>() {
            @Override
            public void onNext(List<tk_national> list) {
                tk_nationalList.clear();
                for(tk_national cp: list)
                    tk_nationalList.add(cp);
                bindtk_nationalSpinner();
            }

        };
        ObServerOnErrorListener errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
                {
                }
                Log.e("ERROR",e.getMessage());

            }
        };


        oDataMethods.retrievtk_national(new ProgressObserver<List<tk_national>>(this, nextListener,errorListener),CRMEndpoint,token);

        return bret;
    }
    public void OnClickBackButton(View view) {


        AlertDialog myAlertDialog =new AlertDialog.Builder(CustomerEditActivity.this).setTitle("退出")
                .setMessage("确定要退出？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                       finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener(){
                    @Override public void onClick(DialogInterface dialog, int which)
                    {//取消按钮的响应事件
                    }
                })
                .show();//显示此对话框


    }
    public void OnClickSaveButton(View view) {


       String sex_tp = this.tk_sex_spinner.getSelectedItem().toString();
       tk_degree_tp dtp = (tk_degree_tp)this.tk_degree_spinner.getSelectedItem();
       country_tp ctp = (country_tp)tk_country_spinner.getSelectedItem();
       if(customer.getName().equalsIgnoreCase(""))
       {
           AlertDialog myAlertDialog =new AlertDialog.Builder(CustomerEditActivity.this).setTitle("错误")
                   .setMessage("客户姓名不能为空")
                   .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                       @Override
                       public void onClick(DialogInterface dialog, int which)
                       {
                            return;
                       }
                   })
                    .show();//显示此对话框
       }
    }
}
