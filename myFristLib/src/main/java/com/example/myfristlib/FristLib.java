package com.example.myfristlib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class FristLib extends Service {
    private static final String TAG = FristLib.class.getSimpleName();

    public FristLib() {
        Log.e(TAG, "FristLib");
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public String getName() {
        Log.e(TAG, "getName");
        return TAG;
    }

    private class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getPackagename() throws RemoteException {

            return "FristLib, Service";
        }
    }

}
