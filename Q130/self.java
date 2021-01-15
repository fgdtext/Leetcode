package Q130;

/*
涂色问题：char[][] dfs涂色。
*/


class Solution {
    
    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    char[][] board;
    int lenw;
    int lend;
    public void solve(char[][] board) {
        this.board = board;
        this.lend = board.length;
        if(lend == 0) return;
        this.lenw = board[0].length;
        if(lenw == 0) return;
        
        for(int j = 0; j < lenw; j++){
            dfs(0, j);
            dfs(lend-1, j);
        }
        for(int i = 0; i < lend; i++){
            dfs(i, 0);
            dfs(i, lenw -1);
        }
        for(int i = 0; i < lend; i++){
            for(int j = 0; j < lenw; j++){
               if(board[i][j] == 'O') board[i][j] = 'X';
               if(board[i][j] == '1') board[i][j] = 'O';
            }
        }
    }
    public void dfs(int i, int j){
        if(board[i][j] != 'O') return;
        board[i][j] = '1';
        for(int k = 0; k < 4; k++){
            // 对四个方向进行深搜。
            int ii = i + dir[k][0];
            int jj = j + dir[k][1];
            boolean ok = ii >= 0 && ii < lend && jj >= 0 && jj < lenw ;
            if(ok && board[ii][jj] == 'O'){
                dfs(ii, jj);
            }
        }
    }
}