package Q205;

import java.util.*;


public class Self {
    
}


class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder a = new StringBuilder();
        int k = 0;
        for(int i = 0; i < s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), ++k);
            }
            a.append(map.get(s.charAt(i)));
        }
        map.clear();
        StringBuilder b = new StringBuilder();
        k = 0;
        for(int i = 0; i < t.length(); i++){
            if(!map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), ++k);
            }
            b.append(map.get(t.charAt(i)));
        }
        return a.toString().equals(b.toString());
    }
}



/*class

体会一下。
假设有
      s*******s
      t*******t
    第一个st出现时，st的数量是相等的。
    第二个st出现时，st的数量也时相等的。
    若有
      s**r****s
      t**t****t
      则到rt时，发现rt数量不相等。则说明不匹配。
*/

class Ans {
    public boolean isIsomorphic(String s, String t) {
           char[] chars = s.toCharArray();
           char[] chart = t.toCharArray();
           int[] preIndexOfs = new int[256];
           int[] preIndexOft = new int[256];
           for (int i = 0; i < chars.length; i++) {
               if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                   return false;
               }
               preIndexOfs[chars[i]] = i + 1;
               preIndexOft[chart[i]] = i + 1;
           }
           return true;
       }
   }