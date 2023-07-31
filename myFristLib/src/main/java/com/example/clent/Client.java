package com.example.clent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.myfristlib.FristLib;
import com.example.myfristlib.IMyAidlInterface;

public class Client {
    private static final String TAG = Client.class.getSimpleName();

    private static final String packageName = "com.example.myfristlib";

    private static final String serviceName = "com.example.myfristlib.FristLib";

    private IMyAidlInterface binderSer;

    private Context context;


    public Client(Context context) {
        this.context = context;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderSer = IMyAidlInterface.Stub.asInterface(service);
            try {
                Log.i(TAG, "name: " + binderSer.getPackagename());
            } catch (RemoteException e) {
                Log.e(TAG, "binderService error");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        Log.i(TAG, "onServiceDisconnected");
        }
    };

    public void startBinderService() {
        Intent intent = new Intent(context.getApplicationContext(), FristLib.class);
        boolean isBinder = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "is binder: " + isBinder);
    }

}
