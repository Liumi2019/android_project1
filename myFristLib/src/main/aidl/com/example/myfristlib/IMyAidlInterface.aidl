// IMyAidlInterface.aidl
package com.example.myfristlib;

import com.example.myfristlib.MyCallBack;
import com.example.myfristlib.MyDataType;

interface IMyAidlInterface {
    String getPackagename();

    void registerCallBack(in MyDataType data, MyCallBack callback);

    void unregisterCallBack(MyCallBack callback);
}