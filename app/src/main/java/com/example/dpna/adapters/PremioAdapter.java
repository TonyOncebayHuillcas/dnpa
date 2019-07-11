package com.example.dpna.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dpna.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PremioAdapter extends BaseAdapter {
    Context context;
    private ArrayList<HashMap<String, String>> postItems;

    public PremioAdapter(Context context, ArrayList<HashMap<String, String>> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    @Override
    public int getCount() {
        return postItems.size();
    }

    @Override
    public Object getItem(int position) {
        return postItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_premio, null);
        }

        final HashMap<String, String> map = postItems.get(position);

        TextView addres = (TextView) convertView.findViewById(R.id.tv_addres);
        addres.setText(map.get("address"));

        TextView description = (TextView) convertView.findViewById(R.id.tv_description);
        description.setText(map.get("description"));

        TextView value_km = (TextView) convertView.findViewById(R.id.tv_value_km);
        value_km.setText(map.get("value_km"));

        return convertView;
    }
}
