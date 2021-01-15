package Q1046;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Self {
    
}


class Solution {
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        if(len == 0) return 0;
        if(len == 1) return stones[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->{return o2-o1;});
        for(int i = 0; i < len; i++){
            pq.offer(stones[i]);
        }
        while(pq.size() > 1){
            int x = pq.poll();
            int y = pq.poll();
            if(x > y)
            pq.offer(x-y);
        }

        return pq.isEmpty() ? 0 : pq.poll();
    }
}



/*class



*/

