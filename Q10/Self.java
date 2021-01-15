package Q10;
/*

失败了， 写不出来了。
该案例，已经无力解决
"aaa"
"ab*a*c*a"


a    a*  a  
a* 无法兼顾到 后边出现的a. a* 后边出现的 a 决定了 当前a* 能匹配几个a.

递归失败。 



*/
import java.util.*;


/*
尝试构造 NFA.


已经通过测试

*/

class Solution2 {
    ArrayList<ArrayList<Integer>> G;   // 红边 构成的 邻接表 图

    public boolean isMatch(String s, String p) {
        int lenp = p.length();
        G = new ArrayList<>();

        // 总状态数 比 p.length 多一个， 最后一个状态时 接收状态。到达接收状态就是成功了。
        for(int i = 0; i <= lenp; i++){
            G.add(new ArrayList<>());
        }
        nfa(p);

        ArrayList<Integer> fromSet = new ArrayList<>();
        fromSet.add(0);
        // 扩展初试 状态集
        bfs(fromSet);

        int n = s.length();
        for(int i = 0; i < n; i++){
            ArrayList<Integer> toSet = new ArrayList<>();
            for(int j = 0; j < fromSet.size(); j++){
                //System.out.println(fromSet.get(j)+"+++"+fromSet.size());
                if(fromSet.get(j) < p.length() && (p.charAt(fromSet.get(j)) == s.charAt(i) ||p.charAt(fromSet.get(j)) == '.' )){
                    toSet.add(fromSet.get(j)+1);
                }
            }
            bfs(toSet);
            fromSet = toSet;
        }
        for(int i = 0; i < fromSet.size(); i++){
            if(fromSet.get(i) == p.length()){
                return true;
            }
        }
        return false;
    }

    public void nfa(String pat){
        int n = pat.length();
        for(int i = 0; i < n; i++){
           if(i < n-1 && pat.charAt(i+1) == '*'){
                G.get(i+1).add(i);
                G.get(i).add(i+1);
           }
           if(pat.charAt(i) == '*'){
               G.get(i).add(i+1);  // i+1 可能越界，所以取出的时候要注意判别。
           }
        }
    }
    // 把一个集合  进行 episo扩展(保留原有元素，并扩展)
    public void bfs(ArrayList<Integer> set){
        int n = G.size();
        boolean[] vis = new boolean[n+1];// 标记该点是否访问
        LinkedList<Integer> queue = new LinkedList<>();
        // 一次性入栈
        for(Integer it : set){
            queue.add(it);
            vis[it] = true;
        }
        while(!queue.isEmpty()){
            int v = queue.getFirst();
            queue.removeFirst();
            for(int i = 0;  i < G.get(v).size(); i++){
                if(!vis[G.get(v).get(i)]){
                    set.add(G.get(v).get(i));
                    vis[G.get(v).get(i)] = true;
                    queue.addLast(G.get(v).get(i));
                }
            }
        } 
    }
}



public class Self{
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean is =  solution.isMatch("aa", "a");
        System.out.println(is);
    }
}