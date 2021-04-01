package Q166;

import java.util.HashMap;
import java.util.HashSet;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        so.fractionToDecimal(-1,-2147483648);
    }
}

class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";

        StringBuilder strpre = new StringBuilder();
        // 这里一定要先强转为long 再求绝对值。
        long a = Math.abs((long)numerator);
        long b = Math.abs((long)denominator);
        if(numerator < 0 && denominator > 0 ||numerator > 0 && denominator < 0){
            strpre.append('-');
        }
        
        // 追加整数部分
        strpre.append(a / b);
        a = a % b;
        if(a == 0) return strpre.toString(); 

        StringBuilder strpost = new StringBuilder();
        HashMap<Long,Integer> map = new HashMap<>();
        int ind = 0;
        map.put(a, ind++);
        while(a != 0){
            a *= 10;
            strpost.append(a/b);
            if(a > b){
                a = a % b;
            }
            if(a == 0) break;
            if(!map.containsKey(a)) map.put(a, ind++);
            else{ // 出现循环小数。
                int preind = map.get(a);
                strpost.insert(preind, '(');
                strpost.append(')');
                break;
            }
        }
        return strpre.append('.').append(strpost).toString();
    }
}