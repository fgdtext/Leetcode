package Q91;

public class Self {
    
}
/*
动态规划，类似于爬楼梯有多少种方案，
只是这题，数字是有范围的，有时候能连着爬2个，有时候只能爬一个。


*/
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len];
        if(s.charAt(0) == '0') return 0;
        dp[0] = 1;
        for(int i = 1; i < len; i++){
            int h = s.charAt(i-1)-'0';
            int l = s.charAt(i)-'0';
            if(h == 0){
                if(l != 0)
                    dp[i] = dp[i-1];
                else return 0;
            }else{
                if(l == 0){
                    int t = h*10+l;
                    if(t <= 26){
                        if(i >=2 )
                            dp[i] = dp[i-2];
                        else dp[i] = 1;
                    }else{
                        return 0;
                    }
                }else{
                    int t = h*10+l;
                    if(t <= 26){
                        if(i >= 2) dp[i] = dp[i-2]+dp[i-1];
                        else dp[i] = 2;
                    }else{
                        dp[i] = dp[i-1];
                    }
                }
            }
        }
        return dp[len-1];
    }
}



class Ans{
    int numDecodings(String s) {
        char[] cs = s.toCharArray();
        if (cs[0] == '0') return 0;
        int pre = 1, curr = 1;//dp[-1] = dp[0] = 1
        for (int i = 1; i < s.length(); i++) {
            int tmp = curr;
            if (cs[i] == '0')
                if (cs[i - 1] == '1' || cs[i - 1] == '2') curr = pre;
                else return 0;
            else if (cs[i - 1] == '1' || (cs[i - 1] == '2' && cs[i] >= '1' && cs[i] <= '6'))
                curr = curr + pre;
            pre = tmp;
        }
        return curr;
    }
}