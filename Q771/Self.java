package Q771;

import java.util.HashSet;

public class Self {
    
}
/*
简单， 没啥看的。
*/
class Solution {
    public int numJewelsInStones(String J, String S) {
        HashSet<Character> set = new HashSet<>();
        for(Character c : J.toCharArray()){
            set.add(c);
        }
        int ans = 0;
        for(Character c : S.toCharArray()){
            if(set.contains(c)) ans++;
        }
        return ans;
    }
}