package ThreadPoolExecutor;

import edu.princeton.cs.algs4.StdOut;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 继承类,扩展线程池
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {
    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                TimeUnit unit, BlockingQueue<Runnable> workQueue){
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r){
        super.beforeExecute(t, r);
        String threadName = t.getName();
        StdOut.println("线程: " + threadName + "准备执行任务! ");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        String threadName = Thread.currentThread().getName();
        StdOut.println("线程: " + threadName + "任务执行结束! ");
    }

    @Override
    protected void terminated() {
        super.terminated();
        StdOut.println("线程池结束");
    }
}
