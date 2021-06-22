package BaseStruct;

import java.util.*;



import java.io.*;
/*

最小生成树的 prim算法。

*/
class Prim1 {
    // 返回边数组，每个元素都是边的下标。
    public int[] primecode(int[][] g, int v0){
        int len = g.length;
        int[] vis = new int[len]; // 标记已纳入点。
        int[] lowcost = new int[len]; //  当前生成树到集合外点的最短边长度。

        for(int i = 0; i < len; i++){
            lowcost[i] = g[v0][i];
        }
        vis[v0] = 1;
        for(int i = 0; i < len; i++){
            int min = Integer.MAX_VALUE;
            int k = -1;
            for(int j = 0; j < len; ++j){
                if(vis[j] == 0 && lowcost[j] < min){
                    min = lowcost[j];
                    k = j;
                }
            }
            
            vis[k] = 1;
            for(int j = 0; j > len; j++){
                if(vis[j] == 0 && g[k][j] < lowcost[j]){
                    lowcost[j] = g[k][j];
                }
            }
        }
        return null;
    }
}



/*

 输出最小生成树。

*/


/*
5
1,4,70;1,5,30;1,3,60;3,4,30;3,5,10;4,5,25;2,4,10


*/

class Prim2 {



    public void primecode() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line_1 = bf.readLine();
        int n = Integer.parseInt(line_1);
        // 一个点，一个临接表。
        ArrayList<int[]>[] edges = new ArrayList[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }

        String[] line_2 = bf.readLine().split(";");
        // 分号隔开每一条边。
        for(String s : line_2){
            String[] edge = s.split(",");
            int from = Integer.parseInt(edge[0])-1;
            int to = Integer.parseInt(edge[1])-1;
            int w = Integer.parseInt(edge[2]);
            edges[from].add(new int[]{to , w});
            edges[to].add(new int[]{from , w});
        }
        // 最小生成树有 n-1条边。
        int[][] ans = new int[n-1][2];
        int ind = 0;
        PriorityQueue<int[]> que = new PriorityQueue<>((o1,o2)->{
            return o1[2] - o2[2];
        });
        // 假设每次都从0开始生成最小生成树。
        int v0 = 0;
        for(int[] edge : edges[v0]){
            // 起点，终点，权重。
            que.offer(new int[]{v0,edge[0],edge[1]});
        }
        boolean[] vis = new boolean[n];
        vis[v0] = true;
        while(!que.isEmpty()){
            int[] edge = que.poll();
             // 起点，终点，权重。
            int x = edge[0], y = edge[1], d = edge[2];
            if(vis[y]) continue;
            vis[y] = true;
            ans[ind][0] = x+1; ans[ind++][1] = y+1;
            for(int[] newedge : edges[y]){
                // 只能加入to点没有访问的边。
                if(!vis[newedge[0]])
                    que.offer(new int[]{y,newedge[0],newedge[1]});
            }
        }
        // 输出 n-1条边
        for(int i = 0; i < n -1; i++){
            System.out.println(ans[i][0] +" - to -" + ans[i][1]);
        }
    }
}

public class Prim{
    public static void main(String[] args)throws IOException {
        Union p = new Union();
        p.Unioncode();
    }
}

class Union {

    public void Unioncode() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line_1 = bf.readLine();
        int n = Integer.parseInt(line_1);
        // 一个点，一个临接表。
        ArrayList<int[]>[] edges = new ArrayList[n];
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
            parent[i] = i;
        }

        String[] line_2 = bf.readLine().split(";");
        // 分号隔开每一条边。
        PriorityQueue<int[]> que = new PriorityQueue<>((o1,o2)->{
            return o1[2] - o2[2];
        });
        
        for(String s : line_2){
            String[] edge = s.split(",");
            int from = Integer.parseInt(edge[0])-1;
            int to = Integer.parseInt(edge[1])-1;
            int w = Integer.parseInt(edge[2]);
            que.offer(new int[]{from, to, w});
        }
        // 最小生成树有 n-1条边。
        int[][] ans = new int[n-1][2];
        int ind = 0;

        while(!que.isEmpty()){
            int[] edge = que.poll();
            int x = edge[0], y = edge[1], d = edge[2];

            int xp = find(parent, x);
            int yp = find(parent, y);
            // 在同一个集合中，不能连接。
            if(xp == yp) continue; 
            // 连接。
            parent[xp] = yp;
            // 记录边
            ans[ind][0] = x+1; ans[ind++][1] = y+1;
        }
        // 输出 n-1条边
        for(int i = 0; i < n -1; i++){
            System.out.println(ans[i][0] +" - to -" + ans[i][1]);
        }
    }
    public int find(int[] parent,int x){
        if(parent[x] != x){
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}