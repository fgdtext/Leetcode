package Q224;

import java.util.LinkedList;


public class Self {
    public static void main(String[] args) {
        String s = "5+3-4-(1+2-7+(10-1+3+5+(3-0+(8-(3+(8-(10-(6-10-8-7+(0+0+7)-10+5-3-2+(9+0+(7+(2-(2-(9)-2+5+4+2+(2+9+1+5+5-8-9-2-9+1+0)-(5-(9)-(0-(7+9)+(10+(6-4+6))+0-2+(10+7+(8+(7-(8-(3)+(2)+(10-6+10-(2)-7-(2)+(3+(8))+(1-3-8)+6-(4+1)+(6))+6-(1)-(10+(4)+(8)+(5+(0))+(3-(6))-(9)-(4)+(2))))))-1)))+(9+6)+(0))))+3-(1))+(7))))))))";
        Solution so = new Solution();
        so.calculate(s);
    }
}


class Solution {
    public int calculate(String s) {
        int len = s.length();
        LinkedList<int[]> stack = new LinkedList<>();
        int num = 0;
        boolean beforeIsNum = false;
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            
            if(c < '0' || c > '9') {
                if(beforeIsNum) pushAnum(stack,num);
                beforeIsNum = false;
                num = 0;
            }
            if(c == ' ') continue;
            //  分别是 符号第一位用 1，数字第一位用0
            if(c == '(' | c == '+' || c == '-') stack.push(new int[]{1,c});
            else if(c >= '0' && c <= '9') {
                if(beforeIsNum){ // 前一位是数字
                    num  = num*10 + (c-'0');
                }else{
                    num = c-'0';
                    beforeIsNum = true;
                }
            }else {  // 遇到右括号
                int t = 0;
                while(!stack.isEmpty() && stack.peek()[0] == 0){
                    t += stack.poll()[1];
                }
                if(!stack.isEmpty() && stack.peek()[0] == 1 && stack.peek()[1] == '(')  stack.pop();
                pushAnum(stack, t);
            }
        }
        if(beforeIsNum) pushAnum(stack,num);
        int t = 0;
        while(!stack.isEmpty() && stack.peek()[0] == 0){
            t += stack.poll()[1];
        }
        return t;
    }
    // 必须保证 c 表示一个数字
    public void pushAnum(LinkedList<int[]> stack, int num){
        if(!stack.isEmpty() && stack.peek()[0] == 1){
            if(stack.peek()[1] == '+'){
                stack.pop();
                stack.push(new int[]{0,num});
            }else if(stack.peek()[1] == '-'){
                stack.pop();
                stack.push(new int[]{0,-num});
            }else stack.push(new int[]{0,num});
        }else stack.push(new int[]{0,num});
    }
}