package ZZZZZ;

import java.util.*;


import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] arr = new int[n][2];
        int max = -1;
        for(int i = 0; i < n; i++){
            String[] s = in.readLine().split(" ");
            arr[i][0] = Integer.parseInt(s[0]);
            arr[i][1] = Integer.parseInt(s[1]);
        }
        Arrays.sort(arr, (o1,o2)->{
            if(o1[0] != o2[0]){
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        max = arr[0][1];
        for(int i = 1; i < n; i++){
            if(arr[i][1] <= max){
                System.out.println("true");
                return;
            }else{
                max = arr[i][1];
            }
        }
        System.out.println("false");
    }
}