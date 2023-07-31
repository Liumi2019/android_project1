package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import learnopengl.fristgl.FirstOpenGl;
import learnserver.useaar.UserAARServer;
import use.refrence.demo.demo1;

/**
 * AppCompatActivity 与 Activity 界面不一致
 * AppCompatActivity 中含有 ActionBar
 * View.OnClickListener() interface 接口实现 button 类对象调用 onClick(View view)
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final demo1 demo1 = new demo1();

    static {
        System.loadLibrary("app");
    }

    private FirstOpenGl firstOpenGl;

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest1 = findViewById(R.id.btn_test1);
        btnTest1.setOnClickListener(this);

        Button btnTest2 = findViewById(R.id.btn_test2);
        btnTest2.setOnClickListener(this);

        Button btnTest3 = findViewById(R.id.btn_gl_test);
        btnTest3.setOnClickListener(this);

        Button btnAarService = findViewById(R.id.btn_aar_service);
        btnAarService.setOnClickListener(this);
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
            case R.id.btn_gl_test:
                testOpenGl();
                break;
            case R.id.btn_aar_service:
                UserAARServer userAARServer = new UserAARServer();
                userAARServer.testAarService(getApplicationContext());
                break;
            default:
                Log.e(TAG, "onClick error!");
        }
    }

    @Override
    protected void onPause() {
        if (glSurfaceView != null) {
            glSurfaceView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (glSurfaceView != null) {
            glSurfaceView.onResume();
        }
        super.onResume();
    }

    private void testNative() {
        testSort();
        demo1.printA();
    }


    private void testSort() {
        UseSort useSort = new UseSort();
        useSort.testSort();
    }

    private void testOpenGl() {
        Intent intent = getIntent();
        int type = intent.getIntExtra(FirstOpenGl.TYPE_NAME, FirstOpenGl.TYPE_JAVA);
        Log.i(TAG, "type: " + type);
        firstOpenGl = new FirstOpenGl(this, type);
        glSurfaceView = firstOpenGl.getGlSurfaceView();
        setContentView(glSurfaceView);
        Toast.makeText(this, firstOpenGl.getGlInfo(), Toast.LENGTH_SHORT).show();
    }

    private void testDog() {
        Dog dog = new Dog();
        dog.setAge(18);
        addAge(dog);
        Toast.makeText(this, dog.getInfo(), Toast.LENGTH_SHORT).show();
    }

    private void addAge(Dog dog) {
        dog.setAge(dog.getAge() + 3);
    }

    // Native
    private native String get();
}