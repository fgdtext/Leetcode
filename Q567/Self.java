package Q567;

public class Self{
    public static void main(String[] args) {
        
    }
}
/*

固定大小的滑动窗口  窗口内字符频率相同即可。

count == curcount  记录当前有多少个字符频率相同。

若窗口大小等于 s1.length.    且频率不为0的所有其他字符的频率相同。那么该s2的子串一定等价于 s1.
*/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[] freq = new int[26];
        int[] f = new int[26];
        int count = 0;
        for(int i = 0; i < len1; i++){
            freq[arr1[i]-'a']++;
            if(freq[arr1[i]-'a'] == 1) count++;
        }
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
            if(curcount == count) return true;
        }
        return false;
    }
}