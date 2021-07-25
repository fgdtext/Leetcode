package ZZmianshi.kk.Q3;

import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] s = in.readLine().split(" ");
        long A = Long.parseLong(s[0]);
        long B = Long.parseLong(s[1]);
        long a = Long.parseLong(s[2]);
        long b = Long.parseLong(s[3]);
        long c  = gcd(a,b);
        a /= c; b /= c;
        if(B*1.0/A < b*1.0/a){
            for(long y = B; y > 0; y--){
                if(y % b == 0){
                    long x = y/b*a;
                    System.out.println(x + " " + y);
                    return;
                }
            }
        }else{
            for(long x = A; x > 0; x--){
                if(x % a == 0){
                    long y = x/a*b;
                    System.out.println(x + " " + y);
                    return;
                }
            }
        }
        System.out.println(0 + " " + 0);
    }
    public static long gcd(long a, long b){
        while(b != 0){
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}