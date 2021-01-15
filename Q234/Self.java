package Q234;

public class Self {
    
}


   class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 /*
快慢指针 ：边界是  fast != null fast.next != null
如果是奇数，则slow停留在正中间， 偶数个 ，slow 停留在 后半段的开头。

链表逆序： 递推逆序。
*/
  class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = null;
        while(slow != null){
            ListNode temp = slow.next;
            slow.next = fast;
            fast = slow;
            slow = temp;
        }
        while(head != null && fast != null){
            if(head.val != fast.val) return false;
            head = head.next;
            fast = fast.next;
        }
        return true;
    }
}