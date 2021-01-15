package Q221;

public class Self {
    
}
/*
i,j 是右下 点 ， k 是 正方形边长。
dp[i][j][k] ： 取决于 dp[i][j][k-1] 和 dp[i-1][j-1][k-1] 和 matrix[i-k+1][j] 和 matrix[i][j-k+1]
四个条件都满足则，[i][j][k] 构成正方形。

时间21 ms  5%  空间 5%

*/
class Slef1 {
    public int maximalSquare(char[][] matrix) {
        int hang = matrix.length;
        if(hang == 0) return 0;
        int lie = matrix[0].length;
        if(hang == 1 || lie == 1){
            for(int i = 0; i < hang; i++){
                for(int j = 0; j < lie; j++){
                    if(matrix[i][j] == '1') return 1;
                }
            }
            return 0;
        }
        int ma = Math.max(hang, lie);
        boolean[][][] dp = new boolean[hang][lie][ma+1];
        int ans = 0;
        for(int i = 0; i < hang; i++){
            for(int j = 0; j < lie; j++){
                int ml = Math.min(i+1, j+1);
                for(int k = 1; k <= ml; k++){
                    if(k == 1 && matrix[i][j] == '1'){  // base case 
                        dp[i][j][k] = true;
                        ans = Math.max(ans, 1);
                        continue;
                    }// 边界开始的
                    if((i == 0 || j == 0) && matrix[i][j] == '1'){
                        dp[i][j][k] = true;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    if(dp[i][j][k-1] && dp[i-1][j-1][k-1] && matrix[i-k+1][j] == '1' && matrix[i][j-k+1] == '1'){
                        dp[i][j][k] = true;
                        ans = Math.max(ans, k*k);
                        continue;
                    }
                }
            }
        }
        return ans;
    }
}

/*
优化， 这种定义空间还可以优化，但是时间上，似乎不好优化了。 所以要换状态定义方式。
时间 ： 11 ms  5.79%
if(!dp[i][j][k]) break;

*/
class Slef2 {
    public int maximalSquare(char[][] matrix) {
        int hang = matrix.length;
        if(hang == 0) return 0;
        int lie = matrix[0].length;
        if(hang == 1 || lie == 1){
            for(int i = 0; i < hang; i++){
                for(int j = 0; j < lie; j++){
                    if(matrix[i][j] == '1') return 1;
                }
            }
            return 0;
        }
        int ma = Math.max(hang, lie);
        boolean[][][] dp = new boolean[hang][lie][ma+1];
        int ans = 0;
        for(int i = 0; i < hang; i++){
            for(int j = 0; j < lie; j++){
                int ml = Math.min(i+1, j+1);
                for(int k = 1; k <= ml; k++){
                    if(k == 1 && matrix[i][j] == '1'){  // base case 
                        dp[i][j][k] = true;
                        ans = Math.max(ans, 1);
                        continue;
                    }// 边界开始的
                    if((i == 0 || j == 0) && matrix[i][j] == '1'){
                        dp[i][j][k] = true;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    if(dp[i][j][k-1] && dp[i-1][j-1][k-1] && matrix[i-k+1][j] == '1' && matrix[i][j-k+1] == '1'){
                        dp[i][j][k] = true;
                        ans = Math.max(ans, k*k);
                        continue;
                    }
                    if(!dp[i][j][k]) break;
                }
            }
        }
        return ans;
    }
}


/*class
上边的三维定义，太麻烦了。

换一种定义方法。
dp[i][j] 为以 i,j为右下点 的最大边长。

dp[i][j] ： 取决于 dp[i-1][j-1] dp[i-1][j] dp[i][j-1].三个正方形向上方的最低的线，和左侧三条线最右的线。
两条线距离ij 最短的，就是 dp[i][j] 的最大边长。

时间还是很差 9ms 8.92%  ,空间好很多
*/


class Self3 {
    public int maximalSquare(char[][] matrix) {
        int hang = matrix.length;
        if(hang == 0) return 0;
        int lie = matrix[0].length;

        int ans = 0;
        int[][] dp = new int[hang][lie];
        for(int i = 0; i < hang; i++){
            for(int j = 0; j < lie; j++){
                if((i == 0 || j == 0) && matrix[i][j] == '1'){
                    dp[i][j] = 1;
                    ans = Math.max(ans, 1);
                    continue;
                }
                if(matrix[i][j] == '1'){
                    if(dp[i-1][j]  == 0 || dp[i][j-1] == 0 || dp[i-1][j-1] == 0){
                        dp[i][j] = 1;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    /*
                    dp[i-1][j-1] + 1
                    dp[i-1][j]   + 1
                    dp[i][j-1]+1
                    dp[i-1][j-1] + 1
                    dp[i][j-1] + 1
                    dp[i-1][j]+1
                    */
                    int upmin = Math.min(dp[i-1][j-1], dp[i-1][j]) + 1;
                    upmin = Math.min(upmin, dp[i][j-1]+1);
                    int leftmin = Math.min(dp[i-1][j-1], dp[i][j-1]) + 1;
                    leftmin = Math.min(leftmin, dp[i-1][j]+1);
                    dp[i][j] = Math.min(upmin, leftmin);
                    ans = Math.max(ans, dp[i][j]*dp[i][j]);
                }
            }
        }
        return ans;
    }
}
/*
优化空间

只使用一维 数组。
pre 保存前一个依赖项，避免被覆盖。

*/
class Self4 {
    public int maximalSquare(char[][] matrix) {
        int hang = matrix.length;
        if(hang == 0) return 0;
        int lie = matrix[0].length;

        int ans = 0;
        int[] dp = new int[lie];
        for(int i = 0; i < hang; i++){
            int pre = dp[0];
            for(int j = 0; j < lie; j++){
                if((i == 0 || j == 0) && matrix[i][j] == '1'){
                    pre = dp[j];
                    dp[j] = 1;
                    ans = Math.max(ans, 1);
                    continue;
                }
                if(matrix[i][j] == '1'){
                    if(dp[j]  == 0 || dp[j-1] == 0 || pre == 0){
                        pre = dp[j];
                        dp[j] = 1;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    int upmin = Math.min(pre, dp[j]) + 1;
                    upmin = Math.min(upmin, dp[j-1]+1);
                    int leftmin = Math.min(pre, dp[j-1]) + 1;
                    leftmin = Math.min(leftmin, dp[j]+1);
                    pre = dp[j];
                    dp[j] = Math.min(upmin, leftmin);
                    ans = Math.max(ans, dp[j]*dp[j]);
                }else{
                    pre = dp[j];
                    dp[j] = 0;
                }
            }
        }
        return ans;
    }
}
/*class

取最小值的优化
             
                    dp[i-1][j-1] + 1
                    dp[i-1][j]   + 1
                    dp[i][j-1]+1
                    dp[i-1][j-1] + 1
                    dp[i][j-1] + 1
                    dp[i-1][j]+1
               
其实就是这6个值的最小值， 分别从上看，从左看，取边长最小。
但是6个数是重复的。
就是求
                    dp[i-1][j-1] + 1
                    dp[i-1][j]   + 1
                    dp[i][j-1]+1
                    最小值

另外， 不要每次都去求面积，没意义， 保存最大边长即可。
*/
/*

最优方法


*/
class Solution {
    public int maximalSquare(char[][] matrix) {
        int hang = matrix.length;
        if(hang == 0) return 0;
        int lie = matrix[0].length;
        int ans = 0;
        int[] dp = new int[lie];
        for(int i = 0; i < hang; i++){
            int pre = dp[0];
            for(int j = 0; j < lie; j++){
                if((i == 0 || j == 0) && matrix[i][j] == '1'){
                    pre = dp[j];
                    dp[j] = 1;
                    ans = Math.max(ans, 1);
                    continue;
                }
                if(matrix[i][j] == '1'){
                    if(dp[j]  == 0 || dp[j-1] == 0 || pre == 0){
                        pre = dp[j];
                        dp[j] = 1;
                        ans = Math.max(ans, 1);
                        continue;
                    }
                    int temppre = pre;  // 避免修改pre 丢失。
                    pre = dp[j];
                    dp[j] = Math.min(Math.min(dp[j-1], temppre), dp[j]) + 1;
                    ans = Math.max(ans, dp[j]);
                }else{
                    pre = dp[j];
                    dp[j] = 0;
                }
            }
        }
        return ans*ans;
    }
}
