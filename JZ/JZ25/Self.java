package JZ.JZ25;

public class Self {
    
}

 class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

 class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null){
            return null;
        }
        RandomListNode node = pHead;
        while (node != null) {
            RandomListNode copy = new RandomListNode(node.label);
            copy.next = node.next;
            node.next = copy;
            node = copy.next;
        }
        node = pHead;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }
        node=pHead;
        RandomListNode root=pHead.next;
        RandomListNode tmp=root;
        while(node!=null){
            node.next=tmp.next;
            tmp.next=node.next==null?null:node.next.next;
            node=node.next;
            tmp=tmp.next;
        }
        return root;
    }
}