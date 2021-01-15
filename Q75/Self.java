package Q75;

public class Self {
    
}
/*
应该是双指针，进行交换得到 ans.

难点是  i 什么时候 ++。  什么时候保持 不变。 

*/


class Solution {
    public void sortColors(int[] nums) {
        if(nums.length <= 1) return;
        int l0 = 0, r2 = nums.length - 1;
        int i = 0;
        while(i <= r2 && l0 < nums.length && r2 >= 0){
            //  遇到  1 则 直接 滑 过去。
            if(nums[i] != 0 && nums[i] != 2){
                i++; continue;  // 理应 ++ 
            }
            //  遇到 0  则 和 L 交换。  i++. 不管交换之后 什么结果， 都要 i++
            //  当 i != L 时， L位置只可能是 1，不可能是 0.否则i在经过时，就已经将这个0归位。
            // 除非 i ==L， 此时 L的位置 可能是 0.
            if(nums[i] == 0){               
                nums[i] = nums[l0]; nums[l0] = 0;
                i++;  
                l0++; 
                // 遇到 2 则 和 r 交换， 若交换后， i 的位置不是 0，也不是 2 ， 则 i++. 
                // 由于 位置 R，可能是 0,2所以，交换过之后， i 位置任然可能是 0,2
            }else if(nums[i] == 2){
                nums[i] = nums[r2]; nums[r2] = 2;
                if(nums[i] != 2 && nums[i] != 0) i++;   // 特殊情况。
                r2--;
            }
        }
    }
}