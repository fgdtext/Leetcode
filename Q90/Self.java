package Q90;
import java.util.*;


public class Self{
    public static void main(String[] args) {
        // Solution so = new Solution();
        // int[] a= {1,2,2};
        // so.subsetsWithDup(a);
    }
}

/*
    对当前ans集合中的每一个列表list : t
    当前元素 key : count  key的个数为count个， 那么对于集合t，加入不同个数的key，就能生成count个不同的集合，其key个数不同。
    那么每次复制t，对t加入不同的key，然后加入ans

*/
class Self2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            if(!map.containsKey(num)) map.put(num,0);
            map.replace(num, map.get(num)+1);
        }
        for(int key : map.keySet()){
            int count = map.get(key);
            int len = ans.size();
            for(int i = 0; i < len; i++){
                List<Integer> t = new ArrayList<>(ans.get(i));
                for(int j = 0; j < count; j++){
                    t.add(key);
                    ans.add(new ArrayList<>(t));
                }
            }
        }
        return ans;
    }
}

/*
        使用二进制法，先获取到所有可能的集合， 然后使用Set去重
*/
class Ans {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> cur = new ArrayList<>();
        
        // 枚举 i 代表，枚举所有的选择方案状态
        // 例如 [1,2]，我们有 []、[1]、[2]、[1,2] 几种方案，分别对应了 00、10、01、11 几种状态
        for (int i = 0; i < (1 << n); i++) {
            cur.clear();
            // 对当前状态进行诸位检查，如果当前状态为 1 代表被选择，加入当前方案中
            for (int j = 0; j < n; j++) {
                int t = (i >> j) & 1;
                if (t == 1) cur.add(nums[j]);
            }
            // 将当前方案中加入结果集
            ans.add(new ArrayList<>(cur));
        }
        return new ArrayList<>(ans);
    }
}
/*

来自同一个父节点的同一层结点，不使用相同元素。 树的每一个结点（包括root）都加入ans
122
第0层: 空集合  
第一层 只有 1,2,两个集合  没有第二个2
第三层 12 22
第四层 122 
*/
class Ans2{
    List<List<Integer>> ans;
    List<Integer> list;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<>();
        list = new ArrayList<>();
        dfs(nums, 0);
        return ans;
    }
    public void dfs(int[] nums, int startIndex){
        ans.add(new ArrayList<>(list));
        for(int i = startIndex; i < nums.length; i++){
            if(i > startIndex && nums[i] == nums[i-1]) continue; // 来自同一个父结点的同一层不使用相同元素
            list.add(nums[i]);
            dfs(nums, i+1);
            list.remove(list.size()-1);
        }
    }
}

class Ans3 {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }
    /*
choosePre : 是否选择了前一个元素，入如果当前集合没有选择前一个元素，那么他也不能选择当前元素。
    */
    public void dfs(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        // 不选择当前元素，可以直接进行，一定不会重复，即使选择了前一个元素，也可以不选当前元素。
        dfs(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        // 要选择当前元素必须满足条件，即其选择了前一个相同的元素，才能选择当前元素。
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }
}