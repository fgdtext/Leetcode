package Q138;

import java.util.HashMap;






class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
/*
  题意就是深拷贝  ： 如何进行深拷贝。
  map : 旧结点 ： 新结点； 通过旧结点找到对应的新结点。
*/

class Self2 {
    public Node copyRandomList(Node head) {
        HashMap<Node,Node> map = new HashMap<>();
        // 利用头结点可以避免head为空的情况。统一操作。
        Node nughead = new Node(-1);
        nughead.next = head;
        Node cur = nughead;
        Node newnughead = new Node(-1);
        Node newcur = newnughead;
        // 先建立起来next连接起来的直线链表。
        while(cur.next != null){
            newcur.next = new Node(cur.next.val);
            newcur = newcur.next;
            cur = cur.next;
            map.put(cur, newcur);
        }
        // 遍历旧链表，对新链表更新random指针。
        cur = nughead.next;
        newcur = newnughead.next;
        while(cur != null){
            Node key = cur.random;
            newcur.random = map.get(key);
            cur = cur.next;
            newcur = newcur.next;
        }
        return newnughead.next;
    }
}


public class Self {
    public static void main(String[] args) {
        Node t = new Node(7);
        Node head = t;
        t.next = new Node(13);
        t = t.next;
        t.next = new Node(11);
        t = t.next;
        t.next = new Node(10);
        t = t.next;
        t.next = new Node(1);
        t = head;
        t.random = null;
        t = t.next;
        t.random = head;
        t = t.next;
        t.random = t.next.next;
        t = t.next;
        t.random = head.next.next;
        t = t.next;
        t.random = head;
        t = head;
        Solution so = new Solution();
        so.copyRandomList(head);
        
    }
}

class Solution{
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;

        // 7,13,11,10,1   ->  7，7，13，13，11，11，10，10，1，1 
        // 即每个原结点后边插入一个相同的结点。排在前边的为原结点，后边的是新结点。
        while(cur != null){
            Node t = new Node(cur.val);
            t.next = cur.next;
            cur.next = t;
            cur = t.next;
        }

        // 对于每一个原结点，找到他的random ，那么其对应新结点的random一定在原结点的random的下一个位置。
        // 完成random的拷贝。
        cur = head;
        while(cur != null){
            if(cur.random == null) cur.next.random = null;
            else cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 分离 链表。 单双位分离为两个链表。
        cur = head;
        Node newhead = head.next;
        Node ne = newhead;
        while(cur != null) {
            cur.next = ne.next;
            cur = cur.next;
            if(cur == null) break;
            ne.next = cur.next;
            ne = ne.next;
        }
        return newhead;
    }
}