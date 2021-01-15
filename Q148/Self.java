package Q148;

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
能想到的就是 二路 归并排序。 链表中1-1合并 2-2合并  4-4合并。直到合并完整。

难处理的是，链表尾部边界。 可能最后取不到 完整的 2^n个。


要求  ：在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序


代码能力 太差了了。  自底向上 的 递归合并 思路 是对的。 但是 写不出来啊。
*/



/*

1. 链表的题 ：  显式阶段，手动重连， 避免 莫名其妙的 成 环。
2. 善用 空头节点。
3. 分段考虑，处理一段，连接一段。



*/

class Solution {
    public ListNode cut(ListNode head, int n) {
        while(head != null && n > 1) {
            head = head.next;
            n--;
        }
        if (head == null) return null;
        ListNode ret = head.next;
        head.next = null;  //  切断了。 左右两条链切断。
        return ret;  // 返回 右侧 链 的  第一个。
    }
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), p = dummy;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode p = head;
        while(p != null) {
            len++;
            p = p.next;
        }
        // 空的 头节点。。 
        ListNode dummy = new ListNode();
        dummy.next = head;
        for (int i = 1; i < len; i *= 2) {
            ListNode cur = dummy.next;
            ListNode tail = dummy;
            while(cur != null) {
                ListNode left = cur;
                ListNode right = cut(left, i);
                cur = cut(right, i);
                tail.next = merge(left, right);
                while(tail.next != null) tail = tail.next;
            }
        }
        return dummy.next;
    }
}
