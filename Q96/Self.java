package Q96;

public class Self {
    
}

/*
123  构成的数量 =  1为根的数量  + 2为根的数量  +  3 为根的数量。
而且 由于  1，3 对称，1为根的数量 = 3 为 根的 数量。

12345   数量 = 1，2，3，4，5为根的和。   =  1，2，3 ，1，2 为根的和。
 经过分析 现在求 1，2，3为根的和即可。 

 先求以1为根的和 ： 那么 由 1-5 . 另外 4 个数在 右侧。 那么以 1 为 根的 数量 = 2~5可以构成的二叉搜索树的数量。

 以 2 为 根的数量：  左侧 只有 1.  那么 以2 为根 的 数量 = 3~5 可以构成的二叉搜索树的数量。

 以 3 为根的 数量 ：  左侧和右侧都有相同数量的元素。 那么 以3 为根的树的数量 = 左侧数量 * 右侧数量。

 注意 ： 上边的 3~5 2~5 不是具体的数， 而是指 3~5有3个数， 2~5 有4个数。

 递归边界 ： 求 数量为 0，1，2，3 个元素的二叉搜索树的数量。即为边界。
0 - 1
1 - 1
2 - 2
3 - 5


要 加上记忆化
dp[i] = j; i个数可以构成的 二叉搜索树的 数量

*/

/*
6666   我 又 写出来了。  哈哈哈哈。

卡塔兰数 

*/

class Solution {
    int[] dp;
    public int numTrees(int n) {
        dp = new int[n+1];
        if(n == 0) return 1;
        if(n == 1) return 1;
        if(n == 2) return 2;
        dp[0] = 1; dp[1] = 1; dp[2] = 2; dp[3] = 5;
        return dfs(n);
    }
    // 求
    public int dfs(int i){
        if(dp[i] != 0) return dp[i];
        int sum = 0;
        // 根的划分。
        for(int j = 1; j <= i/2; j++){
            sum = sum + dfs(j-1) * dfs(i - j);
        }
        sum *= 2;
        if(i % 2 != 0){
            int temp = dfs(i/2);
            sum = sum + temp * temp;
        }
        return dp[i] = sum;
    }
}