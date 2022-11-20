package com.example.app;

import android.util.Log;

public class Dog extends Animal {

    private final String TAG = Dog.class.getName();

    public Dog() {
        super("Lili");
    }

    public Dog(String name) {
        super(name);
    }

    public void setAge() {
        mAge = 12;
    }

    public void setName() {
        mName = "Lili";
    }


    public String getInfo() {
        Log.i(TAG, "getInfo");
        String out = "Name:" + this.mName + "; age:" + this.mAge;
        return out;
    }
}
