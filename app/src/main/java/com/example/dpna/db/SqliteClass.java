package com.example.dpna.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteClass {
    public DatabaseHelper databasehelp;
    private static SqliteClass SqliteInstance = null;

    private SqliteClass(Context context) {
        databasehelp = new DatabaseHelper(context);
    }

    public static SqliteClass getInstance(Context context){
        if(SqliteInstance == null){
            SqliteInstance = new SqliteClass(context);
        }
        return SqliteInstance;
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "app_cloud_tmp.db";

        public Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            //usersql = new AppUserSql();
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
