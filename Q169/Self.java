package Q169;

import java.util.HashMap;

public class Self {
    
}

/*
left : 区间左端点
curmax = 个数最多元素的值
curmaxcount : 当前区间最多的元素个数 - 非最多元素个数
curmaxcount > 0 : 说明 curmax 在该区间占一半以上。
curmaxcount = 0 ： 说明 curmax 在该区间占恰好一半
curmaxcount < 0 : 说明curmax在该区间 已经少于区间长度的一半

投票法，众数个数至少比非众数多一，把COUNT加减当作一个众数抵消掉一个非众数，剩下的一定是众数

摩尔投票法 ：
如果候选人不是maj 则 maj,会和其他非候选人一起反对 会反对候选人,所以候选人一定会下台(maj==0时发生换届选举)
如果候选人是maj , 则maj 会支持自己，其他候选人会反对，同样因为maj 票数超过一半，所以maj 一定会成功当选

我就说我写过这个题。 


我这个 为啥 比 答案好那么多。
*/
class Solution {
    public int majorityElement(int[] nums) {
        int curmax = nums[0];
        int curmaxcount = 1;
        int right = 1;
        while(right < nums.length){
            if(nums[right] == curmax){
                curmaxcount++;
            }else{
                curmaxcount--;
            }
            if(curmaxcount == 0){
                //  curmaxcount = 0 说明 curmax 在该区间占恰好一半 那么right 之前的部分就可以舍弃，
                // 并且后半部分的最大个数仍会 大于一半。
                curmax = nums[++right]; // 从当前right 的下一个位置开始从新计数。
                curmaxcount = 1;
            }
            right++;
        }
        return curmax;
    }
}


/*

答案更精简。

*/
class Ans {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}