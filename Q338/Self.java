package Q338;

public class Self {
    
}
/*
a = n & n-1 
b = a & a-1

计算n , 依赖于a , 计算a依赖于 b.
且 n > a > b


*/
class Self2 {
    public int[] countBits(int num) {
        if(num == 0)return new int[]{0};
        int[] dp = new int[num+1];
        for(int i = 1; i <= num; i++){
            dp[i] = dp[i&(i-1)]+1;
        }
        return dp;
    }
}



/*class
对于所有的数字，只有两类：

奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。


偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。
      因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。

*/

class Solution {
    public int[] countBits(int num) {
        if(num == 0)return new int[]{0};
        int[] ans = new int[num+1];
        for(int i = 1; i <= num; i++){
            if((i&1) == 1) ans[i] = ans[i-1] +1;  // 奇数
            else ans[i] = ans[i>>1];    // 偶数
        }
        return ans;
    }
}