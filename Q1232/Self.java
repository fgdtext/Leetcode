package Q1232;

public class Self {
    public static void main(String[] args) {
       // Solution
    }
}


class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int u = coordinates[0][0], v = coordinates[0][1];
        int a = coordinates[1][0] - u;
        int b = coordinates[1][1] - v;    
        for(int i = 2; i < coordinates.length; i++){
            int na = coordinates[i][0] - u;
            int nb = coordinates[i][1] - v;
            if(na*b != nb*a) return false;
        }
        return true;
    }
}