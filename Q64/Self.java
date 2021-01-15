package Q64;

import Q336.ans1;

public class Self {
    public static void main(String[] args) {
        int[][] t = {{1,3,1},{1,5,1},{4,2,1}};
        Solution s = new Solution();
        int ans = s.minPathSum(t);
        System.out.print(ans);
    }
}
/*
dp   和 Q62 基本一致。 转移过程，有一些差别

*/


class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        int[] dp0 = new int[m];
        dp[0] = grid[0][0];
        dp0[0] = dp[0];
        // 前缀和， 为 路径
        for(int i = 1; i < n; i++){
            dp[i] = grid[0][i] + dp[i-1];
        }
        for(int i = 1; i < m; i++){
            dp0[i] = grid[i][0] + dp0[i-1];
        }
        
        for(int j = 1; j < m; j++)
            for(int i = 1; i < n; i++){
                if(i == 1){ // 第 1 行
                    dp[i] = Math.min(dp0[j], dp[i]) + grid[j][i];
                }else{
                    dp[i] = Math.min(dp[i-1], dp[i]) + grid[j][i];
                }
            }
        return dp[n - 1];
    }
}