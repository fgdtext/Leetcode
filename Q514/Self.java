package Q514;


import java.util.*;


public class Self {
    
}
/*
ring : 使用循环数组

dp[i][j] : 标识当前表盘状态为i, 且读到 key[j]时需要的步骤。
HashMap<Integer,TreeSet> ： key : 字符  val : 在循环数组中的位置。， 该位置不会改变。 我们不移动表盘，而是移动指针。
ans = Max(dp[k][key.len])   k >= 0  && k < ring.len;,  也就是求二维数组的最后一列的最小值。

转移方程 ： dp[i][j] = min(   p[k][j-1] + gettoK(k)  );  k >= 0  && k < ring.len

首先纠正一个误区 ：  我们每次旋转不能只找距离 表针最近的左右两个，因为不能确定转到其他地方会不会影响后边的结果。也就是这里不能使用贪心，只算顺时针最近，和逆时针最近的两个转移点。
而是应该，所有和key[j]相同的都要遍历一遍，取最小。



*/

/*
Ans 的ij 定义和我的恰好相反。 所以，ij互换理解就好。


*/

class Ans {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);  
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);  // 
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}
