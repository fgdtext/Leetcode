package test;

public class Q1 {
    public static void main(String[] args) {
        Mian m = new Mian();
        m.fun(9,4);
    }
}
    /*
     背包容量为 y, 所以 硬币最大值就是 y. 再大就没有意义、
     dp[i][j]: 前 i个数，装到容量为j的背包中的方案数。
     则 dp[i][j] = dp[i-1][j] + dp[i-1][j-i];

    */
class Mian{
    public int fun(int y,int k){
        int[] dp = new int[y+1];
        // 前0个物品放入目标为0的背包中，只有一种方案。
        dp[0] = 1;
        // 这是一个恰好装满的问题，注意第一行后边的初始化。
        // 1 <= j <= y  dp[j] = 0. 方案数为0
        
        /*
            总共有多少个物品，我们不清楚，因为中间有有些值 i%k==0时，不能使用。
            所以我们假设有y个值，分别是 1,2,3...,y 那么表总共有 y+1行。但是因为不知道有多少个值不能使用。
            所以 表的行数不确定。
            这个题还可以提前通过 v[]数组，放入每一个可用的值。假设k = 4.
            那么v[]数组就是：v[] = {1,2,3,5,6,7,9,10,11,13}.没有4的倍数。
            这样就等价于 01背包了。

            下边的写法中，如果使用二维数组，那么 i%4==0的行都是非法行，也不会被依赖。
        */
        for(int i = 1; i <= y; i++){
            // 当i%k==0,说明没有 i这个物品。 
            if(i % k == 0) continue;
            for(int j = y; j >= i; j--){
                dp[j] = dp[j] + dp[j-i];
            }
        }
        System.out.println(dp[y]);
        return dp[y];
    }
}