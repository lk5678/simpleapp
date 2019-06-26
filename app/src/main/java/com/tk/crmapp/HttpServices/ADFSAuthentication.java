package com.tk.crmapp.HttpServices;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.microsoft.aad.adal.ADALError;
import com.microsoft.aad.adal.AuthenticationCallback;
import com.microsoft.aad.adal.AuthenticationContext;
import com.microsoft.aad.adal.AuthenticationException;
import com.microsoft.aad.adal.AuthenticationResult;
import com.microsoft.aad.adal.PromptBehavior;
import com.tk.crmapp.SimpleApp;
import com.tk.crmapp.UI.SetupActivity;
import com.tk.crmapp.UI.authUI.authBaseActivity;

import com.tk.crmapp.SimpleApp;

public   class ADFSAuthentication   {

    private String TAG = "ADFSAuthentication";
    private Boolean isauth = false;
    private String token="";
    private String refreshToken="";
    private String adfsurl = "";
    private String crmurl = "";
    private String clientid ="";
    private SharedPreferences sharedPreferences;
    private authBaseActivity activity = new authBaseActivity();
    private AuthenticationContext authContext;
    public ADFSAuthentication(authBaseActivity activity ,SharedPreferences sharedPreferences,String adslurl,String crmurl,String clientid)
    {
        this.sharedPreferences  = sharedPreferences;
        this.adfsurl = adslurl;
        this.crmurl  = crmurl;
        this.clientid = clientid;
        this.activity = activity;
    }
    public void login(String username)
    {
        String ADFSEndPoint = adfsurl ;
        if (!ADFSEndPoint.equals("")) {
            try {

                authContext = new AuthenticationContext(activity, ADFSEndPoint, false);

                // use the endpoint that the user entered in and use the username,
                // as the hint that is automatically populated into the azure library's webview
              //  String clientId = "595b53e1-b01b-4143-b6ed-fa83f0855bda";;
                String redirectUrl = "https://crm.tk.com";
                String resource = "https://crm.tk.com";

              /*  mAuthContext.acquireToken(
                        SetupActivity.this,
                        mEndpoint,
                        Constants.CLIENT_ID,
                        Constants.REDIRECT_URI,
                        mUsername,
                        this
                );*/
                //UserPasswordCredential credential = new UserPasswordCredential("administrator@tk.com", "Lk567812");

                authContext.acquireToken(
                        activity,
                        resource,
                        clientid,
                        redirectUrl,
                        username,
                        PromptBehavior.REFRESH_SESSION 	,"",
                        getAuthSilentCallback()
                );
                // mAuthContext.acquireToken(SetupActivity.this,resource,clientId,redirectUrl,"administrator@tk.com", callback:this);
            } catch (Exception ex) {
                ex.getCause().printStackTrace();
            }
        }

    }


    public void refreshToken()
    {
        String ADFSEndPoint = adfsurl ;
        if(authContext==null)
        {
            authContext = new AuthenticationContext(activity, ADFSEndPoint, false);
        }
        authContext.acquireTokenByRefreshToken(
                refreshToken,
                clientid, getAuthSilentCallback());
    }
   /* @Override
    public void onSuccess(AuthenticationResult result) {
        if(result.getStatus().name().equalsIgnoreCase("Succeeded")) {
            Log.d(TAG,"refresh token suc...");
            isauth = true;
            token = result.getAccessToken();
            refreshToken = result.getRefreshToken();

            Log.e("MainActivity", token + "");


            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (result.getExpiresOn() != null) {
                editor.putLong(CRMConfigModule.EXPIRES_ON, result.getExpiresOn().getTime());
                editor.putString(CRMConfigModule.TOKEN, token);
                editor.putString(CRMConfigModule.REFRESH_TOKEN, refreshToken);
                editor.apply();


            }
            authBaseActivity activity1 =activity;
            activity.OnAuthSucc();
        }


    }

    @Override
    public void onError(Exception exc) {
        activity.OnAuthFail();
    }*/

    private AuthenticationCallback<AuthenticationResult> getAuthSilentCallback() {
        return new AuthenticationCallback<AuthenticationResult>() {
            @Override
            public void onSuccess(AuthenticationResult result) {
                if(result.getStatus().name().equalsIgnoreCase("Succeeded")) {
                    Log.d(TAG,"refresh token suc...");
                    isauth = true;
                    token = result.getAccessToken();
                    refreshToken = result.getRefreshToken();

                    Log.e("MainActivity", token + "");


                    authBaseActivity activity1 =activity;
                    activity.OnAuthSucc();
                }
            }

            @Override
            public void onError(Exception exception) {

                Log.e(TAG, "Authentication failed: " + exception.toString());
                if (exception instanceof AuthenticationException) {
                    AuthenticationException authException = ((AuthenticationException) exception);
                    ADALError error = authException.getCode();

                 /*   if (error == ADALError.AUTH_REFRESH_FAILED_PROMPT_NOT_ALLOWED ) {
                        mAcquireTokenHandler.sendEmptyMessage(MSG_INTERACTIVE_SIGN_IN_PROMPT_AUTO);
                    }else if(error == ADALError.NO_NETWORK_CONNECTION_POWER_OPTIMIZATION){

                        Log.e(TAG, "Device is in doze mode or the app is in standby mode");
                    }
                    return;
                }
                /* Attempt an interactive on any other exception */
                    //  mAcquireTokenHandler.sendEmptyMessage(MSG_INTERACTIVE_SIGN_IN_PROMPT_AUTO)
                }
            }
        };
    }
}
