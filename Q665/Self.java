package Q665;

public class Self {
    public static void main(String[] args) {
        
    }
}


/*

在修改时，总是希望修改的值尽可能的小，只要当前数更小，那么后边就越可能大于当前数。所以
开头：如果要修改， 则nums[0] = nums[1] 
后续：对于a,b,nums[i](此时一定有a<=b)   若 nums[i] < a 则 nums[i]必须改为b。没有别的选择。
     而若 nums[i] >= a  由于尽可能要nums[i]是更小的数，执行nums[i] = nums[i-1]的代价是大的。
     因 执行 nums[i-1] = nums[i-2] 即修改为  a,a,nums[i] 是代价更小的。
*/

class Solution {
    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        if(len <= 1) return true;
        boolean flag = false; 
        if(nums[0] > nums[1]){
            flag = true; nums[0] = nums[1];
        }
        for(int i = 2; i < len; i++){
            if(nums[i] < nums[i-1]){
                if(flag){  // 已经修改过，则直接返回
                    return false;
                }else{    // 做一次修改。
                    flag = true;
                    if(nums[i] < nums[i-2]){
                        nums[i] = nums[i-1]; // 只能抬高
                    }else{
                        nums[i-1] = nums[i-2]; 
                    }
                }
            }
        }
        return true;
    }
}