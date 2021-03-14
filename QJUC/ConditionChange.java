package QJUC;

import java.util.*;

public class ConditionChange {
    private static List<String> lockObject = new ArrayList();
    
    
    public static void main(String[] args) {
        Consumer consumer1 = new Consumer(lockObject);
        Consumer consumer2 = new Consumer(lockObject);
        Productor productor = new Productor(lockObject);
        consumer1.start();
        consumer2.start();
        productor.start();
    }
    
    
    static class Consumer extends Thread {
        private List<String> lock;
    
        public Consumer(List lock) {
            this.lock = lock;
        }
    
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    //这里使用if的话，就会存在wait条件变化造成程序错误的问题
                    /*
                        这个问题很经典，生产者生产后，会唤醒全部冻结的消费者，因为是在wait处丢失的锁，所以如果消费者1苏醒后
                        会立刻消费掉。而消费者2也会紧跟其后去进行消费，都不会再去if判断是否为空。造成错误.
                    */
                    if (lock.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " list为空");
                        System.out.println(Thread.currentThread().getName() + " 调用wait方法");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName() + "  wait方法结束");
                    }
                    String element = lock.remove(0);
                    System.out.println(Thread.currentThread().getName() + " 取出第一个元素为：" + element);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    
    }
    
    
    static class Productor extends Thread {
        private List<String> lock;
    
        public Productor(List lock) {
            this.lock = lock;
        }
    
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 开始添加元素");
                lock.add(Thread.currentThread().getName());
                lock.notifyAll();
            }
        }
    
    }
}
