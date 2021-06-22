package Q287;

public class Self {
    
}

/*
不能更改原数组（假设数组是只读的）。  违背了。 草。 妈的。
桶归位，移动了，所以不符合数组只读的条件。
不好意思没看到。

*/
class Self1 {
    public int findDuplicate(int[] nums) {
        int k = 0;
        while(k < nums.length){
            if(nums[k] != k){
                if(nums[nums[k]] == nums[k]) return nums[k];
                int temp = nums[nums[k]];
                nums[nums[k]] = nums[k];
                nums[k] = temp;
            }  
        }
        return -1;
    }
}
/*
利用nums[0] != 0  永远成立。


*/
class SELF3 {
    public int findDuplicate(int[] nums) {
       while(nums[0] != 0){
                if(nums[nums[0]] == nums[0]) return nums[0];
                int temp = nums[nums[0]];
                nums[nums[0]] = nums[0];
                nums[0] = temp;
        }
        return -1;
    }
}


/*class
和 Q142 一摸一样。 那个证明一定要会啊。


*/


class Solution {
    public int findDuplicate(int[] nums) {
        if(nums.length == 2) return nums[0];
        int fast = 0;
        int slow = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(nums[fast] != nums[slow]){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
}