package Q42;


public class Self {
    public static void main(String[] args) {
        int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution solution = new Solution();
        int ans = solution.trap(test);
        System.out.println(ans);
    }
}

/*


双指针  ：    
1. 从 左向右  maxleft 是单增的。 每次遇到大于等于maxleft的位置i, 那么maxleft~i之间的水就可以直接计算出来。
        然后更新 maxleft 到 i 的位置。 

2. 为什么只从左向右是不行的。 假设中间某位置，有一个maxh高度。该高度是最高的。 那么 maxleft 会永远停留在该位置，无法找到下一个
        大于等于maxleft的位置i. maxh 之后的水量就无法计算。所以应该从左计算到maxh的位置。然后再从右向左计算到maxh。

单调栈 ： 

由于 maxleft 具有单增性质，单调栈的应用也是可以的。使用下标存入单调增栈。一遍过后，开始出栈，每次出栈两个相邻的中间就是水。

动态规划： 

对于位置 i 记录 i left[i] 左侧大于位置i 高度的位置 还有right[i] 那么位置i贡献的水量就是  height[min(left[i],right[i])] - height[i]

*/


class Solution {
    public int trap(int[] height) {
        int len = height.length;
        if(len < 3) return 0;
        int maxleft = 0;
        int ans = 0;
        for(int i = 1; i < len; i++){
            if(height[i] >= height[maxleft]){
                int h = height[maxleft];
                for(int j = maxleft+1; j < i; j++){
                    ans = ans + (h-height[j]);
                }
                maxleft = i;
            }
        }
        int maxright = len - 1;
        for(int i = len-2; i >= maxleft; i--){
            if(height[i] >= height[maxright]){
                int h = height[maxright];
                for(int j = maxright-1; j > i; j--){
                    ans = ans +  (h-height[j]);
                }
                maxright = i;
            }
        }
        return ans;

    }
}




/*
数组题 
从一个方向 使用两个指针， 进行滑动窗口。

使用两个指针，同时相向，移动，进行双指针。

对一侧使用指针滑向另一侧， 结束后， 从另一侧 使用指针再滑回来。两次滑动的数据进行对比。
    ：  类似，也可以从左右两个方向进行，滑动窗口，再对比两次数据。
*/


// [5,5,1,7,1,1,5,2,7,6]

/*
算是一个双指针吧。 就是效率比较低。45.22%
算法思想：  这个方法太垃圾了。

假设有  8 7 6 5 7   left = :8   right = :7 

在left ~right-1 的范围内找 mid = :7 (第一个).  从图形可以看到。 7到7之间水的体积可以从 mid 计算到 right的位置。
即657的体积+ 7667的体积。


效率是  o(nlgn)的时间

*/
class Slef2 {
    public int trap(int[] height) {
        int len = height.length;
        int ans = 0;
        int left = 0;
        int right = 1;
        while(right < len){
            while(right < len && height[right] <= height[right - 1]){
                right++;
            }
            if(right > height.length - 1){
                return ans;
            }
            int mid = erfen(height, height[right],left,right - 1);
            int minhi = height[right];
            if(mid == left && height[mid] <= height[right]){  
                left = right;
                minhi = height[mid];
            }
            while(mid < right - 1){
                if(height[mid] > height[mid + 1]){
                    ans  = ans + minhi - height[mid + 1];
                    height[mid + 1] = height[right];
                }
                mid++;
            }
            right ++ ;
        }
        return ans;
    }
    public int erfen(int[] arr, int target,int left, int right){
        if(left == right) return left;
        if(arr[right - 1] >= target) return right - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(arr[mid] >= target && arr[mid + 1] < target) return mid;
            if(arr[mid] >= target){
                left = mid + 1;
            }
            if(arr[mid] < target){
                right = mid - 1;
            }
        }
        return left;
    }
}
