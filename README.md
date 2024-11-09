# 学习 Android 开发

## Java 并发编程
**package：** javalearn.thread
**创建线程接口类：** ThreadMaker
创建线程创建线程的方法：

### 实现 Runnable 接口

实现 Runnable 接口，作为线程的入参
``` java
public class RunnableTread implements Runnable {
}

new Thread(new RunnableTread());

```

### 继承 Thread 类

``` java
public class ThreadTread extends Thread {
}

new ThreadTread();
```

实现 Runnable 接口和继承 Thread 类，创建多线程，但不能有返回值和抛异常。实现 Callable 接口可以有返回值和抛异常。

### 实现 Callable 接口
Callable + Future 可返回结果和捕获异常。

``` java
public class CallableThread implements Callable<Long> {
}

FutureTask<Long> oneTask = new FutureTask<Long>(myCallable);

Thread t = new Thread(oneTask);

 try {
    // 调用 Future 的 get() 方法阻塞获取结果，同时记得捕获异常
    result = future.get();
} catch (ExecutionException e) {
    e.printStackTrace();
}
```


## 学习 jni 工具


