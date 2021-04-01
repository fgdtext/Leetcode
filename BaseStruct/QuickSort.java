package BaseStruct;

import java.util.Random;

public class QuickSort {
    private static Random random = new Random(System.currentTimeMillis());

    public void sort(int[] nums, int left, int right){
        int mid = partition(nums, left, right);
        sort(nums, mid+1, right);
        sort(nums, left, mid-1);
    }

    public int partition(int[] nums, int left, int right) {
        // 在区间随机选择一个元素作为标定点
        if (right > left) {
            int randomIndex = left + 1 + random.nextInt(right - left);
            int temp = nums[left];
            nums[left] = nums[randomIndex];
            nums[randomIndex] = temp;
        }
        int pivot = nums[left];

        // 将等于 pivot 的元素分散到两边
        // [left, lt) <= pivot
        // (rt, right] >= pivot

        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && nums[j] >= pivot) --j;
            if(i < j)  nums[i] = nums[j];
            
            while (i < j && nums[i] < pivot) ++i;
            if(i < j)  nums[j] = nums[i];
        }
        nums[i] = pivot; 
        return i;
    }
}