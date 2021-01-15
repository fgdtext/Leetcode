package Q387;

public class Self {
    
}


class Solution {
    public int firstUniqChar(String s) {
        int[] fre = new int[26];
        for(int i = 0; i < s.length(); i++){
            fre[s.charAt(i)-'a']++;
        }
        int i = 0;
        for(; i < s.length(); i++){
            if(fre[s.charAt(i)-'a'] == 1){
                break;
            }
        }
        return i == s.length() ? -1 : i;
    }
}