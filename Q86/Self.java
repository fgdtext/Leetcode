package Q86;




   class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        ListNode leftend = left;
        ListNode rightend = right;
        ListNode cur = head;
        while(cur != null){
            if(cur.val < x){
                leftend.next = cur;
                leftend = leftend.next;
            }else{
                rightend.next = cur;
                rightend = rightend.next;
            }
            ListNode temp = cur;
            cur = cur.next;
            temp.next = null;
        }
        leftend.next = right.next;
        return left.next;
    }
}