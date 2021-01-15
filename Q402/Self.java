package Q402;



public class Self {
    
}
/*
单调栈 ？？？？ 单调栈的应用。  每次遇到小于栈顶的，就弹栈，直到当前元素可以入栈（弹出个数到k为止）。

单调栈的应用 ： 
    1.如果 num[i] >= stack[top] 则可以入栈num[i]
    2.如果 num[i] < stack[top] 则循环出栈 top--,k--，直到满足num[i] >= stack[top] 或者 k==0，然后入栈num[i]
    1,2 保持了 栈为单调递增（非严格）

    // 出栈的一定是构成了 山峰(右侧下降峰)，一定需要删除山峰。删除山峰后，如果还不满足递增，则说明形成新的下降峰继续出栈。

    由于有很多连续的数可能相等 例如  555666 k = 3 此时 栈会连续入栈 555666. 这样只需要最后连续出栈 栈的后边即可。一定会使得栈内的数最小。
    （因为是递增栈，删除栈顶连续的数，一定使得栈内整体最小。）


    如果要使用现成的 单调栈 工具， 必须满足 双端队列的特性， 因为最后取数，是从 栈底开始的。

*/

class Solution {
    public String removeKdigits(String num, int k) {
        if(num.length() == 1 && k == 1) return ""+'0';
        char[] stack = new char[num.length()];  // 构成单调栈。
        int j = 0;
        stack[0] = num.charAt(0);
        int top = 0; // 永远指向栈顶元素。
        for(int i = 1; i < num.length(); i++){
            char c = num.charAt(i);
            while( k > 0 && top >= 0 &&  c < stack[top]){ // top 可能为 -1
                top--;
                k--;
            }
            stack[++top] = c;  // 删除以后再入栈c            
        }
        top -= k;  // 考虑可能连续相等，造成找出的山峰不够数的情况。
        StringBuilder ans = new StringBuilder();
        boolean flag = false;
        for(int i = 0; i <= top; i++){
            if(stack[i] != '0') flag = true;
            if(flag){
                ans.append(stack[i]);
            }
        }
        if(ans.length() == 0) return ""+'0';
        return ans.toString();
    }
}