#include <bits/stdc++.h>
using namespace std;
/*题目： 632. 最小区间
你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。

我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

推荐 ： 合并数列建立滑动窗口 +     和我的想法最接近
  ans1 的我卡在了 不知道使用 unordered_map 去 记录当前区间包含多少数列的元素这一点。
*/


/**
 *   我之前想的滑动窗口是，每次移动，都是在 合并数列中每次移动一个点。如合并以后是[1,3,5,7]移动 1，到3，到5，到7 
 *   然后看L = 1，R = 7两个点这个区间[1-7]是否包含其他所有数列中的数。但是就是这个判断不好做。就是不好判断该区间是否满足包含其他数列中的数。
 *   该判断就是通过  unordered_map  去记录 每个点 出现在哪些数列中。
 *
 *   已经提交成功。 最快的办法。所有方法的空间复杂度基本差不多。
 *   
 *
 * 
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

        set<int> numstomap ;//元素值-所属组序号，默认按值升序
        for(int i = 0;i < nums.size();i ++ )
        {
            for(int j = 0;j < nums[i].size();j++)
            {
                numstomap.insert(nums[i][j]);
            }
        }
        set<int>::iterator left = numstomap.begin();//左指针，左右指针初始都位于左侧
        set<int>::iterator right = numstomap.begin();//右指针

        int res = INT_MAX;//设置一个绝对最大初始值
        int leftv = 0;//返回结果的两个元素值
        int rightv= 0;//
        int inside = 0;
        vector<int> freq(n);
        while( right != numstomap.end() )//终止条件
        {
            //窗口扩张纳入的新元素
            for(const int x : indices[*right]){
                ++freq[x];
                if(freq[x] == 1){
                    ++inside;
                }
            }
            // 在 while中 缩小区间。
            while(inside == n )//已经找到了一个可行解、优化它
            {
                if( *right - *left < res)//记录可行解
                {
                    res = *right - *left;
                    leftv = *left;
                    rightv = *right;
                }
                //收缩窗口
                for(const int x : indices[*left]){
                    --freq[x];
                    if(freq[x] == 0){
                        --inside;
                    }
                }
                left++;
            }
            right++;
        }
        if( res == INT_MAX ) return {};
        else return {leftv,rightv};
    }
};


int main()
{ 
    vector<vector<int> > v ={
        {4,10,15,24,26},
        {0,9,12,20},
        {5,18,22,30}
    };
    Solution s;
    vector<int> ans =  s.smallestRange(v);
    cout<< ans[0] << "**" << ans[1] << endl;



    // 防止黑框消失
    char a, b;
    printf("\n--------------------------\n");
    scanf("%d", a);
    return 0;
}