package Q164;

import java.util.Arrays;

class Solution {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));  // 平均间距
        int bucketSize = (maxVal - minVal) / d + 1; // 桶的数量这里要 加1， 因为桶数少了，可能导致桶内间距过大。多一个同保险

        int[][] bucket = new int[bucketSize][2]; // 每个桶只保留最大最小的值 min max 
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }

        /*

                不停的更新桶里边的最大最小值
        */
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {   // 说明第一次更新该桶
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }
        /*
            相邻两个桶求间距。保存最后的最大值。左边的桶里边的数一定小于右边桶里边的数。
        */
        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }
}
