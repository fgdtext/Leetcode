package Q509;

/*
玩不能写递归求斐波那契。  递归会造成大量的重复计算。
下边的迭代方式，实质就是dp做记录的思想。
*/
class Solution {
    public int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        int a = 0, ans = 1;
        for(int i = 2; i <= n; i++){
            int b = a;
            a = ans;
            ans = a + b; 
        }
        return ans;
    }
}

/*
牛逼 :  矩阵关系
|1 1| |f(n)  |    |f(n)+f(n-1)|    | f(n+1) |
|1 0| |f(n-1)| =  |     f(n)  |  = | f(n)   |

因此有
| f(n+1) |     |1 1|^n | f(1)|
| f(n)   |  =  |1 0|   | f(0)|

只需要求  
|1 1|^n  即可
|1 0|

另外，n == 1时，

定义矩阵乘法 时间是常数，那么若直接求n次方时间为 o(n)
所以使用快速幂。 
*/

class Ans {
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);  //          传入的是n-1
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(res[i][j] + "  ");
            }
            System.out.println();
        }
        return res[0][0];
    }

   // 快速幂
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    // 矩阵乘法
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
