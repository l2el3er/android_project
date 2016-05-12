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


/**
 * A simple {@link Fragment} subclass.
 */
public class KcalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    //start api value
    private static final String API_ID = "Menu_ID";
    private static final String API_NAME = "Menu_Name";
    private static final String API_CAL = "Menu_Cal";
    private static final String API_TYPE = "Menu_Type";

    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

    //end api
   // private static final String NAME = "menu_name";
    //private static final String CAL = "menu_cal";
    //private static final String TYPE = "menu_type";
    private Spinner spinner;
    private AutoCompleteTextView actv;
    private static final String[] paths = {"ทั้งหมด", "ของหวาน", "ผลไม้", "เครื่องดื่ม","อาหารจานเดียว/กับข้าว", "อื่นๆ"};

    //private static final String PRICE = "menu_price";
    private ListView listview;
    //DatabaseHelper_kcal myDB;
    //private ArrayList<HashMap<String, String>> arraylist;
    //private Cursor c;


    public KcalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_kcal, container, false);
        listview = (ListView) v.findViewById(R.id.listview);

        spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, paths);
        actv = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        new FeedAsynTask().execute("http://10.16.68.253/kcal.php?type=0");


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
                // Whatever you want to happen when the first item gets selected
              /*  arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getTypeone();

                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                List<String> arrayname = new ArrayList<String>();
                Cursor crs = myDB.getAllName();
                while (crs.moveToNext()) {
                    String uname = crs.getString(crs.getColumnIndex("Menu_Name"));
                    arrayname.add(uname);
                }
                //String[] languages={"Android ","java","IOS","SQL","JDBC","Web services"};
                //showMessage("Data",arraylist.toString());
                //String[] countries = getResources().getStringArray(new ArrayList<String>("test"));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayname);
                actv.setAdapter(adapter);

                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));
                */
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
               /* arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getTypetwo();

                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                //showMessage("Data",arraylist.toString());
                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));

                */
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
               /* arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getTypethree();

                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                //showMessage("Data",arraylist.toString());
                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));
                */

                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                /*arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getTypefour();

                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                //showMessage("Data",arraylist.toString());
                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));
                */

                break;
            case 4:
                // Whatever you want to happen when the thrid item gets selected
                /*arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getTypefive();

                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                //showMessage("Data",arraylist.toString());
                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));

                */
                break;
            case 5:
                // Whatever you want to happen when the thrid item gets selected
               /* arraylist = new ArrayList<HashMap<String, String>>();
                c = myDB.getAllData();
                while (c.moveToNext()) {

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(NAME, c.getString(1));
                    map.put(CAL, c.getString(2));
                    map.put(TYPE, c.getString(3));
                    arraylist.add(map);
                }
                //showMessage("Data",arraylist.toString());
                c.close();
                listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));
                */
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class FeedAsynTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<HashMap<String, String>> doInBackground(String... params) {
            Log.i("mobile", "prams[0]: "+params[0]);

            JSONParser jParser = new JSONParser(); // get JSON data from URL
            Log.i("mobile", "onPreExecute 2");
            JSONArray json = jParser.getJSONFromUrl(params[0]);
            Log.i("mobile", "onPreExecute 3");
            Log.i("mobile", json.toString());
            for (int i = 0; i < json.length(); i++) {
                try {

                    JSONObject c = json.getJSONObject(i);
                    String mid = c.getString(API_ID);
                    String mpic = c.getString(API_NAME);
                    String mname = c.getString(API_CAL);
                    String mprice = c.getString(API_TYPE);

                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put(API_ID, mid);
                    map.put(API_NAME, mpic);
                    map.put(API_CAL, mname);
                    map.put(API_TYPE, mprice);
                    jsonlist.add(map);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Log.i("mobile", "doInBackground: "+jsonlist.toString());
            return jsonlist;

        }

        @Override
        protected void onPostExecute(ArrayList<HashMap<String, String>> s) {
            super.onPostExecute(s);
            Log.i("mobile", "onPostExecute: "+s);
            listview.setAdapter(new ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(s)));

        }
    }


}
