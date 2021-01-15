package Q24;

import java.util.List;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

/*

加个头节点，来统一所有操作。


*/


  class Se {
    public ListNode swapPairs(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode cur = head;
        ListNode ans = head.next;
        while(cur != null && cur.next != null){
            ListNode temp = cur.next.next;
            pre.next = cur.next;
            cur.next.next = cur;
            cur.next = temp;
            pre = cur;
            cur = cur.next;
        }
        return ans;
    }
}



/*class


递归版本学习一下

*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}