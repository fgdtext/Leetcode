package Q328;



  class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
  class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        ListNode oddhead = head.next;  // 偶数 链表的头。
        ListNode oddtail = head.next;
        ListNode eventail = head;

        ListNode cur = head.next.next; // 前两个 已经在 链上。
        boolean flag = true;
        while(cur != null){
            if(flag){   // 奇数
                eventail.next = cur;
                eventail = eventail.next;
            }else{ // 偶数
                oddtail.next = cur;
                oddtail = oddtail.next;
            }
            flag = !flag;
            cur = cur.next;
        }
        eventail.next = oddhead;
        oddtail.next = null;
        return head;
    }
}