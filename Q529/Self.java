package Q529;
/*

m简单题 ， 没啥难的， 注意规则就好

*/


class Solution {
    int[][] direct = {
        {0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}
    };
    char[][] myboard;
    int lenr;
    int lenc;

    public char[][] updateBoard(char[][] board, int[] click) {
        int target_i = click[0]; 
        int target_j = click[1];
        myboard = board;
        lenr = board.length;
        if(lenr == 0) return board;
        lenc = board[0].length;
        char target = myboard[target_i][target_j];
        
        if(target == 'M'){
            myboard[target_i][target_j] = 'X';
            return myboard;
        }
        if(target == 'E'){
           digui(target_i, target_j);
        }
        return myboard;
    }
    public void digui(int i, int j){
        int count = 0;
        for(int k =0; k < 8; k++){
            int temp_i = i + direct[k][0];
            int temp_j = j + direct[k][1];
            if(temp_i < lenr && temp_i >= 0 && temp_j < lenc && temp_j >= 0){
                char c = myboard[temp_i][temp_j];
                if(c == 'M') count++;
            }
        }
        if(count != 0) myboard[i][j] = (char) ('0' + count);
        else{
            myboard[i][j] = 'B';
            for(int k = 0; k < 8; k++){
                int temp_i = i + direct[k][0];
                int temp_j = j + direct[k][1];
                if(temp_i < lenr && temp_i >= 0 && temp_j < lenc && temp_j >= 0){
                    char c = myboard[temp_i][temp_j];
                    if(c == 'E'){
                        digui(temp_i,temp_j);
                    }
                }
            }
        }
        // 递归

    }
}


/*
3:::0:::0
3:::1
3:::1:::0
3:::2
3:::2:::0
3:::3
3:::3:::0
3:::2
2:::3
2:::3:::1
2:::2
2:::2:::1
3:::1
2:::2
2:::3
2:::1
2:::1:::1
3:::0
2:::1
2:::2
2:::0
2:::0:::0
2:::1
3:::0
1:::0
1:::0:::0
1:::1
1:::1:::1
2:::0
0:::0
0:::0:::0
0:::1
0:::1:::1
1:::0
1:::1
2:::1
0:::1
3:::1
1:::1
2:::0
2:::1



*/

/*
3:::0:::0
3:::1:::0
3:::2:::0
3:::3:::0
2:::3:::1
2:::2:::1
2:::1:::1
2:::0:::0
1:::0:::0
1:::1:::1
0:::0:::0
0:::1:::1


*/