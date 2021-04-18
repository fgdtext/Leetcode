package Q697;

import java.util.*;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] nums = {1,0,1,1,2,2,2};
        so.findShortestSubArray(nums);
    }
}

class Solution {
    public int findShortestSubArray(int[] nums) {
        int len = nums.length, MAXN = 50_000;
        int[] map = new int[MAXN];
        int max = 0;
        for(int num: nums) {
            max = Math.max(max, ++map[num]);
        }
        Arrays.fill(map, 0);
        int l = 0, r = 0, res = MAXN;
        while(r < len) {
            if(max > ++map[nums[r++]]) continue;
            while(max > map[nums[l++]]--) ;
            res = Math.min(res, r - l + 1);
        }
        return res;
    }
}
/*
        易读版本: 不可能写的出来我。
*/
class Solution2 {
    public int findShortestSubArray(int[] nums) {
        int len = nums.length, MAXN = 50_000;
        int[] map = new int[MAXN];
        int max = 0;
        for(int num: nums) {
            max = Math.max(max, ++map[num]);
        }
        Arrays.fill(map, 0);
        int l = 0, r = 0, res = MAXN;
        while(r < len) {
            ++map[nums[r]];
            if(max > map[nums[r]]) {
                r++;
                continue;
            }
            while(max > map[nums[l]]){
                map[nums[l]]--;
                l++;
            } 
            map[nums[l]]--;
            l++;
            r++;
            res = Math.min(res, r - l + 1);
        }
        return res;
    }
}