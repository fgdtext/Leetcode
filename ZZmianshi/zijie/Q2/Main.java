package ZZmianshi.zijie.Q2;

import java.util.*;
import java.io.*;


/*

万万没想到之抓捕孔连顺

在数组中选择3个点，使得 三个点任意两个点的距离 <= d

问有多少种安排的方法
若数组 1234  d= 3
则有 123,124,134,234  共 4种. 就是 C(4,3) 4-1=3没有超过最大距离

*/
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] ln1 = bf.readLine().split(" ");
        String[] ln2 = bf.readLine().split(" ");
        int n = Integer.parseInt(ln1[0]);
        int d = Integer.parseInt(ln1[1]);
        int[] ind = new int[n];
        for(int i = 0; i < n; i++)
            ind[i] = Integer.parseInt(ln2[i]);
        if(n < 3){
            System.out.println(0);
            return;
        }
        long ans = 0;
        int left = 0, right = 2;
        for(; left < n - 2; left++){
            while(right < n && ind[right] - ind[left] <= d){
                right++;
            }
            long num = right - left - 1;
            if(num >= 2){
                ans += num*(num-1)/2;
            }
        }
        System.out.println(ans % 99997867);
    }
}