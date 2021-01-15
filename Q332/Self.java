package Q332;

import java.util.*;


/*
推荐直接使用递归的 euler , 就是把 dfs 由标记点，改为标记 边

也可以使用 回溯法， 写的麻烦。不推荐
*/
//  改为 邻接表的 有向图 的 欧拉 算法
class Solution {
    static class Hnode{
        String valStr;
        TreeMap<String, Integer> treeMap;
    }
    Hnode[] Grp;
    HashMap<String,Integer> map;
    LinkedList<String> ans;
    public List<String> findItinerary(List<List<String>> tickets) {
        int fromNum = tickets.size();
        map = new HashMap<>();
        ans = new LinkedList<>();
        // 初始化出发点
        int k = 0;
        for(int i = 0; i < fromNum; i++){
            // 初始化 map 
            String key = tickets.get(i).get(0);
            if(!map.containsKey(key)){
                map.put(key, k++);
            }
        }
        int fcount = map.size();
        Grp = new Hnode[fcount];
        for(java.util.Map.Entry<String, Integer> it : map.entrySet()){
            if(Grp[it.getValue()] == null){
                Grp[it.getValue()] = new Hnode();
                Grp[it.getValue()].valStr = it.getKey();
                Grp[it.getValue()].treeMap = new TreeMap<>();
            }
        }
        for(List<String> list : tickets){
            TreeMap<String, Integer> treeMap  = Grp[map.get(list.get(0))].treeMap;
            if(!treeMap.containsKey(list.get(1))){
                treeMap.put(list.get(1), 1);
                continue;
            }
            int temp = treeMap.get(list.get(1));
            treeMap.replace(list.get(1), temp+1);
        }
        // 邻接表构成完毕
        euler("JFK");
        ans.addFirst("JFK");

        return ans;
    }
    public void euler(String u){
        if(map.get(u) == null) return;
        TreeMap<String,Integer> treeMap = Grp[map.get(u)].treeMap;
        for(String v : treeMap.keySet()){
            int temp = treeMap.get(v);
            if(temp > 0){
                // 删除 指向 v 的边。不可再用
                treeMap.replace(v, temp - 1);
                euler(v);
                ans.addFirst(v);
            }
        }
    }
}

/*
欧拉： euler

Map<String, PriorityQueue<String>  ： 建立邻接表，方便快捷

PriorityQueue ： 使用 优先队列，保证 字典序。



*/
class ans {
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }
}

