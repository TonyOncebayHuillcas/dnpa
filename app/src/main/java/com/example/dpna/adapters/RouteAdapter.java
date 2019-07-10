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

public class RouteAdapter extends BaseAdapter {
    Context context;
    private ArrayList<HashMap<String, String>> postItems;

    public RouteAdapter(Context context, ArrayList<HashMap<String, String>> postItems) {
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
            convertView = mInflater.inflate(R.layout.row_route, null);
        }
        final HashMap<String, String> map = postItems.get(position);

        TextView velocidad = (TextView) convertView.findViewById(R.id.tv_speed);
        velocidad.setText(map.get("velocidad")+" "+"KM/H");

        TextView distancia = (TextView) convertView.findViewById(R.id.tv_distancia);
        distancia.setText(map.get("distancia")+" KM");

        TextView ganancia = (TextView) convertView.findViewById(R.id.tv_ganancia);
        ganancia.setText(map.get("ganancia"));

        return convertView;
    }
}
