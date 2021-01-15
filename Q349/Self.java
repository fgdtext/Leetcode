package Q349;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;



public class Self {
    
}


class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for(int a : nums1){
            set.add(a);
        }
        ArrayList<Integer> ans_list = new ArrayList<>();
        for(int e : nums2){
            if(set.contains(e)){
                ans_list.add(e);
                set.remove(e);   //  不能重复添加。
            }
        }
        int[] ans = new int[ans_list.size()];
        for(int i = 0; i < ans_list.size(); i++){
            ans[i] = ans_list.get(i);
        }
        return ans;
    }
}