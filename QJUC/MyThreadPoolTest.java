package QJUC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(3, 10, 60, 
                                                TimeUnit.SECONDS, 
                                                new SynchronousQueue<Runnable>());
        for(int i = 0; i < 100; i++){
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getId() + ":::::" + Thread.currentThread().getName());
                try {
                  //  Thread.sleep(1000);
                } catch (Exception e) {
                    //TODO: handle exception
                }
            });
        }
        
    }
}