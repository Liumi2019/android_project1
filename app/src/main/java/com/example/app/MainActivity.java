package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

// AppCompatActivity 与 Activity 界面不一致
// AppCompatActivity 中含有 ActionBar
// View.OnClickListener() interface 接口实现 button 类对象调用 onClick(View view)

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static {
        System.loadLibrary("app");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest1 = findViewById(R.id.btn_test1);
        btnTest1.setOnClickListener(this);
        Button btnTest2 = findViewById(R.id.btn_test2);
        btnTest2.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test1:
                Toast.makeText(this, get(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_test2:
                testNative();
                break;
        }
    }

    private void testNative() {
        testSort();
    }
    
    
    private void testSort() {
        UseSort useSort = new UseSort();
        useSort.testSort();
    }

    private void testDog() {
        Dog dog = new Dog();
        dog.setAge(18);
        addAge(dog);
        Toast toast = Toast.makeText(this, dog.getInfo(), Toast.LENGTH_SHORT);
        toast.show();
    }

    private void addAge(Dog dog) {
        dog.mAge += 3;
    }

    // Native
    private native String get();

}