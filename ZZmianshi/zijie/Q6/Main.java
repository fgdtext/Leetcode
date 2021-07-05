package ZZmianshi.zijie.Q6;

import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int c = Integer.parseInt(s[2]);
        int[] color = new int[c+1];
        int left = 0, right = 0;
        int[][] zui = new int[n][c+1];
        for(int i = 0; i < n; i++){
            String[] ss = in.readLine().split(" ");
            zui[i][0] = Integer.parseInt(ss[0]);
            for(int j = 1; j <= zui[i][0]; j++){
                zui[i][j] = Integer.parseInt(ss[j]);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        while(left < n-1){
            int r = right % n;
            for(int i = 1; i <= zui[r][0]; i++){
                ++color[zui[r][i]];
            }
            if(right - left >= m){
                for(int i = 1; i <= zui[left][0]; i++){
                    --color[zui[left][i]];
                }
                left++;
            }
            for(int i = 1; i <= c; i++){
                if(!set.contains(i)){
                    if(color[i] > 1){
                        set.add(i);
                    }
                }
            }
            right++;
        }
        System.out.println(set.size());
    }
}