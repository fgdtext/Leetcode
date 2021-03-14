package Q706;
import java.util.*;

public class Self {
    public static void main(String[] args) {

       
    }
}

class MyHashMap {
    private static final int BASE = 769;
    private LinkedList<int[]>[] data;
    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for(int i = 0; i < BASE; i++){
            data[i] = new LinkedList<int[]>();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<int[]> it = data[h].iterator();
        while(it.hasNext()){
            int[] t = it.next();
            if(t[0] == key){
                t[1] = value;
                return;
            }
        }
        data[h].add(new int[]{key,value});
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<int[]> it = data[h].iterator();
        while(it.hasNext()){
            int[] t = it.next();
            if(t[0] == key) return t[1];
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<int[]> it = data[h].iterator();
        while(it.hasNext()){
            int[] t = it.next();
            if(t[0] == key) it.remove();;
        }
    }
    public int hash(int x){
        return x % BASE;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */