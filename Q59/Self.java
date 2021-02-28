package Q59;

public class Self {
    
}

// ok  划去一行少一行,划去一列少一列.
// 使用up, dowm,left,right圈定边界.
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int up = 0, dowm = n-1, left = 0, right = n-1;
        int count = 1;
        while(true){
            for(int i = left; i <= right; i++) ans[up][i] = count++; 
            if(++up > dowm) break;
            for(int i = up; i <= dowm; i++) ans[i][right] = count++;
            if(--right < left) break;
            for(int i = right; i >= left; i--) ans[dowm][i] = count++;
            if(--dowm < up) break;
            for(int i = dowm; i >= up; i--) ans[i][left] = count++;
            if(++left > right) break;
        }
        return ans;
    }
}