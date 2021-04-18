package Q399;


import java.util.*;

public class Self {
    
}
/*
构成图，隐式图查找。a/b = 3 b/c = 2  则 a/c = a/b  *(b/c) = 3*2 = 6. 
从图的角度看 就是 a -> b -> c  两个边的距离是3和2, 则a到c的距离是 3*2.
通过dfs函数，返回当前结点到终点的距离。（类似后序）

*/
class Self1 {

    HashMap<String, List<Edge>> mEdges = new HashMap<>();  // 邻接表图，一个String对应一个 List<Edge>
    double[] mRes;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 1. 构建加权有向图
        for (int i = 0; i < equations.size(); i++) {
            List<String> edge = equations.get(i);
            String v1 = edge.get(0);
            String v2 = edge.get(1);
            double val = values[i];
            // 添加到 v1->v2 的边
            List<Edge> v1Edges = mEdges.get(v1);
            if (v1Edges == null) {
                v1Edges = new ArrayList<>();
                mEdges.put(v1, v1Edges);
            }
            v1Edges.add(new Edge(v1, v2, val));
            // 添加到 v2->v1 的边
            List<Edge> v2Edges = mEdges.get(v2);
            if (v2Edges == null) {
                v2Edges = new ArrayList<>();
                mEdges.put(v2, v2Edges);
            }
            v2Edges.add(new Edge(v2, v1, 1.0 / val));
        }

        // 2. dfs 搜索数据
        mRes = new double[queries.size()];
        List<String> visited = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String dest = query.get(1);
            visited.clear();
            mRes[i] = dfs(start, dest, visited);
        }
        return mRes;
    }
    /*
        这个dfs函数设计的很好，

    */
    private double dfs(String start, String dest, List<String> visited) {
        // 验证是否存顶点
        if (!mEdges.containsKey(start) || !mEdges.containsKey(dest)) {
            return -1.0;
        }
        visited.add(start);
        if (start.equals(dest)) {
            return 1.0;
        }
        // 获取 start 顶点的边
        List<Edge> startEdges = mEdges.get(start);
        if (startEdges == null || startEdges.isEmpty()) {
            return -1.0;
        }
        // 深度优先遍历集合
        for (Edge edge : startEdges) {
            if (visited.contains(edge.to)) {
                continue;
            }
            double res = dfs(edge.to, dest, visited);
            if (res != -1.0) { // 找到了，就直接退出，不再找其他分支。
                return res * edge.val;
            }
        }
        return -1.0;
    }

    class Edge {
        String from;
        String to;
        double val;

        public Edge(String from, String to, double val) {
            this.from = from;
            this.to = to;
            this.val = val;
        }
    }

}

/*class

算法2 ： 利用并查集。 
a->b ：表示a/b   
a->b->c 
d->r->c
ad分别是c的两个子节点。
则 a/d == >  a->c->d   ==> a->c * c -> d == > a->c / d -> c == a/d

所以分别求 a->c，  d -> c 就是分子和分母。

按照这个思想，我们可以构造并查集，若一条边分别在两个集合，就把集合合并。

优化 : 路径压缩。 a-> c的路径可能很长。我们可以通过路径压缩，减少找c的过程。

*/



class Ans1 {

    HashMap<String,String> p;  // p[x] 记录节点 x 的父节点
    HashMap<String,Double> d;  //d[x] 记录节点 x 到父节点的距离（即 x / y）
    String find(String x){  // 找到结点 x的祖先结点。
        if(p.get(x) != x){
            String t = find(p.get(x));
            d.replace(x, d.get(x) * d.get(p.get(x)));
            p.replace(x, t);
        }
        return p.get(x);
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        p = new HashMap<>();
        d = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0); 
            String b = equations.get(i).get(1); 
            p.put(a, a); p.put(b, b);
            d.put(a, 1.0); d.put(b, 1.0);
        }
        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0); 
            String b = equations.get(i).get(1); 
            String ra = find(a);
            String rb = find(b);
            // 把两个根接一起
            if(ra == rb) continue; // 啥都不做
            p.replace(ra, rb);
            // 四边形法则
            d.replace(ra, values[i] * d.get(b) / d.get(a));
        }
        double[] ans = new double[queries.size()];
        int k = 0;
        for(List<String> t : queries){
            String a = t.get(0);
            String b = t.get(1);
            //  !p.containsKey(a)  若以知集合里边是变量abc。  但是要求 z/y. 那么就找不到 z/y  ,find会报错。
            if(!p.containsKey(a) || !p.containsKey(b) || find(a) != find(b)) ans[k++] = -1.0;
            else ans[k++] = d.get(a) / d.get(b);   
        }
        return ans;
    }
}

/*
效率差不多。 个人更倾向于 Ans1
*/
class Solution {

    HashMap<String,String> p;  // p[x] 记录节点 x 的祖先节点
    HashMap<String,Double> d;  //d[x] 记录节点 x 到祖先节点的距离（即 x / root）
    String find(String x){  // 找到结点 x的祖先结点。
        if(p.get(x) != x){
            String t = find(p.get(x));
            d.replace(x, d.get(x) * d.get(p.get(x)));
            p.replace(x, t);
        }
        return p.get(x);
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        p = new HashMap<>();
        d = new HashMap<>();
        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0); 
            String b = equations.get(i).get(1); 
            p.put(a, a); p.put(b, b);
            d.put(a, 1.0); d.put(b, 1.0);
        }
        for(int i = 0; i < equations.size(); i++){
            String a = equations.get(i).get(0); 
            String b = equations.get(i).get(1); 
            String ra = find(a);
            // 只找一个根，接在另一个集合的结点上
            p.replace(ra, b);
            d.replace(ra, values[i]/d.get(a));
        }
        double[] ans = new double[queries.size()];
        int k = 0;
        for(List<String> t : queries){
            String a = t.get(0);
            String b = t.get(1);
            if(!p.containsKey(a) || !p.containsKey(b) || find(a) != find(b)) ans[k++] = -1.0;
            else ans[k++] = d.get(a) / d.get(b);   
        }
        return ans;
    }
}