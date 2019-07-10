package com.example.dpna.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.dpna.R;
import com.example.dpna.views.fragment.MapFragment;

public class MapingActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener{
    ImageButton ic_stop,ic_startPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maping);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Fragment fragmento = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmento).commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MapingActivity.this, RunningActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(MapingActivity.this, RunningActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
