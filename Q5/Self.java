package Q5;

/*
参考 Q647 题， 一摸一样



*/
// 动态规划， 递推 填表
class Solution1 {
    public String longestPalindrome(String s) {
        int ans_i = 0, ans_j = 0;   // i <= j;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int j=0;j<n;j++)
            for(int i=0;i<=j;i++)
              if(s.charAt(j)==s.charAt(i))
                    if(dp[i][j] = i==j || j-i==1 || dp[i+1][j-1])
                        if(j-i > ans_j - ans_i){
                            ans_j = j;
                            ans_i = i;
                        }
                    
         
        return s.substring(ans_i,ans_j);
    }
}


// 尝试kmp
// 通过
/*
KMP 可以找出 模式 串 的最长 前缀。 如果要找 回文串，可以通过在逆序字符串中找 最长前缀。

每次kmp结束后， 将模式串删去第一个字符，重新找 最长前缀。

*/

/*
if(j > ans.length()) ans = ss.substring(0, j); // 耗时  保存最长前缀的方式有问题
ss.deleteCharAt(0); // 耗时     模式串删去 首字符 太浪费时间。
*/
class Solution2 {
    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";
        int[] next = new int[s.length()+1];
        getNext(s, next);
        StringBuilder ss_rev = new StringBuilder(s).reverse();
        StringBuilder ss = new StringBuilder(s);
        String  ans = s.substring(0, 1);
        //  KMP只能用于查找 模式串 的 最大前缀。
        while(ss.length() > 0){
            int i = 0, j = 0;
            while(i < ss_rev.length()){
                if(j == -1 || ss_rev.charAt(i) == ss.charAt(j)){
                    ++i;
                    ++j;
                }else{
                    j = next[j];
                }
            }
            if(j > ans.length()) ans = ss.substring(0, j); // 耗时
            ss.deleteCharAt(0); // 耗时
            ss_rev.deleteCharAt(ss.length() - 1); 
        }      
        return ans;
    }
    public void getNext(String s, int[] next){
        int i = 0, j = -1;
        next[0] = -1;
        while(i < s.length() - 1){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else{
                j = next[j];
            }
        }
    }
}

// kmp 优化 o(2*n^2) 比动态规划要慢一倍
class Solution {
    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";
        if(s.length() == 1) return s;
        int[] next = new int[s.length()+1];
        getNext(s, next);
        StringBuilder ss_rev = new StringBuilder(s).reverse();
        //  KMP只能用于查找 模式串 的 最大前缀。
        int begin = 0; 
        int start = 0, end = 1;
        while(begin < s.length()){
            int i = 0, j = 0;
            while(i < ss_rev.length() - begin){
                if(j == -1 || ss_rev.charAt(i) == s.charAt(j + begin)){
                    ++i;
                    ++j;
                }else{
                    j = next[j];
                }
            }
            if(j > end - start) {
                start = begin; 
                end = j + begin;
            }
            ++begin; 
        }      
        return s.substring(start, end);
    }
    public void getNext(String s, int[] next){
        int i = 0, j = -1;
        next[0] = -1;
        while(i < s.length() - 1){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                i++;
                j++;
                next[i] = j;
            }else{
                j = next[j];
            }
        }
    }
}
