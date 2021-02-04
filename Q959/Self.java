package Q959;

import java.util.ArrayList;
import java.util.LinkedList;

public class Self{
    public static void main(String[] args) {
        // Solution s = new Solution();
        // String[] a = { "\\/\\ "," /\\/"," \\/ ","/ / "};
        // s.regionsBySlashes(a);
    }
}





/*

// 自己写的垃圾算法， 既麻烦又麻烦。

不管是 / 还是 \  总会将一个方块切为左右两块。 我们用  二进制 11 分别代表  右左 两块，用于占位标记。节省空间。

对于每一个分块，分为两种情况，是左还是右。

对于每一个左块。分 向上或是向下连通。 左块总是会连通左侧方块的右块。


于是变为一个分类的  dfs 涂色算法。看涂色的总类就是答案。


这里的问题是：  做出了大量的分类。太麻烦。而且效率也不是很高。

有一个知识点，   对于图论的题， 使用 并查集 是比较普遍的， 算法一般会比建立完整的图会搞笑切易编码。



*/
class Self2 {
    int[][] vis;
    String[] grid;
    int ans = 0;
    boolean flag;
    int n;
    public int regionsBySlashes(String[] grid) {
        this.grid = grid;
        n = grid.length;
        vis = new int[n][n];  // 使用二进制标记。
        for(int i = 0; i < n; i++){
            for(int j = 0; j < grid[0].length(); j++){
                for(int k = 0; k < 2; k++){
                    flag = false;
                    if((vis[i][j] & (1 << k)) == 0)
                        dfs(i, j, k);
                    if(flag) ans++;
                }
            }
        }
        return ans;
    }
    public void dfs(int i, int j, int k){ // 表示ij位置的第一个点。(0,1)
        vis[i][j] = vis[i][j] | (1 << k); // 标记该位置
        flag = true; // 标记 发生了占位。
        // 开始 dfs
        if(k == 0){   // 每个点有三个相邻的方向。
            if((vis[i][j] & (1 << 1)) == 0 && grid[i].charAt(j) == ' ') dfs(i, j, 1);   //1
            if(j-1 >= 0 && (vis[i][j-1] & (1 << 1)) == 0) dfs(i, j-1, 1); //2
            if((grid[i].charAt(j) == '/' || grid[i].charAt(j) == ' ') && i-1 >= 0){ // 尝试向上
                int kk = grid[i-1].charAt(j) == '/' ? 1 : 0;
                if((vis[i-1][j] & (1 << kk)) == 0){
                    dfs(i-1, j, kk);
                }
            } 
            if((grid[i].charAt(j) == '\\' || grid[i].charAt(j) == ' ')&& i+1 < n){ // 尝试向下
                int kk = grid[i+1].charAt(j) == '/' ? 0 : 1;
                if((vis[i+1][j] & (1 << kk)) == 0){
                    dfs(i+1, j, kk);
                }
            }
        }else{
            if((vis[i][j] & (1 << 0)) == 0 && grid[i].charAt(j) == ' ') dfs(i, j, 0);   //1
            if(j+1 < n && (vis[i][j+1] & (1 << 0)) == 0) dfs(i, j+1, 0); //2
            if((grid[i].charAt(j) == '\\' || grid[i].charAt(j) == ' ')&& i-1 >= 0){ // 尝试向上
                int kk = grid[i-1].charAt(j) == '/' ? 1 : 0;
                if((vis[i-1][j] & (1 << kk)) == 0){
                    dfs(i-1, j, kk);
                }
            }
            if((grid[i].charAt(j) == '/' || grid[i].charAt(j) == ' ' )&& i+1 < n){ // 尝试向下
                    int kk = grid[i+1].charAt(j) == '/' ? 0 : 1;
                    if((vis[i+1][j] & (1 << kk)) == 0){
                        dfs(i+1, j, kk);
                    }
            }
        }
    }
}


/*class

基于方块拆分。 
   /   
   001
   010
   100     
   \
   100
   010
   001 
   一个小块变为  3*3 的小块。

   于是原图发生转化。 基于 01图直接4方向找联通块即可。
*/


/*class
第二种拆分方法
一个块拆分为 4部分，两个对角线吧一个方块切成4部分。 

相邻两个方块的相邻内块总是可以互通。我们假设默认可以到达。

那么我们根据方格内的斜线，可以连通方格内的4个小格。 

算法  : 
1 :  连通方格内的4个小格,使得每一个方格形成若干个并查集。 方格内合并。
2 ： 合并边界。 对于边界的两个小块，使用并查集合并。 
        1. 横向合并，即左右相邻的合并。 
        2. 上下合并，即向下相邻的合并。
        对于每一个单元格，总是向右，和向下合并即可。
*/
 class Solution {

    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;

        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                // 二维网格转换为一维表格，index 表示将单元格拆分成 4 个小三角形以后，编号为 0 的小三角形的在并查集中的下标
                int index = 4 * (i * N + j);
                char c = row[j];
                // 单元格内合并
                if (c == '/') {
                    // 合并 0、3，合并 1、2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    // 合并 0、1，合并 2、3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }
    // 模板。
    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        // 压缩路径的第二种方式。  非递归方式。效率更好。
        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}
