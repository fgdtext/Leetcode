package Q461;

public class Self {
    
}
/*
通过对比每一位。

*/
class Self2 {
    public int hammingDistance(int x, int y) {
        int ans = 0;
        for(int i = 0; i < 31; i++){
            if(  ( ( (x&(1<<i)) ^ ((1<<i)&y) ) != 0 )) ans++;
        }
        return ans;
    }
}
/*

通过向左移位，只对比最低位。
每次都看奇偶性是否一致

*/

class Solution {
    public int hammingDistance(int x, int y) {
        int ans = 0;
        for(int i = 0; i < 31; i++){
            if((x&1) != (y&1)) ans++;
            x = x>>1;
            y = y>>1;
        }
        return ans;
    }
}