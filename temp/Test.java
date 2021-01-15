package temp;

import java.util.LinkedList;
import java.util.Queue;

public class Test{
    static int[][] map = {
        {1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,1,0,1,1,1},
        {1,0,1,0,1,1,0,0,0,0,0,1},
        {1,0,1,0,1,1,0,1,1,1,0,1},
        {1,0,1,0,0,0,0,0,1,0,0,1},
        {1,0,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,1,0,1,0,0,0,0,1},
        {1,0,1,1,1,0,0,0,1,1,1,1},
        {1,0,0,0,0,0,1,0,0,0,0,1},
        {1,1,1,0,1,1,1,1,0,1,0,1},
        {1,1,1,1,1,1,1,0,0,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1}
    };
    /*
            bfs遍历， 将每个走过的结点标记为它的父结点位置(2,3,4,5)->对应{{1,0},{0,1},{-1,0},{0,-1}}。
            即通过dirct[i]到达的子节点。反向走dirct[i]就可以到父节点
            标记为0: 该点没有走过
            标记为2，3，4，5： 该点已经走过，不可再走。
            标记为1： 不可走
            另外下标 从 0 开始
            入口(2,9),对应坐标 (1,8)
            出口(11,8),对应坐标(10，7);
    */
    public static void main(String[] args) {
        // 四方向指针。
        int[][] direct = {{1,0},{0,1},{-1,0},{0,-1}};

        System.out.println("原图如下：");
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println(); 
        }
        System.out.println(); 
        int[] start = {1,8};  // 起点坐标
        int[] end = {10,7};  // 终点坐标。

        Queue<int[]> queue = new LinkedList<>();

        queue.add(start);
        map[start[0]][start[1]] = 1; //标记出发点

        while(!queue.isEmpty()){  // bfs
            int[] head = queue.poll();
            for(int i = 0; i < 4; i++){
                int ii = head[0] + direct[i][0];
                int jj = head[1] + direct[i][1];
                if(map[ii][jj] == 0){  // 只有为0，该点才能走。

                    map[ii][jj] = i+2; // 指明该点，是父节点 通过 direct[i]到达的该点。

                    if(ii == end[0] && jj == end[1]) break;  // 到达终点。就跳出。

                    queue.offer(new int[]{ii,jj});
                }
            }
        }
        
        if(map[end[0]][end[1]] == 0){   // 若最后 终点标记没有改变，则说明没有通路
            System.out.println("error: 无法到达终点，不存在通路"); 
            return;
        }
        /*
            bfs遍历结果
        */
        System.out.println("经过bfs标记后图如下：");
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println(); 
        }
        System.out.println(); 

        /*      
            迭代从终点找起点。
            从终点 end 反向走 direct 到起点，并把路径标记为 9。  
            由9组成的路径就是最短路径。
        */
        int juli = 0;  // 自身到自身的距离为 0；相邻结点的距离为 1.
        int ii = end[0];
        int jj = end[1];

        while(ii != start[0] || jj != start[1]){
            int dr = map[ii][jj];
            map[ii][jj] = 9;     //
            ii = ii - direct[dr-2][0];  //     减法 direct 就是反向走。
            jj = jj - direct[dr-2][1];  // 
            juli++;
        }
        map[start[0]][start[1]] = 9;

        System.out.println("距离是： " + juli); 
        System.out.println("从终点开始标记最短路径到起点，路径标记为9 ：");
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                System.out.print(map[i][j]+"   ");
            }
            System.out.println(); 
        }
    }
}



/*class

通过 dfs进行回溯法，也可以，但是需要在 整体解空间 中找路径最小值。时间效率太低。

bfs 最多扫描 整个图一遍即可。

*/