package javalearn.thread;

import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 学习创建线程的方法
 */
public class ThreadMaker {
    private static final String TAG = ThreadMaker.class.getSimpleName();

    public static final int new_thread_runnable = 1;

    public static final int new_thread_thread = 2;

    public static final int new_thread_callable = 3;

    private int mCreateType = new_thread_runnable;

    Thread mThread = null;

    public ThreadMaker() {
    }

    public void runThread(int type) {
        Log.i(TAG, "type: " + type);

        if (type == new_thread_callable) {
            FutureTask<Long> oneTask = new FutureTask<Long>(new CallableThread(900L));
            mThread = new Thread(oneTask);
            mThread.setName("callable");
            mThread.start();
            try {
                // 调用 Future 的 get() 方法阻塞获取结果，同时记得捕获异常
                long result = oneTask.get();
            } catch (RuntimeException | ExecutionException | InterruptedException e) {
                Log.w(TAG, "Exception");
            }
            return;
        }

        mCreateType = type;
        createThread();
        if (mThread == null) {
            Log.w(TAG, "please create thread first...");
            return;
        }
        mThread.start();
    }

    private void createThread() {
        if (mCreateType == new_thread_runnable) {
            mThread = new Thread(new RunnableTread(500L));
            mThread.setName("runnable");
        } else if (mCreateType == new_thread_thread) {
            mThread = new ThreadThread(800L);
            mThread.setName("thread");
        }
    }
}
