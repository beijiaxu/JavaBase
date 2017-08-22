package Concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限时等待锁
 * Created by Xu on 2017/7/2.
 */
public class TimeLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            if(lock.tryLock(5, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getId() + " get lock");
                Thread.sleep(6000);
            }
            else
                System.out.println("get lock failed:" + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) {
        TimeLock t = new TimeLock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();t2.start();
    }
}
