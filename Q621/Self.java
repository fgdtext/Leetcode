package Q621;

import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;

class Solution2 {
    public int leastInterval(char[] tasks, int n) {
        int[] tasknum = new int[26];
        // 统计每个任务的数量。
        for(int i = 0; i < tasks.length; i++){
            tasknum[tasks[i] - 'A']++;
        }
        // 大顶堆
        PriorityQueue<Integer> dui = new PriorityQueue<>(26,Collections.reverseOrder());
        for(int i = 0; i < 26; i++){
            if(tasknum[i] > 0)
            dui.add(tasknum[i]);
        }
        int time = 0;
        // dui 不为 空。
        while(!dui.isEmpty()){
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while(i <= n){
                if(!dui.isEmpty()){
                    if (dui.peek() > 1)
                        temp.add(dui.poll() - 1);
                    else
                        dui.poll();
                }
                time++;
                if(dui.isEmpty() && temp.size() == 0) break;
                i++;
            }
            for(int l : temp){
                dui.add(l);
            }
        }
        return time;
    }
}

 class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int max_val = map[25] - 1, idle_slots = max_val * n;
        for (int i = 24; i >= 0 && map[i] > 0; i--) {
            idle_slots -= Math.min(map[i], max_val);
        }
        return idle_slots > 0 ? idle_slots + tasks.length : tasks.length;
    }
}
