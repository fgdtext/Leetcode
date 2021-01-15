package Q947;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        //[[0,0],[0,1],[1,0],[1,1],[2,1],[2,2],[3,2],[3,3],[3,4],[4,3],[4,4]]
        int[][] a = {{0,0},{0,1},{1,0},{1,1},{2,1},{2,2},{3,2},{3,3},{3,4},{4,3},{4,4}};
        so.removeStones(a);
    }
}
/*
假设一个点可以连接两个并查集。 总共 N 个点构成一个数组，。 通过并查集来表示连通。


hangmap ：  x:y x行被y(集合中某结点，通过find找到root)集合占领了 这样就可以找到该行对应的集合。
同理找到列集合。

那么通过合并 该点， 另外两个集合，达到 3方连通。 即 两个不连通的集合， 通过该点e实现了连通。

连通的集合 则最后只会剩下一个1个点。



方法2 : 通过图来构建， 同一行的两个点，必连通。 则可以构建一张图， 连通子图的个数，就是最后剩下的。

*/



class Solution {
    public int removeStones(int[][] stones) {
        int len = stones.length;
        if(len <= 1) return 0;
        int[] parent = new int[len]; // 表示并查集。 
        for(int i = 0; i < len; i++){
            parent[i] = i;
        }
        HashMap<Integer, Integer> hangmap = new HashMap<>(); // 该 行 坐标 被某个集合占领了， x:y x行被y(集合中某结点，通过find找到root)集合占领了
        HashMap<Integer, Integer> liemap = new HashMap<>();  // 列
        for(int i = 0; i < len; i++){
            int[] e = stones[i];
            if(!hangmap.containsKey(e[0])){
                hangmap.put(e[0], i);
            }
            if(!liemap.containsKey(e[1])){
                liemap.put(e[1], i);
            }
            int hangset_ind = hangmap.get(e[0]);  // 找到该行所属集合中的一点。通过该点可以找到该集合的 root.
            int lieset_ind = liemap.get(e[1]);
            int a = find(hangset_ind, parent);
            int b = find(lieset_ind, parent);
            parent[i] = a;   // 合并该点
            if(a != b){   // 两个集合合并。
                parent[b] = a;
            }
        }
        int ans = 0;
        for(int i = 0; i < len; i++){
            if(parent[i] != i) ans++;
        }
        return ans;
    }
    // 压缩路径。
    public int find(int x, int[] parent){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}


