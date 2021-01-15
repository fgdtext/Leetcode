package Q15;

import java.util.*;

import javax.swing.text.AbstractDocument.BranchElement;


/*
效率 非常低, 非常耗费空间,是一个非常差的方法.
hash 表.验证数组唯一性.
这里不能用类似 两数之和的方式使用hash. 控制数组不重复会非常麻烦.需要手写hash表



*/


class Solution {
    int[] nums;
    List<List<Integer>> ans;
    int[] head;
    int[] next;
    int hashlen;
    HashMap<Integer,Integer> map;

    public List<List<Integer>> threeSum(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        hashlen = nums.length * 3;
        head = new int[hashlen];
        Arrays.fill(head, -1);
        next = new int[hashlen];
        Arrays.fill(next, -1);

        ans = new ArrayList<>();

        map = new HashMap<>();

        for(int it : nums){
            if(map.containsKey(it)){
                map.replace(it,map.get(it)+1);
            }else{
                map.put(it, 1);
            }
        }
        int len = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < len; i++){
            if(nums[i] > 0) break;
            if(i == 0 || nums[i] > nums[i-1]){
                for(int j = i+1; j < len; j++){
                    if(j== i+1 || nums[j] > nums[j-1]){
                        int temp = 0-nums[i]-nums[j];
                        if(temp >= 0 && temp >= nums[j] && ishefa(nums[i], nums[j], temp)){
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(temp);
                            ans.add(new ArrayList<>(list));
                            if(!try_to_insert(list,ans.size()-1)){
                                ans.remove(ans.size()-1);
                            }
                            list.clear();
                        }

                    }
                }
            }
        }
        return ans;
    }
    public boolean ishefa(int a, int b, int c){
        if(a == c && b == c){
            if(map.get(c) >= 3) return true;
            return false;
        }
        if(c == a || c == b){
            if(map.get(c) >= 2) return true;
            return false;
        }
        
        return map.containsKey(c);
    }

    public int hash(List<Integer> s) {
        int min = Collections.min(s);
        int sum = 0;
        if (min < 0) {
            for (int i = 0; i < s.size(); i++) {
                sum = sum * 10 + (s.get(i) - min) % 10;
            }
            return sum % hashlen;
        }

        for (int i = 0; i < s.size(); i++) {
            sum = sum * 10 + s.get(i) % 10;
        }
        return sum % hashlen;
    }

    public boolean try_to_insert(List<Integer> s, int index) {
        int h = hash(s);
        int u = head[h];
        while (u != -1) {
            List<Integer> temp = ans.get(u);
            if (temp.get(0) == s.get(0) && temp.get(1) == s.get(1) && temp.get(2) == s.get(2))
                return false;
            u = next[u];
        }
        next[index] = head[h];
        head[h] = index;
        return true;
    }

}


class Solution22 {
    int[] nums;
    List<List<Integer>> ans;
    int[] head;
    int[] next;
    int hashlen = 10003;
    HashMap<Integer,Integer> map;

    public List<List<Integer>> threeSum(int[] nums) {
        this.nums = nums;
        Arrays.sort(nums);
        head = new int[10003];
        Arrays.fill(head, -1);
        next = new int[10003];
        Arrays.fill(next, -1);

        ans = new ArrayList<>();

        map = new HashMap<>();

        for(int it : nums){
            if(map.containsKey(it)){
                map.replace(it,map.get(it)+1);
            }else{
                map.put(it, 1);
            }
        }
        int len = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < len; i++){
            if(nums[i] > 0) break;
            if(i == 0 || nums[i] > nums[i-1]){
                for(int j = i+1; j < len; j++){
                    if(j== i+1 || nums[j] > nums[j-1]){
                        int temp = 0-nums[i]-nums[j];
                        if(temp >= 0 && temp >= nums[j] && ishefa(nums[i], nums[j], temp)){
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(temp);
                            ans.add(new ArrayList<>(list));
                            if(!try_to_insert(list,ans.size()-1)){
                                ans.remove(ans.size()-1);
                            }
                            list.clear();
                        }

                    }
                }
            }
        }
        return ans;
    }
    public boolean ishefa(int a, int b, int c){
        if(a == c && b == c){
            if(map.get(c) >= 3) return true;
            return false;
        }
        if(c == a || c == b){
            if(map.get(c) >= 2) return true;
            return false;
        }
        
        return map.containsKey(c);
    }

    public int hash(List<Integer> s) {
        int min = Collections.min(s);
        int sum = 0;
        if (min < 0) {
            for (int i = 0; i < s.size(); i++) {
                sum = sum * 10 + (s.get(i) - min) % 10;
            }
            return sum % 10003;
        }

        for (int i = 0; i < s.size(); i++) {
            sum = sum * 10 + s.get(i) % 10;
        }
        return sum % 10003;
    }

    public boolean try_to_insert(List<Integer> s, int index) {
        int h = hash(s);
        int u = head[h];
        while (u != -1) {
            List<Integer> temp = ans.get(u);
            if (temp.get(0) == s.get(0) && temp.get(1) == s.get(1) && temp.get(2) == s.get(2))
                return false;
            u = next[u];
        }
        next[index] = head[h];
        head[h] = index;
        return true;
    }

}