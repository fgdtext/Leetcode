#include <bits/stdc++.h>
using namespace std;


/**
 *  map k-v型哈希表。
k 个数列 
0: [-1, 2, 3]
1: [1]
2: [1, 2]
3: [1, 1, 3]

现在构建哈希表。 该哈希表的目的是实现， 通过 key(上边k个数列中的点) 查找 该key在哪些 数列中出现过。即实现 key - array 数字和数组的映射。
unordered_map<int, vector<int>>  一定要使用 unordered_map 哈希map. 其查找效率为 o(1)
映射关系。 
-1: [0]
 1: [1, 2, 3, 3]   //  这里不写重复的也可以。扩大区间和缩小区间统一就好。
 2: [0, 2]
 3: [0, 3]

 该映射关系就是为了，很快的找出 某个数字，出现在哪些数列中。

滑动窗口从 所有数列最小值按整数 连续 滑动到最大值。

 vector<int> freq(n);   数列计数器，永远保存当前区间中所有数，出现在所有区间的频率。


R++ . 则判断新元素是否是数列中点，若是，则对其所有出现过的数列计数器+1. 只要 计数器从0变为1，说明该区间包含了该数列的数字。

L++ ： 缩小区间。
 */



class Solution {
public:
    vector<int> smallestRange(vector<vector<int>>& nums) {
        int n = nums.size();

        unordered_map<int, vector<int>> indices;
        int xMin = INT_MAX, xMax = INT_MIN;
        for (int i = 0; i < n; ++i) {
            for (const int& x: nums[i]) {
                indices[x].push_back(i);
                xMin = min(xMin, x);
                xMax = max(xMax, x);
            }
        }

        vector<int> freq(n);
        int inside = 0;
        int left = xMin, right = xMin - 1;
        int bestLeft = xMin, bestRight = xMax;


      /*
       * 我之前想的滑动窗口是，每次移动，都是在 合并数列中每次移动一个点。如合并以后是[1,3,5,7]移动 1，到3，到5，到7 
       * 然后看L = 1，R = 7两个点这个区间[1-7]是否包含其他所有数列中的数。但是就是这个判断不好做。就是不好判断该区间是否满足包含其他数列中的数。
       * 
       * 官方答案给定滑动窗口是按照整数连续滑动的。 1，2，3，4. 这样，在 R 每移动一个单位比如从3移动到4， 就可以判断4出现在哪些区间。
       * 这样就可以判断 L-R这个区间中的所有数出现的区间个数，满足n则说明包含了所有区间。
       */
      /**
       * 滑动窗口的关键是， R和L的滑动时机。比如本题的滑动方式是， 先移动right,移动后的区间若满足条件，再移动L缩小区间，看是否满足。以此类推。
       */
        while (right < xMax) {
            ++right;
             // left 必须是一个可用点，因为窗口时连续滑动，所以 left可能取到不在数列中的点。
            if (indices.count(right)) {
                // right 在哪些数列中出现过
                for (const int& x: indices[right]) {
                    // x: right 在第x数列中出现过的
                    ++freq[x];
                    //该数列计数器加1  // 如果一个一个数列计数器从0到1，则说明，该当前区间包含该数列。
                    if (freq[x] == 1) {
                        // 出现的队列计数，满n则说明在 
                        ++inside;
                    }
                }
                //满足n ，则说明该区间已经包含了所有数列。
                while (inside == n) {
                    // 选择最小区间。
                    if (right - left < bestRight - bestLeft) {
                        bestLeft = left;
                        bestRight = right;
                    }
                    // left 必须是一个可用点，因为窗口时连续滑动，所以 left可能取到不在数列中的点。
                    if (indices.count(left)) {
                        //将left 出现的数列计数器全部 -1.
                        for (const int& x: indices[left]) {
                            --freq[x];
                            if (freq[x] == 0) {
                                --inside;
                                // 若 inside 仍然满足，则继续缩小 区间。
                            }
                        }
                    }
                    ++left;
                }
            }
        }

        return {bestLeft, bestRight};
    }
};
