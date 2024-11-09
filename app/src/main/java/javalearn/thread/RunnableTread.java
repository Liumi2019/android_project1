package javalearn.thread;

import android.util.Log;

/**
 * 创建线程方法1 实现 Runnable 接口
 */
public class RunnableTread implements Runnable {
    private static final String TAG = RunnableTread.class.getSimpleName();

    private final long mSleepTime;

    public RunnableTread(long sleepTime) {
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
