package Q143;


public class Self {
    
}

   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 
/*
自己写的 递归 虽然很秀， 但是并不好。总归是一个方法。


*/











/*

算法  ：  类似后序 的 做法。  首先做 预处理。  将链表两等分， 若 为奇数，中间结点保存为mid.

例如 12345  分为 12  45  mid = 3
1234  分为 12  34  mid == null;
以 12345为例子。
另 cur -> 1   right -> 4  然后进行递归。 right是全局变量，随着递归right一直在改变位置。

首先 ：dfs(1) right->4 由于后序会接连递归 dfs(2) right->4 此时到达边界 连接 2->4 并且 right -> 5. 向上返回。

此时 dfs(1)中 cur->1,right->5  连接 1->5 5->2;  返回

此时有  1->5->2->4->   4 的后序没有断开，所以可能行成环。 

right_head->4   使用right_head 连接mid 或者 给 right_head置空。



这个代码写的很垃圾。    链表找中点 ， 用快慢指针，可以快一倍时间。 使用递归，花费太多空间。
*/

class Self2 {
    ListNode right;
    public void reorderList(ListNode head) {
        if(head == null) return;
        if(head.next == null) return;
        int len = 0;
        ListNode t = head;
        // 对 链表计数。
        while(t != null){
            len++;
            t = t.next;
        }
        // 令 right 走到 中间。 此时right 仍在左半个链表上
        right = head;
        for(int i = 1; i < len/2; i++){
            right = right.next;
        }

        //  如果是奇数个结点，则保存 正 中间的结点。
        ListNode mid = null;
        if(len % 2 != 0) mid = right.next;
        // 使用 t 将 左右 两侧链表 断开  // 分割为前后两个链表。
        t = right;

        // 由于 奇偶不同，走1，2两个结点，来到 右侧第一个结点。
        if(len % 2 != 0)
            right = right.next.next;
        else 
            right = right.next;
        // 保存 右侧 第一个  结点。 该结点 可能会 是 链表 尾。
        ListNode right_head = right;
        t.next = null;
        dfs(head);

        // right_head 是不考虑 正中间结点的 尾部 结点，所以在这里加上正中间结点mid
        if(len % 2 != 0){
            right_head.next = mid;
            mid.next = null;
        }else{
            right_head.next = null;
        }
    }



    public void dfs(ListNode cur){
        if(cur == null) return;
        if(cur.next == null){
            cur.next = right;
            right = right.next;
            return ;
        }     
        dfs(cur.next);
        ListNode t = right;
        right = right.next;
        t.next = cur.next;
        cur.next = t;
        return;
    }
}

/*
使用  快慢指针 找中点。   把 后半段 链表 进行 递推 反转。 

然后和前半段 链表 进行 交替 合并。  
时间o(n)  空间 o(1)
 
该方法是最好的。 

*/
class Ans {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
