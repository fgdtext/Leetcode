
package Q92;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = {1,2,3,4,5};
        ListNode t = new ListNode(5,null);
        t = new ListNode(4,t);
        // t = new ListNode(3,t);
        // t = new ListNode(2,t);
        // t = new ListNode(1,t);

       so.reverseBetween(t, 1, 2);
    }
}
// 添加头结点。
class Ans {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        // 这个真牛逼 。还能这么玩。 草了。 学不会的。真狗。  这个是真的学不会的。 瞎几把秀。
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}


class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 头结点真好用。操了。
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode  pre = dummyNode;
        for(int i = 0; i < left - 1; i++) pre = pre.next;
        ListNode cur = pre.next;
        ListNode p  = null;
        for(int i = 0; i < right - left; i++){
            ListNode temp = cur.next;
            cur.next = p;
            p = cur;
            cur = temp;
        }
        ListNode r = cur.next;
        cur.next = p;
        pre.next.next = r;
        pre.next = cur;
        return pre == dummyNode ? cur : head;
    }
}
// 反转链表的方法。 记清楚了。 这么简单的代码，写那么复杂。
class Reeverse {
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

class Slef2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode tail = null; // 左段的尾部。
        ListNode l = head;
        int count = 1;
        while(count < left){
            if(count == left -1)tail = l; // 左段的尾部。
            count++;
            l = l.next;
        }
        ListNode tail2 = l;
        count = 0;
        ListNode r = l.next;
        while(count < right - left){
            ListNode t = r.next;
            r.next = l;
            l = r;
            r = t;
            count++;
        }
        if(tail == null){
            head = l;
        }else{
            tail.next = l;
        }
        tail2.next = r;
        return head;
    }

}

// 这个方法最好了。 运用头插法的思想。 
class Ans2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 头结点 简直灵魂。
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        int step = 0;
        while (step < m - 1) {
            g = g.next; p = p.next;
            step ++;
        }
        // p是第一个需要反转的结点。g 是P前边一个结点，代表哨兵。 
        // 以例子为例： 1，2，3，4，5  反转2，3，4    4-2 = 2， 删除2个结点，头插法到g之后即可。
        for (int i = 0; i < n - m; i++) {  
            ListNode removed = p.next; // 引用p后边一个结点
            p.next = p.next.next;   // 删除removed。

            removed.next = g.next; // 头插法插入到g之后。
            g.next = removed;
        }

        return dummyHead.next;
    }
}