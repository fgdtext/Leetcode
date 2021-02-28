package Q54;
import java.util.*;



public class Self {
    
}
/*

时间是挺好, 但是 代码太不优雅了.
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 1;
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(matrix[0][0]);
        matrix[0][0] = 101;
        int i = 0, j = 0;
        boolean up = false, dowm = false, left = false, right = true; //默认先向右出发.

        while(count < m*n){
            int ii = i, jj = j;
            if(right){
                jj+=1;
                if(jj < m && matrix[ii][jj] != 101){
                    j = jj;
                    ans.add(matrix[ii][jj]);
                    matrix[ii][jj] = 101;
                    count++;
                }else{ // 转向
                    right = false; dowm = true;
                }
            }
            else if(dowm){
                ii += 1;
                if(ii < n && matrix[ii][jj] != 101){
                    i = ii;
                    ans.add(matrix[ii][jj]);
                    matrix[ii][jj] = 101;
                    count++;
                }else{
                    dowm = false; left = true;
                }
            }
            else if(left){
                jj -= 1;
                if(jj >= 0 && matrix[ii][jj] != 101){
                    j = jj;
                    ans.add(matrix[ii][jj]);
                    matrix[ii][jj] = 101;
                    count++;
                }else{
                    left = false; up = true;
                }
            }
            else if(up){
                ii -= 1;
                if(ii >= 0 && matrix[ii][jj] != 101){
                    i = ii;
                    ans.add(matrix[ii][jj]);
                    matrix[ii][jj] = 101;
                    count++;
                }else{
                    up = false; right = true;
                }
            }
        }
        return ans;
    }
}

// 更好,而且不会改变原数组.
class Ans {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix.length == 0) {
            return res;
        }
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (true) {
            for (int col = left; col <= right; ++col) 
                res.add(matrix[up][col]);
            // 第一行划过,上边界就+1了, 整个矩形上下变窄了. 就可以继续化下一下.

            if (++up > down) // up == dowm时,即当前只有最后一行.而且上一步是划行.接下来划右侧一列.
                break;

            for (int row = up; row <= down; ++row) 
                res.add(matrix[row][right]);

            if (--right < left) 
                break;

            for (int col = right; col >= left; --col) 
                res.add(matrix[down][col]);

            if (--down < up) 
                break;

            for (int row = down; row >= up; --row) 
                res.add(matrix[row][left]);

            if (++left > right) 
                break;
        }
        return res;
    }
}