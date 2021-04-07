package Q80;

public class Self {
    
}

class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len < 3) return len;
        int pre = nums[0];
        int k = 1;
        int ans = 1;
        int ind = 1;
        for(int i = 1; i < len; i++){
            if(nums[i] == pre){
                k++;
                if(k < 3){
                    ans++;
                    nums[ind++] = nums[i];
                }
            }else{
                k = 1;
                pre = nums[i];
                nums[ind++] = nums[i];
                ans++;
            }
        }
        return ans;
    }
}