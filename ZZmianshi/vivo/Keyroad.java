package ZZmianshi.vivo;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
case1:
10,3,7,6,7  // 每个结点任务需要的时间
0;1,3;0;2,3;2,4  // 分号隔开，每个任务结点，依赖的结点。0表示没有依赖，说明是叶子，出度为0.

类根的 入度为0.
case2:
30,20,20,60,50
0;1,3;1;1,2;3,4
*/



public class Keyroad {
    
    public static void main(String[] args)throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line_1 = in.readLine().split(",");

        int len = line_1.length;

        int[] times = new int[len];

        for(int i = 0; i < len; i++){
            times[i] = Integer.parseInt(line_1[i]);
        }
        String[] line_2 = in.readLine().split(";");
        ArrayList[] l2r = new ArrayList[len];
        for(int i = 0; i < len; i++){
            l2r[i] = new ArrayList<>();
        }
        
        boolean[] notroot = new boolean[len];
        for(int i = 0; i < line_2.length; i++){
            String[] nodes = line_2[i].split(",");
            if(nodes.length == 1 && nodes[0].equals("0")){
                continue;
            } 
            for(int j = 0; j < nodes.length; j++){
                int sub = Integer.parseInt(nodes[j])-1;
                l2r[i].add(sub);  
                notroot[sub] = true;     
            }
        }
        int root = 0;
        for(int i = 0; i < len; i++){
            if(!notroot[i]){
                root = i;
                break;
            }
        }
        
        // 记录完成每个结点的 最短时间。 
        int[] dp = new int[len];
        Arrays.fill(dp,-1);
  
        int ans = dfs(l2r, root,times,dp);
        if(ans == -1) System.out.println("error:循环依赖");
        System.out.println(ans);
    }

    // 
    static int dfs(ArrayList<Integer>[] l2r,int curnode,int[] times,int[] dp){
        if(dp[curnode] > 0) return dp[curnode];
        dp[curnode] = -2; // 表示开始访问。
       
        int maxtime = 0;
        for(int subnode : l2r[curnode]){

          // 只能访问没有访问过的点
          if(dp[subnode] > 0){
                maxtime = Math.max(maxtime, dp[subnode]);
          } else if(dp[subnode] == -1){
                int g = dfs(l2r, subnode,times,dp);
                if(g == -1) return -1;
                maxtime = Math.max(maxtime, g);
           }else if(dp[subnode] == -2){
                // 有循环依赖
                return -1; // 表示出错
           }
        }       
        return  dp[curnode] = maxtime + times[curnode];
    }
}

