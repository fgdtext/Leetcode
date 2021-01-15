package Q581;


public class Self {
    
}
/*
子数组的要求：  子数组的最小值大于左侧数组的最大值，子数组的最大值小于右侧数组的最小值，左侧数组有序，右侧数组有序。
这个算法背后的思想是  *无序*  *子数组*   中  最小元素  的正确位置可以决定左边界，最大元素的正确位置可以决定右边界。
根据上边的判断： 
    1. 在左侧找上升数组到left，在右侧找下降数组到right。left->right中间是无序的。
    2. 应该在left->right(包含left和right ) 中间找最小值min和最大值max。
    3. 在left左侧找第一个大于min的位置indleft，在right右侧找第一个小于max的位置indright。
    4. indright-1 -> indleft+1 就是要找的 最短无序连续子数组

*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int left = 0;
        while(left+1 < nums.length && nums[left] <= nums[left+1]) left++;
        
        int right = nums.length-1;
        while(right > 0 && nums[right] >= nums[right-1])  right--;

        int minval = Integer.MAX_VALUE;
        int maxval = Integer.MIN_VALUE;
        if(left+1 == right && nums[left] < nums[right]) return 0;

        for(int i = left; i <= right; i++){
            minval = Math.min(minval, nums[i]);
            maxval = Math.max(maxval, nums[i]);
        }
        int indleft = 0;
        int indright = nums.length-1;
        while(indleft <= left && nums[indleft] <= minval) indleft++;
        while(indright >= right && nums[indright] >= maxval) indright--;
        return indright < indleft ? 0 : indright - indleft+1;
    }
}