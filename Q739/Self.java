package Q739;

import java.util.Deque;
import java.util.LinkedList;

public class Self {
    
}

/*

维持一个单调栈 （递减） 每次遇到大于栈顶的就不停的出栈，直到小于等于栈顶。 
而对于出栈的下标，新入栈的下标就是第一个温度大于它的位置。


*/
class Solution {
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }
}
