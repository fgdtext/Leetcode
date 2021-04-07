package Q22;

import java.util.*;

public class Self {
    
}
/*
双栈模拟。

*/

// 未完成，下边有更优 算法，思想相同。
class Solution2 {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList();
        LinkedList<Character> stack1 = new LinkedList<>();
        LinkedList<Character> stack2 = new LinkedList<>();
        for(int i = 0; i < n; i++){
            stack1.addFirst('(');
            stack2.addFirst(')');
        }
         // 3对括号，就是 6 个字符
        dfs(new StringBuilder(),stack1,stack2);
        return ans;
    }
    // 回溯
    public void dfs(StringBuilder str, LinkedList<Character> stack1,LinkedList<Character> stack2){
        if(stack1.isEmpty() && stack2.isEmpty()){
            ans.add(str.toString());
            return;
        }
        if(!stack1.isEmpty()){
            char temp = stack1.removeFirst();
            str.append(temp);
            dfs(str, stack1, stack2);
            stack1.addFirst(temp);
            str.deleteCharAt(str.length()-1);
        }
        if(stack1.size() < stack2.size()){
            char temp = stack2.removeFirst();
            str.append(temp);
            dfs(str, stack1, stack2);
            stack2.addFirst(temp);
            str.deleteCharAt(str.length()-1);
        }
    }

}

/*
灵感来源于 上边的 双栈 方式。 但是 由于 两个栈中存的一定是 ( 和) ，所以可以不适用实际的栈结构。
而是使用 n,m 来表示栈的大小。
初试 n 表示栈1中有n个 '('    m 表示 栈 2 中有 m个 ')'

算法 ： 模拟可能的两个栈的出栈(只出栈)  ： 

当 两个栈的大小一样时，只能出栈 '(' ，因为已经出栈的的括号中
左括号的数量 >= 右括号的数量 (括号合法性规则就是，只要已经出栈的左括号 >= 右括号， 就可以继续下去)

当 m > n时，说明 剩余 右括号 很多， 此时既可以  出栈 '(', 又可以出栈 ')'. 

综上 ： 每次 递归 一定会执行 出栈 '('的子递归， 出栈 ')' 的递归要看 m > n 是否成立。

*/


class Solution {
    List<String> ans;
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList();
         // 3对括号，就是 6 个字符
        dfs(new StringBuilder(),n,n);
        return ans;
    }
    // 回溯
    public void dfs(StringBuilder str, int n, int m){
        if(n == 0 && m == 0){
            ans.add(str.toString());
            return;
        }
        if(n != 0){
            str.append('(');
            dfs(str, n-1, m);
            str.deleteCharAt(str.length()-1);
        }
        // 添加右括号的条件。
        if(n < m){
            str.append(')');
            dfs(str, n, m-1);
            str.deleteCharAt(str.length()-1);
        }
    }
}



/*class
还可以按照 char[2*n]的位置来确定每一个位置可能的选择，可能是 ( ，也可能是 ).
确定当前括号 要 符合  出现的 (  要大于 ). 即可以继续确定下一位。
若cur 前的 m == n  则 当前位置只能是 (
否则 也可能是  )

普通回溯法，加限制条件。

*/

class Ans {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return res;
        }
        getParenthesis("",n,n);
        return res;
    }

    private void getParenthesis(String str,int left, int right) {
        if(left == 0 && right == 0 ){
            res.add(str);
            return;
        }
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str+"(",left-1,right);
        }else if(left < right){
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0){
                getParenthesis(str+"(",left-1,right);
            }
            getParenthesis(str+")",left,right-1);
        }
    }
}