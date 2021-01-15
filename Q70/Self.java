package Q70;

public class Self {
    
}
/*
线性dp  dp[i] = dp[i-1] + dp[i-2]   dp 只依赖于 前两个， 用 3个变量保存即可。

位于 i 位置的方法总数 =  位于 i-1 + 位于 i-2的和。

斐波那契类型。 


*/
class Solution {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        if(n == 3) return 3;
        int a = 2, b = 3;
        int ans = 0;
        for(int i = 4; i <= n; i++){
            ans = a + b;
            a = b;
            b = ans;
        }
         return ans;
    }
}