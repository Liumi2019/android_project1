package com.example.clent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.myfristlib.FristLib;
import com.example.myfristlib.IMyAidlInterface;
import com.example.myfristlib.MyCallBack;
import com.example.myfristlib.MyDataType;

public class Client {
    private static final String TAG = Client.class.getSimpleName();

    private static final String packageName = "com.example.myfristlib";

    private static final String serviceName = "com.example.myfristlib.FristLib";

    private IMyAidlInterface binderSer;

    private final Context context;


    public Client(Context context) {
        this.context = context;
    }

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binderSer = IMyAidlInterface.Stub.asInterface(service);
            // 1. 测试字符串传递
            try {
                Log.i(TAG, "name: " + binderSer.getPackagename());
            } catch (RemoteException ignore) {
                Log.e(TAG, "binderService getPackagename() error");
            }

            try{
                MyDataType data = new MyDataType();
                data.setId(50);
                binderSer.registerCallBack(data, callBack);
                Log.i(TAG, "registerCallBack");
            } catch (RemoteException ignore) {
                Log.e(TAG, "binderService registerCallBack() error");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }
    };

    private final MyCallBack callBack = new MyCallBack.Stub() {
        @Override
        public int getNum(int time) throws RemoteException {
            Log.i(TAG, "get time=" + time + "from service!!!");

            if (time > 90) {
                binderSer.unregisterCallBack(callBack);
                Log.i(TAG, "unregisterCallBack");
            }
            return 0;
        }
    };

    public void startBinderService() {
        Intent intent = new Intent(context.getApplicationContext(), FristLib.class);
        boolean isBinder = context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "is binder: " + isBinder);
    }
}
