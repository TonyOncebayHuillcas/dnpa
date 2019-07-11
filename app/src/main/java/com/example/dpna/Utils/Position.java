package com.example.dpna.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.dpna.config.ConstValue;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class Position {
    double lat2=0.0;
    double lng2=0.0;
    public void myLocation(Location loc){

        if (ActivityCompat.checkSelfPermission(ConstValue.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ConstValue.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ConstValue.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(ConstValue.getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(ConstValue.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(ConstValue.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            return;
        }

        LocationManager mLocationManager = (LocationManager)ConstValue.getContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;

        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }

        Location location = bestLocation;

        lat2 = location.getLatitude();
        lng2 = location.getLongitude();
        loc.setLatitude(location.getLatitude());
        loc.setLongitude(location.getLongitude());
    }
}
