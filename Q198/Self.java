package Q198;

public class Self {
    
}
/*

get : 偷当前结点，可以获得的最大值。那么上一个结点就只能是不偷。
no_get : 不偷当前结点，可以获得的最大值。上一个结点可以偷，也可以不偷。

优化空间的 dp .

dp[i]=max(dp[i−2]+nums[i],dp[i−1])
dp[0]=nums[0]
dp[1]=max(nums[0],nums[1])
​	
  

*/
class Solution2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }
}

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int get = nums[0];
        int no_get = 0;
        for(int i = 1; i < nums.length; i++){
            int t_get = get;
            int t_no_get = no_get;
            get = t_no_get + nums[i];
            no_get = Math.max(t_no_get, t_get);
        }
        return Math.max(get, no_get);
    }
}