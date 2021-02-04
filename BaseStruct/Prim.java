package BaseStruct;

/*

最小生成树的 prim算法。

*/
public class Prim {
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