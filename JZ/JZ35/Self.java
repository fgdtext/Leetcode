package JZ.JZ35;

class Solution {
    public int InversePairs(int [] array) {
         int len = array.length;

        if (len < 2) {
            return 0;
        }
        int[] temp = new int[len];
        return reversePairs(array, 0, len - 1, temp);
    }
    
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int leftPairs = reversePairs(nums, left, mid, temp) % 1000000007;
        int rightPairs = reversePairs(nums, mid + 1, right, temp) % 1000000007;

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp)% 1000000007; //这个余数不能省略
        return ((leftPairs  + rightPairs)% 1000000007 + crossPairs);
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) { // 走到头了，就不用比较了
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) { // 需要比较
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1) % 1000000007;
            }
        }
        return count;
    }
}