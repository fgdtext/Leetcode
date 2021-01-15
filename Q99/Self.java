package Q99;

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

根据二分查找树的中序有序性。  用list保存中序结果。 然后在序列中找 两个值
    1. 找两个不同的下标(ouerror,inerror)，其值恰好大于后边一个元素。
    2. 若只能找到一个值大于后边一个元素的下标outerror，则第二个下标为 inerror = outerror + 1;
    交换这两个下标的对应结点的值。


    已经成功提交。 但是效率低， 切空间占用大。  时间： 13ms 原因： 中间有printf语句没注释掉， 注释掉以后:3ms。和答案一样。

    优化： 自己写的查找过程(for 找ouerror ，inerror)太慢了，注释掉，改为答案的找法。优化到3ms. 
*/



class Solution {

    public void recoverTree(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();

        dfs(root,list);
        int len = list.size();
        if(len <= 1) return;
        int outerror = -1, inerror = -1;
        //************ 
        //int count = 0;
        // for(int i = 0; i < len; i++){

        //     if(count == 2) break;
        //     if(i != len - 1 && list.get(i).val > list.get(i+1).val){
        //         boolean flag = false;
        //         if(count == 0) {
        //             outerror = i; flag = true;  
        //         }
        //         if(count == 1){ 
        //             inerror = i + 1; flag = true;
        //         }
        //         if(flag) ++count;
        //         System.out.println(i);
        //     }
        // }
        // if(count < 2) inerror = outerror + 1;
        //************************* 
        for (int i = 0; i < len - 1; ++i) {
            if (list.get(i + 1).val < list.get(i).val) {
                inerror = i + 1;
                if (outerror == -1) {
                    outerror = i;
                } else {
                    break;
                }
            }
        }
        int t = list.get(outerror).val;
        list.get(outerror).val = list.get(inerror).val;
        list.get(inerror).val = t;
    }

    public void dfs(TreeNode root, List<TreeNode> list){
        if(root == null) return;
        dfs(root.left, list);
        list.add(root);
        dfs(root.right,list);
    }


}

