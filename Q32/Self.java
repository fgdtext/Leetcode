package Q32;

import java.util.*;

public class Self {
    
}
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
栈
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
                    stack.push(i);
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


未写出来， 失败
*/
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        int left = 0, right = 0;
        int count = 0;
        int maxlen = 0;
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '('){
                left = right = i;
                count++;
                break;
            }
        }
        right++;
        while(right < len){

            // 使用右指针
          
            if(s.charAt(right) == '('){
                count++;
            }else{
                count--;
            }
            System.out.println(left+":::"+right);
            System.out.println("COUNT:::"+count);
            System.out.println("88888888888888888");
            // 移动左指针。
            while(left < right && (count < 0 || right == len - 1 && count > 0 || s.charAt(left) == ')' )){
                System.out.println(left+":::"+right);
                System.out.println("COUNT:::"+count);
                if(s.charAt(left) == '('){
                    count--;
                }else{
                    count++;
                }

                left++;
            }

            if(count == 0){
                if(maxlen < right - left + 1) maxlen = right - left + 1;
            }
            if(right == len - 1 && count == 0) break;
            if(left == right  && right ==  len -1) break;
            //移动右指针
            if(right < len - 1) right++;
        }
        return maxlen;
    }
}

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