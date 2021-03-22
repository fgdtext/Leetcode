package Q210;

import java.util.*;



public class Self {
    
}
/*
经典的入度为0法 进行拓扑排序。

*/
class Self2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indu = new int[numCourses];  // 课程的入度标记
        LinkedList<Integer>[] map = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++) map[i] = new LinkedList<>();
        for(int[] a : prerequisites) {
            map[a[1]].add(a[0]);
            indu[a[0]]++;  // 入度
        }
        // 存储当前可以学习的课程编号。
        LinkedList<Integer> start = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) if(indu[i] == 0) start.push(i);

        int count = 0;  // 学过多少门课程了。
        int[] ans = new int[numCourses];

        while(count < numCourses){
            if(start.isEmpty()) return new int[0];  // 还没学完所有课程，但是栈却空了，说明，存在环，无法完成学习。
            int c = start.pop();
            ans[count++] = c; // 学习课程c
            for(int e : map[c]){  // 对c的可达边 的入度 减一， 若减为0，则丢入栈中
                if(--indu[e] == 0) start.push(e);
            }
        }
        return ans;
    }
}


/*
弗洛伊德 判圈法。 即倒序的， 当一个点没有后序结点时放入栈中。当一个点访问所有后继结点后放入栈中。
当一个点的后继结点是 已经访问的点时，说明出现了圈。则返回空

注意： [[1,0],[2,0],[3,1],[3,2]]

一个点有三个状态 ：0=未搜索，1=搜索中，2=已完成

如果访问到1，则说明形成环。
访问到2没关系。上边就是个例子。
所以目标就是dfs的去访问 0结点。
*/
class Solution {

}


