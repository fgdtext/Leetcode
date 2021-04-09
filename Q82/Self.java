package Q82;

import java.util.List;


public class Self {
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode t = a;
        t.next = new ListNode(2);
        t = t.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(3);
        t = t.next;
        t.next = new ListNode(4);
        t = t.next;
        t.next = new ListNode(4);
        t = t.next;
        t.next = new ListNode(5);
        t = t.next;
        Solution so = new Solution();
        so.deleteDuplicates(a);
    }
}


 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
     ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 /*

链表的题， 真是麻烦

 */
class Self2 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        //使用空的头结点，来连接新链，和旧链分隔开
        ListNode tailhead = new ListNode(-101);
        ListNode tail = tailhead;
        // pre 永远指向一个值的第一个结点，当再遇到该值，就把pre 置空
        ListNode pre = head;
        // cur: 当前结点
        ListNode cur = head.next;
        // 上一段的值。 
        int preval = head.val;
        while(cur != null){
            /* 如果当前值和上一段的值，不相等，那么分为两种情况
                1. 上一段是重复的：即pre 被置空
                2. 上一段不是重复的： 即pre 指向上一段第一个结点。
                最后pre都要移动到cur的位置。
            */
            if(cur.val != preval){
                if(pre != null){
                    tail.next = pre;
                    tail = tail.next;
                    tail.next = null;
                }
                pre = cur;
                preval = cur.val;
            }else{ // 如果前一段的值，和cur值相等，说明，应该被置空。表示pre这一段值是重复的。
                pre = null;
            }
            cur = cur.next;
        }
        if(pre != null){
            tail.next = pre;
        }
        return tailhead.next;
    }
}


class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode numpy = new ListNode();
        ListNode pre = numpy;
        ListNode cur = head;
        while(cur != null){
            if(cur.next != null && cur.val == cur.next.val) {
                int val = cur.val;
                while(cur != null && cur.val == val){
                    cur = cur.next;
                }
                continue;
            }
            pre.next = cur;
            pre = pre.next;
            cur = cur.next;
            pre.next = null; 
        }
        return numpy.next;
    }
}