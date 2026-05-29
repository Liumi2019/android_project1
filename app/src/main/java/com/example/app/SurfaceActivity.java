package com.example.app;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import learnopengl.secondgl.SecondGl;

public class SurfaceActivity extends Activity implements SurfaceHolder.Callback {
    private static final String TAG = "SurfaceActivity";

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        // SurfaceView 设置透明
        mSurfaceView = findViewById(R.id.surfaceView);
        if (mSurfaceView == null) {
            Log.e(TAG, "mSurfaceView is null");
            return;
        }
        mSurfaceView.setZOrderOnTop(true);
        mSurfaceHolder = mSurfaceView.getHolder();
        if (mSurfaceHolder == null) {
            Log.e(TAG, "mSurfaceHolder is null");
            return;
        }
        mSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        mSurfaceHolder.addCallback(this);
    }

    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (mSurfaceHolder == null) {
            Log.e(TAG, "mSurfaceHolder is null in surfaceCreated");
            return;
        }
        SecondGl secondGl = new SecondGl();
        secondGl.setSurface(mSurfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged: width=" + width + " height=" + height);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.i(TAG, "surfaceDestroyed");
        mSurfaceHolder = null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
