package Q304;

public class Self {
    
}

class NumMatrix1 {
    int[][] sum;
    int[][] matrix;
    int m,n;
    int[][] dir = {{-1,-1},{0,-1},{-1,0}};
    public NumMatrix1(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        m = matrix[0].length;
        sum = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sum[i][j] = matrix[i][j];
                for(int k = 0; k < 3; k++){
                    int ii = i + dir[k][0];
                    int jj = j + dir[k][1];
                    if(ii>=0 && jj>=0){
                        if(k == 0)
                            sum[i][j] -= sum[ii][jj];
                        else 
                            sum[i][j] += sum[ii][jj];
                    }
                }
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int n1 = row2 - row1 + 1;
        int m1 = col2 - col1 + 1;
        int ans = sum[row2][col2];
        if(row2-n1 >= 0) ans -= sum[row2-n1][col2];
        if(col2-m1 >= 0) ans -= sum[row2][col2-m1];
        if(row2-n1 >= 0 && col2-m1 >= 0) ans += sum[row2-n1][col2-m1];
        return ans;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

 
class NumMatrix {
    int[][] sum;
    int[][] matrix;
    int m,n;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        n = matrix.length;
        if(n == 0) return;
        m = matrix[0].length;
        sum = new int[n][m];
        for(int i = 0; i < n; i++){
            int rowsum = 0;
            for(int j = 0; j < m; j++){
                rowsum += matrix[i][j];
                if(i-1 >= 0) sum[i][j] = sum[i-1][j] + rowsum;
                else sum[i][j] = rowsum;
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(n == 0) return 0;
        int n1 = row2 - row1 + 1;
        int m1 = col2 - col1 + 1;
        int ans = sum[row2][col2];
        if(row2-n1 >= 0) ans -= sum[row2-n1][col2];
        if(col2-m1 >= 0) ans -= sum[row2][col2-m1];
        if(row2-n1 >= 0 && col2-m1 >= 0) ans += sum[row2-n1][col2-m1];
        return ans;
    }
}