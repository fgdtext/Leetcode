package Q283;



/*
没啥说的，简单题。

*/
class Solution {
    public void moveZeroes(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) count++;
            else{
                nums[i-count] = nums[i];
            }
        }
        while(count > 0){
            nums[nums.length - count] = 0;
            count--;
        }
    }
}