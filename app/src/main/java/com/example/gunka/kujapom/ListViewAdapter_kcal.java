package com.example.gunka.kujapom;

/**
 * Created by atomath on 12/4/59.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by atomath on 25/2/59.
 */
public class ListViewAdapter_kcal extends BaseAdapter {


    private final ArrayList<HashMap<String, String>> mData;
    public Context mContext;
    public LayoutInflater mInflater;
    public String name = "";


    public ListViewAdapter_kcal(Context context, ArrayList<HashMap<String, String>> arraylist) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mData = arraylist;

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder = null;
        HashMap<String, String> item = mData.get(position);
        if (convertView == null) {
            // load layout
            convertView = mInflater.inflate(R.layout.item_listview_kcal, null);

            holder = new viewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.item_listview_title);
            holder.icon = (ImageView) convertView.findViewById(R.id.imageView);
            holder.productPicture = (ImageView) convertView.findViewById(R.id.item_listview_productPicture);
            holder.Description = (TextView) convertView.findViewById(R.id.item_listview_desc);




        } else {
            //restoration
            holder = (viewHolder) convertView.getTag();

        }
        holder.ID = item.get("Menu_ID");
        convertView.setTag(holder);
        if(new String(item.get("Menu_Creator").toString()).equals(name) && name != ""){
            holder.icon.setImageResource(R.drawable.star);
            Log.i("iconStar", "true");
        } else {
            holder.icon.setImageResource(R.drawable.food);
            Log.i("iconStar", "false");
        }
        holder.title.setText(item.get("Menu_Name"));
        holder.Description.setText(item.get("Menu_Cal") + " กิโลแคลอรี่");
        int myNum = Integer.parseInt(item.get("Menu_Type").toString());
         switch (myNum) {
            case 1:
                holder.productPicture.setImageResource(R.drawable.ico_cate1);
                break;
            case 2:
                holder.productPicture.setImageResource(R.drawable.ico_cate2);
                break;
            case 3:
                holder.productPicture.setImageResource(R.drawable.ico_cate3);
                break;
            case 4:
                holder.productPicture.setImageResource(R.drawable.ico_cate4);
                break;
            case 5:
                holder.productPicture.setImageResource(R.drawable.ico_cate5);
                break;
            default:
                holder.productPicture.setImageResource(R.drawable.avartar);
        }

        return convertView;
    }

    public class viewHolder {
        TextView title;
        ImageView productPicture;
        TextView Description;
        String ID;
        ImageView icon;
        String creator;
    }
}

