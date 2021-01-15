package Q1024;

import java.util.Arrays;

/*

贪心 

o(nlgn)  主要是排序耗时
*/
class Solution {
    public int videoStitching(int[][] clips, int T) {

        Arrays.sort(clips, (a,b)->{
            return a[0]-b[0];
        });
        if(clips[0][0] != 0) return -1;
        int ans = 1;
        int right = clips[0][1];
        int ind = 0;
        for(int i = 1; i < clips.length; i++){
            if(clips[i][0] != 0) break;
            if(right < clips[i][1]){
                right = clips[i][1];
            }
            ind = i;
        }
        // right = T  则 成功了。
        while(right < T){
            boolean flag = false;
            int oldright = right;

            for(int i = ind + 1; i < clips.length; i++){
                if(clips[i][0] > oldright)  break;
                if(clips[i][1] > right){
                    right = clips[i][1];
                    flag = true;
                }
                ind = i;
            }
            // 区间出现间断， 不可行。
            if(!flag){
                return -1;
            }
            ans++;
        }
        return ans;
    }
}

/*class
0：：：：1
0：：：：2
0：：：：4
0：：：：3  ind  首先  在0开头的找右边界最大的， 4 此时 ind 已经扫描到了 3.  
接下来从 ind+1 开始找 所有 左边界 小于等于 4的区间。 找到右边界最大  此时 ind走到下图位置， right 变为 7.

1：：：：3
1：：：：4
2：：：：5
2：：：：6
3：：：：4
4：：：：7
4：：：：5 ind 

然后从 ind +1开始找， 所有左边界 小于等于 right = 7 的 区间， 找到 最大 right = 9. 此时 right = T. 到终点。
5：：：：6
5：：：：7
6：：：：8
6：：：：7
6：：：：9

3 begin 4
2？？？？5
2？？？？6
4？？？？7

6？？？？8
6？？？？9


可以看到， ind 每换一轮， 则找到一个可行区间。



0：：：：1
0：：：：2
0：：：：4
0：：：：3

1：：：：3
1：：：：4
2：：：：5
2：：：：6
3：：：：4
4：：：：7
4：：：：5

5：：：：6
5：：：：7
6：：：：8
6：：：：7
6：：：：9

3 begin 4
2？？？？5
2？？？？6
4？？？？7
6？？？？8
6？？？？9

6++++9
*/

/*
注意到对于所有左端点相同的子区间，其右端点越远越有利。
且最佳方案中不可能出现两个左端点相同的子区间。
于是我们预处理所有的子区间，对于每一个位置 i，
我们记录以其为左端点的子区间中最远的右端点，记为maxn[i]。


maxn[i]  的思想 类似 与 数组的  前缀和，   都属于  当先已经有的  隐藏 信息。 先得到  maxn 或者 sum 数组问题就会简化

*/



class Ans {
    public int videoStitching(int[][] clips, int T) {
        int[] maxn = new int[T];
        int last = 0, ret = 0, pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxn[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }
}



/*\

dp 比较容易想到的方法是动态规划，我们令dp[i] 表示将区间[0,i) 覆盖所需的最少子区间的数量

类似于 01背包的 定义方法

总 问题  ： dp[T] = ans   表示  区间[0,T)  
子问题  ：  令dp[i] 表示将区间[0,i)   dp[i] 的前驱状态 dp[x] x 是位置的。应该是dp[0~i-1]的最小值。
前驱状态由  dp[clip[0]]得到。 因为  clip[0] < i && i <= clip[1] 

*/

class Ans2 {
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                //  该区间可以覆盖 到 i ，或者覆盖范围超出 i 则可得到 dp[i]
                // dp[i] 是从 dp[clip[0]] 转移来的。 因为可能有很多 dp[clip[0]]都可以转移到 dp[i]
                // 所以 这里 另 dp[i] 保持最小值。 
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }
}
