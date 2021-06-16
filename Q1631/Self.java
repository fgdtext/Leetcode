package Q1631;

import java.util.*;

/*class
二分法
是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 x？
解决： 进行dfs或bfs搜索，只准走<=x的边，如果能到达终点，说明存在该路径。
如果存在，那么说明答案必然<=x, 否则>=x
那么就可以使用二分法。因为高度差最大[1,10^6]  所以可以二分。

代码很好写。
*/

class Ans1 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = 999999, ans = 0;
        // 二分
        while (left <= right) {
            int mid = (left + right) / 2;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.offer(new int[]{0, 0});
            boolean[] seen = new boolean[m * n];
            seen[0] = true; // vis 标记已经走过的点。
            // bfs
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int i = 0; i < 4; ++i) {
                    int nx = x + dirs[i][0];
                    int ny = y + dirs[i][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.abs(heights[x][y] - heights[nx][ny]) <= mid) {
                        queue.offer(new int[]{nx, ny});
                        seen[nx * n + ny] = true;
                    }
                }
            }
            if (seen[m * n - 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}



/*class

并查集 

把所有边进行从小到大排序。类似于最小生成树，只要一个边能使得起点和终点的集合合并，那么该边就是ans.
可以肯定的是，这个边在集合中是最大的。缺少该边，就不连通。所以必然是ans.

每加入一个边都要判断 起点是否和终点连通。

*/

class Ans2 {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> edges = new ArrayList<int[]>();
        // 把每个点的向左和向上的边加入。  第一行第一列除外。
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int id = i * n + j;
                if (i > 0) {
                    edges.add(new int[]{id - n, id, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[]{id - 1, id, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        Collections.sort(edges, (edge1,edge2)->{
            return edge1[2] - edge2[2];
        });

        UnionFind uf = new UnionFind(m * n);
        int ans = 0;
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], v = edge[2];
            uf.unite(x, y);
            // 连接 该边两个端点所在的集合。
            if (uf.connected(0, m * n - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }
}

// 并查集模板
class UnionFind {
    int[] parent;
    int[] size;
    int n;
    // 当前连通分量数目
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        // 加权
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }
    // 压缩
    public int findset(int x){
        if(x != parent[x]){
            parent[x] =  findset(parent[x]);
        }
        return parent[x];
    }
    public boolean unite(int x, int y) {
        x = findset(x);
        y = findset(y);
        if (x == y) {
            return false;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        // 小的接在大的上边。
        parent[y] = x;
        size[x] += size[y];
        --setCount;
        return true;
    }
    
    public boolean connected(int x, int y) {
        x = findset(x);
        y = findset(y);
        return x == y;
    }
}



/*class


Dijkstra 算法， 求单源点到其他点的最短路径算法。 这里的最短路径是指，路径上的最长边长。

这个目前实最快的。

*/

class Ans3 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        // {x,y,d} 当前集合到达 点(x,y)的边长。 该队列可能存在(x,y,d1),(x,y,d2)是由不同点更新到的(x,y)
        // 如队列中已经有(x,y,d1),现在又加入(x,y,d2) 若d2 < d1, 那么 d2就会排在前边。
        // 那么出队时一定会取到短边d2, 再取到d1时，seen[x,y]就已经标记了，continue.
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            // 若该点已经在确定最短路径的集合中，就continue/
            if (seen[id]) {
                continue;
            }
            // 若取到终点就结束。dist数组在终点加入队列时就已经更新过了。
            if (x == m - 1 && y == n - 1) {
                break;
            }
            // 将该点标记到集合中。 
            seen[id] = true;
            // 使用该点，去更新他的邻点。
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        return dist[m * n - 1];
    }
}
