package Q503;

import java.util.Arrays;
import java.util.LinkedList;

public class Self {
    
}

/*
记住要遍历两遍， 这样对于数组末尾的元素，可以在数组前端找到更大的值。

使用入栈 数组下标的形式，维护点掉递减栈， 每次遇到需要出栈的时候，即找到了这些出栈元素的更大值。
*/

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] ans = new int[len];
        Arrays.fill(ans, -1);
        int i = 0;
        int k = 0;
        while(k < 2*len -1){
            i = k % len;
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                ans[stack.peek()] = nums[i];
                stack.pop();
            }
            stack.push(i);
            k++;
        }
        return ans;
    }
}



/*class

还有一种方法是动态规划。 
设f(i)为第i个数之后的第一个大于i的数，也就是本题要找的值

这道题最主要的优化下降区间，因为在上升区间，

f(i) = nums[i + 1]

如果在下降区间，要多走很多没用的路程，假设从i到j为下降区间，我们让

dp[i] = j + 1

也就是说当循环来到i寻找f(i)时，可以跳过从i到j这个下降区间，从j+1开始寻找即可

设n为数组长度，初始化 dp[n - 1] = 0

转移方程

dp[i - 1] = dp[i] if nums[i - 1] >= nums[i]
dp[i - 1] = i if nums[i - 1] < nums[i]
*/