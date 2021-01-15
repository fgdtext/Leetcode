package Q121;

public class Self {
    
}


/*
双指针的模板。     right指针在 最后 ++ 。  每次进入循环都要 判断  in 是否应该 ++ 。

right 是一直 右移动，  left 是 有条件移动。

*/

class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int out = 0;
        int in = 0;
        int min_in = Integer.MAX_VALUE;
        int max = 0;
        while(in < len && out < len){
            max = Math.max(max, prices[out] - prices[in]);
            in = min_in > prices[out] ? out : in;
            min_in = in == out ? prices[out] : min_in;
            out++;
        }
        return max;
    }
}