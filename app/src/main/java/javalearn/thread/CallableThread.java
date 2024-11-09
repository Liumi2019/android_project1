package javalearn.thread;

import android.util.Log;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<Long> {
    private static final String TAG = CallableThread.class.getSimpleName();

    private final long mSleepTime;

    public CallableThread(long sleepTime) {
        mSleepTime = sleepTime;
    }

    @Override
    public Long call() throws Exception {
        if (mSleepTime <= 0) {
            // 主动抛异常，需要告知调用者必须处理异常
            throw new RuntimeException();
        }

        Log.i(TAG, "start sleep thread name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(mSleepTime);
        } catch (InterruptedException e) {
            Log.w(TAG, "sleep exception thread name: " + Thread.currentThread().getName());
        }
        Log.w(TAG, "sleep " + mSleepTime + "ms");

        return mSleepTime;
    }
}
