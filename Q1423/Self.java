package Q1423;

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = {1,2,3,4,5,6,1};
        so.maxScore(a, 3);
    }
}
/*
最后的结果必然是 取走左边一段，右边一段。 那么遍历一下就ok 了。双指针。

或者反着想，最后会留下中间一段 len-k个。执行滑动窗口

*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] presum = new int[len];
        presum[0] = cardPoints[0];
        for(int i = 1; i < len; ++i){
            presum[i] = presum[i-1] + cardPoints[i];
        }
        if(k == len) return presum[len-1];
        int left = k - 1, right = len;
        int ans = presum[left];
        left--; right--;
        while(left >= 0){
            ans = Math.max(ans, presum[left]+presum[len-1]-presum[right-1]);
            left--; 
            right--;
        }
        ans = Math.max(ans,presum[len-1] - presum[right-1]);
        return ans;
    }
}