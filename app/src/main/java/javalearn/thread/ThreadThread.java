package javalearn.thread;

import android.util.Log;

public class ThreadThread extends Thread {
    private static final String TAG = ThreadThread.class.getSimpleName();

    private final long mSleepTime;

    public ThreadThread(long sleepTime) {
        mSleepTime = sleepTime;
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
}
