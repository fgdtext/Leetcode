package Q73;

public class Self {
    public static void main(String[] args) {
        int[][] matrix = {{1},{0},{2}};
        Solution so = new Solution();
        so.setZeroes(matrix);
    }
}

class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean row = false;
        boolean col = false;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!row && i == 0 && matrix[i][j] == 0) row = true;
                if(!col && j == 0 && matrix[i][j] == 0) col = true;
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int j = 1; j < m; j++){
            if(matrix[0][j] == 0){
                for(int i = 1; i < n; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 1; i < n; i++){
            if(matrix[i][0] == 0){
                for(int j = 1; j < m; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        if(row)for(int j = 0; j < m; j++) matrix[0][j] = 0;
        if(col)for(int i = 0; i < n; i++) matrix[i][0] = 0;
    }
}