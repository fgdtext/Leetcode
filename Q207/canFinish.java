package Q207;

import java.util.*;

/**
  该算法不好，因为题目只要求判断是否有环。直接bfs,dfs 判断是否有环即可。。 
 */

class Solution {
    public int numasd;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int alen = prerequisites.length;
        int[] count = new int[numCourses];
        LinkedList<Integer>[] lists = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++){
            lists[i] = new LinkedList<Integer>();
        }

        for(int i = 0; i < alen; i++){
            // 统计每门课需要多少门课作为前提。
            int courseto = prerequisites[i][0];
            int coursefrom = prerequisites[i][1];
            ++ count[courseto];
            lists[coursefrom].addFirst(courseto);
        }
        
        // 开始拓扑排序
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            // 记录第一批 需求为  0 的课程   头插法。
            if(count[i] == 0) stack.addFirst(i);           
        }
        // 栈不为空。
        int cp = 0; // 记录完成的课程数量
        while(!stack.isEmpty()){
            int top = stack.removeFirst();
            ++cp;
            int courscount = lists[top].size();
            for(int i = 0; i < courscount; i++){
                int couseto = lists[top].removeFirst();
                --count[couseto];
                // couseto 课程所需要的课程都学完了
                if(count[couseto] == 0){
                    stack.addFirst(couseto);
                }   
            }
        }
        return cp == numCourses;
    }
}


public class canFinish {
    public static void main(String[] args) {
        
        Solution so = new Solution();
        int num1 = 2;
        int[][] a = {{1,0}};
        if(so.canFinish(num1, a)){
            System.out.println("ok");
        }else{
            System.out.println("error");
        }



    }
}

class Solution3 {
    List<List<Integer>> edges;
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; ++i) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }
        return valid;
    }
    /*
        dfs如何找有向图的环： 

    */
    public void dfs(int u) {
        visited[u] = 1;
        for (int v: edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;
                return;
            }
        }
        visited[u] = 2;
    }
}