#include <bits/stdc++.h>
using namespace std;


/**
 * 基于dfs的拓扑排序。
 * 
 * 拓扑排序一定是无环的。 使用dfs ,若找到一个点的孩子已经访问过，则存在环。
 * 若，dfs所有点，都没有找到，一定无环。有环一定能dfs到。
 * 
 * 思路: c[u] = 0,表示该节点没有访问，但其子孙，父节点未知。; 
 * c[u] == -1 表示正在访问他的子孙。
 * c[u] == 1 表示，已经访问完所有子孙。则可以加入topo[]
 * 极端例子，从后向前遍历节点，dfs. 这样。每次都是加入图的最后节点。
 * a-b b-c c-e e-d a-d d-e  :      先找到e , 然后d ,c,b,a.所以要倒序存。图：例题6-15
 */
const int max3 = 10000 + 10,maxn2 = 1000 + 5;
int gra[max3][max3], n;
int c[max3], topo[maxn2];
int t; //剩余多少节点。

// 是否可以合法遍历完其子孙，且是否有圈. 一定可以将u和u的子孙进行拓扑排序(无圈的情况)
bool dfs(int u)
{
    c[u] = -1; // -1 该点已经访问，但还没有访问其子节点。
    for(int v = 0; v < n; v++)
    {   //  u 可以到达 v    
        if(gra[u][v])
        { 
            // false 两个if 返回false一定是存在圈的时候会导致。,否则第二个if 进行dfs，最后一定dfs 到返回true.
            if(c[v] < 0) return false; 
            //若c[v] == 0 v没有访问过，且v不能合法访问完。则返回false.若能合法访问完。则遍历下一个v。
            if(!c[v] && !dfs(v))  return false; 
        }
    }
    // 后序操作，若 u的子节点都合法访问完了,则c[u]修正为1，则该点入序列topo.
    c[u] = 1; topo[--t] = u;
    return true;
}

int toposort()
{
    t = n;
    memset(c, 0, sizeof(c));
    // 每一次循环，都在图中标记一串以 u开始的节点入 topo[]中。且形成新的终点(u的父节点。)。
    for(int u = 0; u < n; ++u)
        if(!dfs(u)) return false;
    return true;
    //例子：a-b c-b b-d; 假设第一次从a开始，则确定abd. 在是c 确定， cabd;
}


 /**
  * 欧拉回路定理 ：一笔画问题
  * 无向图： 1. 连通图，且最多两个奇点(度数为奇数)，则一定存在欧拉回路。 
  *         2. 如果有两个奇数点，则必然是从一个出，一个入。
  *         3 ，如果无奇数点，则可以从任意一个点出发。 同时回到该点。
  * 有向图： 1.最多两个点出度不等于入度。且一个出度比入度大1，一个入度比出度大1.
  * 前提：去掉方向后，图是联通的。
  * 
  * 要能看懂等价题目， 其都是一笔画问题。
  * 
  */
 int G[max3][max3], vis[max3][max3];
//dfs方法  且结合后序输出思想输出
// euler(u) 将u看作root节点, 每完成 一个子节点v的输出后，输出root 到v的路径。
//类似中序遍历，输出左子树后，输出root ,再输出右子树。
//也就是只有其子节点全输出完,才会输出root.
void euler(int u)
{   
    for(int v = 0; v < n; ++v)
    if(G[u][v] && !vis[u][v])  //若该邻接点可达，且该通路未走过。
    {
        vis[u][v] = vis[v][u] = 1; //标记该通路。
        euler(v);              //dfs 搜索下一个。
        printf("%d %d\n", u, v); 
        // 在dfs 之后输出，则后dfs的先输出.
    }
}


/*
理想路径。
*/
/**
 * 例题 6-20 理想路径。
 * 
 * 题目描述为：在最短路的情况下。附加满足一定条件的理想路径。 最短路是前提。
 * 
 * 算法： 两次bfs .  因为最短路是前提，所以我们要先求最短路。
 * 我们选颜色时，必须先选出来从哪条路走下去最近。拿到一个节点，
 * 必须知道它的一个走哪几个点最近，然后从这几个点选出编号最小的颜色。
 * 所以明确：所以第一次bfs。应该先得到，每一个节点到 终点的距离。所以从重点出发，bfs。
 *         第二次bfs, 知道了每个节点到终点的距离。这样，再从起点出发。对比d[起点] - d[下一个节点] = 1； 说明是最短路径。
 *         这样选出所有这样的点。在这些点中选出颜色最小的。走向下一个节点。如果颜色相同，则全部走向下一个节点。(入队列就好)
 * 
 * 
 */

// 一种图的表示法 本质是邻接表。

struct edge
{
    int v1, v2, color;
    edge(int v1, int v2, int color):v1(v1),v2(v2),color(color){};
};

const int  max6 = 10000;
vector<int> graphh[max6]; //一个容器数组 graph[i] 为节点i的邻接表。a =  graph[i][j]表示节点i 有条边在edges[a]中;
vector<edge> edges;