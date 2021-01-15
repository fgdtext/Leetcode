package Q99;


/*
不再经过保存去 找两个下标，而是在中序递归中保存 bef，aft指针，去查找。
bef 要不停的更改指向。和self1 不同的是不再保存结点的引用，在递归过程中去查找。



提交成功： 时间居然不如 self(13ms) 。不知道为什么。  148ms 的提交是在168ms的基础上进行了剪枝。
原因： 不要轻易去尝试 new新的对象， 会非常耗费时间

分析： 中序有序性质，要遍历整课树去查找。 


*/
//  模拟指针。


class Solution3 {

    public void recoverTree(TreeNode root) {
        // 指向null
        NodePtr bef = new NodePtr(null);
        NodePtr out = new NodePtr(null);
        NodePtr in = new NodePtr(null);
        dfs(root, bef, out, in);
        int t = out.ptr.val;
        out.ptr.val = in.ptr.val;
        in.ptr.val = t;
    }
  //  bef 前序指针， out ： 第一个输出边错误结点。in ：第一个输入边错误结点。
    public void dfs(TreeNode root, NodePtr bef,NodePtr out, NodePtr in){
       // if(out.ptr != null && in.ptr != null) return ;
       
        if(root == null) return;
        //if(out.ptr != null && in.ptr != null) return;
        // 不能加 因为会导致in 无法更新到最后。
        dfs(root.left, bef, out, in);
        if(bef.ptr == null) bef.ptr = root;
        else{
            if(bef.ptr.val > root.val){
                in.ptr = root;
                if(out.ptr == null) out.ptr = bef.ptr;
            }
            bef.ptr = root;
        }
        dfs(root.right, bef, out, in);    
    }
}

