package Concurrent.nonLock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Xu on 2017/7/11.
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        AddThread addThread = new AddThread();
        for (int j = 0; j < 10; j++) {
            ts[j] = new Thread(addThread);
        }
        for (int j = 0; j < 10; j++) {
            ts[j].start();
        }
        for (int j = 0; j < 10; j++) {
            ts[j].join();
        }
        System.out.println(i);
    }
}
