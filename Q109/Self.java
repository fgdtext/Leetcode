package Q109;

import java.util.*;

class ListNode {
      int val;
     ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
 

     class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

  // 先转数组
class Solution {
    List<Integer> list;
    public TreeNode sortedListToBST(ListNode head) {
        list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        TreeNode root = new TreeNode();
        if(list.size() == 0) return null;
        toTree(root, 0, list.size() - 1);// 闭区间
        return root;
    }
    public void toTree(TreeNode cur, int l, int r){
        // 只有一个结点一定放 左侧
        if(r < l) return;
        if(l == r){
            cur.val = list.get(l);
            return;
        }
        int num = r - l + 1;
        // 奇数个 ，二分，中间结点

        int mid = l + (r - l)/2;
        if(num % 2 == 0)  mid++;
        cur.val = list.get(mid);
        // 构建子树
        if(mid-1 >= l){
            cur.left = new TreeNode();
            toTree(cur.left, l, mid-1);
        }
        if(mid + 1 <= r){
            cur.right = new TreeNode();
            toTree(cur.right, mid+1, r); 
        }      
    }

}

// 先转数组
class Solution2 {
    List<Integer> list;
    public TreeNode sortedListToBST(ListNode head) {
        list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        TreeNode root;
        if(list.size() == 0) return null;
        root = toTree(0, list.size() - 1);// 闭区间
        return root;
    }
    public TreeNode toTree(int l, int r){
        // 只有一个结点一定放 左侧
        if(r < l) return null;
        if(l == r){
            return new TreeNode(list.get(l));
        }
        int num = r - l + 1;
        // 奇数个 ，二分，中间结点
        int mid = l + (r - l)/2;
        if(num % 2 == 0)  mid++;
        TreeNode cur = new TreeNode(list.get(mid));
        cur.left = toTree(l, mid - 1);
        cur.right = toTree(mid + 1, r);
        return cur;
    }

}


/*
快慢指针： 在链表中快速找到 链表的中间。 slow : 最终指向中间， fast ： 最终指向末尾。 pre 指向 slow 的前一个。

速度比较： 上边两个是先转为数组，事件o(n)，则查找中间点为o(1).
           快慢指针则直接查找。事件复杂度应该是 o(nlgn)啊。 这里为什么会更快？？？？
这里不推荐， 快慢指针。 

最好使用， 中序遍历优化。




*/


class Solution3 {
    public TreeNode sortedListToBST(ListNode head) {
        //边界条件的判断
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        //这里通过快慢指针找到链表的中间结点slow，pre就是中间
        //结点slow的前一个结点
        //  slow 走的路程总是 fast的一半，当fast走完全程时，slow 正好走在中间。
        ListNode slow = head, fast = head, pre = null;
        // fast 每次走两步，所以 fast.next不能为空
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //链表断开为两部分，一部分是node的左子节点，一部分是node
        //的右子节点
        pre.next = null;
        //node就是当前节点
        TreeNode node = new TreeNode(slow.val);
        //从head节点到pre节点是node左子树的节点
        node.left = sortedListToBST(head);
        //从slow.next到链表的末尾是node的右子树的结点
        node.right = sortedListToBST(slow.next);
        return node;
    }
}



/*

分治 + 中序优化。 

中序：  由于 二叉平衡树是一颗 二叉 排序树， 所以该树的中序遍历恰好是有序的，也恰好是 链表的顺序。所以可以中序建立平衡树
       只要每次使用全局 的 globalHead 指针，标记  当前  输入  就好。 globalHead 顺序递增就好。
*/


class Solution4 {
    ListNode globalHead;

    public TreeNode sortedListToBST(ListNode head) {
        globalHead = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }

    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }

    public TreeNode buildTree(int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right + 1) / 2;
        TreeNode root = new TreeNode();


        // 递归建立左子树。
        root.left = buildTree(left, mid - 1);

        // 中序建立 当前结点
        root.val = globalHead.val;
        globalHead = globalHead.next;


        root.right = buildTree(mid + 1, right);
        return root;
    }
}
