package Q132;

import java.util.*;

public class Self {
    
}



/*
动态规划：ishui : 判断 left~right是否是回文串， 二维dp存下来。

dp[i]  :  从0~i最少可以划分为多少个回文串。回文串， 最少。

设置前置left指针， 若left~i是回文串，则出现一种划分有 dp[lef-1] + 1 个回文串。 遍历lefl的每一种可能，就可以找到i的最少回文串。


*/
class Solution {
    public int minCut(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        boolean[][] ishui = new boolean[len][len];
        for(int right = 0; right < len; right++){
            for(int left = 0; left <= right; left++){
                if (charArray[left] == charArray[right] && (right - left <= 2 || ishui[left + 1][right - 1])) {
                    ishui[left][right] = true;
                }
            }
        }
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1; i < len; i++){
            dp[i] = Integer.MAX_VALUE;  // 选最小， 初始化为最大。
            for(int left = 1; left <= i; left++){
                if(ishui[left][i]) dp[i] = Math.min(dp[i], dp[left-1]+1);
            }
            if(ishui[0][i]) dp[i] = 1;
        }
        return dp[len-1]-1;
    }
}