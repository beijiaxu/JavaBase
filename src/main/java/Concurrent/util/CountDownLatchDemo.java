package Concurrent.util;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Xu on 2017/7/3.
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        try{
            //模拟检查任务
            long time = new Random().nextInt(10) * 1000;
            Thread.sleep(time);
            System.out.println("check complete, cost:" + time);
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.execute(demo);
        }
        end.await();
        //发射火箭
        System.out.println("Fire!");
        exec.shutdown();
    }
}
