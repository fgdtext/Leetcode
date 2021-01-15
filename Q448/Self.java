package Q448;

import java.util.*;

public class Self {
    public static void main(String[] args) {
        int[] t = {10,2,5,10,9,1,1,4,3,7};
        Solution so = new Solution();
        so.findDisappearedNumbers(t);
    }
}

class Self2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        boolean flag = false;
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == i) continue;
            k = nums[i];
            /*
                定义: 把k归位

            */
            if(k == nums.length){
                flag = true;
                continue;
            }
            while(nums[k] != k){
                int temp = nums[k];
                nums[k] = k;
                k = temp;
                if(k == nums.length){
                    flag = true;
                    break;
                }
            }  
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != i)
            ans.add(i);
        }
        if(!flag) ans.add(nums.length);
        return ans;
    }
}
/*
代码更简短，但是时间效率仍然是 89.57%

*/
class Self3 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == i+1) continue;
            k = nums[i];
            /*
                定义: 把k归位到nums[k-1];

            */
            while(nums[k-1] != k){
                int temp = nums[k-1];
                nums[k-1] = k;
                k = temp;
            }  
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1)
                ans.add(i+1);
        }
        return ans;
    }
}

/*
之前是元素归位法，发现k，则将k归位到k-1的位置。
这样做的结果就是，归位时会覆盖其他的元素。我们选择不归位，发现K, 则把nums[k-1]变为相反数。
代码更简洁
*/
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if(nums.length == 0) return new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int k = Math.abs(nums[i]);
            if(nums[k-1] > 0) nums[k-1] = -nums[k-1];
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0)
                ans.add(i+1);
        }
        return ans;
    }
}