package Q1438;

import java.util.Deque;
import java.util.LinkedList;

public class Self {
    
}
/*
单调递减队列，维持最大值， 若a < 队尾，则直接添加。 否则队尾一直弹出，直到递减，那么队头一定最大。
单调递增队列类似。

为何单调栈可以保存最大，最小值。

如上所说，递减队列在right++时，总是可以将比较小的值弹出，然后压入大的值，并且保持递减。
窗口扩张后，队头必然是当前窗口的最大值。

再考虑left++, 若left位置的值小于队头，那么必然已经出队，因为队右侧元素下标必然大于队头。
如果相等，那么就正好是应该出队的元素。

单调栈维持窗口最值

*/
class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int len  = nums.length;
        Deque<Integer> qmin = new LinkedList<>();  // 递增
        Deque<Integer> qmax = new LinkedList<>();  // 递减
        int left = 0, right = 0;
        int ans = 0;
        while(right < len){
            while(!qmin.isEmpty() && qmin.peekLast() > nums[right]) qmin.pollLast();
            while(!qmax.isEmpty() && qmax.peekLast() < nums[right]) qmax.pollLast();
            qmax.offerLast(nums[right]);
            qmin.offerLast(nums[right]);
            while(!qmin.isEmpty() && !qmax.isEmpty() && qmax.peekFirst()-qmin.peekFirst() > limit){
                if(qmin.peekFirst() == nums[left]) qmin.pollFirst();
                if(qmax.peekFirst() == nums[left]) qmax.pollFirst();
                left++;
            }
            right++;
            ans = Math.max(ans, right-left);
        }
        return ans;
    }
}