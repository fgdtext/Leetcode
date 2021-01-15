package Q546;

import java.util.BitSet;
import java.util.HashSet;

class Solution2 {
    // 定义状态： 一个压缩数组就是一个状态。状态相同的定义： 数组长度相同，切值都相同。
    // 由于状态改变过程会不断的取出数字，导致数组不连续，所以必须进行数组的压缩，使其连续，状态相同是指，压缩后相同。
    // 所以这里用 ArrayList更合适，因为要不断的 随机 删除(移动耗时) LinkedList(查找耗时)

    // 状态转移： 删除List中一段连续的数字(双指针查找(滑动窗口))
    // 如何进行快速的状态查找，自定义hash表，不好去找哈希函数对100位的数组进行散列，可以直接使用 HashSet<>. 
    
    //    解题失败

    public int removeBoxes(int[] boxes) {
        
        


        return 0;
    }
}



// ans   别看了，学不会

class Solution {
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }

    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }
}