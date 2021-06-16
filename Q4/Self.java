package Q4;

/*
两递增数组，求合并后中位数。要求时间 o(lg(m+n)), 所以不能真的去合并

方法1 ： 找两个数的 第 k 小
         若m+n为偶数， 则找第k 小，和k+1小。找到第k小，就找到了第k+1小。
         若m+n为奇数， 则直接找第k小,返回



好难啊。
*/


class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // 这里 m <= n
        int m = nums1.length, n = nums2.length;
        int k = m + (n - m + 1)/2 ;//  
        int start1 = 0, start2 = 0;
        while(k > 0){
            // if 已经有一个数组全部抛弃掉
            // 数组1 全部排除
            if(start1 >= m){
                if((m+n)%2==0) return (nums2[start2 + k-1] + nums2[start2 + k])*0.5;
                return nums2[start2 + k - 1]*1.0;
            }
            if(start2 >= n){
                if((m+n)%2==0) return (nums1[start1 + k-1] + nums1[start1 + k])*0.5;
                return nums1[start1 + k - 1]*1.0;
            }
            // 要抛弃的数段 的 最后一个数字
            int num1 = 0;
            int num2 = 0;
            // if 超出某一个数组。抛弃该数组
            if(start1 + k/2 >= m){
                // 抛弃数组1 直接输出
                int len = m - start1;
                num2 = nums2[start2 + k/2 - 1];
                num1 = nums1[m-1];
                if(start1 + k/2 == m){
                    if(num1 <= num2){ 
                        if((m+n)%2==0){
                            if(k % 2 != 0) return (nums2[start2 + k/2] + nums2[start2 + k/2 +1])*0.5;
                            return (nums2[start2 + k/2-1] + nums2[start2 + k/2])*0.5;
                        } 
                        return k%2 == 1 ? nums2[start2 + k/2]*1.0 : nums2[start2 + k/2 -1 ]*1.0;
                    }else{
                        start2 = start2 + k/2;
                        k = k - k/2;
                        continue;
                    }
                }else{
                    if(num1 <= num2){
                       
                        if((m+n)%2==0) return (nums2[start2 + k - len - 1] + nums2[start2 + k - len])*0.5;
                        return nums2[start2 + k - len-1]*1.0;
                    }else{
                        start2 = start2 + k/2;
                        k = k - k/2;
                        continue;
                    }
                }
            }
            if(start2 + k/2 >= n){
                num1 = nums1[start1 + k/2 - 1];
                num2 = nums2[n-1];
                int len = n - start2;
                // 抛弃数组1 直接输出
                if(start2 + k/2 == n){
                    if(num1 >= num2){
                        if((m+n)%2==0){
                            if(k % 2 != 0) return (nums1[start1 + k/2] + nums1[start1 + k/2 +1])*0.5;
                            return (nums1[start1 + k/2-1] + nums1[start1 + k/2])*0.5;
                        } 
                        return k%2==1 ? nums1[start1 + k/2]*1.0 :nums1[start1 + k/2 -1]*1.0;
                    }else{
                        start1 = start1 + k/2;
                        k = k - k/2;
                        continue;
                    }
                }else{
                    if(num1 >= num2){

                        if((m+n)%2==0) return (nums1[start1 + k - len - 1] + nums1[start1 + k - len])*0.5;
                        return nums1[start1 + k - len-1]*1.0;
                    }else{
                        start1 = start1 + k/2;
                        k = k - k/2;
                        continue;
                    }
                }
            }
            if(k != 1){
                num1 = nums1[start1 + k/2-1];
                num2 = nums2[start2 + k/2-1];
            }
            // 出现 相等，或者 计数到 k 则都可以直接找到 第 k 小的数
            if(k == 1){
                if((m+n)%2 == 0){
                    int a = Math.min(nums1[start1],nums2[start2]);
                    int b = Math.max(nums1[start1],nums2[start2]);
                    if(start1+1 < m && start2+1 < n){
                         b = Math.min(nums1[start1+1], b);
                         b = Math.min(nums2[start2+1], b);
                    }else if(start1+1 < m){
                        b = Math.min(nums1[start1+1], b);
                    }else if(start2+1 < n){
                        b = Math.min(nums2[start2+1], b);
                    }
                    return (a+b)*0.5;
                }
                else{
                    return Math.min(nums1[start1],nums2[start2])*1.0;
                }
            }
            if(num1 < num2){
                start1 = start1 + k/2;
            }else{
                start2 = start2 + k/2;
            }
            k = k - k/2;
        }
        return 0;
    }
}



