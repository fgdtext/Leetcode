package Q206;

public class Self {
    
}
  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 
/*
递推反转 链表


*/

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }
}