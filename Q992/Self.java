package Q992;

import java.util.HashMap;

public class Self {
    
}
/*
每变化一次窗口，就判断一次窗口看是否合法。
K 个不同整数的子数组 ： 若一个区间的数字总类恰好为 K, 那么就合法，找出所有这样的子数组。
[1,2,1,2,3]
[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]. 子数组的子数组也可能合法。

固定左边界时，移动右边界若合法res++, 那么可以找到 12,123,1212. 但是若这样，21就无法找到，因为right已经到了3.不能回溯。
否则复杂度就不是o(n). 

所以换一种思路。 上边是子数组数字种类恰好为k. 
现在，我们找数字种类最多为k的子数组数量。 那么对于A数组，他的最多为k的子数组数量n1, 最多为k-1的子数组数量n2
满足 res = n1-n2.   因为恰好为 k-1,k-2,....1的子数组是相同的。相减就恰好是 恰好种类为k的子数组数量。

res += right-left;的解释。
 假设本来是 ABC k=3, 然后变为 BCD,那么新的区间数就是 D,CD,BCD.  就是right-left
 right新增加的一个位置，然后以这个位置结尾的区间数，就是新增的。
 若有 12123
 每当right后移动一个位置，就叠加新增的以right结尾的子数组。 
 1 right=0
 2，12 right = 1
 1，21，121  right = 2
 2，12，212，1212，right = 3 然后right++
 次数left要右移动，到  子数字 23  那么又有  3,23, 以3结尾。
 这样就找到了 12123数组的所有数字种类数<=k的数量。


 这其实有集合的思想。 most(k)集合 - most(k-1) 就是剩余的 种类数恰好为k的集合。
*/
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return mostK(A, K) - mostK(A, K - 1);
    }
    public int mostK(int[] A, int k){
        int len = A.length;
        int[] freq = new int[len+1];
        int left = 0; int  right = 0; 
        int count = 0; // 在窗口内数字的种类数。
        int res = 0;
        while(right < len){
            if(freq[A[right]] == 0) count++;
            freq[A[right]]++;
            right++;
            while(count > k){
                freq[A[left]]--;
                if(freq[A[left]] == 0) count--;
                left++;
            }
            // 假设本来是 ABC k=3, 然后变为 BCD,那么新的区间数就是 D,CD,BCD.  就是right-left
            // right新增加的一个位置，然后以这个位置结尾的区间数，就是新增的。
            res += right-left;
        }
        return res;
    }
}