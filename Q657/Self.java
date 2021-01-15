package Q657;

import javax.swing.text.AbstractDocument.BranchElement;

class Solution {
    public boolean judgeCircle(String moves) {
        int r = 0, l = 0, u = 0, d = 0;
        int len = moves.length();
        for(int i = 0; i < len; i++){
            switch(moves.charAt(i)){
                case 'R' : r++; break;
                case 'L' : l++; break;
                case 'U' : u++; break;
                case 'D' : d++; break;
            }
        }
        return r == l && u == d;
    }
}