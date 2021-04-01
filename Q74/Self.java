package Q74;

public class Self {
    public static void main(String[] args) {
        Solution so = new Solution();
        int[][] a = {{-9,-7,-7,-5,-3,-3},{-1,1,2,2,4,6},{8,8,8,8,8,9},{11,13,15,17,19,19}};
        so.searchMatrix(a, 3);
    }
}
/*

先使用第一列的元素， 找到第一个大于target的位置up, 那么target只可能存在于 up -1 行中。因为up行全部大于target



*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int up = 0, down = n-1;
        if(matrix[down][0] == target){
            return true;
        }else if(matrix[down][0] < target) {
            up = down  +1;
        }

        /*
            找第一个大于target的位置 
            为什么 不是 up = mid  down = mid -1
            而是 up = mid + 1, down = mid 
            因为： 我们总是向下取整， 当up + 1 = down 时， mid总是等于up 
            那么若target > matrix[mid][0]  而此时up = mid 那么up就没有改变。 
            导致死循环。
            所以向下取整要求的。找第一个大于target的位置。 对于最后一个位置要特别判断。

            向下取整 ： 找第一个大于target的    mid = (high - low) / 2 + low;
                        low = mid 
            向上取整 ： 找最后一个小于target的  mid = (high - low + 1) / 2 + low;
                        hight = mid 
        */
        while(up < down){
            int mid = up  + ((down - up) >> 1);
            if(target == matrix[mid][0]) return true;
            if(target > matrix[mid][0]){
                up = mid + 1;
            }else{
                down = mid;
            }
        }
        if(up == 0) return false;
        int tarRow = up - 1;
        int left = 0, right = m - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(target == matrix[tarRow][mid]) return true;
            else if(target > matrix[tarRow][mid]){
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        return matrix[tarRow][left] == target;
    }
}