package com.example.app;

import android.util.Log;

public abstract class Animal {

    private static final String TAG = Animal.class.getName();
    public String mName;
    public int mAge;

    public Animal(String name) {
        mName = name;
        mAge = 0;
    }

    // 子类禁止重写
    public final void setName(String name) {
        Log.i(TAG, "setName");
        mName = name;
    }

    // 子类禁止重写
    public final void setAge(int age) {
        Log.i(TAG, "setName");
        mAge = age;
    }

    // 虚函数接口
    public abstract String getInfo();
}
