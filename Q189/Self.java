package Q189;


public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = {1,2};
        so.rotate(a, 3);
    }
}
/*
AB -> BA     (ATBT)T - > BA

*/

class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if(len <= 1 || k == 0) return;
        k = k % len;
        reverse(nums, 0, len-k-1);
        reverse(nums, len-k, len-1);
        reverse(nums, 0, len-1);
    }
    public void reverse(int[] nums,int left, int right){
        while(left < right && left >= 0 && right < nums.length){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++; right--;
        }
    } 
}