package Q289;

public class Self {
    public static void main(String[] args) {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        Solution so = new Solution();
        so.gameOfLife(board);
    }
}

class Solution {
    int n;
    int m;
    public void gameOfLife(int[][] board) {
        n = board.length;
        m = board[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int count = getOneNums(board, i, j);
                if(board[i][j] == 1 && (count < 2 || count > 3)) board[i][j] = 3;
                else if(board[i][j] == 0 && count == 3) board[i][j] = 2;
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
               if(board[i][j] == 3) board[i][j] = 0;
               if(board[i][j] == 2) board[i][j] = 1;
            }
        }
    }
    public int getOneNums(int[][] board, int i, int j){
        int count = 0;
        for(int k = -1; k <= 1; k++){
            for(int l = -1; l <= 1; l++){
                if(k == 0 && l == 0) continue;
                if(i+k >= 0 && i+k < n && j+l >=0 && j+l < m){
                    if(board[i+k][j+l] % 2 == 1) count++;
                }
            }
        }
        return count;
    }
}