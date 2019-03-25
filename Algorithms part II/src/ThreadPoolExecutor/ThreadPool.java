package ThreadPoolExecutor;

import edu.princeton.cs.algs4.StdOut;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    StdOut.println("zxy" + "线程" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void singleThreadPool() {
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    StdOut.println("zxy" + "线程" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    StdOut.println("zxy" + "线程" + threadName + ",正在执行第" + index + "个任务");
                    try {
                        long time = index * 1000;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void scheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //  延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {

            }
        }, 2, TimeUnit.SECONDS);
        //  延迟1秒后,每隔2秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    public static void singleThreadScheduledExecutor() {
        ScheduledExecutorService singleThreadScheduledPool = Executors.newSingleThreadScheduledExecutor();
        //  延迟1秒后,每隔2秒执行一次该任务

        singleThreadScheduledPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                StdOut.println("zxy" + "线程" + threadName + ",正在执行");
            }
        }, 3, 2, TimeUnit.SECONDS);
    }

    private static final int OL = 5;

    /**
     * 自定义线程池ThreadPoolExecutor,创建一个基于{@code PriorityBlockingQueue}
     * 实现的线程池
     */
    public static void priorityThreadPool() {
        ExecutorService priorityThreadPool = new ThreadPoolExecutor(
                3, 3, OL, TimeUnit.SECONDS, new PriorityBlockingQueue<>()
        );
        for (int i = 1; i <= 10; i++) {
            final int priority = i;
            priorityThreadPool.execute(new PriorityRunnable(priority) {
                @Override
                public void doSth() {
                    String threadName = Thread.currentThread().getName();
                    StdOut.println("线程:" + threadName + "正在执行优先级为: " + priority + "的任务");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    /**
     * 带有暂停功能的线程池
     */
    public static void pausableThreadPoolExecutor(){
            PausableThreadPoolExecutor pausableThreadPoolExecutor = new PausableThreadPoolExecutor(
                    1, 1, OL, TimeUnit.SECONDS, new PriorityBlockingQueue<>());
            for (int i = 1; i <= 100; i++){
                final int priority = i;
                pausableThreadPoolExecutor.execute(new PriorityRunnable(i) {
                    @Override
                    public void doSth() {
                    }
                });
            }
    }


    public static void main(String[] args) {
        ExecutorService executorService = new MyThreadPoolExecutor(1, 1, OL, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>() );
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                StdOut.println(Thread.currentThread().getName() + "正在执行任务");
            }
        });

    }
}
