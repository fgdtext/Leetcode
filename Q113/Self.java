package Q113;
import java.util.*;



   class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 

  // error 的 写法  
  /*
  原因  假设来到一个叶子结点。 此时不会去加入到ans, 而是 来到他的左右子结点（空结点）再加入ans,
  这样会导致同一个路径被两次加入到ans.
  还有一点 ：  因为  元素的 值 可能是 负数，
  所以这里不能用这种方式剪枝。
  */
class Solution2 {
    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new ArrayList();
        list = new ArrayList();
        dfs(root, 0, sum);
        return ans;
    }
    // 注意剪枝。
    public void dfs(TreeNode root,int cursum,int sum){
        if(root == null) {
            if(cursum == sum){
                List<Integer> path = new ArrayList(list);
                ans.add(path);
            }
            return;
        }
        list.add(root.val);
        dfs(root.left, cursum + root.val, sum);
        dfs(root.right, cursum + root.val, sum);
        list.remove(list.size() - 1);
    }
}

//  ok : 
class Solution {
    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ans = new ArrayList();
        list = new ArrayList();
        dfs(root, 0, sum);
        return ans;
    }
    // 注意剪枝。
    public void dfs(TreeNode root,int cursum,int sum){
        if(root == null) return;
        if(root.left == null && root.right == null) {
            if(cursum + root.val == sum){
                List<Integer> path = new ArrayList(list);
                path.add(root.val);
                ans.add(path);
            }
            return;
        }
        list.add(root.val);
        dfs(root.left, cursum + root.val, sum);
        dfs(root.right, cursum + root.val, sum);
        list.remove(list.size() - 1);
    }
}