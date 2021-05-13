package JZ.JZ19;
import java.util.ArrayList;
/*
顺时针打印数组

*/
public class Self {
    
}

class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int i = 0, j = -1;
        int n = matrix.length;
        int m = matrix[0].length;
        int k = 0;
        while(k < m*n){
            j++;
            while(j < m && matrix[i][j] != Integer.MIN_VALUE+1){
                ans.add(matrix[i][j]);
                matrix[i][j] = Integer.MIN_VALUE+1;
                j++;
                k++;
            }
            j--;
            i++;
            while(i < n && matrix[i][j] != Integer.MIN_VALUE+1){
                ans.add(matrix[i][j]);
                matrix[i][j] = Integer.MIN_VALUE+1;
                i++;
                k++;
            }
            i--;
            j--;
            while(j >= 0 && matrix[i][j] != Integer.MIN_VALUE+1){
                ans.add(matrix[i][j]);
                matrix[i][j] = Integer.MIN_VALUE+1;
                j--;
                k++;
            }
            j++;
            i--;
            while(i >= 0 && matrix[i][j] != Integer.MIN_VALUE+1){
                ans.add(matrix[i][j]);
                matrix[i][j] = Integer.MIN_VALUE+1;
                i--;
                k++;
            }
            i++;   
            
        }                        
        return ans;
    }
}