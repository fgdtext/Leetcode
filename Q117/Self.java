package Q117;

import java.util.LinkedList;
import java.util.Queue;





class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

/*
双队列，交替存每一层结点。


本答案是不符合  空间复杂度的。

本空间是 o(n), 不是常数级别的。

*/


class Self2 {
    public Node connect(Node root) {
        if(root == null) return null;
        LinkedList<Node> stack1 = new LinkedList<>();
        LinkedList<Node> stack2 = new LinkedList<>();
        stack1.add(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            Node p = null;
            Node q = null;
            if(!stack1.isEmpty()){
                p = stack1.getFirst();
                stack1.removeFirst();
                if(p.left != null) stack2.addLast(p.left);
                if(p.right != null) stack2.addLast(p.right);
                if(stack1.isEmpty()) continue;
                q = stack1.getFirst();
                stack1.removeFirst();
                if(q.left != null) stack2.addLast(q.left);
                if(q.right != null) stack2.addLast(q.right);
                p.next = q;
                while(!stack1.isEmpty()){
                    p = q;
                    q = stack1.getFirst();
                    stack1.removeFirst();
                    if(q.left != null) stack2.addLast(q.left);
                    if(q.right != null) stack2.addLast(q.right);
                    p.next = q;
                }
            }
            if(!stack2.isEmpty()){
                p = stack2.getFirst();
                stack2.removeFirst();
                if(p.left != null) stack1.addLast(p.left);
                if(p.right != null) stack1.addLast(p.right);
                if(stack2.isEmpty()) continue;
                q = stack2.getFirst();
                stack2.removeFirst();
                if(q.left != null) stack1.addLast(q.left);
                if(q.right != null) stack1.addLast(q.right);
                p.next = q;
                while(!stack2.isEmpty()){
                    p = q;
                    q = stack2.getFirst();
                    stack2.removeFirst();
                    if(q.left != null) stack1.addLast(q.left);
                    if(q.right != null) stack1.addLast(q.right);
                    p.next = q;
                }
            }
        }
        return root;
    }
}

// 也是 非常数级别的。  本质也是 不符合 o(1)的。 
//  学习以下，  LinkedList -》 Queue  的使用。  队列 ： offer , poll 
class Ans {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            //    层次遍历 很 重要的 一个 想法，  每次 都 出队 上一层的全部结点。 上一层有多少结点就是
            //  n = queue.size();  
            //    在看看自己些的什么垃圾代码
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }
}



/*

空间 o(1)的算法。 使用上一层的next结点，  从逻辑结果来看，上一层的结构本质就是一个队列。
所以，每次我们只需要保存上一层的第一个结点，就可以获取一个  队列。

*/


class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node  LastHead = root;
        while (LastHead != null) {
            Node cur = LastHead;
            Node last = null;
            Node newHead = null;
            while(cur != null){
                if(cur.left != null){
                    if(newHead == null) newHead = cur.left;
                    if(last != null) last.next = cur.left;
                    last = cur.left;
                }
                if(cur.right != null){
                    if(newHead == null) newHead = cur.right;
                    if(last != null) last.next = cur.right;
                    last = cur.right;
                }
                cur = cur.next;
            }
            LastHead = newHead;
        }
        return root;
    }
}
/*class

在内蒙古的大草原上，一颗小草可以有多重要呢，今天我们就来讲讲小草的故事。。。。
**/