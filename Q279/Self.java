package Q279;

import java.util.Arrays;

public class Self {
    
}
/* 方法1 ： 还算是写出来了， 但是 时间不是最优。  最优可能是个贪心算法。应该是贪心了。
            可是怎么 贪。。。？？？
 1 ~ sqrt(n) ： 背最少的包(可以重复背包)，构成 n

dp[n] -- dp[n-k*k]

时间 o(n^(3/2))   内循环是 根号n
*/
class Solution {
    public int numSquares(int n) {
    //   1 ~ sqrt(n) ： 背最少的包，构成 n
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 1;
        dp[0] = 0;
        if(n == 1) return 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            int d = (int)Math.sqrt(i);  // 不要放在循环上，否则会算很多次。
            for(int k = 1; k <= d; k++){
                dp[i] = Math.min(dp[i], dp[i - k*k]+1);  // 空间不能优化了，因为依赖项不明确。
            }
        }
        return dp[n];
    }
}
