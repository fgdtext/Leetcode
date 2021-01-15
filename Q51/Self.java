package Q51;

import java.util.*;


/*
回溯法， 八皇后问题
*/


class Solution {
    int n;
    char[][] ans_char;
    List<List<String>> ans;
    boolean[][] vis;
    public List<List<String>> solveNQueens(int n) {
        if(n == 0) return null;
        this.n = n;
        ans_char = new char[n][n];
        vis = new boolean[3][n*2];
        for(int i = 0; i < n; i++)
            Arrays.fill(ans_char[i], '.');
        ans = new ArrayList<>();
        if(n == 1){
            List<String> s =  new ArrayList();
            s.add("Q");
            ans.add(s);
            return ans;
        }
        dfs(0);
        return ans;
    }
    public void dfs(int cur){
        if(cur == n){
            List<String> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                list.add(new String(ans_char[i]));
            }
            ans.add(list);
        }else for(int i = 0; i < n; i++){
            if(!vis[0][i] && !vis[1][cur+i] && !vis[2][cur-i+n]){
                ans_char[cur][i] = 'Q';   // 放在该位置， 并加上冲突标志
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = true;
                dfs(cur+1);
                ans_char[cur][i] = '.';   //  撤销该位置，并取消冲突标志
                vis[0][i] = vis[1][cur+i] = vis[2][cur-i+n] = false;
            }            
        }
    }
}