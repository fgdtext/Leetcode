package Q703;

import java.util.PriorityQueue;

public class Self{
    public static void main(String[] args) {
        
    }
}
/*
优先队列
*/
class Self2 {
    PriorityQueue<Integer> q;
    int[] nums;
    int k;
    public Self2(int k, int[] nums) {
       q = new PriorityQueue<>(k);
       this.nums = nums;
       this.k = k;
       for(int i = 0; i < nums.length; i++){
            addToQu(nums[i]);
       }
    }
    public int add(int val) {
        addToQu(val);
        return q.peek();
    }
    public void addToQu(int e){
        if(q.size() == k){
            if(q.peek() < e){
                q.poll();
                q.offer(e);
            }
        }else{
            q.offer(e);
        }
    }
}


