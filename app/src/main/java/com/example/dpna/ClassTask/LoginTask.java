package com.example.dpna.ClassTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dpna.Models.PremioClass;
import com.example.dpna.Models.UserClass;
import com.example.dpna.Utils.Common;
import com.example.dpna.Utils.JSONParser;
import com.example.dpna.config.AsyncResponse;
import com.example.dpna.config.ConstValue;
import com.example.dpna.db.SqliteClass;
import com.example.dpna.views.LoginActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Response;

public class LoginTask extends  AsyncTask<Boolean, Void, String> {
    Common common;
    String rpta;
    public static ProgressDialog dialog;
    public AsyncResponse delegate = null;
    UserClass userClass;

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(ConstValue.getContext(), "", "Cargando", true);
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Boolean... booleans) {
        common = new Common();
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("username", ConstValue.getUsername()));
        nameValuePairs.add(new BasicNameValuePair("password", ConstValue.getPassword()));
        JSONObject jObj = common.sendJsonData(ConstValue.LOGIN, nameValuePairs);
        rpta = jObj.toString();
        System.out.println("MI JSON USER: " + jObj);
        try {
            JSONObject data = jObj.getJSONObject("key");
            rpta= data.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (!jObj.getString("key").isEmpty()){
                System.out.println("Entramos");
                ConstValue.accept=true;
                userClass =  new UserClass(1,ConstValue.getUsername(),ConstValue.getPassword(),56.5,1.57,"token "+jObj.getString("key"),34.5);
                SqliteClass.getInstance(ConstValue.getContext()).databasehelp.usersql.addUser(userClass);

                JSONParser jParser;
                JSONObject json;
                JSONArray jsonArray;

                jParser = new JSONParser();
                jsonArray = jParser.getJSONFromUrl(ConstValue.TIENDA);

                System.out.println("RESULTADO "+String.valueOf(jsonArray.length()));
                for(int j=0;j<jsonArray.length(); j++){
                    JSONObject o =  jsonArray.getJSONObject(j);
                    PremioClass premioClass= new PremioClass(o.getInt("id"),o.getString("address"),o.getString("description"),o.getInt("value_km"));
                    System.out.println("test");

                    SqliteClass.getInstance(ConstValue.getContext()).databasehelp.premiosql.addPremio(premioClass);
                }
                //Toast.makeText(ConstValue.getContext(),"tamaño"+ String.valueOf(jsonList.length()),Toast.LENGTH_LONG).show();
            }else {
                ConstValue.accept=false;
                Toast.makeText(ConstValue.getContext(), "BIKA - Credenciales invalidas.", Toast.LENGTH_LONG).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public LoginTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }
}

