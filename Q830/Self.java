package Q830;

import java.util.ArrayList;
import java.util.List;

public class Self {
    
}

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        if(s.length() < 3) return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        int k = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == s.charAt(i-1)){
                k++;
            }else{
                if(k >= 3){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i-k);
                    temp.add(i-1);
                    ans.add(temp);
                }
                k = 1;
            }
        }
        if(k >= 3){
            List<Integer> temp = new ArrayList<>();
            temp.add(s.length()-k);
            temp.add(s.length()-1);
            ans.add(temp);
        }
        return ans;
    }
}