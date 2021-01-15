package Q145;
import java.util.*;

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
 

 /*
 
 双栈法。
 第二栈用来存，已经两次访问的结点。
 第二栈的作用 ：  记录 经过两次访问的结点， 第三次遇到即(cur == stack2.peek())时，即当前访问结点，出现在栈2中。
 则说明当前访问是第三次。


效率： 不是很好。应该可以优化。

 666666 :  一次 提交 成功。



 */
class Self {
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> stack1 = new LinkedList<>();
        LinkedList<TreeNode> stack2 = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null){
            stack1.addFirst(cur);
            cur = cur.left;
        }
        while(!stack1.isEmpty()){
            cur = stack1.peek();
            if(cur.left == null && cur.right == null) {
                // 叶子一定可以出栈
                stack1.pop();
                ans.add(cur.val);
                continue;
            }
            if(cur == stack2.peek()){
                // 第三次访问可以出栈。
                stack1.pop();
                stack2.pop();
                ans.add(cur.val);
                continue;
            }
            // 访问由结点(即第二次经过当前结点，去访问它的右结点)
            // 先把该结点入栈 第二个 栈
            stack2.push(cur);
            cur = cur.right;
            while(cur != null){
                stack1.addFirst(cur);
                cur = cur.left;
            }  
        }
        return ans;
    }
}


/*class
根 右 左 遍历 的反序

*/
class Ans {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> ans = new LinkedList<>();
        if (null == root) return ans;
        stack.addFirst(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            ans.addFirst(node.val);
            if (null != node.left) { // 先加入的后出栈。    所以这里按照输出顺序是先又后左的。
                stack.addFirst(node.left);
            }
            if (null != node.right) {
                stack.addFirst(node.right);
            }
        }
        return ans;
    }
}


/*class
单栈式  ： 比较难实现的方式。(考点，也是难点，双栈式，还有根右左逆序，方法都 降低了 难度。)


*/
class Ans2 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        // 栈的  标准 使用， 应该是  Deque. 
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 可以输出当前栈顶的条件
            /*
                1. 右子树为空，可以直接输出当前结点。或者2
                2. 右子树已经输出，若右子树已经输出，又后序可知 必有 root.right == prev

            */
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null; // 需要悬空root. 否则下次循环会在上边while 中重复入栈。
            } else { // 若右子树还没有输出，则把右子树入栈。
                stack.push(root);
                root = root.right; // 对右子树的 左子树 while入栈。
            }
        }
        return res;
    }
}



/*class
附加 null 结点法。

*/
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode mark = new TreeNode();
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addFirst(root);
        while(!stack.isEmpty()){
            TreeNode top = stack.getFirst();
            if(top == mark){
                stack.removeFirst();
                ans.add(stack.getFirst().val);
                stack.removeFirst();
                continue;
            }
            stack.push(mark);
            if(top.right != null) stack.addFirst(top.right);
            if(top.left != null) stack.addFirst(top.left);
        }
        return ans;
    }
}

/*class
Morris遍历 : 还不懂， 不要求掌握
*/
class Ans3 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }

    public void addPath(List<Integer> res, TreeNode node) {
        List<Integer> tmp = new ArrayList<Integer>();
        while (node != null) {
            tmp.add(node.val);
            node = node.right;
        }
        for (int i = tmp.size() - 1; i >= 0; --i) {
            res.add(tmp.get(i));
        }
    }
}

