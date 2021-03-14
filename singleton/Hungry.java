package singleton;


// 饿汉式 提前加载  浪费资源  无法避免反射破解  类加载时直接创建.
public class Hungry {

    // 可能会浪费空间   类加载就开始占用 空间。
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];
    private Hungry(){}  // 私有化构造器
    private static Hungry HUNGRY = new Hungry();  // 跟随类加载完成静态变量的初始化。
    public static Hungry getInstance(){   
        return HUNGRY;          // 静态方法获取 单例  暴露出口
    }   
}