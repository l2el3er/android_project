package com.example.gunka.kujapom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.codemobiles.util.CMFeedJsonUtil;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

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
import java.util.Locale;

/**
 * Created by gunka on 28-Apr-16.
 */
public class FeedOneFragment extends Fragment implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private ListView listview;
    private SharedPreferences sharedPreferences;

    public FeedOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tts = new TextToSpeech(getActivity(), this);
        View v = inflater.inflate(R.layout.fragment_feed_one, container, false);
        listview = (ListView) v.findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                ListViewAdapter.viewHolder holder = (ListViewAdapter.viewHolder) v.getTag();

                //holder.
                //title = holder.
                String text = holder.title.getText().toString() +"เผาผลาญ"+ holder.Description.getText();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

                // Log.i("listview", "set : "+"onItemClick: "+holder.title.getText()+holder.Description.getText()+ " กิโลแคลอรี่");
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ListViewAdapter.viewHolder holder = (ListViewAdapter.viewHolder) view.getTag();
                //Log.i("cp", String.valueOf(holder.creator));
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Log.i("cpDelete", "need to delete item ID : " + getID());

                                new TheTask().execute("http://10.16.68.253/kexercise.php",getID());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }

                    private String getID() {
                        return holder.ID;
                    }
                };
                if (holder.creator=="1") {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("ต้องการที่จะลบ ?").setPositiveButton("ใช่", dialogClickListener)
                            .setNegativeButton("ไม่", dialogClickListener).show();
                }
                return false;
            }

            ;
        });

        feedData();
        return v;
    }

    private void feedData() {

        new FeedAsyncTask().execute("http://10.16.68.253/kexercise.php");
    }

    public class FeedAsyncTask extends AsyncTask<String ,Void ,ArrayList<Object>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            Toast.makeText(getActivity(), "Connecting..", Toast.LENGTH_LONG).show();
            Log.i("codemobiles","onPreExecute");
        }

        @Override
        protected ArrayList<Object> doInBackground(String... params) {
            Log.i("codemobiles","doInBackground: "  + params[0]);
            RequestBody request = new FormEncodingBuilder().add("type","json").build();
            ArrayList<Object> result = CMFeedJsonUtil.feed(params[0], request);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Object> s) {

            super.onPostExecute(s);
            Log.i("codemobiles", "onPostExecute");
            if(s != null){
                sharedPreferences = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                Toast.makeText(getActivity(), "size: " + s.size(), Toast.LENGTH_LONG).show();
                listview.setAdapter(new ListViewAdapter(getActivity(), new ArrayList<Object>(s),sharedPreferences.getString("username", "")));
            }

        }
    }



    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("th"));
            Log.i("cp", "well done !");
            Toast.makeText(getActivity(), "tts is ready", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
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
                Log.i("deleteExercise", "3");
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost method = new HttpPost(params[0]);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);

                nameValuePairs.add(new BasicNameValuePair("type", "delete"));
                nameValuePairs.add(new BasicNameValuePair("id", params[1]));
                sharedPreferences = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
                nameValuePairs.add(new BasicNameValuePair("creator", sharedPreferences.getString("username", "")));

                method.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

                HttpResponse response = httpclient.execute(method);
                HttpEntity entity = response.getEntity();
                Log.i("deleteExercise", "4");
                startActivity(new Intent(getActivity(), MainActivity.class));
                if(entity != null){
                    Log.i("deleteExercise", "5 : " + EntityUtils.toString(entity));

                    return EntityUtils.toString(entity);

                }
                else{
                    Log.i("deleteExercise", "6");
                    return "No string.";
                }
            }
            catch(Exception e){
                Log.i("deleteExercise", "7");
                return "Network problem";
            }

        }
    }
}
