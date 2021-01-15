package Q136;

public class Self {
    
}
/*
只出现一次的数


亦或 运算  ：   对于 数组题， 又开辟了 一个 新的 方向。  位运算的 性质 是一个 非常 难以想到的， 关键的 点。

*/

class Solution {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
