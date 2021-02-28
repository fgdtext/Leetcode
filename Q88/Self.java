package Q88;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[] a = new int[1];
        int[] b = {1};
        so.merge(a, 0, b, 1);
    }
}
/*

假设ind2先到-1. 那么此时nums1恰好就是完全有序的。只有ind1先到-1，才需要把nums2的前半段直接复制到nums1
*/
class Self1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int ind1 = m-1, ind2 = n-1;
        int ind = m+n-1;
        while(ind1 >= 0 && ind2 >= 0){
            int max = 0;
            if(nums1[ind1] >= nums2[ind2]){
                max = nums1[ind1--];
            }else{
                max = nums2[ind2--];
            }
            nums1[ind--] = max;
        }
        if(ind1 < 0){
            while(ind >= 0)
                nums1[ind--] = nums2[ind2--];
        }
    }
}