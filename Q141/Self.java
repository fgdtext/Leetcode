package Q141;

import java.util.List;

public class Self {
    
}

  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
         next = null;
     }
  }
 /*

  快慢指针 查看 链表 是否 有环

 */
 class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}