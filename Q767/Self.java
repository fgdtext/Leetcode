package Q767;
import java.util.*;

public class Self {
    
}
/*
基于最大堆的贪心算法
每次在堆中挑选两个数量最多的字符追加到ansStr上。然后对这两个字符的次数-1/


*/
class Ans {
    public String reorganizeString(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            public int compare(Character letter1, Character letter2) {
                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();
    }
}



/*class
基于计数的贪心算法

当 nn 是奇数且出现最多的字母的出现次数是 (n+1)/2 时，
出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。
。其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。

维护偶数下标 evenIndex 和奇数下标 oddIndex，
初始值分别为 0 和 1。遍历每个字母，根据每个字母的出现次数判断字母应该放置在偶数下标还是奇数下标。

下标从0开始， 偶数下标放的元素个数 >= 奇数下标。

按照 abcd.....的顺序对每一种字符安排位置。
如果字母的出现次数大于 0 且小于或等于 n/2，且oddIndex 没有超出数组下标范围
则将字母放置在oddIndex，然后将oddIndex 的值加 2。

如果字母的出现次数大于 n/2，或 oddIndex 超出数组下标范围
则将字母放置在 evenIndex，然后将 evenIndex 的值加 2。

因为可能出现恰好频率占一半+1的字符，该字符必须占领全部偶数位置。所以奇数位置不占万，不准占用偶数位置。
若出现恰好频率占一半+1的字符，则直接安排在偶数位置。

不是 动态规划， 不是 回溯法。

算是贪心吧。 

*/

class Solution {
    public String reorganizeString(String S) {
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        char[] reorganizeArray = new char[length];
        int evenIndex = 0, oddIndex = 1;
        int halfLength = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }
            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }
        }
        return new String(reorganizeArray);
    }
}
