package Q1128;

import java.util.HashMap;

public class Self {
    
}


class Self2 {
    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] e : dominoes){
            int a = Math.min(e[0], e[1]);
            int b = Math.max(e[0], e[1]);
            if(!map.containsKey(a*100+b)){
                map.put(a*100+b, 0);
            }
            map.replace(a*100+b, map.get(a*100+b)+1);
        }
        int ans = 0;
        for (int key : map.keySet()){
            int v = map.get(key);
            ans = ans + v*(v-1)/2;
        }
        return ans;
    }
}


class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            // 每多一个 val ， 就有num[val]个于之配对。
            ret += num[val];
            num[val]++;
        }
        return ret;
    }
}
