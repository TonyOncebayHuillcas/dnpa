package com.example.dpna.Utils;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.example.dpna.config.ConstValue;
import com.example.dpna.db.SqliteClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class JSONParser {



    // constructor
    public JSONParser() {

    }
    public JSONArray getJSONFromUrl(String url) {
        InputStream is = null;
        JSONArray jObj = null;
        String json = "";
        // Making HTTP request
        try {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            // defaultHttpClient
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(url);
            httpPost.setHeader("authorization", SqliteClass.getInstance(ConstValue.getContext()).databasehelp.usersql.getToken(ConstValue.getUsername(),ConstValue.getPassword()).toString());// create new httpGet object


            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(
            //      is, "iso-8859-1"), 8);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();

            if (json.startsWith("ï»¿")) { json = json.substring(3); }
            System.out.println("SB " + url + " json" + json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONArray(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}