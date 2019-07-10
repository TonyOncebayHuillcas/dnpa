package com.example.dpna.config;

import android.content.Context;

public class ConstValue {
    public static String SITE_URL = "https://biciapp-app.herokuapp.com";
    public static String TIENDA = SITE_URL + "/bici/stores/";

    public static Boolean accept;

    public static Boolean getAccept() { return accept; }
    public static void setAccept(Boolean accept) { ConstValue.accept = accept; }

    public static String username;
    public static String getUsername() { return username; }
    public static void setUsername(String username) { ConstValue.username = username; }

    public static String password;
    public static String getPassword() { return password; }
    public static void setPassword(String password) { ConstValue.password = password; }


}
