package temp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        int[][] arrs = {
            {-16,-4,-3,-12,-6,-10,-3},
            {4,-5,6,7,0,0,2},
            {6,0,-1,-2,3,6,8},
            {5,3,4,0,0,-2,7},
            {-1,7,4,0,7,-5,6},
            {0,-1,3,4,12,4,2}
        };
        /*
            dp[i][j] : 从第0行走到 ij出口的最大值

        */ 

        int[][] dp = new int[arrs.length][arrs[0].length];
        // 先初始化为 最小值。
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        // base_case
        for(int j = 0; j < arrs[0].length; j++){
            dp[0][j] = arrs[0][j];
        }
        // dp
        for(int i = 1; i < arrs.length; i++){
            for(int j = 0; j < arrs[0].length; j++){
                for(int k = -2; k <= 2; k++){
                    if(j+k >= 0 && j+k < arrs[0].length){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j+k] + arrs[i][j]);
                    }
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        int m = arrs[0].length / 2;
        for(int i = m-2; i <= m+2; i++){
            ans = Math.max(ans, dp[arrs.length-1][i]);
        }
        System.out.print(ans);

    }
}