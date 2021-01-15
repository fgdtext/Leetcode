package Q300;

import java.util.Arrays;

public class Self {
    
}
/*
定义 : dp[i] 是以i结尾的 最长子序列的长度。
71 ms
, 在所有 Java 提交中击败了
5.62%
的用户    ---->     果然垃圾
o(n^2)
*/
class Self2 {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i = 1; i < nums.length; i++){
            for(int k = i - 1; k >= 0; --k){
                if(nums[i] > nums[k])
                    dp[i] = Math.max(dp[i], dp[k]+1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

/*
优化到 o(nlgn)
tails[k] 的值代表 长度为k+1 子序列 的 最小 尾部 元素值。 

设 res 为 tails 当前长度

通过二分法遍历 [0,res)列表区间，找出 nums[k] 的大小分界点

对于每一个nums[k]
区间中存在tails[i] > nums[k]  ： 将第一个满足 tails[i] > nums[k]执行 tails[i] = nums[k]
区间中不存在 tails[i] > nums[k]  ： 意味着 nums[k] 可以接在前面所有长度的子序列之后
*/

// 这代码写的太好了吧。。。。。。。我好酸啊。
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        // 初始的 序列长度为 0
        int res = 0;  
        // 对于每一个num 更新tails数组
        for(int num : nums) {
            // 在tails数组的 0~res之间二分查找 tails[k] > num
            // 找第一个大于num的位置。
            int i = 0, j = res;   //  若存在则更新位置i ,若不存在，则i == res 更新的就是res 的位置。
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;  // m的位置小于num, 说明第一个大的一定在m的右侧。
                else j = m;   // m的位置大于等于num  ，则第一个大的一定在左侧或则就是m. 
                // 若m左侧元素都小于num,则最终一直更新i到现在j=m的位置。
                // 若左侧元素等于num, 则j一直左移动，直到最左边等于num的位置。
            } 
            // 若不存在，则更新的是 tails[res] 下边会使得res+1.  
            tails[i] = num;
            // 若res == j 说明在 [0~res)中不存在 tails[k] > num 则序列长度+1.
            if(res == j) res++;
            // 这里有一个问题，就是该判断使得，该代码只能找 严格递增的，出现相等的，j的位置是在(0~res)之间，则res不会增加。
            // 
        }
        return res;
    }
}

// 作者：jyd
// 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。