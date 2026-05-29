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
            throw new RuntimeException("CallableThread: invalid sleep time " + mSleepTime);
        }

        Log.i(TAG, "start sleep thread name: " + Thread.currentThread().getName());
        try {
            Thread.sleep(mSleepTime);
        } catch (InterruptedException e) {
            Log.w(TAG, "sleep interrupted, thread name: " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
        Log.w(TAG, "sleep " + mSleepTime + "ms");

        return mSleepTime;
    }
}
