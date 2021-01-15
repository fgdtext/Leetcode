package Q8;

public class Self {
    public static void main(String[] args) {
        String s =  "9223372036854775808";
        Solution so = new Solution();
        so.myAtoi(s);

    }
}
/*
    

*/
class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int k = 0;
        boolean isdigi = false;
        for(; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;
            else if(c == '-' || c == '+'){
                if(c == '-') k = 1;
                break;
            }
            else if(Character.isDigit(c)){
                isdigi = true;
                break;
            }
            else{
                return 0;
            }
        }
        if(!isdigi) i++; // 第一个有效字符不是数字，说明有符号前缀。i++;
        if(i >= s.length()) return 0;
        long digi = 0;
        for(; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                digi = digi*10 + c - '0';
            }else{
                break;
            }
            if(digi - 1 > Integer.MAX_VALUE) break;   // 太大了 就没有必要再追加了。
        }
        digi = k == 0? digi : -digi;
        // 超越int边界
        if(digi > Integer.MAX_VALUE) return Integer.MAX_VALUE;  
        if(digi < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)digi;
    }
}