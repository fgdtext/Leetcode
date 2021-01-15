package Q53;


public class Self {
    
}

/*
假设 nums 数组的长度是 n，下标从 0 到 n−1。


我们用 ai 代表 nums[i]，用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，
那么很显然我们要求的答案就是：max(f(i))
则有状态定义：   f(i) 代表以第 i 个数结尾的「连续子数组的最大和」， 目标求 max(f(i))

那么我们来考虑状态转移方程： f(i-1) 到 f(i)的转换

    f(i-1) 表示以 i-1结尾的 最大子数组和，  
    那么  f(i) = max(a[i], f(i-1) + a[i]) 
    解释 ：  f(i-1)是i-1结尾的最大和， 那么 f(i) 的取值就只有两种可能， a[i], 或者连接之前的 f(i-1) + a[i]
    所以取最大值， 则得到 f(i)

另外可知 ： f(i) 只与 f(i-1)有关， 所以， 只需要一个变量来保存  f(i-1)即可。类似滚动数组。
*/


class Ans {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}



/*


当前最大连续子序列和为 sum，结果为 ans
如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
时间复杂度：O(n)O(n)

*/

class Ans2 {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
