package NIUKE.NC92;

import java.util.*;

/*
最长公共子序列： 返回最长公共子序列 。 不仅仅要求长度。

难点1： dp方程 当ss1[i] == ss2[j]时。 那么dp[i][j] = dp[i-1][j-1]+1
        当ss1[i] == ss2[j]时一定由i-1,j-1转换而来。 而且必然是最大的。符合贪心策略。
        倒着匹配，最后一个字符匹配上，是不会影响前边匹配取最大值的。
       当ss1[i] != ss2[j] 时， Math.max(dp[i-1][j],dp[i][j-1]); 

难点2 ： 初始化： 这题合最长公共子串不同。初始化第一行第一列时。
        werty  匹配 zwasd 我们初始化第一行 w和w相同 则dp[0][1] = 1
        可以看到zwasd 后序的 asd和w并不相同。 但是按照dp定义，dp[0][j]表示位置0j的最长公共子序列长度
        dp[0][2]:到达位置0，2 w:zwa 的最长序列长度应为 1. 而不是 0.所以即使 a != w也应该是1.
        这具有延续性。

        第一行 dp[i][j] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0) 

难点3 : 如何从dp数组找 公共子序列。？？？
        dp[i][j] 由 dp[i-1][j-1], dp[i-1][j], dp[i][j-1]三个方向转化而来。
        若 dp[i][j] == dp[i-1][j] 就说明，ss1[i]不要，我们一定能在0-i-1 和0-j找到目标串。
        不需要考虑其他情况   dp[i][j] == dp[i][j-1] 同理。
        如果不是上边的情况，则一定是 由 dp[i-1][j-1]而来。 则，ss1[i]和ss2[j]就必须匹配。
        然后追加到 ans末尾。此时目标串的最后一个字符就确定了。我们通过dp[i-1][j-1]确定
        前边的 串即可。

*/

 class Solution {
    /**
     * longest common subsequence
     * @param s1 string字符串 the string
     * @param s2 string字符串 the string
     * @return string字符串
     */
    
    public String LCS (String s1, String s2) {
        // write code here
        
        int len1 = s1.length();
        int len2 = s2.length();
        if(len1 == 0 || len2 == 0) return "-1";
        char[] ss1 = s1.toCharArray();
        char[] ss2 = s2.toCharArray();
        int[][] dp = new int[len1][len2];
        dp[0][0] = ss1[0] == ss2[0] ? 1 : 0;
        for (int i = 1; i < len1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], ss1[i] == ss2[0] ? 1 : 0);
        }
        for (int i = 1; i < len2; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], ss1[0] == ss2[i] ? 1 : 0);
        }           
        for(int i = 1; i < len1; i++){
            for(int j = 1; j < len2; j++){
                if(ss1[i] == ss2[j])
                    dp[i][j] = dp[i-1][j-1] + 1;  
                else 
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);            
            }
        }    
        if(dp[len1-1][len2-1] == 0) return "-1";
       
        int ii = len1-1;
        int jj = len2-1;
        int ansl = dp[ii][jj];
        char[] ans = new char[ansl];
        while(ansl > 0){
            if(ii > 0 && dp[ii][jj] == dp[ii-1][jj]){
                ii--;
            }else if(jj > 0 && dp[ii][jj] == dp[ii][jj-1]){
                jj--;
            }else{               
                ans[--ansl] = ss1[ii];
                ii--;
                jj--;
            }
        }                
        return new String(ans);
    }  
}