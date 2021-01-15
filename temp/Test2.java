package temp;

public class Test2 {

    // 定义  函数 f(x)
    static double f(double x){
        return x*(Math.pow(Math.E, -x*x));
    }
    public static void main(String[] args) {

        // 积分区间为
        int a = 1, b = 2;

        // 函数上界 必然小于 2.  且在积分区间内必有  0 < f < 2
        int d = 2;

        // 假设投掷次数 为 n
        int n = 10000000;

        int count = 0;
        for(int i = 0; i < n; i++){
            double x = (b-a)*Math.random() + a;
            double y = d * Math.random();
            if(y <= f(x)){
                count++;
            }
        }
        double result = d*(b-a)*count*1.0/n;
        System.out.println("结果是" + result);
        System.out.println("结果是" + f(1));
        System.out.println("结果是" + f(2));
        // 结果是0.1750286
        // 结果是0.1748266
        // 结果是0.1745938
        // 结果是0.1748476
    }
}