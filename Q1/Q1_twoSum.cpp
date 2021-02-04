#include <bits/stdc++.h>
using namespace std;

/**
 * leetcode : 1. 两数之和  为 k
 * 解法1：暴力   o(n^2) 垃圾
 * 解法2: hashmap    根据值可以查到下标。 由于值和下标是关联的，所以只用哈希表的话需要存结构体{index, value}然后根据value散列
 * 
 * 
 * 
 * 该方法还有其他方案。这里的  手写hash 是常用方法， 但是太原生，只有在 确定两个数组是否是重复出现时，才推荐手动实现。
 * 一般情况使用 Hashmap HashSet 就可以。
 * **********已经提交正确
 * 
 * 
 * 注意： 我们如果只是要通过某种映射关系去查找，使用map 也可以。
 */


const int hashSize = 1000;
int head[hashSize];
int *nextix;


// 哈希表使用 vector 会更节省空间


// 可以直接写在查找中。i 是 nums 的下标
// 注意 负数 不要越界。
int myhash(int i)
{
    return abs(i % hashSize);
}


int  try_to_insert(vector<int> &nums, int i)
{  
    int h = myhash(nums[i]);
    int u = head[h];
    while(u != -1)
    {
        if(nums[i] == nums[u]) return 0;
        u = nextix[u];
    }
    // i 指向 head[h] 
    nextix[i] = head[h];
    // 修改 head[h] 的指向 到 i 。
    head[h] = i;
    return 1;
}

// 算法 遍历 数组 nums  在哈希表中 找 k -  nums[i]  找到则返回
// 在哈希表中查找 元素 v (不是下标), 返回下标。
int find(vector<int> &nums, int v)
{
    int h = myhash(v);
    // u 指向第一个元素的位置。
    int u = head[h];
    while (u != -1)
    {
        if(nums[u] == v) return u;
        u = nextix[u];
    }
    return -1;
}


class Solution
{
public:
    vector<int> twoSum(vector<int> &nums, int target)
    {
        vector<int> ans;
        int len = nums.size();
        // 因为哈希表使用数组实现的，所以这里换为数组
        // 初始化 哈希表。主要是 nextix
        nextix = new int[len];
        memset(head, -1, sizeof(head));
        memset(nextix, -1, sizeof(nextix));

        // 插入所有数据到 hash 表
        for (int i = 0; i < len; i++){
            // nums[i] 插入到 hash表中
            try_to_insert(nums, i);
        }
        // 在哈希表中查找每一个 元素是否 匹配题意
        for (int i = 0; i < len; i++)
        {

            int j = find(nums, target - nums[i]);
            // 没找到
            if (j == -1)
                continue;
            // i,j 重叠
            if (i == j)
                continue;
            // 如果要找的两个数target - a[i] ==  a[i] 但是存在  a[j] == a[i]  j > i 的情况。会导致放弃。
            if (i > j)
            {
                int t = j;
                j = i;
                i = t;
            }
            ans.push_back(i);
            ans.push_back(j);
            return ans;
        }
        return ans;
    }
};


int main()
{

    int t = 0;
    vector<int> cc = {0, 4, 3, 0};

    Solution so;
    vector<int> aa = so.twoSum(cc, t);
    printf("%d %d", aa[0], aa[1]);

    // 防止黑框消失
    char a, b;
    printf("\n--------------------------\n");
    scanf("%d", a);
    return 0;
}

