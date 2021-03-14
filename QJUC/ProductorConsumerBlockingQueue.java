package QJUC;

import java.util.concurrent.*;
import java.util.*;
/*

LinkedBlockingQueue  : 容量很大 int大小的容量.很难满
*/

public class ProductorConsumerBlockingQueue {
        private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    
        public static void main(String[] args) {
            ExecutorService service = Executors.newFixedThreadPool(15);
            for (int i = 0; i < 5; i++) {
                service.submit(new Productor(queue));
            }
            for (int i = 0; i < 10; i++) {
                service.submit(new Consumer(queue));
            }
        }
    
    
        static class Productor implements Runnable {
    
            private BlockingQueue queue;
    
            public Productor(BlockingQueue queue) {
                this.queue = queue;
            }
    
            @Override
            public void run() {
                try {
                    while (true) {
                        Random random = new Random();
                        int i = random.nextInt();
                        System.out.println("生产者" + Thread.currentThread().getName() + "生产数据" + i);
                        queue.put(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    
        static class Consumer implements Runnable {
            private BlockingQueue queue;
    
            public Consumer(BlockingQueue queue) {
                this.queue = queue;
            }
    
            @Override
            public void run() {
                try {
                    while (true) {
                        Integer element = (Integer) queue.take();
                        System.out.println("消费者" + Thread.currentThread().getName() + "正在消费数据" + element);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } 
}