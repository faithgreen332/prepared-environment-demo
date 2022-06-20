package com.funpay.common.executors;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Leeko
 * @date 2022/2/17
 **/
public class AsyncExecutor {


    private static volatile ScheduledThreadPoolExecutor executor;

    /**
     * 默认两个线程
     */
    private final static int CORE_SIZE = 2;

    private AsyncExecutor() {
    }

    public static ScheduledThreadPoolExecutor getInstance() {
        if (executor == null) {
            synchronized (AsyncExecutor.class) {
                if (executor == null) {
                    executor = new ScheduledThreadPoolExecutor(CORE_SIZE, new ThreadFactory() {
                        private final AtomicInteger threadNumber = new AtomicInteger(1);

                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "AsyncExecutor-" + threadNumber.getAndIncrement());

                        }
                    });
                }
            }
        }
        return executor;
    }

    /**
     * 单次任务
     */
    public static void execOnce(Runnable task, long delay, TimeUnit unit) {
        if (task == null) {
            return;
        }
        executor.schedule(task, delay, unit);
    }
}
