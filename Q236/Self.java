package Q236;

public class Self {
    
}


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 /*

后序遍历， 找到pq 分别在 左右 子树

ok: 8 ms
, 在所有 Java 提交中击败了
58.24%
的用户
不是很好。 要优化  :  
 if(ans != null) return 0;  每次递归后，都要判断是否已经找到。

7 ms
, 在所有 Java 提交中击败了
99.87%
的用户




 */
class Solution {
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }
    // 返回 0 ： 没有pq, 返回 1： 有 p, 返回 2 : 有q
    public int dfs(TreeNode cur, TreeNode p, TreeNode q){
        if(ans != null) return 0;  // 已经找到了。
        if(cur == null){
            return 0;
        }
        int left = dfs(cur.left, p, q);
        if(ans != null) return 0;
        int right = dfs(cur.right, p, q);
        if(ans != null) return 0;
        if(left == 0 && right == 0) {
            if(cur == p) return 1;
            if(cur == q) return 2;
            return 0;
        }
        if(left == 1 && right == 2 || left == 2 && right == 1){
            ans = cur;
            return 0;
        }
        if(left == 0){
            if(right == 1){
                if(cur == q){
                    ans = cur;
                    return 0;
                }
                return 1;
            }
            if(right == 2){
                if(cur == p){
                    ans = cur;
                    return 0;
                }
                return 2;
            }
        }
        if(right == 0){
            if(left == 1){
                if(cur == q){
                    ans = cur;
                    return 0;
                }
                return 1;
            }
            if(left == 2){
                if(cur == p){
                    ans = cur;
                    return 0;
                }
                return 2;
            }
        }
        return 0;
    }
}



/*class

Ans :  更好 
代码 更简洁
*/

class Ans {

    private TreeNode ans;
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(ans != null) return false;
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        if(ans != null) return false;  // 剪枝
        boolean rson = dfs(root.right, p, q);
        if(ans != null) return false;
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        } 
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
}
