package com.example.gunka.kujapom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
        Glide.with(getApplicationContext()).load(sharedPreferences.getString("url_picture", "")).placeholder(R.drawable.unloadimage).into(imageView);
        info.setText(sharedPreferences.getString("username", ""));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object,GraphResponse response) {
                            try {

                                String id = object.getString("id");
                                String url = "http://graph.facebook.com/" + id + "/picture";

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", object.getString("name"));
                                editor.putString("url_picture", url);

                                info.setText("สวัสดี, " + object.getString("name"));
                                Glide.with(getApplicationContext()).load(url).placeholder(R.drawable.unloadimage).into(imageView);

                                editor.commit();
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
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
