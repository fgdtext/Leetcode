package JZ.JZ59;



import java.util.*;


class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}


 class Solution {
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > ans = new ArrayList<ArrayList<Integer> >();
        if(pRoot == null) return ans;
        LinkedList<TreeNode> que = new LinkedList<TreeNode>();
        //起初队列：  root,null
        que.addLast(pRoot);
        que.addLast(null);
       
        ArrayList<Integer> ls = new ArrayList<Integer>();

        // 首先从左侧出队。
        boolean flag = true;

        while(!que.isEmpty()){
            TreeNode top;
            if(flag) top = que.getFirst(); // 左侧出队
            else top = que.getLast();       // 右侧出队
            // 此处只是获取队头，没有出队
            if(top == null){                // null 永远不出队列
                flag = !flag;               // 切换方向
                ans.add(ls);
                ls = new ArrayList<Integer>();
                if(que.size() == 1) break; // 若只有一个 null，说明遍历完了。
                continue;                
            }
            ls.add(top.val);
            // 左侧出队，则右侧入队。 先左子，再右子。
            if(flag) {
                que.removeFirst();
                if(top.left != null) que.addLast(top.left);
                if(top.right != null) que.addLast(top.right);
            } // 相反。
            else {
                que.removeLast();
                if(top.right != null) que.addFirst(top.right);
                if(top.left != null) que.addFirst(top.left);
            }            
            // 构成了双端栈。从一侧出栈，在另一侧入栈。                                    
        }
        return ans;
    }
}