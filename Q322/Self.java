package Q322;

import java.util.Arrays;
/*
背包问题。

dp[i] : 凑够 i 需要的最少硬币。 

dp[i] = min(dp[i], dp[i-k]+1) k：coins  k <= i

*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        Arrays.fill(dp, 10000);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length && coins[j] <= i; j++){
                dp[i] = Math.min(dp[i], dp[i-coins[j]] + 1);
            }
        }
        /*
            如果能凑出来，则dp[amount]最终会依赖于 dp[0],这样必然是小于10000的数。
            而若凑不出来，则必然不会依赖于 dp[0], 否则一定能凑出来。不依赖于dp[0] 则 dp[amount] == 10000

        */
        return dp[amount] == 10000? -1 : dp[amount];
    }
}