package com.example.dpna.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.dpna.Models.PremioClass;
import com.example.dpna.Models.RouteClass;
import com.example.dpna.R;
import com.example.dpna.adapters.PremioAdapter;
import com.example.dpna.adapters.RouteAdapter;
import com.example.dpna.db.SqliteClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiftCardActivity extends AppCompatActivity {
    Context context;
    Activity activity;
    ListView itemListView;
    PremioClass premioClass;
    static ArrayList<HashMap<String, String>> itemArray;
    PremioAdapter premioAdapter;
    ArrayList<PremioClass>itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_card);

        context=this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        itemArray = new ArrayList<HashMap<String,String>>();
        itemListView = (ListView) findViewById(R.id.list_premios);

        itemArray = new ArrayList<HashMap<String,String>>();

        itemList= SqliteClass.getInstance(context).databasehelp.premiosql.getAllItem();
        getList(itemList);

    }
    public void getList(ArrayList<PremioClass> list){
        itemArray = new ArrayList<HashMap<String,String>>();
        itemList = new ArrayList<PremioClass>();
        itemList =list;

        for(int z=0; z < itemList.size(); z++){
            PremioClass cc = itemList.get(z);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(cc.getId()));
            map.put("description",cc.getDescription());
            map.put("address",cc.getAddress());
            map.put("value_km",String.valueOf(cc.getValue_km()));
            itemArray.add(map);
        }
        premioAdapter = new PremioAdapter(context, itemArray);
        itemListView.setAdapter(premioAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GiftCardActivity.this, InitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(GiftCardActivity.this, InitActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }
}
