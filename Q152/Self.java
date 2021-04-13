package Q152;



public class Self {
    
}
/*
负负 得正。

利用前缀积 降低时间复杂度。


o(n^2) 空间，内存溢出。 优化空间使用。

优化为 滚动数组， 空间满足了  但是时间 o(n^3) 最后一个 案例超时

答案 的 时间 复杂度 o(n)    卧槽，差太多了吧

当前定义为 ：  dp[i][j] : i~j 的最大乘积。

看来这样不行。得换一个定义。

*/
class Slef {
    public int maxProduct(int[] nums) {

        int[] dp = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            for(int j =i; j < nums.length; j++){
                if(j == i) dp[j] = nums[i];
                else{
                    int temp = dp[j];
                    int max = nums[i];
                    int digi = 1;
                    for(int k = i; k <= j; k++){
                        digi *= nums[k];
                        max = Math.max(max, digi);
                    }
                    dp[j] = max;
                    digi = 1;
                    for(int k = j; k >= i; k--){
                        digi *= nums[k];
                        max = Math.max(max, digi);
                    }
                    dp[j] = Math.max(dp[j], max);
                    dp[j] = Math.max(dp[j], temp);
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
            }
        }
        return dp[nums.length-1];
    }
}


/*

定义  dp[i] 0~i 的最大乘积 

力求 o(n) 的复杂度

目前实现了 o(n^2) 还是不行。
*/

class Self2 {
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i =1; i < nums.length; i++){
            dp[i] = dp[i-1];
            int digi = nums[i];
            int max = nums[i];
            for(int j = i-1; j >= 0; j--){
                digi *= nums[j];
                max = Math.max(max, digi);
            }
            dp[i] = Math.max(dp[i], max);
        }
        return dp[nums.length -1];
    }
}


/*
再优化。

空间只需要 三个即可。 但是时间？？？还嫩怎么优化呢？？
dp[i] : 必须以 i 结尾的  乘积最大子数组。

最后在 dp数组中遍历找出最大的那个.

nums[i] > 0  需要 dp_max[i-1] 越大越好
nums[i] = 0  dp[i] = 0;
nums[i] < 0  需要 dp_min[i-1] 越小越好

矛盾 

*/
class Ans1 {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
// 优化空间
class Ans2 {
    public int maxProduct(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}


/*

和上边 Ans2 意思相同. 


*/
class Solution {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int ans = nums[0];  int max = nums[0];  int min = nums[0];
        for (int i = 1; i < length; ++i) {
            if(nums[i] == 0){
                max = min = 0;
            }else if(nums[i] > 0){
                int ma = max, mi = min;
                // 前一个最大的 可能也是 负数
                max = Math.max(ma*nums[i], nums[i]);
                min = Math.min(mi*nums[i], nums[i]);
            }else{
                int ma = max, mi = min;
                max = Math.max(mi*nums[i], nums[i]);
                min = Math.min(ma*nums[i], nums[i]);
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
