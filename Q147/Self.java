package Q147;

public class Self {
    
}


  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        ListNode mark = new ListNode(-1);
        ListNode last = head;
        mark.next = head;
        head = head.next;
        mark.next.next = null;
        while(head != null){
            ListNode temp = head;
            if(temp.val >= last.val){
                last.next = temp;
                last = last.next;
                last.next = null;
                continue;
            }
            head = head.next;
            ListNode cur = mark;
            while(cur.next != null && cur.next.val < temp.val) cur = cur.next;
            if(cur.next == null){
                last = temp;
            }
            temp.next = cur.next;
            cur.next = temp;
        }
        return mark.next;
    }
}