package Q33;


/*
自己写的代码真是太垃圾了。
虽然写出来了。

我真垃圾

先找  旋转点

*/

class Self {
    int ans = -1;
    int target;
    public int search(int[] nums, int target) {
        this.target = target;
        if(nums.length == 1){ return target == nums[0] ? 0 : -1;}
        int ind;
        if(nums[0] < nums[nums.length - 1]){
            ind = 0;
        }else if(nums[nums.length - 1] < nums[nums.length - 2]){
            ind = nums.length - 1;
        }else{
            ind = erfen(nums, 1, nums.length-2);
            if(ans != -1) return ans;
        }
 System.out.println(ind+"...");
        if(ind == 0 || target >= nums[ind] && target <= nums[nums.length - 1]){
            ans = erseach(nums, ind, nums.length - 1);
        }else{
            ans = erseach(nums, 0, ind - 1);
        }
        return ans;

    }
    // 二分查找旋转点
    public int erfen(int[] nums,int left, int right){
        int mid = left + (right - left)/2;
        if(nums[mid] == target) {
            ans = mid;
            return 0;
        }
        if(nums[mid] < nums[mid+1] && nums[mid] < nums[mid-1]) return mid;
        if(nums[mid] > nums[right]) {
            return erfen(nums, mid + 1, right);
        }
        if(nums[mid] < nums[left]){
            return erfen(nums, left, mid - 1 );
        }
        if(nums[mid] > nums[left] && nums[mid] < nums[right]){
            return left;
        } 
        return 0;
    }
    // 二分查找。
    public int erseach(int[] nums, int left, int right){
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }

}