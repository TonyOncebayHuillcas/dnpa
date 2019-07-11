package com.example.dpna.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpna.R;
import com.example.dpna.Utils.Position;
import com.example.dpna.config.ConstValue;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RunningActivity extends AppCompatActivity {
    Context context;
    Activity activity;
    Chronometer chronometer;
    ImageButton ic_stop, ic_pauseStart, ic_map;
    TextView tv_speed, tv_distance, tv_ganancia;
    boolean aux = true;
    double lat1 = 0.0;
    double lng1 = 0.0;
    double lat2 = 0.0;
    double lng2 = 0.0;
    final Handler handler = new Handler();
    Location location;
    double auxi=0.0;
    //Handler handler = new Handler(); // En esta zona creamos el objeto Handler

    public double radioTierra = 6371;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);

        context = this;
        activity = this;
        ConstValue.setContext(context);
        ConstValue.setActivity(activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        ejecutar();

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();

        tv_speed = (TextView) findViewById(R.id.tv_speed);


        tv_distance = (TextView) findViewById(R.id.tv_distance);

        tv_ganancia = (TextView) findViewById(R.id.tv_ganancia);

        ic_stop = (ImageButton) findViewById(R.id.ic_stop);
        ic_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                handler.removeCallbacksAndMessages(null);
                Toast.makeText(context, "Parado", Toast.LENGTH_LONG).show();
            }
        });

        ic_pauseStart = (ImageButton) findViewById(R.id.ic_pauseStart);
        ic_pauseStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aux) {
                    chronometer.stop();
                    ic_pauseStart.setImageResource(R.drawable.ic_play);
                    aux = false;
                } else {
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


    private void ejecutar() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                actualizarPosicion();

                lat1=lat2;
                lng1=lng2;

                handler.postDelayed(this, 5000);//se ejecutara cada 10 segundos
            }
        }, 5000);//empezara a ejecutarse después de 5 milisegundos
    }


    private void actualizarPosicion() {
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(RunningActivity.this, "Coordenadas GPS actualizadas: " + "lat--> " + location.getLatitude() + " long--> " + location.getLongitude(), Toast.LENGTH_LONG).show();
                lng2=location.getLongitude();
                lat2=location.getLatitude();

                DecimalFormat df = new DecimalFormat("000.000");
                double dLat = Math.toRadians(lat2 - lat1);
                double dLng = Math.toRadians(lng2 - lng1);
                double sindLat = Math.sin(dLat / 2);
                double sindLng = Math.sin(dLng / 2);
                double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                        * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
                double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
                double distancia = radioTierra * va2;
                distancia=distancia/1000;
                auxi=distancia+auxi;
                tv_distance.setText(String.valueOf(df.format(auxi)));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(RunningActivity.this, "Cambios en proveedor " + provider + " Estado-->" + status, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(RunningActivity.this, "Proveedor habilitado " + provider, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(RunningActivity.this, "Proveedor deshabilitado " + provider, Toast.LENGTH_LONG).show();
            }

        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locListener);
    }
    public void Distncia() {
        lat2 = location.getLatitude();
        lng2 = location.getLongitude();
        //DecimalFormat df = new DecimalFormat("000.00");

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;
        //df.format(distancia);
        tv_distance.setText(String.valueOf(distancia));


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
