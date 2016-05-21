package com.example.gunka.kujapom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codemobiles.util.CMXmlJsonConvertor;

import java.util.ArrayList;

/**
 * Created by gunka on 25-Feb-16.
 */
public class ListViewAdapter extends BaseAdapter {

    public Context mContext;
    public LayoutInflater mInflater;
    ArrayList<Object> result;


    public ListViewAdapter(Context context, ArrayList<Object> s) {
        result = s;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;

        if (convertView == null) {
            // load layout
            convertView = mInflater.inflate(R.layout.item_listview_kcal, null);

            holder = new viewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_listview_title);
            holder.productPicture = (ImageView) convertView.findViewById(R.id.item_listview_productPicture);
            holder.Description = (TextView) convertView.findViewById(R.id.item_listview_desc);
            convertView.setTag(holder);


        } else {
            //restoration
            holder = (viewHolder) convertView.getTag();

        }

        Object item = result.get(position);
        holder.title.setText(CMXmlJsonConvertor.getValue(item,"name"));
        holder.Description.setText(CMXmlJsonConvertor.getValue(item,"cal") + " กิโลแคลอรี่");
        int myNum = Integer.parseInt(CMXmlJsonConvertor.getValue(item,"type"));
        switch (myNum) {
            case 1:
                holder.productPicture.setImageResource(R.drawable.ico_burn1);
                break;
            case 2:
                holder.productPicture.setImageResource(R.drawable.ico_burn2);
                break;
        }


        return convertView;
    }

    public class viewHolder {
        TextView title;
        ImageView productPicture;
        TextView Description;
    }

}
