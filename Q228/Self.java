package Q228;

import java.util.*;


class Solution {
    public List<String> summaryRanges(int[] nums) {
        int len = nums.length;
        List<String> ans = new ArrayList<>();
        if(len == 0)  return ans;
        int left = nums[0];
        for(int i = 1; i < len; i++){
            if(nums[i-1]+1 != nums[i]){
                if(left == nums[i-1]) ans.add(left+"");
                else ans.add(left+"->"+nums[i-1]);
                left = nums[i];
            }
        }
        if(left == nums[len-1]) ans.add(left+"");
        else ans.add(left+"->"+nums[len-1]);
        return ans;
    }
}