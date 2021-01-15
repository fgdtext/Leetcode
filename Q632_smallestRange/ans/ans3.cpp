#include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    vector<int> smallestRange(vector<vector<int>>& nums) {
        multimap<int,int> numstomap ;//元素值-所属组序号，默认按值升序
        for(int i = 0;i < nums.size();i ++ )
        {
            for(int j = 0;j < nums[i].size();j++)
            {
                numstomap.insert(pair<int,int>(nums[i][j],i));
            }
        }
        multimap<int,int>::iterator left = numstomap.begin();//左指针，左右指针初始都位于左侧
        multimap<int,int>::iterator right = numstomap.begin();//右指针
        int res = INT_MAX;//设置一个绝对最大初始值
        int leftv = 0;//返回结果的两个元素值
        int rightv= 0;//
        int k = nums.size();//组个数
        unordered_map<int,int> curmap;//组序号-个数 ///  使用hashmap  不允许出现重复key, 所以map 长度为k 时，说明该区间满足。
        while( right != numstomap.end() )//终止条件
        {
            curmap[right->second] ++;//窗口扩张纳入的新元素
            // 在 while中 缩小区间。
            while(curmap.size() == k )//已经找到了一个可行解、优化它
            {
                if( right->first - left->first < res)//记录可行解
                {
                    res = right->first - left->first;
                    leftv = left->first;
                    rightv = right->first;
                }
                curmap[left->second] --;//收缩窗口
                if( curmap[left->second] == 0)
                {
                    curmap.erase(left->second);
                }
                left++;
            }
            right++;

        }
        if( res == INT_MAX ) return {};
        else return {leftv,rightv};
    }
};

