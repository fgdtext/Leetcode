package Q20;

import java.util.HashMap;
import java.util.LinkedList;

/*
括号验证是否匹配。



*/

class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        LinkedList<Character> queue = new LinkedList<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(')', '(');
        map.put(']', '[');

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(queue.getFirst().equals(map.get(c))){
                 queue.removeFirst();
                 continue;
            }
            queue.addFirst(c);

        }
        return queue.isEmpty();
    }
}