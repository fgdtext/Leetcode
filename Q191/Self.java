package Q191;

public class Self {
    
}
/*
不断把数字最后一个 1 反转
将 n 和 n-1做与运算会将最低位的 1 变成 0
*/

class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for(; n!=0; ++count){
            n &= n-1;
        }
        return count;
    }
}