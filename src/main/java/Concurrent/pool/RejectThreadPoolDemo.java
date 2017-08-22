package Concurrent.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池和拒绝策略
 * Created by Xu on 2017/7/4.
 */
public class RejectThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10),
//                (r, executor1) -> System.out.println(r.toString() + " is discard"));
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            executor.submit(task);
//            Thread.sleep(10);
//        }

        //自定义ThreadFactory
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    System.out.println("create" + t);
                    return t;
                });
        for (int i = 0; i < 5; i++) {
            es.submit(task);
        }
    }
}
