package Q99;


/*
不再经过保存去 找两个下标，而是在中序递归中保存 bef，aft指针，去查找。
bef 要不停的更改指向。和self1 不同的是不再保存结点的引用，在递归过程中去查找。



提交成功： 时间居然不如 self(13ms) 。不知道为什么。  148ms 的提交是在168ms的基础上进行了剪枝。
原因： 一定要把输出语句注释掉，非常耗费时间。 和模拟指针无关。这里的找的过程只慢了一点点。 

分析： 中序有序性质，要遍历整课树去查找。 
时间 ： 4ms(最快2ms) 使用类属性的递归(ans2,2ms)  test(3ms)(不适用模拟指针，答案的查找过程)  
       4ms的原因： 使用模拟指针慢了1ms, 自己的查找过程慢了1ms.



*/
//  模拟指针。
class NodePtr{
    TreeNode ptr;
    NodePtr(TreeNode r){this.ptr = r;}
}
class IntPtr{
    int val;
    IntPtr(int v){this.val = v;}
}

class Solution2 {

    public void recoverTree(TreeNode root) {
        // 指向null
        NodePtr bef = new NodePtr(null);
        NodePtr out = new NodePtr(null);
        NodePtr in = new NodePtr(null);
        IntPtr count = new IntPtr(0);
        dfs(root, bef, out, in,count);
        int t = out.ptr.val;
        out.ptr.val = in.ptr.val;
        in.ptr.val = t;
    }
  //  bef 前序指针， out ： 第一个输出边错误结点。in ：第一个输入边错误结点。
    public void dfs(TreeNode root, NodePtr bef,NodePtr out, NodePtr in,IntPtr count){
       // if(out.ptr != null && in.ptr != null) return ;
        if(count.val == 2) return;
        if(root == null) return;
        dfs(root.left, bef, out, in,count);

        // 中序操作。 测试当前结点。// 耗费时间了。
        if(bef.ptr != null && bef.ptr.val > root.val){
            boolean flag = false;
            if(count.val == 0){
                out.ptr = bef.ptr; in.ptr = root;flag = true;
            }
            if(count.val == 1){ 
                in.ptr = root; flag = true;
            }
            if(flag) count.val++;
        }
        bef.ptr = root;
        dfs(root.right, bef, out, in,count);    
    }
}

