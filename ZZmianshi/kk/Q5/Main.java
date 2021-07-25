package ZZmianshi.kk.Q5;
import java.util.*;
import java.io.*;
public class Main{
    static long mod = 998244353;
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        int[] arr = new int[5];
        for(int i = 0; i < 5; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int n = arr[0]*arr[0];
        long res = 1;
        res =  Cab(n,arr[1]) % mod * res % mod ;
        res = Cab(n-arr[1],arr[2]) % mod * res % mod ;
        res = Cab(n-arr[1]-arr[2],arr[3]) % mod * res % mod ;
        System.out.println(res);
    }
  public static long Cab(int n, int a){
        long resup = 1;
        long resdown = 1;
        for(int i = n; i >= n-a+1; i--){
            resup = resup * i % mod;
        }
        for(int i = 2; i <= a; i++){
            resdown = resdown / i % mod;
        }
        return resup / resdown;
    }
}