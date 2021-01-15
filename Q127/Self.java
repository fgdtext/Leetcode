package Q127;

import java.util.*;

public class Self {
    
}
/*
应该是 bfs 搜索 最短路径。

首先用所有word 组成一图。 然后构造图。

无向图
*/


class Self2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<ArrayList<Integer>> g = new ArrayList<>();  // 邻接表
        int index= -1;
        for(int i = 0; i < wordList.size(); i++){
            g.add(new ArrayList<>()); // 加入 字典。
            if(wordList.get(i).equals(endWord)) index = i;
        }
        if(index == -1) return 0;   //  终点不在 字典中
        if(can(beginWord, endWord)) return 2;
        g.add(new ArrayList<>()); // 加入起点。

        for(int i = 0; i < wordList.size(); i++){
            ArrayList<Integer> from = g.get(i);    
            for(int j = i+1; j < wordList.size(); j++){
                ArrayList<Integer> to = g.get(j);
                if(can(wordList.get(i), wordList.get(j))){
                    from.add(j); to.add(i);
                }
            }
        }
        // 把起点加入 图中。
        for(int i = 0; i < wordList.size(); i++){
            if(can(wordList.get(i), beginWord)){
                g.get(i).add(wordList.size()); g.get(wordList.size()).add(i);
            }
        }
        // 起点或者终点是孤立点，一定不可以转换。
        if(g.get(wordList.size()).size() == 0 || g.get(index).size() == 0) return 0;
        // 临界表构造完成。  开始 bfs 算距离
        boolean[] vis = new boolean[g.size()];
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(wordList.size()); // 加入 起点
        queue.offer(-1); // 用于分层。
        int ans = 0;
        while(!queue.isEmpty()){
            int top = queue.poll();
            if(top == -1){  // ans++, 且加入新的 分层 结点。
                if(queue.isEmpty()) return 0; // 避免死循环
                ans++;
                queue.offer(-1);
            }else{
                if(top == index)  return ans+1;
                for(int e : g.get(top)){ // 邻接点
                    if(!vis[e]){  // 邻接点没有访问
                        vis[e] = true; // 标记
                        queue.offer(e);
                    }
                }
            }
        }
        return 0;
    }
    // 两个单词可以互通，则 返回true. 
    public boolean can(String a, String b){
        int count = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)) count++;
        }
        return count <= 1;
    }
}
/*

注意这个建图方式。


*/

class Solution {
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
}
