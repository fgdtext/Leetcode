package Q32;

import java.util.*;


  /*

动态规划  count[i][j] 表示 i~j的左括号数 - 右括号数  若为0则为一个合法的 括号序列

若出现 < 0 则 i到j之后的序列都是不合法序列

时间太差劲。o(n^2)的时间 时间5.20% 很差。

*/

class Solution1 {
    public int longestValidParentheses(String s) {
        int len = s.length();
        //
        int[] count = new int[len];
        int maxlen = 0;
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ')') continue;
            count[i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(j) == '(') count[j] = count[j-1] + 1;
                else count[j] = count[j-1] - 1;
                if(count[j] < 0) break; // 以i 开头的字符串到j-1是合格的,i~j是非法的
                if(count[j] == 0 && maxlen < j - i + 1) maxlen = j - i + 1; 
            }
        }
        return maxlen;
    }
}

/*
题解的动态规划，一维数组填表。且扫描一遍即可得到整张表


*/
 class Ans1 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }
}

/*
栈 : 还必须放进去一个-1。 他出栈时
*/
public class Self {
    public static void main(String[] args) {
        Ans2 so = new Ans2();
        so.longestValidParentheses("())(()");
    }
}


/*
    这我想不到，这栈用的太骚了。
    问题： 为什么要用 占位 不用行不行。对于 ()() 这样的例子，假如入栈，出栈，会得到2. 后边的实际是可以继续扩展的，但是由于我们已经全部出栈
            会导致前边的丢失。
            所以我们总是需要在出栈后，栈中永远保留，最长串的前一个位置。这样匹配出栈时，总是可以占位留在栈顶。

*/
 class Ans2 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);  // 用来占位的。冒充 (的位置
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

/*
参考 题解方法3的 正向逆向结合的方法。这里没有进行逆向的扫描，导致，有正确的结果被掩盖

"()(()"  : 这个例子是无法通过的。  在while(right < len) 窗口是整个串， 通过减去left的左括号是不行的。因为即使减去后count=0了
            也无法保证后边的左括号一定是在右括号左侧的。 比如可能出现 )(() count 也是0
未写出来， 失败
*/

class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int count = 0;
        int maxlen = 0;
        while(right < len){
            if(s.charAt(right) == '('){
                count++;
            }else{
                count--;
            }
            if(count == 0) maxlen = Math.max(maxlen, right - left + 1);
            if(count < 0){
                left = right+1; count = 0;
            }
            right++;
        }
        
        while(count > 0 && left < len){
            if(s.charAt(left++) == '('){
                count--;
            }else{
                count++;
            }
            if(count == 0) {
                maxlen = Math.max(maxlen, right - left + 1);
                break;
            }
        }
        return maxlen;
    }
}
/*

最优算法
 )(()
 (()
*/
 class Ans_3 {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}