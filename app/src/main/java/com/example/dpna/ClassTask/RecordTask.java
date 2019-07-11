package com.example.dpna.ClassTask;

import android.os.AsyncTask;

import com.example.dpna.config.ConstValue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RecordTask extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... strings) {
        URL url =null;
        HttpURLConnection conn = null;

        try {
            url=new URL(ConstValue.TIENDA);
            conn = (HttpURLConnection) url.openConnection();//in the real code, there is an ip and a port

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
