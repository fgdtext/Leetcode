package Q208;


public class Self {
    
}
/*  
删除操作  相比 插入，查找，前缀查找操作，难一些。

*/
class Trie {
    class Node{   
        boolean exist;
        Node[] next = new Node[26]; // 使用链表，表示。
    }
    /** Initialize your data structure here. */
    Node root;
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++){
            if(cur.next[word.charAt(i)-'a'] == null){
                cur.next[word.charAt(i)-'a'] = new Node();
            }
            cur = cur.next[word.charAt(i)-'a'];
        }
        cur.exist = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++){
            if(cur.next[word.charAt(i)-'a'] == null){
                return false;
            }
            cur = cur.next[word.charAt(i)-'a'];
        }
        return cur.exist;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for(int i = 0; i < prefix.length(); i++){
            if(cur.next[prefix.charAt(i)-'a'] == null){
                return false;
            }
            cur = cur.next[prefix.charAt(i)-'a'];
        }
        return true;
    }
}
