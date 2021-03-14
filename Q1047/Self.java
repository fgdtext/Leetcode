package Q1047;

import java.util.*;


/*
遇到和栈顶相同的，就出栈，且该元素不入栈。
不相同或者空栈，就直接入栈。

*/

class Solution {
    public String removeDuplicates(String S) {
        LinkedList<Character> stack = new LinkedList<>();
        for(char c : S.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == c){
                stack.pop();
            }else{
                stack.push(c);
            }
        }
        StringBuilder strr = new StringBuilder();
        while(!stack.isEmpty()) strr.append(stack.removeLast());
        return strr.toString();
    }
}