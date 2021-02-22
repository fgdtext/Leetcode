package Q485;

public class Self {
    
}

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int curcount = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            if(nums[i] == 1) curcount++;
            else{
                ans = Math.max(ans, curcount);
                curcount = 0;
            }
        }
        ans = Math.max(ans, curcount);
        return ans;
    }
}