package NIUKE.NC118;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Self {
    public static void main(String[] args) {
        int i = 0;
        ArrayList<Double[]> list = new ArrayList<>();
        while(true){
            System.out.println(i++);
           list.add(new Double[1024 * 1024]);
        }
    }
}

 class Solution {
    public int InversePairs(int [] array) {
        int len = array.length;
        LinkedList<Integer> stack = new LinkedList<>();
        int[] dp = new int[len];
        for(int i = 0; i < len; i++){
            int maxk = 0;
            while(!stack.isEmpty() && array[i] < array[stack.peek()]){
                maxk++;
                stack.pop();
            }
            dp[i] = dp[i-1];
        }
        return 0;
    }
}