package com.tk.crmapp.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.tk.crmapp.HttpServices.ObserverOnNextListener;
import com.tk.crmapp.HttpServices.oDataMethods;
import com.tk.crmapp.R;
import com.tk.crmapp.SimpleApp;


import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import com.tk.crmapp.models.*;
import com.tk.crmapp.progress.ProgressObserver;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CoordinatorLayout coordinatorLayout;
    private static final int SETUP_ID = 0;
    private static final int IMAGELIST_ID=1;
    private static boolean isauth = false;

    private AuthenticationContext authContext;
    private SimpleApp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app =(SimpleApp) this.getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_container);
    }

    private void refreshStorageData() {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"OnStar...");
        if(!isauth) {
            Log.d(TAG,"startAuthentication...");
            startAuthentication();
        }
    }

    public void startAuthentication() {


        try
        {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {

                startSignIn();


            }

        } catch(Exception ex) {
            Log.e("MainActivity", ex.getCause().getMessage());
            startSignIn();
        }


    }










    private void startSignIn()
    {
        Log.d(TAG,"goto signin");
        Intent setup = new Intent(MainActivity.this, SetupActivity.class);
        startActivityForResult(setup, SETUP_ID);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SETUP_ID:

                if (resultCode == RESULT_OK) {
                    isauth = true;

                    ;
                }
                break;
            default:
                if (authContext != null) {
                    authContext.onActivityResult(requestCode, resultCode, data);
                }
                break;

        }

    }
    public void OnClickCustomerButton(View view) {
        if (isauth) {

            Intent intent = new Intent(MainActivity.this, CustomerListActivity.class);

            startActivityForResult(intent, IMAGELIST_ID);
        }
    }
}
