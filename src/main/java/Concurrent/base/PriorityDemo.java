package Concurrent.base;

/**
 * Created by Xu on 2017/6/27.
 */
public class PriorityDemo {
    private static class HighPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if(count > 1000_0000) {
                        System.out.println("HighPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    private static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if(count > 1000_0000) {
                        System.out.println("LowPriority is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread high = new HighPriority();
        Thread low = new LowPriority();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
        Thread.sleep(50);
        System.out.println(HighPriority.count);
        System.out.println(LowPriority.count);
    }
}
