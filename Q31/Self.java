package Q31;

import java.util.Arrays;

public class Self {
    
}
// 

//31. 下一个排列
/*
654321 ： 是最大排列
146532 ： 是 14  开头的最大排列， 此时递增只能用6532中大于4的最小元素5开始 ： 15开头
        而15开头的最小字典序为 15 2346 . 即交换大于4的最小元素5与4的位置，然后后边构成递减序列，逆置该递减序列，即为最小字典序。



*/

class Solution1 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int ind = len - 2;
        // 查找 4的位置
        for(; ind >= 0; ind--){
            if(nums[ind] < nums[ind+1]) break;
        }
        if(ind < 0){
            reverse(0, len - 1, nums);
            return;
        }
        int minval = nums[ind];
        int i = len - 1;
        // 交换
        for(; i > ind; i--){
            if(minval < nums[i]) {
                nums[ind] = nums[i];
                nums[i] = minval;
                break;
            }
        }
        // 数组反转
        reverse(ind+1, len - 1, nums);
    }
    public void reverse(int left, int right,int[] nums){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++; right--;
        }
    }
}

/*
第二次写   ok 。  找规律 还是 花了 一些 时间

*/

class Solution {
    public void nextPermutation(int[] nums) {
        // 从右向左 找 山顶
        int len = nums.length;
        int top = len - 1;
        while(top > 0 && nums[top] <= nums[top - 1]){
            top--;  // 向上爬坡   。。 爬坡 一定要这么写， 先判断下一个位置是不是高于当前位置，高，则爬坡。
        }
        if(top == 0){
            reverse(nums, 0); return;
        }
        // 交换
        for(int i = len - 1; i >= top; i--){
            if(nums[top-1] < nums[i]) {
                int temp = nums[top-1];
                nums[top-1] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        reverse(nums, top);
    }
    public void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while(start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}