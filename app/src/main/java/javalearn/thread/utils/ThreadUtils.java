package javalearn.thread.utils;

import android.util.Log;

/**
 * 线程工具类
 * 1. 创建线程
 * 2. 线程停止
 */
public class ThreadUtils {
    /**
     * 创建线程
     *
     * @param threadName thread name
     * @param runnable   runnable
     * @return Thread
     */
    public static Thread createThread(String threadName, Runnable runnable) {
        return createThread(threadName, runnable, getDefaultUncaughtExceptionHandler());
    }

    /**
     * 创建线程
     *
     * @param threadName thread name
     * @param runnable   runnable
     * @param handler    线程未捕获异常处理
     * @return Thread
     */
    public static Thread createThread(String threadName, Runnable runnable, Thread.UncaughtExceptionHandler handler) {
        Thread thread = new Thread(runnable, threadName);
        thread.setUncaughtExceptionHandler(handler);
        return thread;
    }

    /**
     * 获取默认的未捕获异常处理
     *
     * @return 默认的未捕获异常处理 Handler
     */
    public static Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return (t, e) -> Log.e("uncaught exception in thread name: " + t.getName() + " :", e.toString());
    }

    public static void shutdownGracefully(final Thread thread) {
        shutdownGracefully(thread, 0);
    }

    public static void shutdownGracefully(final Thread thread, final long timeout) {
        if (thread == null) {
            return;
        }

        if (thread.isAlive()) {
            try {
                thread.interrupt();
                thread.join(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private ThreadUtils() {
        // 禁止实例化
    }
}
