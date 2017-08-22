package Concurrent.util;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Contended
public class LongAdderDemo {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 100_0000;

    private AtomicLong acount = new AtomicLong(0L);
    private LongAdder lacount = new LongAdder();
    private long count = 0;

    static CountDownLatch cdlsync = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdlatomic = new CountDownLatch(TASK_COUNT);
    static CountDownLatch cdladdr = new CountDownLatch(TASK_COUNT);

    private synchronized long inc() {
        return ++count;
    }

    private synchronized long getCount() {
        return count;
    }

    public class SyncThread implements Runnable {
        protected String name;
        protected long startTime;
        LongAdderDemo out;

        public SyncThread(long startTime, LongAdderDemo out) {
            this.startTime = startTime;
            this.out = out;
        }

        @Override
        public void run() {
            long v = out.getCount();
            while (v<TARGET_COUNT)
                v = out.inc();

            long endTime = System.currentTimeMillis();
            System.out.println("SyncThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdlsync.countDown();
        }
    }

    public void testSync() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncThread syncThread = new SyncThread(startTime, this);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.execute(syncThread);
        }
        cdlsync.await();
        exe.shutdown();
    }




    public class AtomicThread implements Runnable {
        protected String name;
        protected long startTime;

        public AtomicThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = acount.get();
            while (v<TARGET_COUNT)
                v = acount.incrementAndGet();

            long endTime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdlatomic.countDown();
        }
    }

    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicThread atomicThread = new AtomicThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.execute(atomicThread);
        }
        cdlatomic.await();
        exe.shutdown();
    }



    public class LongAddrThread implements Runnable {
        protected String name;
        protected long startTime;

        public LongAddrThread(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            long v = lacount.sum();
            while (v<TARGET_COUNT) {
                lacount.increment();
                v = lacount.sum();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("LongAddrThread spend:" + (endTime - startTime) + "ms" + " v=" + v);
            cdladdr.countDown();
        }
    }

    public void testAtomicLong() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        LongAddrThread longAddrThread = new LongAddrThread(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.execute(longAddrThread);
        }
        cdladdr.await();
        exe.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testAtomicLong();
    }


}
