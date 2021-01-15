package Q621;

import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;

class Solution {
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