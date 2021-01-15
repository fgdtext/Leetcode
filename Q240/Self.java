package Q240;

public class Self {
    
}


/*

4分取中， 但是时间不好。  每次只能排除1/4的区域


再优化 ： 4分之后，假设只有一个 存在 target ,  对于每一个矩阵。左上角最小， 右下角最大。 如果寻找的值不在这个范围，那么就不需要找。

再再优化  ：  目前这种分法 ，每次都要去剩下的3快中去找。   可以先找中间列， 然后对中间列二分查找，找到就返回，否则就找出 target 在该列的有序插入位置
这样，右下角一定大于target,  左上角一定小于target ,所以这两个区域都不包含 target.   
*/
class Self1 {
    int[][] matrix;
    int target;
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        this.matrix = matrix;
        this.target = target;
        return serach(0, 0, matrix.length - 1, matrix[0].length - 1);
    }
    public boolean serach(int i1, int j1, int i2, int j2){
        if(i1 > i2 || j1 > j2) return false;
        int midi = (i1+i2)/2;
        int midj = (j1+j2)/2;
        if(matrix[midi][midj] == target) return true; // 找到了
        boolean flag = false;
        if(i2 == i1 && j1 == j2) return matrix[i1][j1] == target; // 只有一个元素。
        if(i2 == i1){ // 只有一行  // 二分
            if(matrix[midi][midj] > target){
                flag = serach(i1, j1, i1, midj-1);
                if(flag) return true;
            }else{
                flag = serach(i1, midj+1, i1, j2);
                if(flag) return true;
            }
        }
        if(j2 == j1){ // 只有一行  // 二分
            if(matrix[midi][midj] > target){
                flag = serach(i1, j1, midi-1, j1);
                if(flag) return true;
            }else{             
                flag = serach(midi+1, j2, i2, j2);
                if(flag) return true;
            }
        }
        if(matrix[midi][midj] > target){
            flag = serach(i1, j1, midi-1, midj-1);
            if(flag) return true;
            flag = serach(i1, midj, midi-1, j2);
            if(flag) return true;
            flag = serach(midi, j1, i2, midj-1);
            if(flag) return true;
        }else{
            flag = serach(midi+1,  midj+1, i2, j2);
            if(flag) return true;
            flag = serach(i1, midj+1, midi, j2);
            if(flag) return true;
            flag = serach(midi+1, j1, i2, midj);
            if(flag) return true;
        }
        return false;
    }
}


/*

太他妈的妙了， 一直沉浸在如何二分，  从图中可以发现，  对每一个  m[i][j] 都有   m[i][j+1] >  m[i][j] > m[i-1][j]   

于是 我们可以从 m[n-1][0] 开始搜索。 每次向上或者向右移动。 直到遇到边界。

移动的次数最多 n+m 所以时间  o(n+m)

太妙了啊  ..  最好的方法。
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }
            // 越界就跳出。
        return false;
    }
}