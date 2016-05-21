package com.example.gunka.kujapom;

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

import java.util.Locale;

/**
 * Created by gunka on 28-Apr-16.
 */
public class FeedOneFragment extends Fragment implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private ListView listview;
    DatabaseHelper myDB;

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

        myDB = new DatabaseHelper(getActivity());
        feedData();
        return v;
    }

    private void feedData() {
        listview.setAdapter(new ListViewAdapter(getActivity(), myDB));
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
}
