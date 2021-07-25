package Q9;

import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException{
        
        System.out.println();
    }
  
    public static int numSquares(int n) {
        
        int m = (int)Math.sqrt(n);
        int[] dp = new int[n+1];
        Arrays.fill(dp,10000);
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = i*i; j <= n; j++){
                dp[j] = Math.min(dp[j],dp[j-i*i]+1);
            }
        }
        return dp[n];
    }
      
}