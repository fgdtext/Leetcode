package Q1122;

import java.util.*;


public class Self {
    
}


class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer,Integer> map = new HashMap();
        int[] mem = new int[1001];

        HashSet<Integer> set = new HashSet<>();
        for(int e : arr2){
            set.add(e);

        }
        for(int i = 0; i < arr1.length; i++){
            if(set.contains(arr1[i])){
                if(!map.containsKey(arr1[i])){
                    map.put(arr1[i], 0);
                }
                map.replace(arr1[i], map.get(arr1[i]) +1);
            }else{
                mem[arr1[i]]++;  // 计数排序
            }
        }

        int i = 0;
        for(int e : arr2){
            int count = map.get(e);
            while(count > 0){
                arr1[i++] = e;
                count--;
            }
        }
        for(int k = 0; k <= 1000; k++){
            while(mem[k] > 0){
                arr1[i++] = k;
                mem[k]--;
            }
        }
        return arr1;
    }
}



/*
一个关键点 ：  由于使用  frequency  下标为 值。 arr2[i] 这个数 恰好对应 frequency的下标。可以快速找到 该数出现的频率。

其实 , 这题 看到 val <= 1000 后 ， 就可以明确是 计数 问题。
*/

class Ans {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;


        for (int x : arr2) {   //  frequency[x]  :  数x 出现的频率。
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }
}
