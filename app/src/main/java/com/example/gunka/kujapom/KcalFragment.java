package com.example.gunka.kujapom;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KcalFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String NAME = "menu_name";
    private static final String CAL = "menu_cal";
    private static final String TYPE = "menu_type";
    private Spinner spinner;
    private AutoCompleteTextView actv;
    private static final String[] paths = {"อาหารจานเดียว/กับข้าว", "ของหวาน", "ผลไม้", "เครื่องดื่ม", "อื่นๆ", "ทั้งหมด"};

    //private static final String PRICE = "menu_price";
    private ListView listview;
    DatabaseHelper_kcal myDB;
    private ArrayList<HashMap<String, String>> arraylist;
    private Cursor c;


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

        new FeedAsynTask().execute("");


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
                arraylist = new ArrayList<HashMap<String, String>>();
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

                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                arraylist = new ArrayList<HashMap<String, String>>();
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


                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                arraylist = new ArrayList<HashMap<String, String>>();
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


                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                arraylist = new ArrayList<HashMap<String, String>>();
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


                break;
            case 4:
                // Whatever you want to happen when the thrid item gets selected
                arraylist = new ArrayList<HashMap<String, String>>();
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


                break;
            case 5:
                // Whatever you want to happen when the thrid item gets selected
                arraylist = new ArrayList<HashMap<String, String>>();
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

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class FeedAsynTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            myDB = new DatabaseHelper_kcal(getActivity());
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            arraylist = new ArrayList<HashMap<String, String>>();
            c = myDB.getTypeone();

            while (c.moveToNext()) {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put(NAME, c.getString(1));
                map.put(CAL, c.getString(2));
                map.put(TYPE, c.getString(3));
                arraylist.add(map);
            }
            c.close();
            listview.setAdapter(new com.example.gunka.kujapom.ListViewAdapter_kcal(getActivity(), new ArrayList<HashMap<String, String>>(arraylist)));


        }
    }


}
