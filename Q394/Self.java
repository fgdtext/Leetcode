package Q394;

import java.util.*;

public class Self {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Solution ss = new Solution();
        String ans = ss.decodeString(s);
    }
}
/*

*/
class Self1 {
    public String decodeString(String s) {
        int len = s.length();
        LinkedList<Character> stack = new LinkedList<>();
        for(int i = 0; i < len; i++){
            Character c = s.charAt(i);
            if(c == ']'){  // 遇到 ']'出栈，一直出栈，直到左括号。
                // 先出栈目前要重复的字符串。
                ArrayList<Character> queueC = new ArrayList<>();
                while(stack.peekLast() != '['){
                    queueC.add(stack.removeLast());
                }
                stack.removeLast();
                int j = 0;
                int k = 0;
                while(!stack.isEmpty() && stack.peekLast() <= '9' && stack.peekLast() >= '0'){
                    k = (int)((stack.removeLast()-'0') * Math.pow(10, j++)) + k;
                }
                while(k > 0){
                    for(j = queueC.size()-1; j >= 0; j--){
                        stack.addLast(queueC.get(j));
                    }
                    k--;
                }
            }else{ // 只要不是 ']' 就入栈
                stack.addLast(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()){
            ans.append(stack.removeFirst());
        }
        return ans.toString();
    }
}
/*
比用 ArrayList，LinkedList快很多。
*/
class Solution {
    public String decodeString(String s) {
        int len = s.length();
        StringBuilder stack = new StringBuilder();
        for(int i = 0; i < len; i++){
            Character c = s.charAt(i);
            if(c == ']'){  // 遇到 ']'出栈，一直出栈，直到左括号。
                // 先出栈目前要重复的字符串。
                
                int ind = stack.length()-1;
                while(stack.charAt(ind) != '['){
                    ind--;
                }
                StringBuilder queueC = new StringBuilder(stack.substring(ind+1));
                stack.delete(ind, stack.length());

                ind = stack.length()-1;
                int j = 0;
                int k = 0;
                while(ind>=0 && stack.charAt(ind) >= '0' && stack.charAt(ind) <= '9'){
                    k = (int)((stack.charAt(ind)-'0') * Math.pow(10, j++)) + k;
                    ind--;
                }
                stack.delete(ind+1, stack.length());

                while(k > 0){
                    stack.append(queueC);
                    k--;
                }
            }else{ // 只要不是 ']' 就入栈
                stack.append(c);
            }
        }
        return stack.toString();
    }
}



/*
直接用  String .  

*/
class Ans {
    int ptr;

    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++))); 
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }
    /*
        当发现第一个数字后，就加载这个数字到

    */
    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}

/*
数字栈和符号栈分开
*/

class Ans2 {
    public String decodeString(String s) {
       StringBuffer ans=new StringBuffer();
       Stack<Integer> multiStack=new Stack<>();
       Stack<StringBuffer> ansStack=new Stack<>();
       int multi=0;
       for(char c:s.toCharArray()){
           if(Character.isDigit(c))multi=multi*10+c-'0';
           else if(c=='['){  // 数字后一定有 '['
               ansStack.add(ans);
               multiStack.add(multi);
               ans=new StringBuffer();
               multi=0;
           }else if(Character.isAlphabetic(c)){
               ans.append(c);
           }else{
               StringBuffer ansTmp=ansStack.pop();
               int tmp=multiStack.pop();
               for(int i=0;i<tmp;i++)ansTmp.append(ans);
               ans=ansTmp;
           }
       }
       return ans.toString();
   }
}
