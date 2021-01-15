 package Q2;
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
 
  class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode ans = l1;
        ListNode last1 = null;
        ListNode last2 = null;
        while(l1 != null && l2 != null){
            int temp = (carry + l1.val + l2.val) / 10;
            l1.val = (carry + l1.val + l2.val) % 10;
            carry = temp;
            if(l1.next == null) last1 = l1; 
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int temp = (carry + l1.val) / 10;
            l1.val = (carry + l1.val) % 10;
            carry = temp;
             if(l1.next == null) last1 = l1; 
            l1 = l1.next;
        }
        if(l2  != null) last1.next = l2;
        while(l2  != null){
            int temp = (carry + l2.val) / 10;
            l2.val = (carry + l2.val) % 10;
            carry = temp;
            if(l2.next == null) last2 = l2;
            l2 = l2.next;
        }
        if(carry > 0){
            if(last1.next != null){
                last2.next = new ListNode(carry);
            }else{

                last1.next = new ListNode(carry);
            }   
        }
        return ans;
    }
}

/*class

java.lang.NullPointerException
  at line 29, Solution.addTwoNumbers
  at line 57, __DriverSolution__.__helper__
  at line 87, __Driver__.main

*/