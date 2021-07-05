package ZZmianshi.zijie.Q1;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] ss = in.readLine().split(" ");
        int[] h = new int[n+1];
        int ansh = 0;
        for(int i = 1; i <= n; i++){
            h[i] = Integer.parseInt(ss[i-1]);
            ansh = Math.max(ansh, h[i]);
        }
        int max = ansh;
         for(int i = 0; i < max; i++){
             if(can(h,i,max)){
                 System.out.println("true");
             }else{
                 System.out.println("flase");
             }
         }
    }
    // 初始能量h是否能到达
    public static boolean can(int[] h, long e,int max){
        int len = h.length;
        for(int i = 1; i < len; i++){
            //if(e >= max) return true;
            e = 2 * e  - h[i];
            e %= Long.MAX_VALUE / 2 - 1;
            if(e < 0) {
                return false;
            }
                
        }
        return true;
    }
}