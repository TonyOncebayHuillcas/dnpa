package com.example.dpna.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dpna.R;
import com.example.dpna.config.ConstValue;
import com.example.dpna.db.SqliteClass;

public class InitActivity extends AppCompatActivity {
    Context context;
    Activity activity;
    Button btn_go;
    ImageButton imgbtn_history,imgbtn_myPosition,imgbtn_cardGift;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        context=this;
        activity=this;

        //ConstValue.setContext(context);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //Toast.makeText(context,SqliteClass.getInstance(context).databasehelp.premiosql.getAllItem()+ "",Toast.LENGTH_LONG).show();

        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitActivity.this, RunningActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        imgbtn_cardGift = (ImageButton) findViewById(R.id.imgbtn_cardGift);
        imgbtn_cardGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitActivity.this, GiftCardActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        imgbtn_history = (ImageButton) findViewById(R.id.imgbtn_history);
        imgbtn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InitActivity.this, HistoryRoutesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InitActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(InitActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

}
