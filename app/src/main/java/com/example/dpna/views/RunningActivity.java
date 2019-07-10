package com.example.dpna.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.example.dpna.R;

public class RunningActivity extends AppCompatActivity {
    Chronometer chronometer;
    ImageButton ic_stop,ic_pauseStart,ic_map;
    boolean aux = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.start();

        ic_stop = (ImageButton) findViewById(R.id.ic_stop);
        ic_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        ic_pauseStart = (ImageButton) findViewById(R.id.ic_pauseStart);
        ic_pauseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux){
                    chronometer.stop();
                    ic_pauseStart.setImageResource(R.drawable.ic_play);
                    aux = false;
                }else {
                    chronometer.start();
                    ic_pauseStart.setImageResource(R.drawable.ic_pause);
                    aux = true;
                }

            }
        });

        ic_map = (ImageButton) findViewById(R.id.ic_map);
        ic_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunningActivity.this, MapingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RunningActivity.this, InitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(RunningActivity.this, InitActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

}
