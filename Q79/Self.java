package Q79;





public class Self {
    public static void main(String[] args) {
        String word = "ABCD";
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        Solution s = new Solution();
        boolean ans =  s.exist(board, word);
        System.out.print(ans);
    }
}

/*
随机起点 的  合法路径 搜索。

*/

class Solution {
    boolean[][][] dp;
    boolean[][] vis;
    int[][] direct = {{0,1},{1,0},{-1,0},{0,-1}};
    public boolean exist(char[][] board, String word) {
        if(board.length == 1 && board[0].length == 1){
            if(word.length() == 1){
                if(board[0][0] == word.charAt(0)) return true;
            }else{
                return false;
            }
        }
        vis = new boolean[board.length][board[0].length];
        dp = new boolean[board.length][board[0].length][word.length()];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0))
                if(dfs(board, word, i, j, 0)) return true;
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, int i, int j,int curlen){
        if(curlen == word.length()){
            return true;
        }
        if(vis[i][j]) return false;
        vis[i][j] = true;
        if(word.charAt(curlen) != board[i][j]){
            vis[i][j] = false;
            return false;
        } 
        // 4个 可能的方向
        for(int k = 0; k < 4; k++){
            int new_i = direct[k][0] + i;
            int new_j = direct[k][1] + j;
            // 防止越界
            if(new_i >= 0 && new_i < board.length && new_j >= 0 && new_j < board[0].length){
                
                if(dfs(board, word, new_i, new_j, curlen+1)) return true;
            }
        }
        vis[i][j] = false;
        return false;
    }
}