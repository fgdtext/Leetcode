package singleton;

public enum EnumSingle  {
    INSTANCE; // 这就是个实例只会自己创建自己
    private EnumSingle(){}
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

// 最安全 ,反射不能突破