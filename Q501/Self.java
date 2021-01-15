package Q501;

import java.util.ArrayList;


class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
/*

利用中序有序特性。 每次保留最大的几个元素在数组中


写代码要脑子好的时候写。要多考虑边界 以及 特殊情况。
以后不要依赖与 提交系统给的报错信息。

*/



class Solution {
    int preval;
    int precount;
    int maxcount;
    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];
        ArrayList<Integer> list = new ArrayList<>();
        preval = - (1 << 31);
        precount = 0;
        maxcount = -1;
        dfs(root, list);
        if(precount > maxcount){
            list.clear();
            list.add(preval);
        }else if (precount == maxcount){
            list.add(preval);
        }
        int[] ans = new int[list.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void dfs(TreeNode root, ArrayList<Integer> list){
        if(root == null) return;


        dfs(root.left, list);
        
        if(preval == -(1 << 31) ||  root.val == preval){
            if(preval == -(1 << 31)) list.add(root.val);
            precount++;
        }else{
            if(precount > maxcount){
                maxcount = precount;
                list.clear();
                list.add(preval);
            }else if (precount == maxcount){
                list.add(preval);
            }
            precount = 1;
        }
        preval = root.val;
        dfs(root.right, list);
    }
}

