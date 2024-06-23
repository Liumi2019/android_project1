package com.example.app;

import android.util.Log;

/**
 * 动物虚基类 定义动物的基本属性
 * 1. 名字
 * 2. 年龄
 */
public abstract class Animal {
    private static final String TAG = Animal.class.getName();

    private String mName;

    private int mAge;

    public Animal() {
        mName = null;
        mAge = 0;
    }

    /**
     * @param name name
     */
    public Animal(String name) {
        mName = name;
        mAge = 0;
    }

    public Animal(String name, int age) {
        mName = name;
        mAge = age;
    }

    /**
     * 子类禁止重写
     * 设置动物的名字
     *
     * @param name 动物名字
     */
    public final void setName(String name) {
        Log.i(TAG, "setName");
        mName = name;
    }

    /**
     * 子类禁止重写
     * 获取动物的名字
     *
     * @return 动物名字
     */
    public final String getName() {
        Log.i(TAG, "setName");
        return mName;
    }

    /**
     * 子类禁止重写
     * 设置动物的年龄
     *
     * @param age 动物年龄
     */
    public final void setAge(int age) {
        Log.i(TAG, "setName");
        mAge = age;
    }

    /**
     * 子类禁止重写
     * 获取动物的年龄
     *
     * @return 动物年龄
     */
    public final int getAge() {
        Log.i(TAG, "getName");
        return mAge;
    }

    /**
     * 获取动物的信息
     *
     * @return 动物的信息
     */
    public abstract String getInfo();
}
