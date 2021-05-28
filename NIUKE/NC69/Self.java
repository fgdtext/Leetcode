package NIUKE.NC69;

import java.util.*;

 class ListNode {
    int val;
    ListNode next = null;
   public ListNode(int val) {
      this.val = val;
    }
  }
 

 class Solution {

    public ListNode FindKthToTail(ListNode pHead, int k) {
        // write code here
        if (pHead == null) {
            return null;
        }
        ListNode right = pHead;
        int count = 0;
        while (right != null) {
            right = right.next;
            count += 1;
            if (count == k) {
                break;
            }
        }
        if (count != k) {
            return null;
        }
        ListNode left = pHead;
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        return left;
    }
}








