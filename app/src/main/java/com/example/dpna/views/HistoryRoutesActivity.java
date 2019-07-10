package com.example.dpna.views;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.dpna.Models.RouteClass;
import com.example.dpna.R;
import com.example.dpna.adapters.RouteAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryRoutesActivity extends AppCompatActivity {
    Activity activity;
    ListView itemListView;
    RouteClass routeClass;
    static ArrayList<HashMap<String, String>> itemArray;
    RouteAdapter routeAdapter;
    ArrayList<RouteClass>itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_routes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        activity =  this;

        itemArray = new ArrayList<HashMap<String,String>>();
        itemListView = (ListView) findViewById(R.id.list_routes);

        itemArray = new ArrayList<HashMap<String,String>>();

        for(int i = 0; i< 12; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            itemList = new ArrayList<RouteClass>();
            map.put("id","1");
            map.put("velocidad", "23");
            map.put("distancia","87");
            map.put("ganancia", "34");
            itemArray.add(map);
            routeClass = new RouteClass(3,87, 23,34);
            itemList.add(routeClass);
        }
        routeAdapter = new RouteAdapter(activity, itemArray);
        itemListView.setAdapter((ListAdapter) routeAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HistoryRoutesActivity.this, InitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(HistoryRoutesActivity.this, InitActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
