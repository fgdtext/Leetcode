package Q15;

import java.util.*;

public class Self {
    
}
/*
数组有 重复 元素， 使用 减法的 begin写法    Q39，Q40的写法  递归

该方法超时。

*/
class Solution2 {
    int[] nums;
    List<List<Integer>> ans;
    int[] head;
    int[] next;
    int hashlen = 1003;
    public List<List<Integer>> threeSum(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        head = new int[1003];
        Arrays.fill(head, -1); 
        next = new int[1003];
        Arrays.fill(next, -1); 

        ans = new ArrayList<>();

        dfs(0, 0, new ArrayList<>());

        return ans;
    }


    public int hash(List<Integer> s){
        int min =  Collections.min(s);
        int sum = 0;
        if(min < 0){
            for(int i = 0; i< s.size(); i++){
                sum  = sum*10 + (s.get(i)-min)%10;
            }
            return sum % 1003;
        }

        for(int i = 0; i< s.size(); i++){
            sum = sum*10 + s.get(i)%10;
        }
        return sum % 1003;
    }

    public boolean try_to_insert(List<Integer> s,int index){
        int h = hash(s);
        int u = head[h];
        while(u != -1){
            List<Integer> temp = ans.get(u);
            if(temp.get(0) == s.get(0) && temp.get(1) == s.get(1) && temp.get(2) == s.get(2)) return false;
            u = next[u];
        }
        next[index] = head[h];
        head[h] = index;
        return true;
    }


    void dfs(int begin,int add, List<Integer> list){
        if(list.size() == 3 && add == 0){
            ans.add(new ArrayList<>(list));
            if(!try_to_insert(list,ans.size()-1)){
                ans.remove(ans.size()-1);
            }
            return;
        }
        // 这里不能  or上  add == 0 ,因为数组种可能有 0 .
        if(list.size() == 3 || begin == nums.length) return;


        for(int i = begin+1; i < nums.length; i++){
            if(add >= 0 && nums[i] > 0) return;
            if(add < 0 && nums[i] > 0 && nums[i] > 0-add) return;
            list.add(nums[i]);
            dfs(i, add+nums[i], list);
            list.remove(list.size()-1);

            dfs(i, add, list);
        }

    }
}