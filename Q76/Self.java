package Q76;

import java.util.HashMap;



public class Self {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Solution ss = new Solution();
        String ans = ss.minWindow(s, t);
        System.out.println(ans);
    }
}
/*
该 最小子串 应有的性质 ：   1.以t中某个 字符开头， 以 t中某个字符 结尾。

20% 的算法， 还有 优化 空间。
*/

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> stdmap = new HashMap<>();
        // 标准 map. 
        for(Character c : t.toCharArray()){
            if(stdmap.containsKey(c)){
                stdmap.replace(c,stdmap.get(c) + 1);
            }else{
                stdmap.put(c, 1);
            }
        }
        HashMap<Character, Integer> curmap = new HashMap<>();
        int left = 0, right = 0;
        int minlen = s.length() + 1;
        int start = -1;
        int k = 0;
        while(right < s.length()){
            Character c = s.charAt(right);
            // 如果属于 
            if(stdmap.containsKey(c)){
                k++;
                if(curmap.containsKey(c)){
                    curmap.replace(c,curmap.get(c) + 1);
                }else{
                    curmap.put(c, 1);
                }
            }
            if(k < t.length()) { right++; continue;} // 必然没有覆盖字符串 t 
            boolean flag = check(stdmap, curmap);
            if(!flag) {right++; continue;}
            
            // 当前已经覆盖了 子串。 开始进行收缩。  该 字符还有余量，可以缩减，
             while(!stdmap.containsKey(s.charAt(left))) left++;
            while(curmap.get(s.charAt(left)) > stdmap.get(s.charAt(left))){
                curmap.replace(s.charAt(left), curmap.get(s.charAt(left)) - 1);
                left++;
                while(!stdmap.containsKey(s.charAt(left))) left++;
            }
            // 当前 left - right 为 一个 最短子串。与上一个最短子串比较长度。

            if(right - left + 1 < minlen){
                minlen = right - left + 1;
                start = left;
            }
            right++;
            while(right < s.length() && !stdmap.containsKey(s.charAt(right))){
                right++;
            }
            if(right >= s.length()) break;
        }
        if(start == -1) return "";
        return s.substring(start, start+minlen);
    }
    public boolean check(HashMap<Character, Integer> stdmap, HashMap<Character, Integer> curmap){
        for(Character c : stdmap.keySet()){
            if(!curmap.containsKey(c)) return false;
            if(stdmap.get(c) > curmap.get(c)) return false;
        }
        return true;
    }
}