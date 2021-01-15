package Q21;

import java.util.List;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 /*

合并两个有序链表 
 */
  class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode tail = new ListNode();     
        if(l1 == null) return l2;
        if(l2 == null) return l1; 
        ListNode head = l1.val <= l2.val ? l1 : l2;  
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            }else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        while(l1 != null){
            tail.next = l1;
            l1 = l1.next;
            tail = tail.next;// 一定不能完了 移动尾部 指针
        }

        while(l2 != null){
            tail.next = l2;
            l2 = l2.next;
            tail = tail.next;   // 一定不能完了 移动尾部 指针
        }
        return head;
    }
}