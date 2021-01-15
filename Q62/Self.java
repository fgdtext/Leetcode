package Q62;

import java.util.Arrays;

public class Self {
    
}

/*

这是个杨辉三角形，每个位置的路径 = 该位置左边的路径 + 该位置上边的路径

记忆化搜索  记录 已经 计算过的 值
*/



class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return dfs(m-1, n-1,dp);
    }
    public int dfs(int i, int j, int[][] dp){
        if(dp[i][j] != 0) return dp[i][j];
        if(i < 0 || j < 0) return 0;
        if(i == 0 || j == 0) return 1;
        return dp[i][j] = dfs(i - 1, j,dp) + dfs(i, j - 1,dp);
    }
}

/*
dp 递推 ， 滚动数组。 注意 计算方向。

*/
class Self2 {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int j = 1; j < m; j++)
            for(int i = 1; i < n; i++){
                if(i == 1){
                    dp[i] = dp[i] + 1;
                }else{
                    dp[i] = dp[i] + dp[i-1];
                }
            }
        return dp[n - 1];
    }
}