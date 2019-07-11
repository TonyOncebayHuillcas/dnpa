package com.example.dpna.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dpna.Models.PremioClass;
import com.example.dpna.Models.UserClass;

import java.io.File;
import java.util.ArrayList;

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
        private static final String DATABASE_NAME = "app_cloud_bika.db";


        /*TABLE_APP_USER*/
        public static final String TABLE_APP_USER = "user";
        private static final String KEY_USEID = "id";
        private static final String KEY_USEUSE = "user";
        private static final String KEY_USEPAS = "password";
        private static final String KEY_USEPES = "peso";
        private static final String KEY_USEEST = "estatura";
        private static final String KEY_USETOK = "token";
        private static final String KEY_USEKM = "km";

        /*TABLE_APP_PREMIO*/
        public static final String TABLE_APP_PREMIO = "premio";
        private static final String KEY_PREID = "id";
        private static final String KEY_PREADD = "addres";
        private static final String KEY_PREDES = "description";
        private static final String KEY_PREVAL = "value_km";

        /*@SQL*/
        public AppUserSql usersql;
        public AppPremioSql premiosql;


        public Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            usersql = new AppUserSql();
            premiosql = new AppPremioSql();
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            /*@TABLE_USER*/
            String CREATE_TABLE_USER = " CREATE TABLE " + TABLE_APP_USER + "("
                    + KEY_USEID + " INTEGER PRIMARY KEY," + KEY_USEUSE + " TEXT,"+ KEY_USEPAS + " TEXT,"
                    + KEY_USEPES + " TEXT," + KEY_USEEST + " TEXT,"+ KEY_USETOK + " TEXT," + KEY_USEKM + " TEXT)";

            /*@TABLE_PREMIO*/
            String CREATE_TABLE_PREMIO = " CREATE TABLE " + TABLE_APP_PREMIO + "("
                    + KEY_PREID + " INTEGER PRIMARY KEY," + KEY_PREADD + " TEXT,"
                    + KEY_PREDES + " TEXT," + KEY_PREVAL + " TEXT)";


            /*@EXECSQL_CREATE*/
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_PREMIO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APP_PREMIO);

        }

        public boolean checkDataBase(){
            File dbFile = new File(context.getDatabasePath(DATABASE_NAME).toString());
            return dbFile.exists();
        }

        public void deleteDataBase(){
            context.deleteDatabase(DATABASE_NAME);
        }

        /*@CLASS_USERSQL*/
        public class AppUserSql{
            public AppUserSql(){}
            public void deleteUser(){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_APP_USER,null,null);
                db.close();
            }

            public String getActive(String pid) {
                String result = "";
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor mCount= db.rawQuery("SELECT active FROM " + TABLE_APP_USER + " WHERE id = '" + pid + "'",null);
                mCount.moveToFirst();
                result= mCount.getString(0);
                db.close();
                return result;
            }

            public void updateActive(String pid, String value) {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("active", value);
                db.update(TABLE_APP_USER, values, KEY_USEID + " = ?",new String[] { pid });
                db.close();
            }

            public void addUser (UserClass user){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_USEID, user.getId());
                values.put(KEY_USEUSE, user.getUser());
                values.put(KEY_USEPAS, user.getPassword());
                values.put(KEY_USEPES, user.getPeso());
                values.put(KEY_USEEST, user.getEstatura());
                values.put(KEY_USETOK, user.getToken());
                values.put(KEY_USEKM, user.getKm());
                db.insert(TABLE_APP_USER, null, values);
                db.close();
            }
            public int updateUser(UserClass user) {
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_USEUSE, user.getUser());
                int value = db.update(TABLE_APP_USER, values, KEY_USEUSE + " = ?",new String[] { String.valueOf(user.getUser())});
                db.close();
                return value;
            }

            public boolean isRegisterUser(String sName, String sPassword) {
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor = db.query(TABLE_APP_USER, new String[] { KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEUSE + "=?" + " and "+ KEY_USEPAS + "=?",
                        new String[] {sName, sPassword}, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                if(cursor.getCount() == 1){
                    db.close();
                    return true;
                }else{
                    db.close();
                    return false;
                }
            }

            public UserClass getUser (int id){
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor =  db.query(TABLE_APP_USER, new String[] { KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEID + "=?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                UserClass user = new UserClass(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getString(5),
                        cursor.getInt(6));
                db.close();
                return user;
            }

            public ArrayList<UserClass> getUserData() {
                ArrayList<UserClass> userList = new ArrayList<UserClass>();
                String selectQuery = "SELECT * FROM " + TABLE_APP_USER + " LIMIT 1";
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do {
                        UserClass user = new UserClass();
                        user.setId(cursor.getInt(cursor.getColumnIndex(KEY_USEID)));
                        user.setUser(cursor.getString(cursor.getColumnIndex(KEY_USEUSE)));
                        user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_USEPAS)));
                        user.setPeso(cursor.getInt(cursor.getColumnIndex(KEY_USEPES)));
                        user.setEstatura(cursor.getInt(cursor.getColumnIndex(KEY_USEEST)));
                        user.setToken(cursor.getString(cursor.getColumnIndex(KEY_USETOK)));
                        user.setKm(cursor.getInt(cursor.getColumnIndex(KEY_USEKM)));
                        userList.add(user);
                    } while (cursor.moveToNext());
                }
                db.close();
                return userList;
            }

            public int getId(String sName, String sPassword) {
                int _id=0;
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor = db.query(TABLE_APP_USER, new String[] { KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEUSE + "=?" + " and "+ KEY_USEPAS + "=?",
                        new String[] {sName, sPassword}, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                _id = cursor.getInt(0);
                db.close();
                return _id;
            }
            public String getData(int nField, String sName) {
                String _date = null;
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor = db.query(TABLE_APP_USER, new String[] {KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEUSE + "=?",
                        new String[] {sName}, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                _date = cursor.getString(nField);
                db.close();
                return _date;
            }
            public String getDate( String sName) {
                String _date = null;
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor = db.query(TABLE_APP_USER, new String[] {KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEUSE + "=?",
                        new String[] {sName}, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                _date = cursor.getString(4);
                db.close();
                return _date;
            }
            public String getToken(String sName, String sPassword) {
                String _date = null;
                SQLiteDatabase db = databasehelp.getReadableDatabase();
                Cursor cursor = db.query(TABLE_APP_USER, new String[] {KEY_USEID , KEY_USEUSE, KEY_USEPAS, KEY_USEPES
                                ,KEY_USEEST,KEY_USETOK ,KEY_USEKM}, KEY_USEUSE + "=?" + " and "+ KEY_USEPAS + "=?",
                        new String[] {sName, sPassword}, null, null, null, null);
                if (cursor != null){
                    cursor.moveToFirst();
                }
                _date = cursor.getString(5);
                db.close();
                return _date;
            }

        }

        /*@CLASS_PREMIOSQL*/
        public class AppPremioSql{
            public AppPremioSql(){ }
            public void deletePremio(){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                db.delete(TABLE_APP_PREMIO,null,null);
                db.close();
            }

            public void addPremio(PremioClass premioClass){
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(KEY_PREID,premioClass.getId());
                values.put(KEY_PREADD,premioClass.getAddress());
                values.put(KEY_PREDES,premioClass.getDescription());
                values.put(KEY_PREVAL,premioClass.getValue_km());
                db.insert(TABLE_APP_PREMIO, null, values);
                db.close();
            }

            public ArrayList<PremioClass> getAllItem(){
                ArrayList<PremioClass> orderClassList = new ArrayList<PremioClass>();
                String selectQuery = "SELECT  * FROM " + TABLE_APP_PREMIO;
                SQLiteDatabase db = databasehelp.getWritableDatabase();
                Cursor cursor = db.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()){
                    do {
                        PremioClass item = new PremioClass();
                        item.setId(cursor.getInt(cursor.getColumnIndex(KEY_PREID)));
                        item.setAddress(cursor.getString(cursor.getColumnIndex(KEY_PREADD)));
                        item.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PREDES)));
                        item.setValue_km(cursor.getInt(cursor.getColumnIndex(KEY_PREVAL)));
                        orderClassList.add(item);
                    } while (cursor.moveToNext());
                }
                db.close();
                return orderClassList;
            }

        }
    }

}
