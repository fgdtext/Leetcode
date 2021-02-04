package Q480;

import java.util.*;

/*
class Solution {
public:
    priority_queue<int> small;
    priority_queue<int, vector<int>, greater<int> > big;
    unordered_map<int, int> mp;
    double get(int& k){
        if(k%2) return small.top();
        else return ((long long)small.top()+big.top())*0.5;
    }
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        for(int i = 0; i < k; i++){small.push(nums[i]);};
        for(int i = 0; i < k / 2; i++){big.push(small.top()); small.pop();}
        vector<double> ans{get(k)};
        for(int i = k; i < nums.size(); i++){
            int balance = 0;
            int l = nums[i-k];
            mp[l]++;
            if(!small.empty() && l<=small.top()){balance--;}
            else {balance++;}
            if(!small.empty() && nums[i] <= small.top()){
                small.push(nums[i]);
                balance++;
            }
            else{
                big.push(nums[i]);
                balance--;
            }
            if(balance>0){
                big.push(small.top());
                small.pop();
            }
            if(balance<0){
                small.push(big.top());
                big.pop();
            }
            while(!small.empty() && mp[small.top()]>0){
                mp[small.top()]--;
                small.pop();
            }
            while(!big.empty() && mp[big.top()]>0){
                mp[big.top()]--;
                big.pop();
            }
            ans.push_back(get(k));
            
        }
        return ans;
    }
};
*/

public class Self{
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] nums = {2147483647,2147483647};
        so.medianSlidingWindow(nums, 2);
    }
}
/*
本题的方法是手写 BST树来实现。 而java 中 TreeSet用的红黑树其实就是特殊的BST但是不能有重复元素
所以用 TreeMap键值对的方式。


下边是用双优先队列， 大小顶堆   small(大顶)：保存比较小的部分  big(小顶)保存比较大的部分。
small - big = 0,1 那么堆顶就是中间元素。

延迟删除： 删除时先记账。记录到map中。只对balance修改。记录balance是为两个堆的大小差为0，1

平衡好以后， 就可以对堆顶消账。只对堆顶消账， 堆中间的元素不用管。先欠着。

然后堆顶就是中间元素。

*/
class Solution {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> big;
    HashMap<Integer,Integer> map;
    public double get(int k){
        if(k % 2 == 1) return small.peek();  // 奇数
        else return ((double)small.peek()+big.peek()) / 2; // 偶数先把一个转为 double,另一个会自动提升
    }
    public double[] medianSlidingWindow(int[] nums, int k) {
        // 大顶堆， 保存左侧小的部分
        small = new PriorityQueue<>((o1,o2)->{ return Integer.valueOf(o2).compareTo(Integer.valueOf(o1));}); 
        // 小顶堆， 保存大数部分。
        big = new PriorityQueue<Integer>();
        map = new HashMap();
        int len = nums.length;
        double[] ans = new double[len-k+1];
        int a = 0;
        for(int i = 0; i < k; ++i) small.offer(nums[i]);
        for(int i = 0; i < k/2; ++i) big.offer(small.poll());
        ans[a++] = get(k);
        for(int i = k; i< len; ++i){
            int balance = 0;
            int l = nums[i-k];
            // 删除左侧元素，但是要先记账
            if(!map.containsKey(l)) map.put(l, 0);
            map.replace(l, map.get(l)+1); // 记录在map账本中，l的key有几个，说明有几个l要删除，但是还记着账
            // 只记账不行，还要通过balance来记录在哪个队列删除，在哪个队列加入的新元素。
            /* balance 表示在滑动中，左侧 - 右侧。
                默认 balance  = 0.  若删除small，添加进small,那么 balance = 0 反之亦然。
                若删除small, 添加到 big. 那么 balance  = -2. 
                反之是2.
            */
            // 删除
            if(!small.isEmpty() && l <= small.peek()) --balance; // 加入到small
            else ++balance; // 否则加入 big
            // 添加新元素
            if(nums[i] <= small.peek()){
                small.offer(nums[i]); ++balance;
            }else{
                big.offer(nums[i]); --balance;
            }
            // 由于balance 存在 2 -2的情况。那么 左侧 - 右侧 就不是 0或则1. 栈顶就不是中位数。
            // 左侧多了
            if(balance > 0) big.offer(small.poll());
            if(balance < 0) small.offer(big.poll());

            // 消账，如果堆顶记录在账本内，则说明要该元素是本应该删除的。
            while(!small.isEmpty() && map.containsKey(small.peek()) && map.get(small.peek()) > 0){
                map.replace(small.peek(), map.get(small.peek())-1);
                small.poll();
            }
            while(!big.isEmpty() && map.containsKey(big.peek()) && map.get(big.peek()) > 0){
                map.replace(big.peek(), map.get(big.peek())-1);
                big.poll();
            }
            ans[a++] = get(k);
        }
        return ans;
    }
}