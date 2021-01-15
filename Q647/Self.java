package Q647;

/*
目测是一个滑动窗口， 想错了，不是滑动窗口，

回文串的  1. 中心扩展思想。
         2. 动态规划

*/

class Solution {
    public int countSubstrings(String s) {
        int maxCount = 0;
        int s_len = s.length();
        // 每个长为N的字符串都有2N-1个中心
        for(int i = 0; i < s_len; i++){
            int oddPa = getPalindromeCnt(s, s_len, i, i);
            int evenPa = getPalindromeCnt(s, s_len, i, i+1);
            maxCount += oddPa + evenPa;
        }
        return maxCount;
    }
    
    public int getPalindromeCnt(String s, int s_len, int start, int end){
        int cnt = 0;
        // 每次中心确定相等后, 都确定了一个回文串. 随意回文开始结束的位置发生变化, 另一个新的回文串确认过程开始了.
        while(start >= 0 && end < s_len && s.charAt(start)==s.charAt(end)){
            start -= 1;
            end += 1;
            cnt++;
        }
        return cnt;
    }
}



//  动态规划  : 填表法。 这种题就适合填表法， 具体细节可以去看题解(leetcode 有图示)

class Solution02 {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count=0;
        for(int j=0;j<n;j++)
            for(int i=0;i<=j;i++)
              if(s.charAt(j)==s.charAt(i))
                    if(dp[i][j] = i==j || j-i==1 || dp[i+1][j-1])
                        count++;
        return count;
    }
}

