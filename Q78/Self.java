package Q78;
import java.util.*;
public class Self {
    
}


/*class
找到所有子集


二进制法。 
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for(int i = 0; i < (1 << len); i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < len; j++){
                if((i & (1 << j)) != 0){
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}




