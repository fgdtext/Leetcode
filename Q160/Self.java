package Q160;

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
求 差值， 让长的那个 先走差值的距离。
此时，两个引用，距离 交叉点 距离 都相同。一块走就ok



*/


  class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cur = headA;
        int a = 0;
        while(cur != null){
            a++;
            cur = cur.next;
        }
        cur = headB;
        int b = 0;
        while(cur != null){
            b++;
            cur = cur.next;
        }
        int c = Math.abs(a-b);
        if(a > b){
            while(c > 0){
                headA = headA.next;
                c--;
            }
        }
        if(b > a){
            while(c > 0){
                headB = headB.next;
                c--;
            }
        }
        while(headA != headB){
            if(headB == null || headA == null) return null;
            headB = headB.next;
            headA = headA.next;
        }
        return headB;
    }
}