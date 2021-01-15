package Q19;

import java.util.*;
public class Self {
    
}
/*

可以递归来写，每层。回后边有多少元素。在特定层满足后边有n-1个元素，就可以删除该


*/

class ListNode {

      int val;
      ListNode next;
      ListNode(int x) { val = x; }

}
 // 递归写法
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        int flag = getnumaft(head, n);
        if(flag == -1) return head; // 若出现-1标志则说明,在删除位置在 链表的中间和末尾。
        return head.next;   // 没有出现-1 ，则说明 要删除的是倒数第 len 个，即头结点。
    }
    // 返回当前结点开始后有多少结点
    public int getnumaft(ListNode cur,int n){
        if(cur == null) return 0;
        // 获取 cur.next 开始的， 后边有多少结点。
        int curaft = getnumaft(cur.next,n); // 获取cur之后有多少结点。
        if(curaft == n) {
            cur.next = cur.next.next;
            return -1;   // 返回 -1 代表找到了要删除位置的前驱。
        }
        if(curaft == -1) return -1; // 向上传递 -1 标志
        return curaft+1;
    }
}


/*
滑动窗口写法   ------  滑动窗口是 两个指针向同一个方向滑动。
双指针------    一般来说，是从左右向中间滑动。

left  right   : left 指向待删除结点的前驱。left初始为null,第一次滑动时变为head.right初试为head,初试距离为1.
第一步： 让 right一直向右走，直到 right 距离 left为 n时，right移动一个，left就移动一个，直到right为末尾结点(不能指向空。)。



*/


class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = null;
        ListNode right = head;
        int num = 1;
        // 保证right不会为 null.right 会停在链表末尾
        while(right.next != null){
            right = right.next;
            if(num == n){
                if(left == null) left = head;
                else left = left.next;
            }else{
                num++;
            }
        }
        if(left == null){
            return head.next;
        }
        left.next = left.next.next;
        return head;
    }
}



