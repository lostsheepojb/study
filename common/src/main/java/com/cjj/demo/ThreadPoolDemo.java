package com.cjj.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cjj
 * @date 2022/12/2 11:22
 * @description 线程池的创建与使用实例
 *
 * 创建线程池参数说明
 * 1.corePoolSize：核心线程数，线程池中最小的线程数量
 * 2.maximumPoolSize：最大线程数量
 * 3.keepAliveTime：非核心空闲线程存活时间
 * 4.unit：keepAliveTime的时间单位
 * 5.workQueue：工作队列，当线程都在执行任务时，新增的任务放在工作队列中等待。
 *              可选队列：ArrayBlockingQueue（数组的有界阻塞队列）、
 *                       LinkedBlockingQueue（链表的无界阻塞队列）、
 *                       SynchronousQueue（一个不缓存任务的阻塞队列）、
 *                       PriorityBlockingQueue（具有优先级的无界阻塞队列）、
 *                       DelayQueue（这是一个无界阻塞延迟队列）
 * 6.threadFactory：线程工厂，创建一个线程工厂用来创建线程，可以用来设定线程名、是否为daemon线程等。默认有两种工厂选择，也可自定义工厂
 *                  可选工厂（不推荐使用）：Executors.privilegedThreadFactory()、
 *                                        Executors.defaultThreadFactory()
 * 7.handler：拒绝策略，当工作队列满后，对任务要执行的策略。默认有四种策略选择，也可自定义拒绝策略
 *            可选策略：AbortPolicy（丢弃任务并抛出 RejectedExecutionException异常）、
 *                     DiscardPolicy（丢弃任务，但是不抛出异常）、
 *                     DiscardOldestPolicy（丢弃队列最前面的任务，然后加入队列）、
 *                     CallerRunsPolicy（由调用线程（即提交任务给线程池的线程）处理该任务）
 *
 * tip1：可通过Executors工具类快速创建线程池，但不建议使用，因为该工具类的参数设置有导致内存溢出的风险
 * tip2：核心线程数量设置建议，IO密集型任务核心线程数=CPU核数的两倍、CPU密集型任务核心线程数=CPU的核心数。
 *
 **/
public class ThreadPoolDemo {

    /**
     * CPU核数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * IO处理线程数
     */
    private static final int IO_MAX = Math.max(2, CPU_COUNT * 2);


    /**
     * 创建IO密集型任务线程
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor createThreadPoolTest() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(IO_MAX, IO_MAX, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new MyThreadFactory("test"), new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }

    /**
     * 自定义线程工厂类
     */
    static class MyThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory(String threadPoolName) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + threadPoolName + "--thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            //创建线程，并设置线程分组，名称等
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            //设置为非守护线程
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            //设置线程优先级
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    public static void main(String[] args) {
        //1.创建线程池
        ThreadPoolExecutor threadPool = createThreadPoolTest();
        //2.提交任务到线程池执行
        threadPool.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("执行任务");
            } catch (Exception e) {
                System.out.println("执行任务出错");
            }
        });
        //3.获取线程池状态
        //3.1活跃的线程数量
        System.out.println("活跃的线程数量：" + threadPool.getActiveCount());
        //4.任务执行完后关闭线程池
        threadPool.shutdown();
    }
}
