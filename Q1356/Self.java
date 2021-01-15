package Q1356;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Self {
    public static void main(String[] args) {
        int[] arr = {1024,512,256,128,64,32,16,8,4,2,1};
        Solution so = new Solution();
        arr = so.sortByBits(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i]+" ");
        }
    }
}


/*
效率不是很高啊。



*/


class Solution {
    public int[] sortByBits(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int e : arr){
            int count = count(e);
            if(!map.containsKey(count)){
                map.put(count, new ArrayList<>());
            }
            map.get(count).add(e);
        }
        int cur = 0;
        for(int i = 0; i <= 31; i++){
            ArrayList<Integer> list = map.get(i);
            if(list == null) continue;
            Collections.sort(list);
            for(int e : list){
                arr[cur++] = e;
            }
        }
        return arr;
    }
    // 对 0 - 30 位计数
    public int count(int num){
        int conut = 0;
        int k = 1;
        for(int i = 0; i < 31; i++){
            if((num&k) != 0) conut++;
            k*=2;
        }
        return conut;
    }
}