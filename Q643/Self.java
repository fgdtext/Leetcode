package Q643;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a= {0,4,0,3,2};
        so.findMaxAverage(a, 1);
    }
}


class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int presum = 0;
        for(int i = 0; i < k; ++i){
            presum += nums[i];
        }
        int maxsun = presum;
        for(int i = k; i < nums.length; ++i){
            presum = presum+nums[i]-nums[i-k];
            maxsun = Math.max(maxsun, presum);
        }
        return maxsun*1.0 / k;
    }
}