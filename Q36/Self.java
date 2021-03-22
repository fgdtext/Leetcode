package Q36;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] flag = new int[3][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                char c = board[i][j];
                if(Character.isDigit(c)){
                    int num = c-'0';
                    int offset = (1 << num);
                    if((flag[0][i] & offset) > 0)  return false;
                        flag[0][i] = flag[0][i] | offset;
                    if((flag[1][j] & offset) > 0)  return false;
                        flag[1][j] = flag[1][j] | offset;
                    if((flag[2][i/3*3 + j/3] & offset) > 0) return false;
                        flag[2][i/3*3 + j/3] = flag[2][i/3*3 + j/3] | offset;
                }
            }
        }
        return true;
    }
}

/*class

                    if(i == 3 && j == 0){
                        for(int ii = 0; ii < 9; ii++){
                            for(int jj = 0; jj < 9; jj++){
                                System.out.print(flag[ii][jj]);
                            }
                        }
                    }

*/