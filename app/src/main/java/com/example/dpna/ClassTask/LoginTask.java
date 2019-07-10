package com.example.dpna.ClassTask;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.dpna.config.ConstValue;
import com.example.dpna.views.InitActivity;

public class LoginTask extends  AsyncTask<Boolean, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Boolean... booleans) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
       ConstValue.setAccept(true);
        super.onPostExecute(s);
    }
}
