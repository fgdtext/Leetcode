package Q976;

import java.util.Arrays;

public class Self {
    
}
/*
    排序+贪心   从右枚举。
    三角形两边之和大于第三边，即两条短边之和要大于第三边。
    每次确定最长边，那么相邻的两个边就是最可能的能组成三角形的边。
*/
class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}

