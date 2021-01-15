package Q327;

public class Self {
    
}
//    动态规划  error , 时间仍是 o(n^2)
/*
dp[i] :  以 i 结尾的数组的  区间和在 [lower, upper] 之间的个数

不行 : dp[i-1] 到 dp[i] 的转移还是o(n) ，时间还是 o(n^2) 不合格
由 nums[i] 和之前的构成新的区间，判断是否在要求之内。耗时o(n)
*/
class Self1 {
// 动态规划。 失败。
}


//    前缀和  +  归并排序
/*
看题解： 通过前缀和转化问题  ：  sum[i] - sum[j] -> {lower, upper} 找到所有满足的 ij对。

看一个简单的问题 ： 两个有序数组 a[i] - b[j] -> {lower, upper}, 找 ij 的过程是简单的。
    对每一个i,  对b建立双指针l,r . 找到最小的l (l再小，会导致a[i]-b[l]不在lower, upper内),和最大的 r(同理) 。


所以我们可以求出两个有序数组中可以 满足 a[i] - b[j] -> {lower, upper} 的区间个数。

那么为什么可以使用归并排序呢， 首先自底向上的归并， 每次都是合并两个有序数组，我们找到了有序数组。

那么为什么可以排序呢，排序后前缀和数组发生变化，不影响结果吗。

看递归过程， 类似后序，每次都是一分为二， 先得到两侧子数组的 区间个数。 然后再合并 两个子数组。
a[i] - b[j] -> {lower, upper} 合并时，两个子数组中 前缀和已经完成排序，而这个排序并不会影响合并的结果。因为区间端点
一个在a数组，一个在b数组。

从前缀和顺序来看， 我们每次 都要使得 右侧数组[i] - 左侧数组[j] ，才是合法的。所以，i在右侧的位置无关紧要。j也同理。


*/

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long s = 0;
        // 下标从 1 开始 存放
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) { // 这个是求反的。>=是符合要求的。所以<才继续找
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {// <= 是符合要求的。
                    r++;
                }
                ret += r - l;
                i++;
            }
            // 合并两个有序数组
            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            // 赋值 回原 数组
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}