package Concurrent.test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class UnsafeArrayList {
    static ArrayList<Object> al = new ArrayList<>();
    static class AddTask implements Runnable {

//        static ReentrantLock lock = new ReentrantLock();
//        static AtomicInteger integer = new AtomicInteger();
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 100_0000; i++) {
//                lock.lock();
//                int a = integer.incrementAndGet();
                al.add(new Object());
//                if(a % 1000 == 0)
//                    System.out.println(a);
//                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new AddTask(), "t1");
        Thread t2 = new Thread(new AddTask(), "t2");
        t1.start();t2.start();
        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();
    }
}
