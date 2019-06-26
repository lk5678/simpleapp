package com.tk.crmapp.UI;

import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.tk.crmapp.R;
import com.tk.crmapp.SimpleApp;
import com.tk.crmapp.databinding.ActivitySetupBinding;
import com.tk.crmapp.models.CRMConfigBean;

import com.tk.crmapp.tools.NetworkCalls;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;


import okhttp3.Headers;

public class SetupActivity  extends Activity implements View.OnClickListener ,AuthenticationCallback<AuthenticationResult>
{
    private static final String REDIRECT_URI = "https://crm.tk.com";
    static final String CLIENT_ID = "595b53e1-b01b-4143-b6ed-fa83f0855bda";

    private SimpleApp app;
    private ActivitySetupBinding binding;
    private AuthenticationContext authContext;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (SimpleApp)getApplication();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setup);




        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        binding.editCrfendpoint.setText(app.getCrmconfig().getCRMEndpoint());
        binding.editUsername.setText(app.getCrmconfig().getUsername());
        Button loginbutton = (Button)findViewById(R.id.login_button);
        loginbutton.setOnClickListener(this);
    }

    private boolean isConnected() {
        ConnectivityManager manager = (ConnectivityManager)this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    @Override

    public void onClick(View v) {
        int ItemId = v.getId();//获取组件的id值
        if(ItemId == R.id.login_button) {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if (!isConnected()) {
                  Snackbar.make(v, "network not connection!",Snackbar.LENGTH_LONG).show();
                return;
            }
            String CRMEndpoint = binding.editCrfendpoint.getText().toString();
            String username = binding.editUsername.getText().toString();
            if (CRMEndpoint.equals("")) {
                  Snackbar.make(v, "pls input crm url",   Snackbar.LENGTH_LONG).show();
                return;
            }

            if (!Patterns.WEB_URL.matcher(CRMEndpoint).matches()) {
                 Snackbar.make(v, "crm url is invalid",          Snackbar.LENGTH_LONG).show();
                return;
            }
            CRMConfigBean crmconfig = app.getCrmconfig();
            crmconfig.setCRMEndpoint(CRMEndpoint);
            crmconfig.setUsername(username);
            app.setCrmconfig(crmconfig);
            progressDialog.setMessage("Challenging Organization...");
            progressDialog.show();


            final Call<String> call = NetworkCalls.getAuthority(CRMEndpoint);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Boolean foundHeader = false;
                    Headers headers = response.headers();
                    if (headers != null) {
                        for (int i = 0; i < headers.size(); i++) {

                            if (headers.name(i).equals("WWW-Authenticate")) {
                                foundHeader = true;
                                String value = headers.value(i);
                                String adfsendpoint = value.substring(value.indexOf("https://"));
                                CRMConfigBean crmconfig = app.getCrmconfig();
                                crmconfig.setADFSEndpoint(adfsendpoint);
                                app.setCrmconfig(crmconfig);

                                login();
                                break;
                            }
                        }
                    }

                    if (!foundHeader) {
                        Toast.makeText(getApplicationContext(), "Unable to Challenge", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Response response = ((HttpException) t).response();
                    Boolean foundHeader = false;
                    Headers headers = response.headers();
                    if (headers != null) {
                        for (int i = 0; i < headers.size(); i++) {

                            if (headers.name(i).equals("WWW-Authenticate")) {
                                foundHeader = true;

                                String adfsendpoint = headers.value(i).substring( headers.value(i).indexOf("https://"));
                                CRMConfigBean crmconfig = app.getCrmconfig();
                                crmconfig.setADFSEndpoint(adfsendpoint);
                                app.setCrmconfig(crmconfig);
                                login();
                                break;
                            }
                        }
                    }

                    if (!foundHeader) {
                        Toast.makeText(getApplicationContext(), "Unable to Challenge", Toast.LENGTH_LONG).show();
                    }
                }

            });
        }

    }
    private void login()
    {

        try {
                CRMConfigBean crmconfig = app.getCrmconfig();
                authContext = new AuthenticationContext(SetupActivity.this, crmconfig.ADFSEndpoint, false);
                progressDialog.show();




                authContext.acquireToken(
                        SetupActivity.this,
                        crmconfig.getCRMEndpoint(),
                        crmconfig.getClientid(),
                        crmconfig.getCRMEndpoint(),
                        crmconfig.getUsername(),
                        PromptBehavior.Always	,"",
                        this
                    );
                // mAuthContext.acquireToken(SetupActivity.this,resource,clientId,redirectUrl,"administrator@tk.com", callback:this);
            } catch (Exception ex) {
                ex.getCause().printStackTrace();
            }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (authContext != null) {
            if (resultCode != 2001) {
                authContext.onActivityResult(requestCode, resultCode, data);
            }
            else {
                final CookieManager cookieManager = CookieManager.getInstance();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    CookieSyncManager.createInstance(this);
                    cookieManager.removeAllCookie();
                    CookieSyncManager.createInstance(this).sync();

                    authContext.getCache().removeAll();
                }
                else {
                    cookieManager.removeAllCookies(value -> authContext.getCache().removeAll());
                }
            }
        }
    }

    /**
     * If the login is successful, save the current token into the property created for the application,
     * store the username and endpoint into application shared storage, and return to the main activity
     * @param result returns <T> the result of the authentication
     */
    @Override
    public void onSuccess(AuthenticationResult result) {

        CRMConfigBean crmconfig = app.getCrmconfig();
        crmconfig.setTOKEN(result.getAccessToken());
        crmconfig.setREFRESH_TOKEN(result.getRefreshToken());
        crmconfig.setEXPIRES_ON(result.getExpiresOn().getTime());
        app.setCrmconfig(crmconfig);
        Log.i("SignInActivity","token:"+result.getAccessToken());
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra("result", "Authentication suc");
        //设置返回数据
        setResult(RESULT_OK, intent);
        this.finish();
    }

    @Override
    public void onError(Exception ex) {
        Log.e("SignInActivity", ex.getCause().getMessage()+"");
    }
    @Override
    protected void onDestroy() {
        if( progressDialog!=null)
            progressDialog.dismiss();
        super.onDestroy();
    }

}
