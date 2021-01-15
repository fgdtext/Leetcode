package Q336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//  Hashmap 查找与字串对称的串。


class Solution {
    HashMap<String,Integer> indexs = new HashMap<>();

    public List<List<Integer>> palindromePairs(String[] words) {
        

        int len = words.length;
        // 记录 每一个 串 的逆序串的下标。
        for(int i = 0; i < len; i++){
            indexs.put(new StringBuffer(words[i]).reverse().toString(), i);
        }
        // 对每一个串，分两种情况去 找该串的匹配串。
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for(int i = 0; i < len; i++){
            int strlen = words[i].length();
            // 因为回文串会找空串。
            if(strlen == 0) continue;
            //  j == stelen  则，说明是分为空串，和整串。。
            for(int j = 0; j <= strlen; j++){

                if(isHui(words[i], j, strlen-1)){

                    int leftId = findj(words[i], 0, j-1);

                    if(leftId != -1 && leftId != i){
                        ans.add(Arrays.asList(i,leftId));
                    } 
                }
                // j -1 防止后边越界。j!=0 放置j-1前边越界。
                //两个互相对称的串不能互相找  用a 串找出  ab  ba  再用b串找出 ba ab重复了。整串只找一侧。
                if(j!=0 && isHui(words[i], 0, j - 1)){
                    int rightId = findj(words[i], j, strlen-1);
                    if(rightId != -1 && rightId != i){
                        ans.add(Arrays.asList(rightId, i));
                    }
                }

            }
        }
        return ans;
    }
    public int findj(String s, int l, int r){
        return indexs.getOrDefault(s.substring(l, r+1), -1);
    }

    public boolean isHui(String s, int l, int r){
        boolean flag = true;
        while(l < r){
            if(s.charAt(l) != s.charAt(r)) {flag = false; break;}
            ++l; --r;
        }
        return flag;
    }
}


public class self{
    public static void main(String[] args) {
        int len = 10;
        for(int i = 0; i < len; i++){
            for(int j = 0;(i!=j) && (j < len); j++){
                System.out.printf("i: %d,,,j: %d\n", i,j);
            }
        }
    }
}