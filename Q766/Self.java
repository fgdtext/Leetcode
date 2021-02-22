package Q766;




/*

太麻烦了。

*/
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        for(int j = 0; j < m; j++){
            int ii = 0;
            int jj = j;
            int num = matrix[ii][jj];
            while(ii < n && jj < m){
                if(num != matrix[ii][jj]) return false;
                ii += 1;
                jj += 1;
            }
        }
        for(int i = 0; i < n; i++){
            int ii = i;
            int jj = 0;
            int num = matrix[ii][jj];
            while(ii < n && jj < m){
                if(num != matrix[ii][jj]) return false;
                ii += 1;
                jj += 1;
            }
        }
        return true;
    }
}

/*
我是智障
*/
class Ans {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}