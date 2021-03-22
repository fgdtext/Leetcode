package Q150;

import java.util.LinkedList;


public class Self {
    public static void main(String[] args) {
        String[] tokens = {
            "10","6","9","3","+","-11","*","/","*","17","+","5","+"
        };
        Solution so = new Solution();
        so.evalRPN(tokens);

    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < tokens.length; i++){
            char c = tokens[i].charAt(0);
            if(Character.isDigit(c) ||  tokens[i].length() > 1  ){
                Integer a = new Integer(tokens[i]);
                stack.push(a);
            }else if(c == '*'){
                int a = stack.pop();
                stack.push(stack.pop() * a);
            }else if(c == '/'){
                int a = stack.pop();
                stack.push(stack.pop() / a);
            }
            else if(c == '+'){
                int a = stack.pop();
                stack.push(stack.pop() + a);
            }
            else if(c == '-'){
                int a = stack.pop();
                stack.push(stack.pop() - a);
            }
        }
        return stack.pop();
    }
}