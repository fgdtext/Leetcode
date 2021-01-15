package Q547;

import java.util.HashMap;
import java.util.HashSet;



/*
合并并查集，最后并查集的个数就是连通域的个数。

*/
class Self2 {
    // 压缩路径
    public int find(int x){
        return 0;
    }
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            set.add(i);  // 初始时，每个结点都是自身的root,则共有 len 个并查集。
            map.put(i, i); // 自身是自身的父节点。
        }

        return 2;
    }
}



/*
dfs 找连通域的个数


*/
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        boolean[] vis = new boolean[len];
        int ans = 0;
        for(int i = 0; i < len; i++){
            if(!vis[i]){
                dfs(i,vis,isConnected);
                ans++;
            }
        }
        return ans;
    }
    public void dfs(int x,boolean[] vis,int[][] isConnected){
        vis[x] = true;
        for(int i = 0; i < vis.length; i++){
            if(i != x && !vis[i] && isConnected[x][i] == 1){
                dfs(i, vis, isConnected);
            }
        }
    }
}