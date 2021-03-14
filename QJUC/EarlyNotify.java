package QJUC;



public class EarlyNotify {

    private static String lockObject = "";

    public static void main(String[] args) {
        // WaitThread waitThread = new WaitThread(lockObject);
        // NotifyThread notifyThread = new NotifyThread(lockObject);
        // notifyThread.start();
        // try {
        //     Thread.sleep(3000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // waitThread.start();


        new Thread(()->{
            testa();
        }).start();
        synchronized(EarlyNotify.class){
            for(int i = 0; i < 100000; i++){
                System.out.println(i + Thread.currentThread().getName());
            }
        }
    }
    public void teest(){
            // The standard idiom for calling the wait method in Java 
        // sharedObject 
        Object sharedObject = new Object();
        boolean condition = true;
        
      
    }

    public static synchronized void testa(){
        for(int i = 0; i < 100000; i++){
            System.out.println(i + Thread.currentThread().getName());
        }
    }

    static class WaitThread extends Thread {
        private String lock;

        public WaitThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            boolean condition = true;
            synchronized (lock) {
                try {
                    while (condition) { //不满足则等待
                        lock.wait(); 
                        // (Releases lock, and reacquires on wakeup) 
                    } 
                    
                    // 生产或者消费 
                    // 唤醒该锁上所有冻结的线程
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class NotifyThread extends Thread {
        private String lock;

        public NotifyThread(String lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "  进去代码块");
                System.out.println(Thread.currentThread().getName() + "  开始notify");
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "   结束开始notify");
            }
        }
    }
}