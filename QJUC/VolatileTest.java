package QJUC;

class Mycount{
    volatile int a = 0;
    public void add(){
        a++;
    }
}

public class VolatileTest{
    public static void main(String[] args) {
        Mycount co = new Mycount();
        new Thread(()->{
            for(int i = 0; i < 100; i++){
                co.add();
            }
        }).start();
        new Thread(()->{
            for(int i = 0; i < 100; i++){
                co.add();
            }
        }).start();
        System.out.println(co.a);
    }
}