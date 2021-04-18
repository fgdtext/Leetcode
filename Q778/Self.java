package Q778;

import java.util.*;

public class Self {
    
}
/*
Dijkstra 算法

抽象为有向图。一个点到其邻点的距离为 两个点的最高高度。
自身到自身的边长为自身的高度。

这个题关键是图的构成： 当水面覆盖两个相邻的桶时，才是连通的。即水为t>max()时，两个点连通。

*/
class Self2 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int swimInWater(int[][] grid) {
        int h = minimumEffortPath(grid);
        return h;
    }

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
        pq.offer(new int[]{0, 0, heights[0][0]});
        // 只要是从自身出发的边，边长等于高度。

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = heights[0][0];
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
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.max(heights[x][y], heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.max(heights[x][y], heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }
        return dist[m * n - 1];
    }
}



/*
二分法

*/
class Ans1 {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int swimInWater(int[][] grid) {
        return minimumEffortPath(grid);
    }
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int left = 0, right = n*n-1, ans = 0;
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
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && !seen[nx * n + ny] && Math.max(heights[x][y], heights[nx][ny]) <= mid) {
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

