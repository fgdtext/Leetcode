package Q1002;
import java.util.*;

public class Self {
    
}

class Solution {
    public List<String> commonChars(String[] A) {
        int[] fre = new int[26];
        for(int i = 0; i < A.length; i++){
            int[] temp = new int[26];
            for(int j = 0; j < A[i].length(); j++){
                temp[A[i].charAt(j) - 'a'] ++;
            }
            for(int k = 0; k < 26; k++){
                if(fre[k] == 0) fre[k] = temp[k];
                else fre[k] = Math.min(fre[k], temp[k]);
            }
        }
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            for(int k = 0; k < fre[i]; k++){
                Character c = (char)('a' + i);
                ans.add(c.toString());
            }
        }
        return ans;
    }
}