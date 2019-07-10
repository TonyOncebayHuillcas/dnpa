package com.example.dpna.ClassTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import static android.content.ContentValues.TAG;

public class ChronometerTask extends Service {
    Context context;
    private static final String TAG_SERVICE = "SERVICE";

    public static final String CREATE_SERVICE = "CREATE_SERVICE";

    public static final String START_SERVICE = "START_SERVICE";

    public static final String STOP_SERVICE = "STOP_SERVICE";

    public ChronometerTask() {
        Log.i(TAG, "onCreate!");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = getApplicationContext();
        if (intent != null) {
            String action = intent.getAction();

            switch (action) {
                case CREATE_SERVICE:
                    createService();
                    break;
                case START_SERVICE:
                    startService();
                    break;
                case STOP_SERVICE:
                    stopServices();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void createService(){
        Log.d(TAG_SERVICE, "Create service.");

    }

    private void startService() {
        Log.d(TAG_SERVICE, "Start service.");


    }

    private void stopServices() {
        Log.d(TAG_SERVICE, "Stop service.");
        //Toast.makeText(context,"Deteniendo el servicio",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
