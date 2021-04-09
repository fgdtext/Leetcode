package Q76;

import java.util.HashMap;




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
public class Self {
    public static void main(String[] args) {
        String s = "ab";
        String t = "b";
        Solution3 ss = new Solution3();
        String ans = ss.minWindow(s, t);
        System.out.println(ans);
    }
}


class Solution3 {
    public String minWindow(String s, String t) {
        int slen = s.length();
        HashMap<Character,Integer> freq = new HashMap<>();
        
        for(char c : t.toCharArray()){
            if(!freq.containsKey(c)) freq.put(c, 0);
            freq.replace(c, freq.get(c)+1);
        }
        HashMap<Character,Integer> curf = new HashMap<>();
        for(char c : freq.keySet()){
            curf.put(c, 0);
        }
        int k = 0;
        int i = 0;
        for(; i < slen; i++){
            char c = s.charAt(i);
            if(curf.containsKey(c)){
                curf.replace(c, curf.get(c)+1);
                int kk = freq.get(c);
                if(curf.get(c) == kk) {
                    k++;
                }
            }
            if(k == freq.size()) break;
        }
        if(k < freq.size()) return "";
        int ansl = 0, ansr = i+1;
        int left = 0, right = i+1;
        //先收缩一次
        while(true){
            char cl = s.charAt(left);
            if(curf.containsKey(cl)){
                if(curf.get(cl) > freq.get(cl)){
                    curf.replace(cl, curf.get(cl)-1);
                }else{
                    break;
                }
            }
            left++;
            if(ansr-ansl > right-left) {
                ansl = left; ansr = right;
            }
        }
        while(right < s.length()){
            char cr = s.charAt(right);
            if(curf.containsKey(cr)){
                curf.replace(cr, curf.get(cr)+1);
                // 开始收缩窗口
                while(true){
                    char cl = s.charAt(left);
                    if(curf.containsKey(cl)){
                        if(curf.get(cl) > freq.get(cl)){
                            curf.replace(cl, curf.get(cl)-1);
                        }else{
                            break;
                        }
                    }
                    left++;
                    if(ansr-ansl > right-left+1){
                        ansl = left; ansr = right+1;
                    }
                }
            }
            right++;
        }
        while(true){
            char cl = s.charAt(left);
            if(curf.containsKey(cl)){
                if(curf.get(cl) > freq.get(cl)){
                    curf.replace(cl, curf.get(cl)-1);
                }else{
                    break;
                }
            }
            left++;
            if(ansr-ansl > right-left) {
                ansl = left; ansr = right;
            }
        }
        return s.substring(ansl,ansr);
    }
}