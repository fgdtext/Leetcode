package Q456;

import java.util.LinkedList;


public class Self {
    
}

/*

维持一个单调递减的栈。

由于我们要找  小 大 中 
栈中保存可能是3 的元素。那么大的值总会使得较小的值弹出。而且弹出的值必定在3的右侧。
由于其小于3， 我们每次只需要使用 max_2 保存最大的2.  由于我们的入栈规定是，nums[i] > max_2时才入栈，那么栈顶总是大于>max_2的
所以栈顶总是满足 3 > max_2的。 我们遇到一个心的元素 num[i] ,如果出现 nums[i] < max_2 , 那么一定有
    3 > max_2 > nums[i]. 于是查找成功。

每次我们都把栈顶当作3.  

*/
class Solution {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        if(len < 3) return false;
        LinkedList<Integer> stack = new LinkedList<>();
        int max_2 = Integer.MIN_VALUE;
        stack.push(nums[len-1]);
        for(int i = len-2; i >= 0; i--){
            if(nums[i] < max_2) return true;
            while(!stack.isEmpty() && nums[i] > stack.peek()){
               max_2 = Math.max(stack.pop(), max_2);
            }
            if(nums[i] > max_2)
                stack.push(nums[i]);
        }
        return false;
    }
}