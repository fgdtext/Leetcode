package Q324;

import java.util.Arrays;





class Self2 {
    public void wiggleSort(int[] nums) {

    }
    // Do partition in arr[begin, end), with the first element as the pivot.
    //9,5,2,1,4,7,5,8,3,6

    void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    int quickSelect_no_well(int[] arr, int start, int end, int k){
        int privt = arr[start];
        int i = start; int j = end;
        while(i < j){
            while(i < j && arr[j] >= privt) j--;
            if(i < j) arr[i] = arr[j];
            while(i < j && arr[i] <= privt) i++;
            if(i < j) arr[j] = arr[i];
        }
        arr[i] = privt;
        if(k == i+1 - start) return i;
        else if(k > i+1 - start) return quickSelect_no_well(arr, i+1, end, k-i-1+start);
        else return quickSelect_no_well(arr, start, i-1, k);
    }
    // 在arr中找第k小
    int quickSelect_well(int[] arr, int k){
        int start = 0; int end = arr.length-1;
        while(true){
            int ind = partition(arr, start, end);
            if(ind+1 == k) return arr[ind];
            else if(ind+1 > k) end = ind-1;
            else start = ind+1;
        }
    }
    // partition : 该函数一定要单独写避免上边直接在quickSelect中做划分。可以看到参数十分的混乱
    int partition(int[] arr, int start, int end){
        int i = start; int j = end;
        int pivot = arr[start];
        while(i < j){
            if(i < j && arr[j] >= pivot) j--;
            if(i < j) arr[i] = arr[j];
            if(i < j && arr[i] <= pivot) i++;
            if(i < j) arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }


    // 3-way-partition
    void three_way_partition(int[] nums, int target){
        int i = 0, j = 0, k = nums.length - 1;
        // j遇到小于 target的值 x ,则与i交换，i++。i就是下一个应该交换的位置。
        // j遇到大于 target的值 y, 则于k交换，k--. k就是下一个应该交换的位置。
        // 
        while(j < k){
            if(nums[j] > target){
                swap(nums, j, k);
                // 此处j增加的原因是，原本k的位置的值其大小不知道。可能会大于也可能会小于target,也就是说，
                // 交换回来的值仍然大于或者小于target.  
                // 换回大的，那么j让然要再次交换。
                // 换回小的，那么j之前还可能有 等于target的没有换到后边。那么最后答案就会出现 小于target出现在target后边。
                --k;
            }
            else if(nums[j] < target){
                swap(nums, j, i);
                ++i;
                // 次数j增加的原因是，i<=j, i==j时就无需交换，i < j 时，由于j已经经过 i, i处的值必然小于等于 j的值。
                // 交换回来后，j处的值必然小于等于target。
                // ij为什么要交换呢? , nums[i]<=target   nums[j]<target   也就是说，nums[i]可能等于target. 
                // 所以就是为了这种可能而交换，如果前边没有遇到过target,那么交换就无意义。
                // 如果前边遇到过target,那么交换就有意义。
                // i之前的值一定小于 target. 而 i <= j  所以 nums[i] <= target. 
                ++j;
            }
            else{
                // 此时nums[j] == target, 不进行交换，停留在当前位置，如果后边有小于target的值，那么就可能被交换到后边。
                ++j;
            }
        }
    }
}
public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] arr = {1,1,2,1,2,2,1};
        so.wiggleSort(arr);
        for(int k = 0; k < arr.length; k++)
            System.out.print(arr[k]+"  ");

    }
}


class Solution {
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int target = quickSelect_well(nums, (len+1)/2);
        three_way_partition(nums, target);
        int mid = (len - 1) /2;
        int[] left = Arrays.copyOfRange(nums, 0, mid+1);
        int[] right = Arrays.copyOfRange(nums, mid+1, len);

        for(int i = 0; i < left.length; i++){
            nums[2*i] = left[left.length - 1 - i];
        }
        for(int i = 0; i < right.length; i++){
            nums[2*i+1] = right[right.length - 1 - i];
        }

    }
      // 在arr中找第k小
      int quickSelect_well(int[] arr, int k){
        int start = 0; int end = arr.length-1;
        while(true){
            int ind = partition(arr, start, end);
            if(ind+1 == k) return arr[ind];
            else if(ind+1 > k) end = ind-1;
            else start = ind+1;
        }
    }
    // partition : 该函数一定要单独写避免上边直接在quickSelect中做划分。可以看到参数十分的混乱
    int partition(int[] arr, int start, int end){
        int i = start; int j = end;
        int pivot = arr[start];
        while(i < j){
            if(i < j && arr[j] >= pivot) j--;
            arr[i] = arr[j];
            if(i < j && arr[i] <= pivot) i++;
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }

    void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // 3-way-partition
    void three_way_partition(int[] nums, int target){
        int i = 0, j = 0, k = nums.length - 1;
        while(j < k){
            if(nums[j] > target){
                swap(nums, j, k);
                --k;
            }
            else if(nums[j] < target){
                swap(nums, j, i);
                ++i;
                ++j;
            }
            else{
                ++j;
            }
        }
    }
}