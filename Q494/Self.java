package Q494;



public class Self {
    // public static void main(String[] args) {
    //     Ans so = new Ans();
    //     int[] a = {9,7,0,3,9,8,6,5,7,6};
    //     so.findTargetSumWays(a, 2);
    // }
}
/*
下边是错误的示例，没有什么用。 时间复杂度 > 2^n
二进制子集生成。太慢了，行不通
超时 ：
*/
class Self2 {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int ans = 0;
        for(int i = 0; i < (1<<n); i++){
            int tempsum = 0;
            for(int j = 0; j < n; j++){
                if((i & (1 << j)) == 0){
                    tempsum -= nums[j];
                }else{
                    tempsum += nums[j];
                }
            }
            if(tempsum == S) ans++;
        }
        return ans;
    }
}
/*class
既然超时： 减枝又没有什么好剪掉的。那就dp了

设计dp  ,  题目的本质是对每一个数做出一个选择，非正即负的，这点类似 0-1背包问题。所以，可以使用0-1背包的定义来解决。
只是这里不是选或则不选，而是分为正负

dp[i][j] : 前i个数能凑够j，的方案数。

对于i,可以选，也可以不选。

dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][abs(j-nums[i])]  

解释： 第i个数为负，前i个数凑够j的方案数 = 前i-1个数凑够 j+nums[i]的方案数。  因为第i个数为负，所以j从i-1到i是缩小的。
        同理可得为正的情况。

并且 j-nums[i] < 0的情况和Math.abs(j-nums[i])的方案数是一样的
这点很好想，只要符号全部求反即可。所以方案数必然相同。
*/
class Self3 {
    public int findTargetSumWays(int[] nums, int S) {
        int ss = Math.abs(S);
        if(ss > 1000) return 0;
        int[][] dp = new int[nums.length][1001];
        // base_case
        for(int j = 0; j <= 1000; j++){
            if(nums[0] == 0 && j == 0){
                dp[0][j] = 2; break;
            }
            dp[0][Math.abs(nums[0])] = 1;
        }
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j <= 1000; j++){
                int a = Math.abs(j-nums[i]);
                int b = j+nums[i] > 1000 ? 0 : dp[i-1][j+nums[i]];
                dp[i][j] = b + dp[i-1][a];
            }
        }
        // 打印
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j <= 10; j++){
                System.out.print(dp[i][j]+"  ");
            }
            System.out.println("*********************");
        }
        return dp[nums.length-1][ss];
    }
}
/*class
优化第一行的 赋值

只有一个物品时，必然只能是和为j, j唯一，所以第一行只赋值一个位置即可。

*/
class Self4 {
    public int findTargetSumWays(int[] nums, int S) {
        int ss = Math.abs(S);
        if(ss > 1000) return 0;
        int[][] dp = new int[nums.length][1001];

        if(nums[0] == 0)  dp[0][0] = 2;
        else dp[0][Math.abs(nums[0])] = 1;
        
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j <= 1000; j++){
                int a = Math.abs(j-nums[i]);
                int b = j+nums[i] > 1000 ? 0 : dp[i-1][j+nums[i]];
                dp[i][j] = b + dp[i-1][a];
            }
        }
        return dp[nums.length-1][ss];
    }
}
   /*class
        行遍历再优化，  每行dp 最远算到 sum前缀和的位置。

        另外空间优化，就不写了， 两行交替更换即可。
   */
  class Self5 {
    public int findTargetSumWays(int[] nums, int S) {
           int ss = Math.abs(S);
           if(ss > 1000) return 0;
           int[][] dp = new int[nums.length][1001];

           if(nums[0] == 0)  dp[0][0] = 2;  // dp[0][0]： 如果第一个物品是0，则凑成j=0的可能有两种，正负零。
           else dp[0][nums[0]] = 1;  //其余都只可能有一种方案。
           int sum = nums[0];
           for(int i = 1; i < nums.length; i++){
                sum += nums[i];
               for(int j = 0; j <= sum; j++){
                   int a = dp[i-1][Math.abs(j-nums[i])];
                   int b = j+nums[i] > 1000 ? 0 : dp[i-1][j+nums[i]];
                   dp[i][j] = a + b;
               }
           }
           return dp[nums.length-1][ss];
       }
   }



class Ans5 {

}



   /*class

            答案的空间优化方案。
            每次都拿next更新。dp做依赖
            

   */




/*class
数学优化

原问题等同于： 找到nums一个正子集和一个负子集，使得总和等于target

我们假设P是正子集，N是负子集 例如： 假设nums = [1, 2, 3, 4, 5]，target = 3，一个可能的解决方案是+1-2+3-4+5 = 3 这里正子集P = [1, 3, 5]和负子集N = [2, 4]

那么让我们看看如何将其转换为子集求和问题：

                  sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)
因此，原来的问题已转化为一个求子集的和问题： 找到nums的一个子集 P，使得sum(P) = (target + sum(nums)) / 2

*/