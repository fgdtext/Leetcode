package Q1365;

import java.util.*;

/*
计数排序 + 前缀和 的思想。
*/


class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] sum = new int[101];
        for(int i = 0; i < nums.length; i++){
            sum[nums[i]]++;
        }
        int pre = 0;
        for(int i = 1; i < 101; i++){
            int temp = sum[i];
            sum[i] = sum[i-1] + pre;      
            pre = temp;      
        }
        sum[0] = 0;
        for(int i = 0; i < nums.length; i++){
            nums[i] = sum[nums[i]];
        }
        return nums;
    }
}