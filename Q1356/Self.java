package Q1356;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
        int[][] temp = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            temp[i][0] = arr[i];
            temp[i][1] = hammingWeight(arr[i]);
        }
        Arrays.sort(temp,new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
               return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i][0];
        }
        return arr;
    }
   
   
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }
}