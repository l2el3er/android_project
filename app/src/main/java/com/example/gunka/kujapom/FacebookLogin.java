package com.example.gunka.kujapom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class FacebookLogin extends AppCompatActivity {


    CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView info;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.facebook_login);

        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        imageView = (ImageView) findViewById(R.id.profile);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("facebookLogin", "well done3 !");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,GraphResponse response) {
                                try {
                                    String id = object.getString("id");
                                    String url = "http://graph.facebook.com/" + id + "/picture";
                                    info.setText("สวัสดี, " + object.getString("name"));
                                    Glide.with(getApplicationContext()).load(url).placeholder(R.drawable.unloadimage).into(imageView);
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("facebookLogin", "well done4 !");
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("facebookLogin", "well done5 !");
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("fb", "well done6 !");
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
