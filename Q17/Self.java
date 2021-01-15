package Q17;

import java.util.ArrayList;
import java.util.List;

class Solution {
    String[] map = {
        "0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    List<String> ans;
    StringBuffer strBuf;
    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if(digits.length() == 0) return ans;
        strBuf = new StringBuffer();
        digui(0, digits.length(), digits);
        return ans;
    }
    public void digui(int cur, int len,String digits){
        // 到头了
        if(cur == len){
            ans.add(strBuf.toString());
            return;
        }
        // 获取当前位置的数字
        int num = digits.charAt(cur) - '0';
        for(int i = 0; i < map[num].length(); i++){
            strBuf.append(map[num].charAt(i));
            digui(cur+1, len, digits);
            strBuf.deleteCharAt(strBuf.length() - 1);
        }
    }
}