package Q162;

public class Self {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        Solution so = new Solution();
        so.findPeakElement(nums);
    }
}
/*
简直伊拉克风格的代码，这都能跑通。 卧槽。 神了。 

0 len-1 是特殊位置，只要大于相邻点直接返回即可。 对于内部其他位置。进行二分法

left= 0,right = len-1;
mid 弱 mid < left, 而由于位置1一定大于位置0（否则答案就是0），mid又小于left,那么 一定存在一个山峰在 left ~mid-1之间。
若mid < right ,那么 一定有一个山峰在 mid+1 到right之间。

若 mid > left  mid > right  ， 则此时 mid本身就可能是一个山峰。此时在 left和mid之间取mid2, 若mid2大，则山峰在left到mid-1之间。
若mid2小， 则 可能的区间是mid2到right。

再在mid和right之间取mid3, 若mid3大，则山峰在mid+1和right之间，。
否则在 mid2 和mid3之间。 

所以总的来看。 迭代范围总是缩小一般。肯能取 left-mid-1   也可能是  mid-1 到right
            也可能是  mid2 到mid3之间。 反正都是  left-right长度的一半。


*/
class Solution {
    public int findPeakElement(int[] nums) {
        int i = 1;
        int len = nums.length;
        if(len == 1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[len-1] > nums[len-2]) return len-1;
        int left = 0, right = len-1;
        while(left < right){
            int mid = (right - left) /2 + left;
            if(mid != 0 && mid != len  -1 && nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) return mid;
            if(nums[mid] < nums[left]) right = mid-1;
            else if(nums[mid] < nums[right]) left = mid +1;
            else{
                int mid2 = (mid-left)/2+left;
                if(nums[mid2] > nums[mid]) {
                    right = mid-1;
                    continue;
                }else left = mid2+1;

                int mid3 = (right-mid)/2+mid;
                if(nums[mid3] > nums[mid]){
                    left = mid+1;
                    continue;
                }else right = mid3-1;
            }
        }
        return left;
    }
}



/*
看看答案。操了， 凭什么我想不到，日他妈的。我写那么长的垃圾代码。简直浪费生命。

一句话： 若mid向右是上升的， 那么山峰在右边。如果是下降的，那么山峰在左边。日了狗的。
*/
 class Ans {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
