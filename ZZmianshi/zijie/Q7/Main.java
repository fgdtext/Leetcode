package ZZmianshi.zijie.Q7;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1])-1;
        long[] arr = new long[n];
        String[] ss = in.readLine().split(" ");
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(ss[i]);
        }
        long min = Integer.MAX_VALUE;
        int minind = -1;
        for(int i = 0; i <= n; i++){
            int ind = (x + i) % n;
            if(arr[ind] <= min){
                min = arr[ind];
                minind = ind;
            }
        }
        for(int i = 0; i < n; i++){
            arr[i] -= min;
        }
        int ind = (minind+1)%n;
        long sum = 0;
        if(minind != x){
            while(ind != x){
            arr[ind] -= 1;
            ind = (ind+1)%n;
            sum++;
            }
            arr[ind] -= 1;
            sum++;
        }
        arr[minind] = min*n + sum;
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }
    }
}