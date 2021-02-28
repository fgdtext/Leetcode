package Q61;

import java.util.List;

public class Self {
    
}

 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  /*

先让 p走K步. 但是有个问题.

  */
  class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        int c = 0;
        ListNode p = head, q = head;
        int len = 0;
        while(c++ < k){
            p = p.next;
            len++;
            if(p == null) { // 走完一遍,则要对K取模,防止走第三遍. 
                p = head;
                k = k % len;
                c = 0;  // 重新开始数K个结点.
            }
        }
        while(p.next != null){
            p = p.next;
            q = q.next;
        }
        p.next = head;
        ListNode ans = q.next;
        q.next = null;
        return ans;
    }
}