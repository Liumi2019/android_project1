package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("app");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dog dog = new Dog();
        dog.setAge();

        addAge(dog);

        Toast toast = Toast.makeText(this, dog.getInfo(), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void addAge(Dog dog) {
        dog.mAge += 3;
    }

    // Native
    public native String get();

}