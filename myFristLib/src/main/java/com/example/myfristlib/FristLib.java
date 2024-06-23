package com.example.myfristlib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myfristlib.MyCallBack;

public class FristLib extends Service {
    private static final String TAG = FristLib.class.getSimpleName();

    private RemoteCallbackList<MyCallBack> callBacks = null;

    private final Thread thread = new Thread(()->{
        for (int i = 0; i < 100; i++) {
            updateCallback(i);
            Log.i(TAG, "time:" + i * 100);
        }
    });

    public FristLib() {
        Log.e(TAG, "FristLib");
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        thread.start();
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

        @Override
        public void registerCallBack(MyDataType data, MyCallBack callback) throws RemoteException {
            if (data.getId() == 50) {
                Log.i(TAG, "data = " + data);
            }
            register(callback);
        }

        @Override
        public void unregisterCallBack(MyCallBack callback) throws RemoteException {
            unregister(callback);
        }
    }

    private synchronized void updateCallback(int time) {
        int len = -1;
        if (callBacks != null) {
            len = callBacks.beginBroadcast();
            Log.i(TAG, "callBacks size:" + len);
            try {
                for (int j = 0; j < len; j++) {
                    callBacks.getBroadcastItem(j).getNum(time);
                }
            } catch (RemoteException ignore) {
                Log.e(TAG, "callBacks error");
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            Log.w(TAG, "sleep error");
        }

        if (callBacks != null && len >0) {
            callBacks.finishBroadcast();
        }
    }

    private synchronized void register(MyCallBack callback) {
        callBacks = new RemoteCallbackList<>();
        callBacks.register(callback);
    }

    private synchronized void unregister(MyCallBack callback) {
        if (callBacks != null) {
            callBacks.unregister(callback);
            callBacks = null;
        }
    }
}
