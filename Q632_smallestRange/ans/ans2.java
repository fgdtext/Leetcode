package Q632_smallestRange.ans;

import java.util.PriorityQueue;
import java.util.List;

/**
优先队列(堆) + 多链表合并。

算法： 数列是有序的。 维持堆的大小为K, 每个数列加入一个数字。堆是升序的。每次出堆最小的元素，然后把该元素后一个元素入堆。
  在次过程中：区间为  堆中 max - min .     使用st,ed 保存最小区间。 每次弹出最小元素，比较区间。然后加入新元素。
  如果弹出的最小值的后边已经没有元素。则结束。

 */
class Solution {
public int[] smallestRange(List<List<Integer>> nums) {
    int n = nums.size();
    int inf = 0x3f3f3f;
    int max = -inf; // 当前最大值
    int st = -inf;  // 起点
    int ed = inf;   // 终点

    PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));

    // 相当于合并k个有序链表，把 head 放进去
    for (int i = 0; i < n; i++) {
        int val = nums.get(i).get(0);
        //将指定的元素插入到此优先级队列中。 
        pq.offer(new Node(i, 0, val));
        max = Math.max(max, val);
    }
    
    // 必须包含 k 个元素
    while (pq.size() == n) {
        //检索并删除此队列的头，如果此队列为空，则返回 null 
        Node node = pq.poll();
        int i = node.i;
        int j = node.j;
        int val = node.val;

        // 更新区间长度
        if (max - val < ed - st) {
            st = val;
            ed = max;
        }
        
        // 为堆中填充元素  // j + 1 < nums.get(i).size()  不满足，则说明有数列走到头了。
        if (j + 1 < nums.get(i).size()) {
            int nVal = nums.get(i).get(j + 1);
            pq.offer(new Node(i, j + 1, nVal));
            max = Math.max(max, nVal);
        }
    }
    return new int[]{st, ed};

 }
}
class Node{
    int i, j, val;

    public Node(int i, int j, int val) {
        this.i = i;
        this.j = j;
        this.val = val;
    }
}
