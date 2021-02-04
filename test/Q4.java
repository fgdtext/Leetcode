package test;


/*
题目看图 Q4.

题目的本质是求每一层的坐标上点(x,y)互质的个数。 如果一个点的坐标(x,y)是可以约分的，那么一定会被前边的层遮挡。
则互质的点是本层可以被发现的点。
*/
public class Q4 {

    
    // 求出每一层互质的个数。
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