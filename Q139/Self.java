package Q139;
import java.util.*;

public class Self {
    
}
/*
o(n^2)  dp 可能可以优化， 但是我 不会
dp[i] :  0~i 可以划分。

则dp[i] = dp[j] && contains(j+1,i) 其中  0 <= j < i  当所有j 都不能使得 dp[i] = true 时， dp[i] = false;

这题 记忆化搜索也是可以的。

*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>();
        for(String ss : wordDict){
            set.add(ss);
        }
        int len = s.length();
        boolean[] dp = new boolean[len];
        // base_case
        dp[0] = set.contains(""+s.charAt(0));
        // [0~i] 恰好是一个单词的情况。
        for(int i = 0; i < len; i++){
            dp[i] = set.contains(s.substring(0, i+1));
        }

        // 从 第二个 字母 开始 dp
        for(int i = 1; i < len; i++){
            for(int j = i-1; j >= 0; j--){
                // [0,j] 可分割。
                if(dp[i]) break;  // 
                if(dp[j]) dp[i] = set.contains(s.substring(j+1, i+1));
            }
        }
        return dp[len-1];
    }
}