package Q23;

import java.util.PriorityQueue;


public class Self {
    
}

// 合并K个升序链表


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
  // 使用堆 
  /*
 使用大小为k 的堆， 来保存 每个链表的最小元素，则每次取出来的一定是 所有最小元素中最小的。再把该结点的后序一个结点入堆。



  */

  class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        // 小 顶堆
        PriorityQueue<int[]> q = new PriorityQueue<>(lists.length,(o1,o2)->{return o1[0] - o2[0];});
        // 初始化堆
        for(int i = 0; i < lists.length; i++)if(lists[i] != null){

            q.offer(new int[]{lists[i].val,i});
        }
        if(q.isEmpty()) return null;
        // 找出第一个结点
        int[] temp = q.poll();
        ListNode tail = lists[temp[1]];
        lists[temp[1]] = tail.next;
        if(tail.next != null){
            temp[0] = tail.next.val;
            q.offer(temp);
        }
        ListNode head = tail;

        while(!q.isEmpty()){
            // 取出堆顶 a
            int[] a = q.poll();

            tail.next = lists[a[1]];
            tail = tail.next;

            lists[a[1]] = lists[a[1]].next;
            if(lists[a[1]] != null){
                a[0] = lists[a[1]].val;
                q.offer(a);
            }  
        }
        return head;
    }
}


/*class

分治算法。两两合并。

*/
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1; // 骚什么骚
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}

