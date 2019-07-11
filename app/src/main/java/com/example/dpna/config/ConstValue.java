package com.example.dpna.config;

import android.app.Activity;
import android.content.Context;

public class ConstValue {
    public static String SITE_URL = "https://biciapp-app.herokuapp.com";
    public static String LOGIN = SITE_URL + "/accounts/api/login/";
    public static String TIENDA = SITE_URL + "/bici/stores/";

    public static Boolean accept = false;


    public static Context context;
    public static Context getContext() { return context; }
    public static void setContext(Context context) { ConstValue.context = context; }

    public static Activity activity;
    public static Activity getActivity() { return activity; }
    public static void setActivity(Activity activity) { ConstValue.activity = activity; }

    public static String username;
    public static String getUsername() { return username; }
    public static void setUsername(String username) { ConstValue.username = username; }

    public static String password;
    public static String getPassword() { return password; }
    public static void setPassword(String password) { ConstValue.password = password; }


}
