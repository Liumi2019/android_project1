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

    public final void setName(String name) {
        Log.i(TAG, "setName");
        mName = name;
    }

    public void setAge(int age) {
        Log.i(TAG, "setName");
        mAge = age;
    }

    public abstract String getInfo();
}
