package Q227;

import java.util.LinkedList;

public class Self {
    public static void main(String[] args) {
        String s = "1-1-1";
        Solution so = new Solution();
        so.calculate(s);
    }
}

class Solution {
    public int calculate(String s) {
        int len = s.length();
        // 只要存进来就是+法。
        LinkedList<int[]> stack = new LinkedList<>();
        int i = 0;
        while(i < len){
            char c = s.charAt(i);
            if(c == ' ') {i++; continue;}
            if(c <= '9' && c >= '0'){
                int t = c - '0';
                i++;
                while(i < len && s.charAt(i) <= '9' && s.charAt(i) >= '0'){
                    t = t * 10 + s.charAt(i)-'0';
                    i++;
                }
                // 获取到完整的数字  开始计算。只进行变号，* /
                int[] cc = stack.peek();
                if(cc == null || cc[0] == 0){
                    stack.push(new int[]{0,t});
                }else{
                    stack.pop();
                    if(cc[1] == '*')
                        stack.push(new int[]{0,t * stack.pop()[1]}); 
                    else if(cc[1] == '/') stack.push(new int[]{0, stack.pop()[1] / t});
                    else stack.push(new int[]{0, -t});  // 变号
                }

            }else if(c == '*' || c == '/' || c == '-'){
                stack.push(new int[]{1,c});
                i++;
            }else{ // '+'
                i++;
            }
        }
        int ans = 0;
        while(!stack.isEmpty()){
            ans += stack.pop()[1];
        }
        return ans;
    }
}