package Q316;

import java.util.HashSet;

public class Self {
    
}


class Solution {
    public String removeDuplicateLetters(String s) {
        int[] fre = new int[26];
        int len = s.length();
        for(int i = 0; i < len; i++){
            fre[s.charAt(i)-'a']++;
        }
        // 使用单调栈
        /*
            最终的结果可能并不是单调， 因为我们要求ans 中必须包含所有字符，那么当一个字符只有这一个时，就不能在从栈中删除，而这一点可能是凸点。
        */
        StringBuilder ans = new StringBuilder();
        // HashSet<Character> set = new HashSet<>();
        boolean[] vis = new boolean[26];   // 替换set  时间空间更好。
        for(int i = 0; i < len; i++){
            char c = s.charAt(i); 
            fre[c-'a']--;
            if(!vis[c-'a']){
                // fre[ans.charAt(ans.length()-1)-'a'] 可能会造成凸点。 但是总的来说，还是单调栈，维持递增。
                // 只要当前字符c < 末尾a_ch。且 a_ch 在后序的字符串中还存在，那么就能删除她  
                //  因为    a_ch c.... a_ch 的字典序大于 c...a_ch
                while(ans.length() > 0 && c < ans.charAt(ans.length()-1) && fre[ans.charAt(ans.length()-1)-'a'] > 0){
                    char a_ch = ans.charAt(ans.length()-1);
                    ans.deleteCharAt(ans.length()-1);
                    vis[a_ch-'a'] = false;
                }
                ans.append(c);
                vis[c-'a'] = true;
            }
        }
        return ans.toString();
    }
}