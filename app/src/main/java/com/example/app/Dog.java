package com.example.app;

import android.util.Log;

public class Dog extends Animal {

    static private final String TAG = Dog.class.getSimpleName();

    // 多线程入口接口 Runnable 实现
    // 静态内部类
    private static class RunnableDome implements Runnable {
        public void run() {
            for (int i = 0; i < 100; ++i) {
                Log.i(TAG, "Runnable Dome run " + i);
            }
        }
    }

    public Dog() {
        super("Lili");
    }

    public Dog(String name) {
        super(name);
    }

    public String getInfo() {
        Log.i(TAG, "getInfo");
        String out = "Name:" + this.mName + "; age:" + this.mAge;
        RunnableDome runnableDome = new RunnableDome();
        Thread thread1 = new Thread(runnableDome);
        Runnable runnable = () -> {
            for (int i = 0; i < 100; ++i) {
                Log.i(TAG, "Runnable running " + i);
            }
        };
        Thread thread2 = new Thread(runnable);
        thread2.start();
        thread1.start();
        return out;
    }
}
