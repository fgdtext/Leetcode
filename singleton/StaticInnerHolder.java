package singleton;



/*
利用静态代码只加载一次的特性，直接完成对
内部类是为了 限制对外部类的创建。因为内部类是静态的，只有在加载的时候才会执行。
*/
public class StaticInnerHolder {
    private StaticInnerHolder(){}
    private static StaticInnerHolder getInstance(){
        return InnerClass.holder;   // 调用该方法时才会加载内部类，完成单例的创建。
    }
    public static class InnerClass{
        private static final StaticInnerHolder holder = new StaticInnerHolder();
    }
}


