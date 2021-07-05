package ZZmianshi.zijie.Q5;

import java.util.*;
import java.io.*;


/*
https://www.nowcoder.com/questionTerminal/d25162386a3140cbbe6dc071e1eb6ed6?answerType=1&f=discussion


为了不断优化推荐效果，今日头条每天要存储和处理海量数据。假设有这样一种场景：
我们对用户按照它们的注册时间先后来标号，对于一类文章，每个用户都有不同的喜好值，
我们会想知道某一段时间内注册的用户（标号相连的一批用户）中，有多少用户对这类文章喜好值为k。
因为一些特殊的原因，不会出现一个查询的用户区间完全覆盖另一个查询的用户区间(不存在L1<=L2<=R2<=R1)。

输入： 第1行为n代表用户的个数 第2行为n个整数，
第i个代表用户标号为i的用户对某类文章的喜好度 第3行为一个正整数q代表查询的组数 
 第4行到第（3+q）行，每行包含3个整数l,r,k代表一组查询，
 即标号为l<=i<=r的用户中对这类文章喜好值为k的用户的个数。 
 数据范围n <= 300000,q<=300000 k是整型


5 //n
1 2 3 3 5 // n各用户的喜爱度
3       // n个查询
1 2 1   // l,r,k 在lr内查找喜爱度为k 的个数。
2 4 5
3 5 3

分析： 假设处理查询 3 5 3 那么我们就要在3到5之间遍历一遍k=3的个数。处理p个查询，时间就是o(p*n)

时间复杂度太高了。

由查询可知，当我们查询k=3时，那么其他值的喜爱度是多余的数据。
我们可以通过map把不同喜爱度的值分开存储。
若 k = 3 那么，map(3) = arr[2,3] 就是说下标为2和3时，k=3

*/
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] fav = new int[n];
        HashMap<Integer,ArrayList<Integer> > mp = new HashMap<>();
        String[] s = in.readLine().split(" ");
        for(int i = 0; i < n; i++){
            fav[i] = Integer.parseInt(s[i]);
            if(!mp.containsKey(fav[i])){
                mp.put(fav[i],new ArrayList<>());
            }
            mp.get(fav[i]).add(i);
        }
        int q = Integer.parseInt(in.readLine());
        for(int i = 0; i < q; i++){
            String[] ss = in.readLine().split(" ");
            int l = Integer.parseInt(ss[0])-1;
            int r = Integer.parseInt(ss[1])-1;
            int k = Integer.parseInt(ss[2]);
            if(!mp.containsKey(k)){
                System.out.println(0);
                continue;
            }
            int ld = erfenl(mp.get(k), l);
            if(ld >= mp.get(k).size()){
                System.out.println(0);
                continue;
            }
            if(mp.get(k).get(ld) > r) {
                System.out.println(0);
                continue;
            }
            int rd = erfenr(mp.get(k), r);
            if(rd < 0){
                System.out.println(0);
                continue;
            }
            System.out.println(rd-ld+1);
        }
    }
    // 数组不重复，有序，找tar
    public static int erfenl(ArrayList<Integer> arr, int tar){
        int l = 0, r = arr.size();
        while(l < r){
            int mid = (r-l)/2 + l;
            if(arr.get(mid) == tar) return mid;
            else if(arr.get(mid) > tar){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
    public static int erfenr(ArrayList<Integer> arr, int tar){
        int l = -1, r = arr.size()-1;
        while(l < r){
            int mid = (r-l + 1)/2 + l;
            if(arr.get(mid) == tar) return mid;
            else if(arr.get(mid) > tar){
                r = mid - 1;
            }else {
                l = mid;
            }
        }
        return l;
    }
}