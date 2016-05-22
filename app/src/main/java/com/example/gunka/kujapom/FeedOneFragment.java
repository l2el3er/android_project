package com.example.gunka.kujapom;

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

import com.codemobiles.util.CMFeedJsonUtil;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by gunka on 28-Apr-16.
 */
public class FeedOneFragment extends Fragment implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private ListView listview;

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
                listview.setAdapter(new ListViewAdapter(getActivity(), new ArrayList<Object>(s)));
            }

        }
    }



    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("th"));
            Log.i("cp", "well done !");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
    }
}
