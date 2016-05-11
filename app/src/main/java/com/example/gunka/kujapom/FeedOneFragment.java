package com.example.gunka.kujapom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by gunka on 28-Apr-16.
 */
public class FeedOneFragment extends Fragment {
    private ListView listview;
    DatabaseHelper myDB;

    public FeedOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_feed_one, container, false);
        listview = (ListView) v.findViewById(R.id.listview);

        myDB = new DatabaseHelper(getActivity());
        feedData();
        return v;
    }

    private void feedData() {
        listview.setAdapter(new ListViewAdapter(getActivity(), myDB));
    }

}
