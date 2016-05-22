package com.example.gunka.kujapom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class InsertFood extends AppCompatActivity {

    private EditText EdtName;
    private EditText EdtCal;
    private Button submit;
    private SharedPreferences sharedPreferences;
    private Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food);
        EdtName = (EditText) findViewById(R.id.EdtName);
        EdtCal = (EditText) findViewById(R.id.EdtCal);
        type = (Spinner) findViewById(R.id.type);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(String.valueOf(EdtName.length())) == 0 || Integer.parseInt(String.valueOf(EdtCal.length())) == 0) {
                    Log.i("insertExercise", "1");
                    Toast.makeText(getApplicationContext(), "กรุณากรอกให้ครบ", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Log.i("insertExercise", "2 : " + String.valueOf(type.getSelectedItem()));
                    String Menu_type = "5";
                    if (new String(String.valueOf(type.getSelectedItem())).equals("อาหารจานเดียว")) {
                        Menu_type = "1";
                    } else if (new String(String.valueOf(type.getSelectedItem())).equals("ของหวาน")) {
                        Menu_type = "2";
                    } else if (new String(String.valueOf(type.getSelectedItem())).equals("ผลไม้")) {
                        Menu_type = "3";
                    } else if (new String(String.valueOf(type.getSelectedItem())).equals("เครื่องดื่ม")) {
                        Menu_type = "4";
                    }
                    new TheTask().execute("http://10.16.68.253/kcal.php", EdtName.getText().toString(), Menu_type, EdtCal.getText().toString());

                }
            }
        });
    }

    class TheTask extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                Log.i("insertExercise", "3");
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost(params[0]);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);

                nameValuePairs.add(new BasicNameValuePair("type", "insert"));
                nameValuePairs.add(new BasicNameValuePair("name", params[1]));
                nameValuePairs.add(new BasicNameValuePair("Menu_Type", params[2]));
                nameValuePairs.add(new BasicNameValuePair("cal", params[3]));

                sharedPreferences = getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                nameValuePairs.add(new BasicNameValuePair("creator", sharedPreferences.getString("username", "")));
                //nameValuePairs.add(new BasicNameValuePair("creator", "นำชัย โสไกร"));

                method.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

                HttpResponse response = httpclient.execute(method);
                HttpEntity entity = response.getEntity();
                Log.i("insertExercise", "4");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                if(entity != null){
                    Log.i("insertExercise", "5 : " + EntityUtils.toString(entity));
                    Toast.makeText(getApplicationContext(), EntityUtils.toString(entity), Toast.LENGTH_LONG).show();

                    return EntityUtils.toString(entity);

                }
                else{
                    Log.i("insertExercise", "6");
                    return "No string.";
                }
            }
            catch(Exception e){
                Log.i("insertExercise", "7");
                return "Network problem";
            }

        }
    }
}
