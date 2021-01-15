package Q973;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Self {
    
}

/*
基于堆 的 时间复杂度是 o(nlgn)  

基于 快排 的 时间 复杂度  是 o(n) 但是不好实现。比较麻烦。



*/

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> dui = new PriorityQueue<>((a,b)->{
            return b[0]*b[0]+b[1]*b[1] - a[0]*a[0] - a[1]*a[1];
        });
        for(int i = 0; i < points.length; i++){
            if(dui.size() < K) dui.add(points[i]);
            else{
                int[] temp = dui.peek();
                if(temp[0]*temp[0]+temp[1]*temp[1] > points[i][0]*points[i][0] + points[i][1]*points[i][1]){
                    dui.poll();
                    dui.add(points[i]);
                }
            }
        }
        int[][] ans = new int[dui.size()][2];
        int i = 0;
        for(int[] e : dui){
            ans[i++] = e;
        }
        return ans;
    }
}