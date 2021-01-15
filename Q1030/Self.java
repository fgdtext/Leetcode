package Q1030;

/*
单元格的曼哈顿距离 
|r1 - r2| + |c1 - c2| 

首先 bfs 
*/


/*

错误： 通过画圈去遍历的方法是错误的。  对角线的增长速度和 xy方向是不同的。

输入 3 5 2 3  发现错误。    不能用类似蛇形遍历的方式去排序。


*/
class Self2 {
    int[][] ans;
    int index;
    int R, C;
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        ans = new int[R*C][2];
        this.R = R;
        this.C = C;
        int maxcir = Math.max(r0, c0);
        maxcir = Math.max(R-r0, maxcir);
        maxcir = Math.max(C-c0, maxcir);
        ans[index][0] = r0;
        ans[index][1] = c0;
        index++;
        System.out.println(maxcir + "maxcir");
        for(int i = 1; i <= maxcir; i++){
            System.out.println(i + "i");
            add4point(r0, c0, i,0); // xy轴点
            // 中间的点。
            for(int k = 1; k < i; k++){
                System.out.println(k + "k");
                add4point(r0, c0, i, k);
                add4point(r0, c0, i, -k);
            }
            add4point(r0, c0, i, i);// 4个顶点
                    System.out.println("888888888888888888888888888");
        }
        return ans; 
    }
    // 要有越界判断  要对k 分上下
    public void add4point(int r0, int c0, int i,int k){
        
        int[][] point4 = new int[4][2];
        point4[0][1] = c0-i;  // 列坐标
        point4[0][0] = r0+k;  // 行坐标
        point4[1][1] = c0-k;  // 列坐标
        point4[1][0] = r0-i;  // 行坐标
        point4[2][1] = c0+i;  // 列坐标
        point4[2][0] = r0-k;  // 行坐标
        point4[3][1] = c0+k;  // 列坐标
        point4[3][0] = r0+i;  // 行坐标
        for(int l = 0; l < 4; l++){
            System.out.println(point4[l][0] + ":::" + point4[l][1]);
            if(point4[l][0] >= 0 && point4[l][1] >= 0 && point4[l][0] < R && point4[l][1] < C){
                ans[index][0] = point4[l][0];
                ans[index][1] = point4[l][1];
                index++;
            }
        }
    }
}


/*
应该是 4方向 bfs . 每跨过一个格子， 曼哈顿 距离就+1.

哎 侮辱歧途。 真是浪费时间。  对于矩阵， bfs dfs， 一定不能忘啊， 不要只想着 绕圈，蛇形遍历啥的，代码又难搞。


桶排序也是可以的。  bfs 写起来更顺手。
*/

class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        boolean[][] vis = new boolean[R][C];
        int[][] ans = new int[R*C][2];
        int[][] direct = {{1,0},{0,1},{-1,0},{0,-1}};
        int top = -1;   // 出队  ==-1不能出队  应该出队的位置
        int tail = 0; // 队尾加入  应该加入的位置
        ans[tail][0] = r0;
        ans[tail][1] = c0;
        vis[r0][c0] = true;
        top = 0;
        tail++;
        while(top < tail){
            int x = ans[top][0];
            int y = ans[top][1];
            top++;  // 出队成功
            for(int i = 0; i < 4; i++){
                int xx = x + direct[i][0];
                int yy = y + direct[i][1];
                if(xx >= 0 && xx < R && yy >= 0 && yy < C && !vis[xx][yy]){  // 还没有访问
                    // 入队
                    ans[tail][0] = xx;
                    ans[tail][1] = yy;
                    tail++;  // 队尾加1
                    vis[xx][yy] = true; // 加标记
                }
            }
        }
        return ans;
    }
}