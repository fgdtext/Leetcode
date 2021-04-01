package Q334;

import java.util.Arrays;
import java.util.LinkedList;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] nums = {1,2,1,2,1,2,1,2,1,2};
        so.increasingTriplet(nums);
    }
}

/*
滑动窗口

递增时，窗口直接扩
[6,7,1,2]

LIS : 增加到3时就返回。加不到3就返回false
*/

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len < 3) return false;
        // 定义Dp:  dp[i] = j;  长度为i+1的最长上升子序列的最后元素为 j.
        long[] dp = new long[3];
        Arrays.fill(dp, Long.MAX_VALUE); // 初始：都是-1
        int k = 0;
        for(int num : nums){
            int j = 0;
            while(j < k){
                if(dp[j] >= num){
                    break;
                }
                j++;
            }
            dp[j] = num;
            if(j == k) k++;
            if(k == 3) return true;
        }
        return k == 3;
    }
}



// 找到的序列是严格递增的。 
// 是因为我们找到的是 第一个  大于等于 num的位置。
// 优化 
class Self3 {
    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        if(len < 3) return false;
        // 定义Dp:  dp[i] = j;  长度为i+1的最长上升子序列的最后元素为 j.
        int[] dp = new int[2];
        Arrays.fill(dp, Integer.MAX_VALUE); // 初始：都是-1
        for(int num : nums){
            if(dp[0] >= num){
                dp[0] = num;
            }else if(dp[1] >= num){
                dp[1] = num;
            }else if(dp[1] < num){
                return true;
            }
        }
        return false;
    }
}