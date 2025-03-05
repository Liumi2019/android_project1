package javalearn.thread.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂：创建一些列带有特殊线程名的线程
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicInteger threadNumber = new AtomicInteger(0);
    private final String namePrefix;

    public ThreadFactoryImpl(@NonNull String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, namePrefix + threadNumber.getAndIncrement());
        thread.setUncaughtExceptionHandler(ThreadUtils.getDefaultUncaughtExceptionHandler());
        return thread;
    }
}
