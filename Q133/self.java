package Q133;

import java.util.*;
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// bfs
class Solution {
    Node root;
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        root = new Node(node.val);
        Map<Node,Node> map = new HashMap<>();
        // 队列
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        // 标记访问
        map.put(node, root);
        while(!queue.isEmpty()){
            //    t  出队结点
            Node t = queue.remove();
            int neilen = t.neighbors.size();

            for(int i = 0; i < neilen; i++){

                // 若还未标记, 被复制点   temp 为 t 的子结点
                Node  temp = t.neighbors.get(i);
                if(!map.containsKey(temp)){
                    // 进行拷贝， 新结点
                    Node copynode = new Node(temp.val);
                    // 标记结点
                    map.put(temp, copynode);
                    // 入队 老结点
                    queue.add(temp);

                }
                map.get(t).neighbors.add(map.get(temp));
            }
        }
        return root;
    }
}

// dfs 
class Solution2 {
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}