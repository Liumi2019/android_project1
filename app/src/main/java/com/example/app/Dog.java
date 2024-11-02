package com.example.app;

import android.util.Log;

/**
 * 动物 dog 继承 Animal
 */
public class Dog extends Animal {
    static private final String TAG = Dog.class.getSimpleName();

    public Dog() {
        super("Lili");
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public String getInfo() {
        Log.i(TAG, "getInfo");
        return "Name:" + this.getName() + "; age:" + this.getAge();
    }

    /**
     * 静态内部类
     * 多线程入口接口 Runnable 实现 线程入口函数
     */
    private static class RunnableDome implements Runnable {
        public void run() {
            for (int i = 0; i < 100; ++i) {
                Log.i(TAG, "Runnable Dome run " + i);
            }
        }
    }

    public int testThread() {
        RunnableDome runnableDome = new RunnableDome();

        Thread thread1 = new Thread(runnableDome);

        // 使用 lambda 表达式构造线程入口函数
        Runnable runnable = () -> {
            for (int i = 0; i < 100; ++i) {
                Log.i(TAG, "Runnable running " + i);
            }
        };
        Thread thread2 = new Thread(runnable);

        thread2.start();
        try {
            thread2.join();
        } catch (Exception ignore) {
            Log.e(TAG, "thread2 join running error");
        }
        thread1.start();
        try {
            thread1.join();
        } catch (Exception ignore) {
            Log.e(TAG, "thread2 join running error");
        }
        return 0;
    }
}
