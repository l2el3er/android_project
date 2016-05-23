package com.example.gunka.kujapom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class KcalFragment extends Fragment implements AdapterView.OnItemSelectedListener,TextToSpeech.OnInitListener {
    private TextToSpeech tts;
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
    private ImageButton btnSearch;
    private SharedPreferences sharedPreferences;


    public KcalFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tts = new TextToSpeech(getActivity(), this);
        View v = inflater.inflate(R.layout.fragment_kcal, container, false);
        listview = (ListView) v.findViewById(R.id.listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                ListViewAdapter_kcal.viewHolder holder = (ListViewAdapter_kcal.viewHolder) v.getTag();

                //holder.
                //title = holder.
                String text = holder.title.getText().toString() + holder.Description.getText();
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);

                // Log.i("listview", "set : "+"onItemClick: "+holder.title.getText()+holder.Description.getText()+ " กิโลแคลอรี่");
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ListViewAdapter_kcal.viewHolder holder = (ListViewAdapter_kcal.viewHolder) view.getTag();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                Log.i("cp", "need to delete item ID : "+getID());
                                //
                                //  DO SOMETHING
                                //
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }

                    private String getID() {
                        return  holder.ID;
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("ต้องการที่จะลบ ?").setPositiveButton("ใช่", dialogClickListener)
                        .setNegativeButton("ไม่", dialogClickListener).show();
                    return false;
                };
        });
        new FeedAsynTask().execute("http://10.16.68.253/kcal.php?type=");

        spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, paths);

        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1);
        btnSearch = (ImageButton) v.findViewById(R.id.btnSearch);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        setWidgetEventListener();


        return v;

    }

    private void setWidgetEventListener() {

        btnSearch.setOnClickListener(new View.OnClickListener() {
            public static final int RESULT_SPEECH = 1;

            @Override
            public void onClick(View v) {

            if(Integer.parseInt(String.valueOf(actv.length()))==0){
                    Log.i("cp", "no input data");
                return;
                }
                tts.speak("ค้นหา"+actv.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                jsonlist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        JSONObject c = json.getJSONObject(i);
                        String type = actv.getText().toString();

                        if(c.getString(API_NAME).toLowerCase().contains(type.toLowerCase())) {
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
                sharedPreferences = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);

                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

            }


        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        sharedPreferences = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));


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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

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
                listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(jsonlist),sharedPreferences.getString("username", "")));

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("th"));
            Log.i("cp", "well done !");
            //Toast.makeText(getActivity(), "tts is ready", Toast.LENGTH_SHORT).show();
           }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        tts.shutdown();
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
            sharedPreferences = getActivity().getSharedPreferences("MY_PREFERENCE", Context.MODE_PRIVATE);
            listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(s),sharedPreferences.getString("username", "")));

        }
    }

}
