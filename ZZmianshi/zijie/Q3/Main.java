package ZZmianshi.zijie.Q3;

import java.util.*;
import java.io.*;
/*
给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：

区间中的最小数 * 区间所有数的和最后程序输出经过计算后的最大值即可，不需要输出具体的区间。
如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
[6] = 6 * 6 = 36;

[2] = 2 * 2 = 4;

[1] = 1 * 1 = 1;

[6,2] = 2 * 8 = 16;

[2,1] = 1 * 3 = 3;

[6, 2, 1] = 1 * 9 = 9;

从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。


该题：类似于 力扣中 求数组最大矩形的题目。

使用单调递增栈，栈底是最小值，当有一个较小值使得栈顶出栈时，则以栈顶为高的最大矩形就确定了。

这题： 求区间最小值*区间和的乘积的最大值。 = min * sum 当出现一个值使得栈顶出栈时，那么以栈顶为最小值的
区间就可以确定了。就是次顶的右侧开始，到新值的左侧。

*/
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        if(s == null || s.equals("")) return;
        int n = Integer.parseInt(s);
        String[] ss = in.readLine().split(" ");
        int[] a = new int[n];

        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(ss[i]);
        }
        int[] sum = new int[n];
        sum[0] = a[0];
        for(int i = 1; i < n; i++){
            sum[i] = sum[i-1] + a[i];
        }
        int ans = 0;
        int right = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        while(right < n){
            while(!stack.isEmpty() && a[stack.getLast()] >= a[right]){
                int h = stack.removeLast();
                int k = 0;
                if(stack.isEmpty()) k = sum[right-1];
                else k = sum[right - 1] - sum[stack.getLast()];
                ans = Math.max(ans,k*a[h]);
            }
            stack.addLast(right++);
        }
        System.out.println(ans);
    }
}