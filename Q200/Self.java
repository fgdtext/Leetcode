package Q200;

import java.util.LinkedList;

public class Self {
    
}
/*
全图 dfs 标记 ： 涂色 数块。



*/
class Solution {
    int[][] direct = {{1,0},{-1,0},{0,1},{0,-1}};
    public int numIslands(char[][] grid) {
        boolean[][] vis = new boolean[grid.length][grid[0].length];
        int ans = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(!vis[i][j] && grid[i][j] == '1'){
                    dfs(i, j, vis, grid);
                    ans++;
                }
            }
        }
        return ans;
    }
    public void dfs(int i, int j, boolean[][] vis, char[][] grid){
        vis[i][j] = true;
        for(int k = 0; k < 4; k++){
            int ii = i + direct[k][0];
            int jj = j + direct[k][1];
            if(ii >= 0 && jj >= 0 && ii < vis.length && jj < vis[0].length && !vis[ii][jj] && grid[ii][jj] == '1'){
                dfs(ii, jj, vis, grid);
            }
        }
    }
}