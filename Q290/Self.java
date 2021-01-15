package Q290;

import java.util.HashMap;

public class Self{
    public static void main(String[] args) {
        Solution so = new  Solution();
        so.wordPattern("abba", "dog cat cat dog");


    }
}


/*
两个 hash表。 用统一的标准做映射即可。

*/
class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        int k = 0;
        int s_value = 0;
        for(int i = 0; i < ss.length; i++){
            if(map.containsKey(ss[i])){
                s_value = s_value*10 + map.get(ss[i]);
            }else{
                k++;
                s_value = s_value*10 + k;
                map.put(ss[i], k);
            }
        }
        HashMap<Character, Integer> mapp = new HashMap<>();
        k = 0;
        int p_value = 0;
        for(int i = 0; i < pattern.length(); i++){
            if(mapp.containsKey(pattern.charAt(i))){
                p_value = p_value*10 + mapp.get(pattern.charAt(i));
            }else{
                k++;
                p_value = p_value*10 + k;
                mapp.put(pattern.charAt(i), k);
            }
        }
        return s_value == p_value;
    }
}