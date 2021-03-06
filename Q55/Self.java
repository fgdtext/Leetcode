package Q55;

/*
终于能写出来dp了。 hhhhhh

........   是个贪心，，哎。   
从时间来分析，这个dp 的速度会是 o(n＾２). 因为 dp的长度就是 n ,  填n次表必然出结果。但是每次填表要搜索　ｏ（ｎ）
表不会重复填写。
*/
class Self2 {
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        return dp(nums.length - 1, dp, nums) > 0;
    }
    public int dp(int i, int[] dp,int[] nums){
        if(dp[i] != 0) return dp[i]; //  记忆化 直接返回
        if(i == 0) return 1;        //  base_case 
        for(int j = i-1 ; j >= 0; j--){   //   每种情况 进行递归。
            if(nums[j] >= i - j && dp(j, dp, nums) == 1) {
                dp[i] = 1;
                return 1;
            }
        }
        dp[i] = -1;
        return -1;
    }
}

/*class

 贪心

如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
如果可以一直跳到最后，就成功了。

*/

/*class


因该用贪心选择。遍历一遍即可，　在每个位置，都去刷新，自己能到的最远的位置。当前可以走的最远位置，会随着自己的移动，不断的变的更远。

*/

class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int maxfar = 0; // 当前可以走到的最远位置。
        for(int i = 0; i < len; i++){
            if(i > maxfar) break;
            maxfar = Math.max(maxfar, i + nums[i]);
        }
        return maxfar >= len-1;
    }
}