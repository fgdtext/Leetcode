package Q239;

import java.util.*;


public class Self {
    
}
/*
这个维护逻辑就是单调栈（Monotonous Stack，也可以说是单调队列）。
本题根据题设，是要求栈上元素只能单调递减，即[5,4,3,1]是一个有效的单调栈状态，
当遇到了新元素2，则不能直接压栈（否则就不单调递减了），必须先把比2小的元素（即末尾的1）弹出，
再压栈——[5,4,3,2]。在题目中，实际上栈中的元素总是滑动窗口元素的子集（因为可能有弹出操作），
所以栈顶元素必然是窗口的最大值。但是随着窗口移动，栈顶需要退出窗口，
所以，题解在维护单调栈的时候用的是元素下标，而非元素实际的值，
就是为了判断栈顶何时出栈（当窗口左端已经pass栈顶下标的时候）。

可能会比较疑惑单调栈为什么能解决问题。
单调栈的典型用途是用于找到数组中下一个比自身大的元素（the next greater element, NGE），
可以在一次遍历就获得所有元素的NGE。而我们以[5,4,3,1]->[5,4,3,2]为例，
其实弹出1是因为可以明确知道3~2之间的所有元素都比3和2小，那么他们的值是多少其实已经无所谓了，
在任何时候他们都不会是结果集中的一员（向左有3，向右有2，轮不到中间的元素）。

ruguo

单调栈的思维很精巧也很高效，是比较高级的栈维护技巧。以下题目类似：

单调栈 是 递减的。 每次添加过当前元素后，栈中元素都是 >= 当前元素的。

还有一个特性 ： 单调栈中的 数据， 下标一定是递增的。
*/
class Solution {
    ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    int [] nums;
    // k是窗口大小   ， 该函数用于位置单调，并维持滑动窗口大小。  该问题的关键就是该函数。
    public void clean_deque(int i, int k) {
      // remove indexes of elements not from sliding window
      //  队头的元素恰好是滑出窗口的那一个。
      if (!deq.isEmpty() && deq.getFirst() == i - k)
        deq.removeFirst();
  
      // remove from deq indexes of all elements 
      // which are smaller than current element nums[i]
      while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])  deq.removeLast();
    }
  
    public int[] maxSlidingWindow(int[] nums, int k) {
      int n = nums.length;
      if (n * k == 0) return new int[0];
      if (k == 1) return nums;
  
      // init deque and output
      this.nums = nums;
      int max_idx = 0;

      //  初始化 单调栈
      for (int i = 0; i < k; i++) {
        clean_deque(i, k);
        deq.addLast(i);
        // compute max in nums[:k]
        if (nums[i] > nums[max_idx]) max_idx = i;
      }


      int [] output = new int[n - k + 1];
      // 
      // 找出第一个窗口的 最大值。
      output[0] = nums[max_idx];
  
      // 不断滑动窗口。
      for (int i  = k; i < n; i++) {
        clean_deque(i, k);   //  
        deq.addLast(i);
        output[i - k + 1] = nums[deq.getFirst()];
      }
      return output;
    }
  }
