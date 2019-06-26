package com.tk.crmapp.UI;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;

import com.tk.crmapp.HttpServices.ObServerOnErrorListener;
import com.tk.crmapp.HttpServices.ObserverOnNextListener;
import com.tk.crmapp.HttpServices.oDataMethods;
import com.tk.crmapp.R;
import com.tk.crmapp.SimpleApp;
import com.tk.crmapp.UI.adapter.ImageListAdapter;
import com.tk.crmapp.models.Customer;
import com.tk.crmapp.progress.ProgressObserver;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.adapter.rxjava2.HttpException;

public class CustomerListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    private List<Customer> customerList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private static final int SETUP_ID = 0;
    private SimpleApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview);
        Toolbar toolbar = (Toolbar)findViewById(R.id.customertoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        Intent intent = getIntent();
        app = (SimpleApp)getApplication();
        String crmendpoint = app.getCrmconfig().getCRMEndpoint();
        String token = app.getCrmconfig().getTOKEN();
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_container);
        GetCustomer(crmendpoint,token);

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_add_customer:
                    Intent intent  = new Intent(CustomerListActivity.this,CustomerEditActivity.class);

                    startActivity(intent);
                    break;

            }



            return true;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        MenuItem menuItem=menu.findItem(R.id.action_search_customer);//
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menuItem);//加载searchview
        searchView.setOnQueryTextListener(this);//为搜索框设置监听事件
        searchView.setSubmitButtonEnabled(true);//设置是否显示搜索按钮
        searchView.setQueryHint("查找");//设置提示信息
        searchView.setIconifiedByDefault(true);//设置搜索默认为图标
        return true;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
      return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        String crmendpoint = app.getCrmconfig().getCRMEndpoint();
        String token = app.getCrmconfig().getTOKEN();
        query=query.replaceAll("\n","");
        GetCustomer(crmendpoint,token,query);

        return true;
    }
    private void showList()
    {
        //创建适配器，在适配器中导入数据 1.当前类 2.list_view一行的布局 3.数据集合
        ImageListAdapter imageListAdapter = new ImageListAdapter(CustomerListActivity.this,R.layout.imageviewlist,customerList);
        ListView listView = (ListView)findViewById(R.id.ImageListView); //将适配器导入Listview
        listView.setAdapter(imageListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Customer customer = customerList.get(i);
                Intent intent  = new Intent(CustomerListActivity.this,CustomerDetailActivity.class);
                intent.putExtra("customer",customer);
                startActivity(intent);
               // Toast.makeText(ImageListActivity.this,customer.getAccountId()+"|"+customer.getName(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void GetCustomer(String CRMEndpoint,String  token){
        ObserverOnNextListener<List<Customer>> nextListener = new ObserverOnNextListener<List<Customer>>() {
            @Override
            public void onNext(List<Customer> list) {


                for (Customer customer : list) {
                    customerList .add(customer);
                }
                showList();
            }

        };
        ObServerOnErrorListener  errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
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
                        Intent setup = new Intent(CustomerListActivity.this, SetupActivity.class);
                        startActivityForResult(setup, SETUP_ID);
                    }
                }
                Log.e("ERROR",e.getMessage());
            }
        };
        String selfield="AccountId,Name,tk_address,tk_citizenship_tp,tk_degree_tp,tk_gender_tp,tk_x_risk_value_01";
        String filter ="";
        //"substringof('%E4%BA%8E',Name)";
        oDataMethods.retrieveAccounts(new ProgressObserver<List<Customer>>(this, nextListener,errorListener),selfield,filter,CRMEndpoint,token);


    }

    @TargetApi(Build.VERSION_CODES.O)
    public void GetCustomer(String CRMEndpoint, String  token, String customerName)
    {
        ObserverOnNextListener<List<Customer>> nextListener = new ObserverOnNextListener<List<Customer>>() {
            @Override
            public void onNext(List<Customer> list) {
                customerList.clear();

                for (Customer customer : list) {
                    customerList .add(customer);
                }
                showList();
            }

        };
        ObServerOnErrorListener  errorListener = new ObServerOnErrorListener()
        {
            @Override
            public void OnError(Throwable e)
            {
                if(e instanceof  retrofit2.adapter.rxjava2.HttpException) //網絡問題
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
                        Intent setup = new Intent(CustomerListActivity.this, SetupActivity.class);
                        startActivityForResult(setup, SETUP_ID);
                    }
                }
                Log.e("ERROR",e.getMessage());
            }
        };


        String filter = null;
        filter = "substringof(\'"+customerName+"\',Name)";
      /* try {
            filter = "substringof(\'" + android.util.Base64.encodeToString(customerName.getBytes("GB2312"), android.util.Base64.NO_WRAP) + "\',Name)";
        }
        catch (UnsupportedEncodingException e)
        {
            filter="";
        }*/
        String selfield="AccountId,Name,tk_address,tk_citizenship_tp,tk_degree_tp,tk_gender_tp,tk_x_risk_value_01";
        String base64str;
        filter = "substringof('%E5%B0%8F',Name)";
        try {

            base64str  = Base64.encodeToString(customerName.getBytes("UTF-8"), Base64.NO_WRAP);
        }
      catch (UnsupportedEncodingException e)
        {
            filter="";
        }
        filter = "substringof('"+customerName+"',Name)";
        //"substringof('%E4%BA%8E',Name)";
        oDataMethods.retrieveAccounts(new ProgressObserver<List<Customer>>(this, nextListener,errorListener),selfield,filter,CRMEndpoint,token);


    }


}
