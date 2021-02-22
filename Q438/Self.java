package Q438;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Self {
    public static void main(String[] args) {
        // String s = "abacbabc";
        // String p = "abc";
        // Solution so = new Solution();
        // so.findAnagrams(s, p);
    }
}
/*
等价于  Q567

*/
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int len2 = s.length();
        int len1 = p.length();
        char[] arr2 = s.toCharArray();
        char[] arr1 = p.toCharArray();
        int[] freq = new int[26];
        int[] f = new int[26];
        int count = 0;
        for(int i = 0; i < len1; i++){
            freq[arr1[i]-'a']++;
            if(freq[arr1[i]-'a'] == 1) count++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int left = 0, right = 0;
        int curcount = 0;
        while(right < len2){
            int rind = arr2[right]-'a';
            f[rind]++;
            if(f[rind] == freq[rind]) curcount++;
            right++;
            if(right - left > len1){
                int lind = arr2[left]-'a';
                if(f[lind] == freq[lind]) curcount--;
                f[lind]--;
                left++;
            }
            if(curcount == count) {
                ans.add(left);
            }
        }
        return ans;
    }
}










class Self2 {
    public List<Integer> findAnagrams(String s, String p) {
        if(p.length() > s.length()) return new ArrayList<>();
        int[] frep = new int[26];
        HashSet<Character> allset = new HashSet<>(); // 全集

        int[] fres = new int[26];
        HashSet<Character> partset = new HashSet<>(); // 已经收集够的字符

        for(int i = 0; i < p.length(); i++){
            frep[p.charAt(i)-'a']++;
            allset.add(p.charAt(i));
            fres[s.charAt(i)-'a']++;
        }
        for(int i = 0; i < 26; i++){
          if(frep[i] != 0 && frep[i] <= fres[i]) partset.add(Character.valueOf((char)(i+'a')));
        }
        List<Integer> ans = new ArrayList<>();
        if(partset.size() == allset.size()) ans.add(0);
        for(int i = p.length(); i < s.length(); i++){
            fres[s.charAt(i)-'a']++;
            fres[s.charAt(i-p.length())-'a']--;
            if(fres[s.charAt(i)-'a'] == frep[s.charAt(i)-'a']) 
                partset.add(s.charAt(i));
            if(fres[s.charAt(i-p.length())-'a'] < frep[s.charAt(i-p.length())-'a']) 
                partset.remove(s.charAt(i-p.length()));
            if(partset.size() == allset.size()){
                ans.add(i-p.length()+1);
            }
        }
        return ans;
    }
}

/*
就是right是一定会移动，left发现超标（tempCounts[curR] > counts[curR]）就移动

本质：  若窗口划过的每一个字符不超标，就可以继续扩大，否则就通过left缩小窗口

窗口内，26个字符频率永远不超标，所以当right - left == p.length, 那么窗口内就一定是p的异构词

维持26个字符频率永远不超标 ： 通过right,left维持不超标，发现超标就left缩小窗口。

可以维持的原理 : 每次超标都是因为现在right指向的字符引起的，tempCounts[curR] > counts[curR]
所以只要有tempCounts[curR] > counts[curR]就要left缩小窗口
*/
class Ans {
    public List<Integer> findAnagrams(String s, String p) {

       // 先对目标串p每个字符进行字符计数，统计出每个字符的出现次数
       int pLength = p.length();
       int sLength = s.length();

       int[] counts = new int[26];
       for(int i = 0; i < pLength; i++){
            counts[p.charAt(i) - 'a']++;
       }

       ArrayList<Integer> res = new ArrayList<>();  // 存储结果的结果集

        int[] tempCounts = new int[26]; // 记录窗口内每种字符的出现次数
        int left = 0, right = 0;
        while(right < sLength){
            int curR = s.charAt(right) - 'a';
            tempCounts[curR]++;        // curR字符的出现次数加一
            right++;    // 新增一个字符后，窗口右指针右移一位
            while(tempCounts[curR] > counts[curR]){ // 不断缩小窗口大小，直到把超标字符移出去一个，使得不超标
                tempCounts[s.charAt(left) - 'a']--;
                left++;     // 移走一个字符后窗口左指针右移一位
            }
            if(right - left == pLength){
                res.add(left);
            }
        }
        return res;
    }
}
