package Q140;
import java.util.*;
public class Self {
    public static void main(String[] args) {
        String s = "aaaaaaa";
//         "aaaaaaa"
// ["aaaa","aaa"]
        List<String> words = new ArrayList<>();
        words.add("aaaa");
        words.add("aaa");
        // words.add("and");
        // words.add("sand");
        // words.add("dog");
        Solution ss = new Solution();
        words =  ss.wordBreak(s, words);
        for(String sss : words){
            System.out.println(sss);
        }

    }
}
/*
1. 先计算dp[i] : 0-i 是否可以划分。 备用。用于下边剪枝。
2. 从末尾往左进行回溯法， 
    1. 若从cur - pre 是一个单词，这时，该单词可以继续向左延展，
                也可以在cur 左侧切割，加一个空格。但是切割的这种情况，若左侧字符串有dp[cur-1]是不可分割的。
                    那么就是说，不能在cur 左侧切割。直接剪枝。这个dp剪枝会提升非常多。
    2. 若从cur - pre 不是单词，那么就只能继续延展，期望能构造出单词。
        
*/

class Solution {
    HashSet<String> set;
    boolean[] dp;
    List<String> ans;
    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>();
        for(String ss : wordDict){
            set.add(ss);
        }
        // 1. 获取dp[i]
        int len = s.length();
        dp = new boolean[len];
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
        // 不可分割， 则直接返回空的 list 
        if(!dp[len-1]) return new ArrayList<>();
        ans = new ArrayList();
        huisu(s.length()-1, s.length(), new StringBuilder() , s);
        return ans;
    }
    // 分隔符，使用 空格。
    public void huisu(int cur, int pre, StringBuilder pre_temp, String s){
        // 从 右 向 左 找。
        if(cur < 0){
            if(set.contains(s.substring(0, pre)))
                ans.add(pre_temp.toString());
            return;
        }
        // 如果 cur, pre 已经是一个单词
        if(set.contains(s.substring(cur, pre))){ // 分两种情况， 即是否断开。可能断开，也可能继续合并。
            //if(cur > 0 && !dp[cur-1]) return; // 由于 后边都是单词，但是左边是不可分割的，所以，要回退。
            // 2 继续拼接
            StringBuilder temp =  new  StringBuilder("").append(s.charAt(cur)).append(pre_temp);
            huisu(cur-1, pre, temp, s);  
            if(cur > 0 && !dp[cur-1]) return; 
            // 1. 断开， 左边加 空格。
            if(cur > 0){
                temp = new StringBuilder(" ").append(s.charAt(cur)).append(pre_temp);
                huisu(cur-1, cur, temp, s);
            }         
        }else{ //  cur, pre 不是单词， 那么只能继续合并
            // 3. 继续拼接
            StringBuilder temp = new StringBuilder("").append(s.charAt(cur)).append(pre_temp);
            
            huisu(cur-1, pre, temp, s);  
        }
    }
}

/*class


        // 2. 获取 exist
        boolean[][] exist = new boolean[s.length()][s.length()+1];
        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j <= s.length(); j++){
                exist[i][j] = set.contains(s.substring(i, j));
            }
        }

*/