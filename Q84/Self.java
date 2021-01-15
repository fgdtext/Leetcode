package Q84;

import java.util.ArrayDeque;
import java.util.Deque;

public class Self {
    public static void main(String[] args) {
        int[] arr = {2,1,2};
        Solution so = new Solution();
        so.largestRectangleArea(arr);

    }
}


/*
重点

算法演进思想 : 
1.   求以当前块为高度的矩形的面积。那么可以以该高度向左右扩展，只要大于等于当前块就可以扩展。
    这是原始的暴力枚举法。时间应该是 o(n^2)
    那么我们可以基于这样的想法进行优化。

2.  我们的目的仍然是枚举每一个可能的高度。 但是在扩展时，相邻矩形的扩展效率太低。不停的重复去扩展。
    想办法只一次遍历就得到以位置i 的高度的矩形。
3.  建立一个单调栈。 非严格递增栈(使用下标，不仅可以知道高度，还可以知道位置然后求宽度) 

    很明显， 当遇到某位置j 高度小于栈顶时，那么栈顶的矩形面积就可以确定。栈顶a, 栈内次顶b ah >= bh
    那么就可以确定a 的左边界是 b+1.  右边界是j-1.  ab两块并不是一定相邻的，但是中间的块一定是 >= ah 的 若 < ah，则一定会在栈中
    如   7，8，10   不可能出现栈中元素为  7，10    8一定在其中。若8不在。  则应该是  7，11，10 


*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if(len == 0) return 0;
        if(len == 1) return heights[0];
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for(int i = 1; i < len; i++){
            while(!stack.isEmpty() &&  heights[i] < heights[stack.peek()]){
                int ind = stack.poll();
                // i要取栈顶的左边一个的位置。因为
                /*
                    例如2，1，2    2入栈，1会使得2出栈。 得到第一个块的面积是2.  此时栈内有 1
                    然后2 入栈。  栈内为 1，2。那么以这个2为高的面积仍然是2. 然后出栈2.  此时栈内有 1
                    若宽度使用 元素1的下标，那么以1为高的面积是 下标1到2，宽度为2.  出现了错误。以1为高的宽度应该是3.
                    这是因为1的入栈导致之前2出栈，左边就丢失了。 所以取1的栈顶的下一个作为左边界，因为左侧元素 <= 栈顶。
                    这样宽度总是 w+1. 计算时减1即可。
                */
                int r = stack.isEmpty()? -1 :  stack.peek();
                ans = Math.max(ans, heights[ind]*(i - r - 1));
            }
            stack.push(i); 
        }
        // 最终栈中是非严格递增的。有相等也没关系。
        while(!stack.isEmpty()){
            int ind = stack.poll();
            int r = stack.isEmpty()? -1 :  stack.peek();
            ans = Math.max(ans, heights[ind]*(len - r - 1));
        }
        return ans;
    }
}


