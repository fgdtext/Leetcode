package BaseStruct;

/*

1~N中与N互质的数的个数叫欧拉函数，记为φ(N)

互质是公约数只有1的两个整数，叫做互质整数 ，两个数可以不是质数，但是两个数不能约分。

*/

class Self{



    int phi(int x)
    {
        int res=x;
        for(int i=2;i*i<=x;++i)
        {
            if(x%i==0)
            {
                res=res/i*(i-1);
                while(x%i==0) x/=i;
            }
        }
        if(x>1) res=res/x*(x-1);
        return res;
    }

}



/*class
一道阿里面试题
*/

