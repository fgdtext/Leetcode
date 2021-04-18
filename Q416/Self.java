package Q416;

import java.util.Arrays;


        // 时间估算： 如果使用子集生成那么就是  o(2^n)， 而 n = 200. 这样无法接受。
        // 多半是同台规划。 
        // 贪心策略 ： 
        // 二分策略：
        //  可以先排序， o(nlgn)
        // 总和： 每个 就是 总 和 的 一半 ， 时间 是 o(n)
        // 再求前缀和。？？？   合并上一步
        // 问题变为 ： 寻找子集 构成  sum/2.   贪心，从大往小找？？回溯法？ 


public class Self {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,5,11,5};
        boolean a =  solution.canPartition(nums);
        System.out.println("result::" + a);
    }
}

/*
卧槽， 我真的 太 66666了， 我居然写出来了。 

该 模板和 Q55 相同。    定义状态：dp(i,tempsum) 之前的数是否能拼凑为 tempsum. 

每一层 tempsum 都维持不变，或者 变小。


// 以上定义 其实 与 01 背包 状态定义非常相似

01背包定义 ： f[i][j]=max(f[i−1][j],f[i−1][j−w[i]]+v[i])   
即f[i][j]表示前i件物品恰放入一个容量为j的背包可以获得的最大价值

这里的   j 就类似 该题目的 tempsum. 即剩余应拼凑量。 
前i件物品， 在该题 就是说 ，i 之前的所有数是否能拼凑出剩余应拼凑量tempsum(j)

01背包的  前i 个物品，是还没有选择放不放的物品， 其放入容量为 j 的包中。 
如果 i 放， 则转化为 ： 前 i-1个物品， 放入 j-vi的背包可以获得的最大值。
如果不放， 则转化为  ：  前 i-1个物品，放入 j 的背包可以获得的最大值。


本质是一个  背包问题。 也可以通过填表来实现，但是我不会，hhhhhhh
*/

/*  DP 

1. 填表法  dp[i][j] for for 
2. 记忆化递归。dp[i] . 

*/
// 记忆化递归。
class Slef {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        //  数组元素必然都是小于   sum / 2 的。
        // 从 后 往前 2^200  回溯法，还是无法接受。
        int[] dp = new int[nums.length];
        return dp(nums.length - 1, sum / 2, dp, nums) > 0;
    }
    //  定义 dp[i] 
    public int dp(int i,int tempsum, int[] dp,int[] nums){
        if(dp[i] != 0) return dp[i]; //  记忆化 直接返回
        // 递归边界 :  即 已经到达 数组  的 边界。 如果需求tempsum已经为 0 说明拼凑成功
        // 或者 tempsum == nums[0] 则 
        if(i == 0){
            if(tempsum == 0 || tempsum == nums[0]) return 1;
            return -1;
        }
        // 即使没有到达数组边界。 只要 nums[i] == tempsum 成立，则拼凑成功。
        if(nums[i] == tempsum){
            dp[i] = 1;
            return 1;
        }
        // bfs: i之前的每一个数。 从后往前 搞。符合贪心决策， 更容易提前找到。这里的贪心不是完美的。
        // 但是可以加速。
        for(int j = i-1 ; j >= 0; j--){   //   每种情况 进行递归。
            // 如果nums[j] == tempsum - nums[i] 则， 拼凑成功
            if(nums[j] == tempsum - nums[i]){
                dp[i] = 1;
                return 1;
            }
            // nums[j] 仍然不够， 继续 向前递归。
            if(nums[j] < tempsum - nums[i]) {
                boolean flag = dp(j, tempsum - nums[i], dp, nums) == 1 || dp(j, tempsum, dp, nums) == 1;
                if(flag){
                    dp[i] = 1;
                    return 1;
                }
            }
        }
        dp[i] = -1;
        return -1;
    }
}

/*
尝试 不使用 记忆化，看一下效率

判定 超时 。不适用 记忆化，就是不行。

可以这么来考虑， 由dp(i,tempsum)来考虑，来自右侧的选择有多种， 都可能导致需要在i 处拼凑 tempsum。
那么就 引出了子问题，右侧的多种选择，在 (i,tempsum)的表现是一致的。只要从i向左能拼凑出tempsum就成功。

，所以可以看出来， 上边的dp定义是成立的。 但是这里不好用填表法，这种定义 tempsum太大。定义为dp[i][tempsum]不好。
所以使用递归来记忆 tempsum。
*/

class Solution3 {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        //  数组元素必然都是小于   sum / 2 的。
        // 从 后 往前 2^200  回溯法，还是无法接受。
        return dp(nums.length - 1, sum / 2, nums) > 0;
    }
    //  定义 dp[i] 
    public int dp(int i,int tempsum, int[] nums){
        if(i == 0){
            if(tempsum == 0 || tempsum == nums[0]) return 1;
            return -1;
        }
        if(nums[i] == tempsum){
            return 1;
        }
        for(int j = i-1 ; j >= 0; j--){   //   每种情况 进行递归。
            if(nums[j] == tempsum - nums[i]){
                return 1;
            }
            if(nums[j] < tempsum - nums[i]) {
                boolean flag = dp(j, tempsum - nums[i], nums) == 1 || dp(j, tempsum, nums) == 1;
                if(flag){
                    return 1;
                }
            }
        }
        return -1;
    }
}


class Solution {
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target+1]; // 使得背包恰好装满
        dp[0] = true;
        for(int i = 1; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
                dp[j] = dp[j] | dp[j-nums[i]];
            }
        }
        return  dp[target];
    }
}