class Solution2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // 这里 m <= n
        int m = nums1.length, n = nums2.length;
        int k = m + (n - m + 1)/2 ;//  
        int start1 = 0, start2 = 0;
        while(k > 0){
            // 从 start 开始后边还有几个 元素。
            m = nums1.length; n = nums2.length;
            int len1 = m - start1;
            int len2 = n - start2;
            if(len1 > len2){
                int[] temp = nums2; nums2 = nums1; nums1 = temp;
                continue;
            }
            // 以下， 一定有 nums1 长度小于 nums2
            // 若 nums1 长度为 0
            if(len1 == 0) return nums2[start2 + k - 1];  
            if(k == 1) {
                if((m+n)% 2 == 0){
                    int a = Math.min(nums1[start1],nums2[start2]);
                    int b = Math.max(nums1[start1],nums2[start2]);
                    if(start1 + 1 < m) b = Math.min(nums1[start1+1], b);
                    if(start2 + 1 < n) b = Math.min(nums2[start2+1], b);
                    return (a+b)*0.5;
                }else{
                    return Math.min(nums1[start1],nums2[start2]);
                }
            } 
            int i = start1 + k/2 - 1;
            int j = start2 + k/2 - 1;
            if(nums1[i] > nums2[j]){
                start2 = j + 1;
                k = k - (j - start2 + 1);
            }else{
                start1 = i + 1;
                k = k - (i - start1 + 1);
            }
           // System.out.equals("k::"+k);
        }
        return 0 ;
    }
}

class Solution3 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 这里 m <= n
        int m = nums1.length, n = nums2.length;
        if(m > n){
           return findMedianSortedArrays(nums2,nums1);
        }
        int k = m + (n - m + 1)/2 ;//  
        int start1 = 0, start2 = 0;
        while(k > 0){
            // 从 start 开始后边还有几个 元素。
            m = nums1.length; n = nums2.length;
            int len1 = m - start1;
            int len2 = n - start2;
            if(len1 > len2){
                int[] temp = nums2; nums2 = nums1; nums1 = temp;
                int t = start2; start2 = start1; start1 = t;
                continue;
            }
            // 以下， 一定有 nums1 长度小于 nums2
            // 若 nums1 长度为 0
            //System.out.println("k::"+k);
            if(len1 == 0) {
                if((m+n)% 2 == 0){
                    return (nums2[start2 + k - 1] + nums2[start2 + k]) * 0.5;
                }
                return nums2[start2 + k - 1]; 
            } 
            if(k == 1) {
                if((m+n)% 2 == 0){
                    int a = Math.min(nums1[start1],nums2[start2]);
                    int b = Math.max(nums1[start1],nums2[start2]);
                    if(start1 + 1 < m) b = Math.min(nums1[start1+1], b);
                    if(start2 + 1 < n) b = Math.min(nums2[start2+1], b);
                    return (a+b)*0.5;
                }else{
                    return Math.min(nums1[start1],nums2[start2]);
                }
            } 
            int i = start1 + Math.min(len1, k/2) - 1;
            int j = start2 + Math.min(len2, k/2) - 1;
            if(nums1[i] > nums2[j]){
                k = k - (j - start2 + 1);
                start2 = j + 1;
                //System.out.println("k::"+k);
            }else{
                k = k - (i - start1 + 1);
                start1 = i + 1;
                //System.out.println("k::"+k);
            }
        }
        return 0 ;
    }
}

// 找 第 k 小 题解 Solution3 的 递归写法
class ans01{
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        // 当 n + m 为 奇数时 left等于right
        // 假设 1，2，3，4 left就是2的位置，right就是 3的位置
        int left = (n + m + 1) / 2;  
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
    }
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
}
/*
上边的方法都是基于 找 第  k 小 的方法。通过对 k/2的位置的比较，来排除一半，实现 lg(k) = lg((m+n)/2)的时间复杂度

下边的方法，基于对 短 数组 nums1 进行划分(插入分割线)来实现
    将两个数组都分为左右两部分，若中位数位于分割线左侧。则两侧元素数量基本相同。L > R
    这里要求，不管总数是奇数还是偶数，一定使得 中位数(一个或两个数)都在分割线的左侧。





*/
 class ans02 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        // 分割线左边的所有元素需要满足的个数 m + (n - m + 1) / 2;
        int totalLeft = (m + n + 1) / 2;

        // 在 nums1 的区间 [0, m] 里查找恰当的分割线，
        // 使得 nums1[i - 1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left = 0;
        int right = m;

        while (left < right) {
            // i, j 是互补的。  i 每次都 进行二分，(在 left,right的中间。)
            int i = left + (right - left + 1) / 2;
            int j = totalLeft - i;
            if (nums1[i - 1] > nums2[j]) {
                // 下一轮搜索的区间 [left, i - 1]
                right = i - 1;
            } else {
                // 下一轮搜索的区间 [i, right]
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];
        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if (((m + n) % 2) == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
        }
    }
}

public class Self{
    public static void main(String[] args) {
        Solution3  solution3 = new Solution3();
        int[] nums1 = {1};
        int[] nums2 = {2,3,4,5,6,7};
        System.out.println(solution3.findMedianSortedArrays(nums1, nums2));
        System.out.println("hello");
    }
}