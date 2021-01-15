package Q486;

import java.util.Arrays;

/*

回溯法 递归。 时间太大

该回溯法， 每次递归， a 选择一个，b 选择一个。 若a的选择可以使得a一定赢，则a赢。
例如： a 选择左端， 此时b有两端可选， 若不管b 选哪一端，都是a赢，则判定a 赢

*/

class Solution {
    int[] nums;
    int[][] dp; // 记录
    public boolean PredictTheWinner(int[] nums) {
        this.nums = nums;
        this.dp = new int[nums.length][nums.length];     
        return recur(0, nums.length-1,0,0);
    }
    public boolean recur(int i, int j,int a, int b){
        // 奇数个元素 的 情况
        if(i == j){ // 只剩下最后一个 给 a
            a += nums[i];
            if(a >= b) return true;
            return false;
        }else if(i+1 == j){  //剩下两个的情况。
            int maxend = Math.max(nums[i], nums[j]);
            int minend = Math.min(nums[i], nums[j]);
            a += maxend;
            b += minend;
            if(a >= b) return true;
            return false;
        }
        // a 拿 i, b 拿 i+1
        boolean q1 =  recur(i+2, j, a+nums[i], b+nums[i+1]);
        // a 拿 i, b 拿 j
        boolean q2 =  recur(i+1, j-1, a+nums[i], b+nums[j]);
        if(q1 && q2) return true;   //   剪枝
        // a 拿 j, b 拿 j-1
        boolean q3 =  recur(i, j-2, a+nums[j], b+nums[j-1]);
        // a 拿 j, b 拿 i
        boolean q4 =  recur(i+1, j-1, a+nums[j], b+nums[i]);
        return  q1 && q2 || q3 && q4;
    }
   
}

/*

每次递归，由一个人来选。交替选择。

使用相对得分的概念。

定义 dp[i][j] 表示作为先手，在区间 nums[i..j] 里进行选择可以获得的相对分数(净胜分)。
dp[i][j] : 即i~j 中， 所有当前人选择的所有分 - 另一个人选择的所有分。 

即在数组状态为 i~j 的时候开始新的比赛，先手可以领先多少分。该问题即为  子问题。

可以通过递归(自上向下去写代码，计算顺序一定是自下向上) 在 0~ n-1开始，a为先手

若 a 选左端点，则 可以分两种可能，去计算当前memo[i][j]. 因为 1~n-1    0~ n-2 b的净胜分不同。 
可以计算出不同的 0~ n-1的 净胜分。 返回值一定是对手的净胜分。

100 % 算法



*/ 
class Solution2 {
    int[] nums;
    int[][] memo;
    public boolean PredictTheWinner(int[] nums) {
        this.nums = nums;
        memo = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++)
            Arrays.fill(memo[i], Integer.MIN_VALUE);  
        return dfs(0, nums.length-1) >= 0;
    }
    // 
    public int dfs(int i, int j){
        if(i > j) return 0;
        if(memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
        int left = nums[i] - dfs(i+1, j);
        int right = nums[j] - dfs(i, j-1);
        // 由于当前选择有两种，影响导致下一级对手的选择也有两种，就有两种净胜分。
        return memo[i][j] = Math.max(left, right);
    }
   
}
/*

递推 ; dp 


*/
 class Solution3 {

    // 状态转移方程：dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])

    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        
        // dp[i][j]：作为先手，在区间 nums[i..j] 里进行选择可以获得的相对分数
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }
}