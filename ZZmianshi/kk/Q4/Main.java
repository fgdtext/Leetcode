package ZZmianshi.kk.Q4;
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] arr = new int[3][n];
        for(int i = 0; i < 3; i++){
            String[] s = in.readLine().split(" ");
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        long[][] dp = new long[3][n];
        for(int j = 1; j < n; j++){
            for(int i = 0; i < 3; i++){
                long d1 = Math.abs(arr[0][j-1]-arr[i][j]) + dp[0][j-1];
                long d2 = Math.abs(arr[1][j-1]-arr[i][j]) + dp[1][j-1];
                long d3 = Math.abs(arr[2][j-1]-arr[i][j]) + dp[2][j-1];
                dp[i][j] = Math.min(d1,d2);
                dp[i][j] = Math.min(dp[i][j],d3);
            }
        }
        long ans = Long.MAX_VALUE;
        for(int i = 0; i < 3; i++){
            ans = Math.min(ans,dp[i][n-1]);
        }
        System.out.println(ans);
        
        
    }
}