package Q16;

import java.util.*;
/*

三数之和 最接近target.  该题是 三数之和= target 的改编。
对于三个数 abc  我们用for 固定(a,i) 然后在 i+1 到 len-1找 b和c
找 b和c ： 使用双指针。

参考Q11装最多水的容器。
对于一排数，数的大小，代表容器的高度，根据水桶原则，装水量由短板决定。
那么对于left和right 我们抛弃谁呢，抛弃短板，指针向内移动。假设left小，那么left++
我们假设固定left, 那么以left为左边界的矩形，就是最大的，所以直接抛弃。
假设输入 ： 152343453459   nums[left] = 1  nums[right]= 9 那么左边界高度为1时，
该容器最大时就是 （1）*（right-left）也即我们发现使用1作为左边界的所有容器，最大就是当前。
这样我们就排除了很多不必要的枚举。

转化到  三数之和 ==  target： ************************************
 
假设对于枚举 bc 时有 输入      2 3 5 6 7 11 12 15  target = 11

2+15 > 11  那么以 15作为右边界的所有组合，就是当前 2+15=17是最小的。所以出现15的组合最小和为17仍然大于 11.
    所以 15可以被抛弃。
2+12 > 11 同理抛弃 12
2+11 > 11 同理抛弃 11
2+7 < 11 同理抛弃 2 
3+7 < 11同理抛弃 3
5+7 > 11 抛弃 7
5+6 = 11 得到 答案

再转化到当前题 ： 对于以上枚举，被排除的枚举状态都差于以上枚举。 ****************************
只需要在 以上枚举中找出最优即可。
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int start = i + 1;
            int end = len - 1;
            while (start < end) {
                int value = nums[i] + nums[start] + nums[end];
                if (value == target) {
                    return value;
                }
                if (Math.abs(value - target) < min) {
                    min = Math.abs(value - target);
                    ans = value;
                }
                if (value > target) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        return ans;
    }
}