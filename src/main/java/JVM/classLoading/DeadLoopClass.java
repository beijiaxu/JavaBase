package JVM.classLoading;

/**
 * 类加载耗时长导致多个进程阻塞
 */
public class DeadLoopClass {
    static  {
        if(true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true) {
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + " start");
            DeadLoopClass dlc = new DeadLoopClass();
            System.out.println(Thread.currentThread() + " run over");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}
