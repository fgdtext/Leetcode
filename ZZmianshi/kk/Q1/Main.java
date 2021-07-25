package ZZmianshi.kk.Q1;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        int n = s.length();
        char[] ss = new char[n+1];
        for(int i = 1; i <= n; i++){
            ss[i] = s.charAt(i-1);
        }
        int[][] dp = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i],0);
        }
        int[] am = {0,100,200,200,220};
        for(int i = n-1; i > 0; i--){
            for(int j = i+1; j <= n; j++){
                if(ss[i] == ss[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i+1][j]+am[ss[i]-'0'], dp[i][j-1]+am[ss[i]-'0']);
                }
                
            }
        }
        System.out.println(dp[1][n]);
    }
}

/*class
4422
*/