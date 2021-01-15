package Q34;
import java.util.*;

/*
二分。 
看代码吧。 挺简单的。

nums[mid] == target 的条件列举全就ok 
*/

class Solution {
    int[] ans = {-1,-1};
    public int[] searchRange(int[] nums, int target) {

        ans = erfen(nums, target, 0, nums.length -1);
        return ans;
    }
    public int[] erfen(int[] nums, int target, int left, int right){
        if(left > right) return new int[]{-1,-1};
        if(left == right){
            if(nums[left] == target) return new int[]{left,left};
            return  new int[]{-1,-1};
        }

        int mid = left + (right - left)/2;
        // 一刀切在中间
        if(nums[mid] == target){
            int[] l = erfen(nums, target, left, mid - 1);
            int[] r = erfen(nums, target, mid + 1, right);
            // 只有mid 一个
            if(l[0] == -1 && r[0] == -1) return new int[]{mid,mid};
            if(l[0] == -1){ // mid  左侧 没有
                return new int[]{mid, r[1]};
            }
            if(r[0] == -1){ // mid 右侧 没有
                return new int[]{l[0], mid};
            }
            // 两侧都有，区间合并
            return new int[]{l[0],r[1]};
        }else if(nums[mid] > target){ // 切在了右边，应该在左侧找
            return erfen(nums, target, left, mid - 1);
        }else{ // 在右侧找
            return erfen(nums, target, mid + 1, right);
        }
    }
}