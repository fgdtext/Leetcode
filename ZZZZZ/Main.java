package ZZZZZ;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
// while((str = in.readLine())!=null)

/*

关键路径中的 最短完成任务的时间。
*/
public class Main{
    public static void main(String[] args)throws IOException {
         
        int a = gcd(100, 30);
        System.out.println(a);
    }
    static int gcd(int a,int b){
        while(b != 0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}

/*
case1:
10,3,7,6,7
0;1,3;0;2,3;2,4

case2:
30,20,20,60,50
0;1,3;1;1,2;3,4
*/