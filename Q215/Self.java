package Q215;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;




/*

使用 内置 堆 : 优先队列来做。 
该方法不是一个好的方法。   基于快排的选择法才是比较好的。  

要求用堆的话， 最好能自己实现，大顶堆，或者小顶堆。
*/
class Self1 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer>  dui = new PriorityQueue<>(k);
        for(int e : nums){
            insert(dui, e, k);
        }
        return dui.peek();
    }
    public void insert(PriorityQueue<Integer> dui, int key,int k){
        if(dui.size() == k){
            if(key > dui.peek()){
                dui.poll();
                dui.offer(key);
            }
        }else{
            dui.offer(key);
        }
    }
}







/*
手写小顶堆。
每次都删去堆顶
int[] dui = new int[k] , 限制了容量为k.
*/

public class Self{
    public static void main(String[] args) {
        Dui d = new Dui(10);
        d.offer(88);
        d.offer(55);
        d.offer(35);
        d.offer(3);
        d.offer(5);
        d.offer(7);
        d.offer(9);
        d.offer(345);
        d.offer(23);
        d.offer(365);

        d.offer(52);
        d.offer(37);

        while(d.curlen > 0){
            System.out.print(d.poll() + " " );
            // System.out.println();
            // for(int i = 0; i < d.curlen; i++){
            //     System.out.print(d.dui[i] + " " );
            // }
            // System.out.println();
        }
    }
}













/*

一个固定大小的堆。


*/
class Dui{
    int[] dui;
    int curlen;
    public Dui(int k){
        dui = new int[k];
        curlen = 0;
    }
    // 调整数组最后一个元素到合适的位置。  写好了。
    public void downtoup(){
        if(curlen <= 1) return;
        int cur = curlen - 1;
        int key = dui[cur];
        while(cur > 0){
            int parent = (cur - 1) >>> 1;
            if(key >= dui[parent]) break;
            dui[cur] = dui[parent]; // 父亲下调，儿子才能上调。
            cur = parent;
        }
        dui[cur] = key;
    }
    public void uptodown(){
        if(curlen <= 1) return;
        int cur = 0;
        int key = dui[cur];
        int half = curlen >>> 1;
        while(cur < half){
            int child = (cur << 1) + 1;
            int right = child + 1;
            if(right < curlen && dui[child] > dui[right]) 
                child = right;
            if(key <= dui[child]) break;
            dui[cur] = dui[child];
            cur = child;
        }
        dui[cur] = key;
    }
    // 向堆内添加元素
    public void offer(int key){
        if(curlen < dui.length){  // 堆还有空闲 空间
            dui[curlen++] = key;
            // 自下向上调整。
            downtoup();
        }else{
            if(key > dui[0]){ // 只要新元素大于 堆顶时，才会加入该元素。
                dui[0] = key;
                uptodown();   // 自上向下调整。
            }
        }
    }
    public int poll(){
        if(curlen == 0) return Integer.MIN_VALUE;  // error 
        if(curlen == 1) return dui[--curlen];
        int res = dui[0];
        dui[0] = dui[--curlen];
        uptodown();
        return res;
    }
}



 class Ans {

    private static Random random = new Random(System.currentTimeMillis());

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        // 转换一下，第 k 大元素的索引是 len - k
        int target = len - k;

        while (true) {
            // index 这个位置通过partition操作将恰好归位一个元素。那么这么位置的元素就恰好对应其下标。
            int index = partition(nums, left, right);
            if (index == target) {
                return nums[index];
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
    }

    public int partition(int[] nums, int left, int right) {
        // 在区间随机选择一个元素作为标定点
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIndex);
        }

        int pivot = nums[left];

        // 将等于 pivot 的元素分散到两边
        // [left, lt) <= pivot
        // (rt, right] >= pivot

        int lt = left + 1;
        int rt = right;

        while (true) {
            while (lt <= rt && nums[lt] < pivot) {
                lt++;
            }
            while (lt <= rt && nums[rt] > pivot) {
                rt--;
            }

            if (lt > rt) {
                break;
            }
            swap(nums, lt, rt);
            lt++;
            rt--;
        }

        swap(nums, left, rt);
        return rt;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}