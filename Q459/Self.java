package Q459;


/*
Q459  :  判定一个字符串 是否 是   由重复子串构成。

1. 子串一定重头开始
2. 字串的长度一定是 总长度的 因子。


*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int totlen = s.length();
        boolean flag = false;
        for(int i = totlen/2; i >= 1; i--){
            if(totlen % i != 0) continue;
            int k = 0;
            boolean inflag = true;
            for(int j = i; j < totlen; j++){
                if(k == i) k = 0;
                if(s.charAt(j) != s.charAt(k)){
                    inflag = false;
                    break;
                }
                k++;
            }
            if(inflag) flag = true;
        }
        return flag;
    }
}