package com.example.dpna.Utils;

import com.example.dpna.config.ConstValue;
import com.example.dpna.db.SqliteClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.HttpHeaders;

public class Common {
    public String postJsonData(String STRURL){
        JSONObject objReturn = null;
        String url = STRURL;
        StringBuilder body = new StringBuilder();
        HttpClient  httpclient=null;
        httpclient = new DefaultHttpClient(); // create new httpClient
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("authorization","token 65364fe38b5dad0254b89dc66fe4105e0615d678");// create new httpGet object
        HttpResponse response = null;

        try {
            response = httpclient.execute(httpGet); // execute httpGet
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                // System.out.println(statusLine);
                body.append(statusLine + "\n");
                HttpEntity e = response.getEntity();
                String entity = EntityUtils.toString(e);
                body.append(entity);
            } else {
                body.append(statusLine + "\n");
                // System.out.println(statusLine);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body.toString();
    }


    public JSONObject sendJsonData(String STRURL, ArrayList<NameValuePair> nameValuePairs){
        JSONObject objReturn = null;
        String errorString = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(STRURL);
        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                InputStream is = r_entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = sb.toString();
                if (json.startsWith("ï»¿")) { json = json.substring(3); }
                objReturn = new JSONObject(json);
            } else {
                objReturn = new JSONObject("{error:'Error occurred! Http Status Code:'}");
            }
        } catch (ClientProtocolException e) {
            errorString = e.toString();
        } catch (IOException e) {
            errorString = e.toString();
        }catch (JSONException e) {
            errorString = e.toString();
        }
        if(errorString!=null){
            try {
                objReturn = new JSONObject("{error:'"+errorString+"'}");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return objReturn;
    }
    public JSONObject sendMultiPartData(String STRURL, AndroidMultiPartEntity entity){
        JSONObject objReturn = null;
        String errorString = null;
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(STRURL);
        try {
            httppost.setEntity(entity);
            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                InputStream is = r_entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                String json = sb.toString();
                objReturn = new JSONObject(json);
            } else {
                objReturn = new JSONObject("{error:'Error occurred! Http Status Code:'}");
            }

        } catch (ClientProtocolException e) {
            errorString = e.toString();

        } catch (IOException e) {
            errorString = e.toString();
        }catch (JSONException e) {
            errorString = e.toString();
        }

        if(errorString!=null){
            try {
                objReturn = new JSONObject("{error:'"+errorString+"'}");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return objReturn;
    }
}