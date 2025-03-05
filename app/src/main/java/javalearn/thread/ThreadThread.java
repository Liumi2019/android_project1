package javalearn.thread;

import android.util.Log;

import androidx.annotation.NonNull;

public class ThreadThread extends Thread {
    private static final String TAG = ThreadThread.class.getSimpleName();

    private final long mSleepTime;

    private final String mThreadName;

    public ThreadThread(@NonNull String threadName, long sleepTime) {
        mSleepTime = sleepTime;
        mThreadName = threadName;
    }

    public ThreadThread(@NonNull String threadName) {
        mSleepTime = 0;
        mThreadName = threadName;

    }

    @Override
    public void run() {
        if (mSleepTime <= 0) {
            return;
        }

        Log.i(TAG, "start sleep thread name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(mSleepTime);
        } catch (InterruptedException e) {
            Log.w(TAG, "sleep exception thread name: " + Thread.currentThread().getName());
        }
        Log.w(TAG, "sleep " + mSleepTime + "ms");
    }

    public static Thread createThread(@NonNull String threadName, long sleepTime) {
        return new ThreadThread(threadName, sleepTime);
    }
}
