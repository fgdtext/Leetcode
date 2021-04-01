package Q1006;




/*
o(n)的算法 ： 就是去模拟运算。

*/
class Self2 {
    public int clumsy(int N) {
        int ans = 0;
        if(N == 1) return 1;
        boolean isfirst = true;
        int offset = N;
        while(offset > 0){
            int temp = 0;
            if(offset-1 == 0) temp = offset;
            if(offset-1 > 0) temp = offset*(offset - 1);
            if(offset-2 > 0) temp = temp / (offset - 2);
            if(offset-3 > 0) 
                if(isfirst) temp = temp + (offset - 3);
                else temp = temp - (offset - 3);
            if(isfirst) {ans += temp; isfirst = false;}
            else ans -= temp;
            offset -= 4;
        }
        return ans;
    }
}
