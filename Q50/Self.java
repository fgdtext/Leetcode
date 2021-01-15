package Q50;

class Solution {
    // 迭代好理解
    public double myPow(double x, int n) {
        if(n == 0) return 1;

        // 浮点数不能这么比较
        //if(x == 1) return 1;
        //  n > 0 ?  diedai(x, n) :
        return n > 0 ?  diedai(x, n) : 1.0/diedai(x, -n);
    }
    public double diedai(double x, int n){
        if(n == 0) return 1;
        double temp = diedai(x, n/2);
        return n % 2 == 0 ? temp*temp : temp * temp * x;
    }
}

public class Self{
    public static void main(String[] args) {

        int a = 2147483647;
        System.out.println(-a);    // 仍然输出 -2147483648 (负的) 因为其是小负数(整数范围)。
        // 整数范围    -2147483648  ---   2147483647  
    }
}