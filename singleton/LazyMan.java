package singleton;

public class LazyMan {
    private LazyMan(){} // 私有化构造器

    private volatile static LazyMan lazyMan;
    public LazyMan getInstance(){
        if(lazyMan == null){
            synchronized(LazyMan.class){
                if(lazyMan == null){
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }
}