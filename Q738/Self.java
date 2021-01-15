package Q738;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        so.monotoneIncreasingDigits(332);
        System.out.print("123");
    }
}
/*
这是用单调栈 

*/
class Self2 {
    int k = 0;
    public int monotoneIncreasingDigits(int N) {
        int[] num = new int[32];
        Arrays.fill(num, -1);
        dfs(N, num);
        LinkedList<Integer> stack = new LinkedList<>();
        boolean flag = false;
        for(int i = 0; i < k; i++){
            if(stack.isEmpty() || num[i] >= stack.peekLast()) stack.addLast(num[i]);
            else{
                int top = stack.removeLast();
                while(true){
                    if(stack.isEmpty() || top - 1 >= stack.peekLast()){
                        stack.addLast(top-1);
                        flag = true;
                        break;
                    }else{
                        top = stack.removeLast();
                    }
                }
            }
            if(flag) break;
        }
        int ans = 0;
        flag = false;
        for(int i = 0; i < k; i++){
            if(flag){
                ans = ans*10 + 9;
            }else{
                if(num[i] == stack.peek()){
                    ans = ans*10 + num[i];
                }else{
                    ans = ans*10 + stack.peek();
                    flag = true;
                }
                stack.removeFirst();
            }
        }
        return ans;
    }
    public void dfs(int n, int[] num){
        if(n == 0) return;
        dfs(n/10, num);
        num[k++] = n%10;
    }
}




class Self3 {
    public int monotoneIncreasingDigits(int N) {
            char[] num = Integer.toString(N).toCharArray();
            LinkedList<Character> stack = new LinkedList<>();
            boolean flag = false;
            int k = num.length;
            for(int i = 0; i < k; i++){
                if(stack.isEmpty() || num[i] >= stack.peekLast()) stack.addLast(num[i]);
                else{
                    char top = stack.removeLast();
                    while(true){
                        if(stack.isEmpty() || top - 1 >= stack.peekLast()){
                            stack.addLast((char)(top-1));
                            flag = true;
                            break;
                        }else{
                            top = stack.removeLast();
                        }
                    }
                }
                if(flag) break;
            }
            int ans = 0;
            int i;
            for( i = 0; i < k; i++){
                if(num[i] == stack.peekFirst()){
                    ans = ans*10 + (num[i]-'0');
                }else{
                    break;
                }
                stack.removeFirst();
            }
            if(i < k){
                ans = ans*10 + stack.peekFirst()-'0';
                int t = ((int)Math.pow(10, k-i-1));
                ans = ans * t + t - 1;
            }
            return ans;
        }
    }
    
    
/*class

不用栈。 直接用数字来

*/

class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        // 从左向右找下降坡的山峰
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            // 从山峰开始向左削掉山峰，取得第一个小的位置，
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }
}

