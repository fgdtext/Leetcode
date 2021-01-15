package Q85;

import java.util.*;

public class Self {
    
}



/*

dp[i][j][k]: 记录以ij为右下角的矩形的长和宽。k={0,1，2，3}(向左的最大矩形，向上的最大矩形)

dp[i][j]  -依赖--> dp[i][j-1], dp[i-1][j]

完全  error 

压根不是动态规划

*/





/*class
1. 暴力
2. 单调栈
*/

/*


从第0行开始算从该行向上的最大矩形面积。
每一行都先算出以该行为底的每个位置的小矩形的高度。最终算以该行为底向上的矩形的最大面积。


*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int lenr = matrix.length;
        if(lenr == 0) return 0;
        int lenc = matrix[0].length;
        if(lenr == 0) return 0;
        int[] heights = new int[lenc];
        int ans = 0;

        for(int i = 0; i < lenr; i++){
            for(int j = 0; j < lenc; j++){
                heights[j] = matrix[i][j] == '1' ? heights[j]+1 : 0;
            }
            int a = largestRectangleArea(heights);
            ans = Math.max(ans, a);
        }
        return ans;
    }

    //  完全就是 Q84 的函数
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