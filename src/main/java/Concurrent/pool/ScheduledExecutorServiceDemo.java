package Concurrent.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Xu on 2017/7/4.
 */
public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，则调度也不会启动
        service.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() / 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0 , 2, TimeUnit.SECONDS);


    }
}
