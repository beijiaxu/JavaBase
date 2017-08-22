package Concurrent.base;

/**
 * Created by Xu on 2017/6/22.
 */
public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true) {
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Interrupted");
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted when sleep");
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        });
        t1.start();
        Thread.sleep(500);
        t1.interrupt();
        System.exit(0);
    }
}
