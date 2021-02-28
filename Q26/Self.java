package Q26;

public class Self {
    
}
/*
ok  很简单.
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int len =  nums.length;
        if(len <= 1) return len;
        int i = 0; int j = 1;
        while(j < len){
            if(nums[j] != nums[i]) nums[++i] = nums[j];
            j++;
        }
        return i+1;
    }
}