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
            // 在tails数组的 0~res之间二分查找 tails[k] >= num
            // 找第一个大于等于num的位置。
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



/*

    不仅要求出最长子序列的长度，还要返回 字典序最小的最长子序列 的 序列。
    1. 在 二分法的基础上求出 dp[n] ,在求出dp[n]的同时也求出 maxlen[i]
        maxlen[i]: 以下标为 i元素结尾的最长子序列。 在求 dp时求出 maxlen 
    2. 从dp可知，最长子序列的末尾元素一定是 dp[ind-1].
        然后从 arr 数组 合 maxlen数组 求出 ans数组。
    arr ： 2,1,5,3,6,4,8,9,7   dp = 1,3,4,7,9,0,0,0,0 ind = 5

    arr ： 2,1,5,3,6,4,8,9,7
 maxlen ： 1 1 2 2 3 3 4 5 4

 ans(假设从1开始)
           1 2 3 4 5 
                   j 
            可以看出 j = maxlen[i] = 5 时，只能是最后的9. j的位置就是其长度，合maxlen是对应的。
            为什么不能是7呢？ 因为 7的最长为 4，不可能放在第5个位置。
*/

 class SolutionAndReturnArr {
    /**
     * retrun the longest increasing subsequence
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS (int[] arr) {
        // write code here
        int n = arr.length;
        int[] dp = new int[n]; // dp[i]: 长度为i+1的递增子序列的最小末尾元素为 dp[i]
        int ind = 0;
        dp[ind++] = arr[0];
        int[] maxlen = new int[n];
        maxlen[0] = 1;
        for(int i = 1; i < n; i++){
            int e = arr[i];
            int left = 0, right = ind;
            while(left < right){
                int mid = left + (right - left) / 2;
                if(dp[mid] <= e){
                    left = mid + 1;
                }else{
                    right = mid;
                }                              
            }
            dp[left] = e;
            maxlen[i] = left+1;
            if(right == ind) ind++; 
        }
        int[] ans = new int[ind];
        ans[ind-1] = dp[ind-1];
        for(int i = n-1, j = ind; j > 0; i--){
            if(maxlen[i] == j) {
                ans[--j] = arr[i];
            }
        }        
        return ans;
    }
}