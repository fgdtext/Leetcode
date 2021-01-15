package Q142;

import java.util.HashSet;

public class Selfg {
    
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
效率太差。 
*/
 class Self1 {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while(cur != null){
            if(set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }
}


/*class

进阶：
你是否可以不用额外空间解决此题？

快慢指针：  每轮 slow 走一步， fast走两步，

链表分为 a部分：直线部分，b部分：环状部分。

f； 表示，fast走过的步数， s : 表示 slow 走过的步数。

这样总有  f = 2*s

由于 fast 每轮 都要 多走一步，所以，必然会和  slow相遇(在环上)。
相遇时必有 ：  f =  s + nb   又 f = 2*s  (假设fast 比 slow 多走了n圈。)

则有  f = 2nb. s = nb. //  a 不同，则n不同， n是整数。

当指针走到 环形起始点的时候， 总有 s = a + kb。 而当相遇的时候 s =  nb. 则例多转或者少转是一样的。
所以 当相遇时， s再走 a 步， 正好到达 起始点。

由于 a 是未知数。 此时，把fast初始化到 head .  head 到起始点也是a步。

此时令：  fast 和 slow 每轮走一步。 则一定会在 起始点相遇。


通过 哈希表来 实现 是 非常简单的。  链表的快慢指针是非常好的一个知识点。

*/
 class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && slow != null){
            fast = fast.next;
            // 走空 则 说明没有环。
            if(fast != null) fast = fast.next;
            else return null;
            slow = slow.next;
            // 第一次相遇
            if(fast == slow) break;
        }
        if(fast == null) return null;
        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}