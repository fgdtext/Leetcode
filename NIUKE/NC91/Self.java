package NIUKE.NC91;


import java.util.*;


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