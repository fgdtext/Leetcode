package Q1207;

import java.util.*;

public class Self {
    
}

/*
计数问题 桶计数 

用数组 空间 效率低。 因为 数据分布 不均匀，浪费大量空间。


*/

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];
        for(int i = 0; i < arr.length; i++){
            count[arr[i]+1000]++;
        }
        int[] temp = new int[1001];
        for(int i = 0; i < 2001; i++){
            temp[count[i]]++;
        }
        for(int i = 1; i < temp.length; i++){
            if(temp[i] > 1) return false;
        }
        return true;
    }
}

/*
用  hash表 
*/
class Ans {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }
        Set<Integer> times = new HashSet<Integer>();
        for (Map.Entry<Integer, Integer> x : occur.entrySet()) {
            times.add(x.getValue());
        }

        //  如果有 任意 两个 数的 频率 相同。 那么 频率 的种类就会 < 频率总数。
        return times.size() == occur.size();
    }
}