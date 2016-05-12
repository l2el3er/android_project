package com.example.gunka.kujapom;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KcalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String API_ID = "Menu_ID";
    private static final String API_NAME = "Menu_Name";
    private static final String API_CAL = "Menu_Cal";
    private static final String API_TYPE = "Menu_Type";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
    private JSONParser jParser;
    private JSONArray json;

    private Spinner spinner;
    private AutoCompleteTextView actv;
    private static final String[] paths = {"---เลือกประเภท---","อาหารจานเดียว/กับข้าว", "ของหวาน", "ผลไม้", "เครื่องดื่ม", "อื่นๆ","ทั้งหมด"};


    private ListView listview;



    public KcalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_kcal, container, false);
        listview = (ListView) v.findViewById(R.id.listview);
        new FeedAsynTask().execute("http://10.16.68.253/kcal.php?type=");

        spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, paths);
        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        return v;


    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:

                break;
            case 1:

                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());
                        if(type==1) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(API_ID, c.getString(API_ID));
                            map.put(API_NAME, c.getString(API_NAME));
                            map.put(API_CAL, c.getString(API_CAL));
                            map.put(API_TYPE, c.getString(API_TYPE));
                            jsonlist.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));

                break;
            case 2:
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());
                        if(type==2) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(API_ID, c.getString(API_ID));
                            map.put(API_NAME, c.getString(API_NAME));
                            map.put(API_CAL, c.getString(API_CAL));
                            map.put(API_TYPE, c.getString(API_TYPE));
                            jsonlist.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));


                break;
            case 3:
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());
                        if(type==3) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(API_ID, c.getString(API_ID));
                            map.put(API_NAME, c.getString(API_NAME));
                            map.put(API_CAL, c.getString(API_CAL));
                            map.put(API_TYPE, c.getString(API_TYPE));
                            jsonlist.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));

                break;
            case 4:
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());
                        if(type==4) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(API_ID, c.getString(API_ID));
                            map.put(API_NAME, c.getString(API_NAME));
                            map.put(API_CAL, c.getString(API_CAL));
                            map.put(API_TYPE, c.getString(API_TYPE));
                            jsonlist.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));

                break;
            case 5:
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());
                        if(type==5) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put(API_ID, c.getString(API_ID));
                            map.put(API_NAME, c.getString(API_NAME));
                            map.put(API_CAL, c.getString(API_CAL));
                            map.put(API_TYPE, c.getString(API_TYPE));
                            jsonlist.add(map);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));

                break;
            case 6:
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        int type = Integer.parseInt(c.getString(API_TYPE).toString());

                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(API_ID, c.getString(API_ID));
                        map.put(API_NAME, c.getString(API_NAME));
                        map.put(API_CAL, c.getString(API_CAL));
                        map.put(API_TYPE, c.getString(API_TYPE));
                        jsonlist.add(map);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist)));

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class FeedAsynTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {


        private ArrayAdapter<String> adapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... params) {



            jParser = new JSONParser(); // get JSON data from URL
            json = jParser.getJSONFromUrl(params[0]+"0");

            for (int i = 0; i < json.length(); i++) {
                try {

                    JSONObject c = json.getJSONObject(i);


                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(API_ID, c.getString(API_ID));
                    map.put(API_NAME, c.getString(API_NAME));
                    map.put(API_CAL, c.getString(API_CAL));
                    map.put(API_TYPE, c.getString(API_TYPE));
                    jsonlist.add(map);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            ////
            List<String> arrayname = new ArrayList<String>();

            for (int i = 0; i < json.length(); i++) {
                try {
                    JSONObject c = json.getJSONObject(i);
                    arrayname.add(c.getString(API_NAME));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
             adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayname);
           


            ////
            Log.i("mobile", "doInBackground: "+jsonlist.toString());
            return jsonlist;

        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> s) {
            super.onPostExecute(s);
            Log.i("mobile", "onPostExecute: " + s);
            actv.setAdapter(adapter);
            listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(s)));

        }
    }
}
