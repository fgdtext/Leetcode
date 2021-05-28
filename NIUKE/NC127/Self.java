package NIUKE.NC127;

import java.util.*;

/*
最长公共子串


dp[i][j] = dp[i-1][j-1]

注意在计算下一行的时候，dp[i][0]一定要单独计算。因为他的答案只有 0或者1

*/
 class Solution {
    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        // write code here
        int len1 = str1.length();
        int len2 = str2.length();
        char[] ss1 = str1.toCharArray();
        char[] ss2 = str2.toCharArray();
        int[][] dp = new int[len1][len2];
        int maxi = 0;  int maxlen = 0;
        for(int j = 0; j < len2; j++){
            if(ss1[0] == ss2[j]) {
                dp[0][j] = 1;
                maxlen = 1;
            }
        }
        for(int i = 1; i < len1; i++){
            for(int j = 0; j < len2; j++){
                  if(ss1[i] == ss2[j]){
                      if(j == 0){
                           dp[i][j] = 1;
                      }else{
                          dp[i][j] = dp[i-1][j-1] + 1;                          
                      }
                      if(dp[i][j] > maxlen){
                              maxi = i;
                              maxlen = dp[i][j];
                      }                     
                  }
            }
        }
        return str1.substring(maxi - maxlen+1, maxi+1);
    }
}


/*
dp[i][j] = dp[i-1][j-1] 可以优化空间。 j倒着算即可。

big_bug : 一个很大的bug。 在没有优化空间时，因为初始化都是 0. 
所以当 ss1[i] != ss2[j] 时，不需要处理，直接就是 0.
但是优化空间以后， 如果不处理。 那么 dp[j]很可能是 前一行的 dp[j]
所以一定要手动 置 0 。

*/


 class Solution2 {
    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        // write code here
        int len1 = str1.length();
        int len2 = str2.length();
        char[] ss1 = str1.toCharArray();
        char[] ss2 = str2.toCharArray();
        int[] dp = new int[len2];
        int maxi = 0;  int maxlen = 0;
        for(int j = 0; j < len2; j++){
            if(ss1[0] == ss2[j]) {
                dp[j] = 1;
                maxlen = 1;
            }
        }
        for(int i = 1; i < len1; i++){
            for(int j = len2-1; j >= 0; j--){
                  if(ss1[i] == ss2[j]){
                      if(j == 0){  // dp[i][0]单独计算
                           dp[j] = 1;
                      }else{
                          dp[j] = dp[j-1] + 1;                          
                      }
                      if(dp[j] > maxlen){
                              maxi = i;
                              maxlen = dp[j];
                      }                     
                  }else{  // ss1[i] != ss2[j] 显式置 0 . 否则 dp[i][j] = dp[i-1][j].不会变为0.
                      dp[j] = 0;
                  }
            }
        }
        return str1.substring(maxi - maxlen+1, maxi+1);
    }
}