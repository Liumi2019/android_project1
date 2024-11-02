package com.example.app;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class UseSort {

    /**
     * 使用实现 Comparable<Human> 接口实现在大多数情况不合适，使用Lambda表达式更合适
     */
    private class Human implements Comparable<Human> {
        private final String name;
        private final int age;
        private final String TAG = Human.class.getSimpleName();

        Human(@NonNull String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }


        public void printInfo() {
            Log.i(TAG, "name: " + name + ", age: " + age);
        }

        @Override
        public int compareTo(Human human) {
            return this.age - human.age;
        }
    } // Class Human

    public UseSort() {

    }

    public void testSort() {
        Human h1 = new Human("aa", 20);
        Human h2 = new Human("bb", 30);
        Human h3 = new Human("cc", 25);
        Human h4 = new Human("dd", 5);

        Human[] H4 = {h1, h2, h3, h4};
        Arrays.sort(H4);

        for (Human hi : H4) {
            hi.printInfo();
        }
    }

}
