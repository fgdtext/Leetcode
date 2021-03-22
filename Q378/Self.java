package Q378;

import java.util.*;

public class Self {
    
}

class Ans {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}

/*
不是使用下标来二分， 而是取 最小值，和最大值进行二分查找。 

太妙了啊。
*/
class Solution{
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0];
        int right = matrix[matrix.length-1][matrix[0].length-1];
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(check(matrix, k, mid, matrix.length)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
    public boolean check(int[][] matrix, int k, int mid,int n) {
        int j = 0; int i = n-1;
        int num = 0;
        while(i >= 0 && j < n){
            if(matrix[i][j] <= mid){
                num += i+1;   // i这一列向上都是小于mid的。 对小于mid的计数，当前位置小于mid，则该列向上的都小于mid.
                j++;
            }else{
                i--;
            }
        }
        return num >= k;
    }
}