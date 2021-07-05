package ZZmianshi.xiaomi.Q2;

import java.util.*;
/*

现在有一个城市销售经理，需要从公司出发，去拜访市内的商家，已知他的位置以及商家的位置，
但是由于城市道路交通的原因，他只能在左右中选择一个方向，在上下中选择一个方向，
现在问他有多少种方案到达商家地址。

给定一个地图map及它的长宽n和m，其中1代表经理位置，2代表商家位置，-1代表不能经过的地区，
0代表可以经过的地区，请返回方案数，保证一定存在合法路径。保证矩阵的长宽都小于等于10。


对于给定的起始位置和终点，两个点的相对位置决定，经理只能朝着更近的方向走。
若 x0 = 0, y0 = 0   x1= 10, y1 = 10;
那么只能朝下走，或者朝右走。  终点相对于自己在右下角的位置。
*/
public class Main {

    public int countPath(int[][] map, int n, int m) {
        // write code here
        // 起始点，和终点。
        int x0 = 0, x1 = 0,y0 = 0,y1 = 0;
        int k = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 1){
                    k++; x0 = i; y0 = j;
                }else if(map[i][j] == 2){
                    k++; x1 = i; y1 = j;
                }
                if(k == 2)break;
            }
            if(k == 2) break;
        }
        int ans = 0;
        // 若同行同列，只能沿着该行该列走。
        if(x0 == x1 || y0 == y1) return 1;  
        // 根据 x方向的相对位置，判断是向上走，还是向下走。
        int dirx = 0, diry = 0;
        if(x0 < x1) dirx = 1;
        else dirx = -1;
        if(y0 < y1) diry = 1;
        else diry = -1;
        
        // 和x0,y0同行同列的只有一种走法
        int[][] dp = new int[n][m];
        for(int i = 0; i < m; i++){
            dp[x0][i] = 1;
        }
        for(int i = 0; i < n; i++){
            dp[i][y0] = 1;
        }
        
        // 注意中间的判别方式，根据dirx的正负，判断相对大小。 x每次走dirx个
        for(int i = x0 + dirx; dirx == 1 ? i <= x1 : i >= x1; i += dirx){
           
            for(int j = y0 + diry; diry == 1 ? j <= y1 : j >= y1; j += diry){
                int ii = i - dirx;
                int jj = j - diry;
                // 假设终点在右下，那么上一个走过的位置，只能是上一个，或者左一个。
                if( ii >= 0 && ii < n && jj >= 0 && jj < m){
                    dp[i][j] = dp[ii][j] + dp[i][jj];
                }
            }
        }
        return dp[x1][y1];
    }
}