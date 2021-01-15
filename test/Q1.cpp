#include<bits/stdc++.h>
using namespace std;
int m, k;
int l = 0;
vector<int> st;
void dfsss(int cur,int add);
void dfss(int cur,int add);
void dfs(int cur,int add);
// int main(){
//     scanf("%d%d",&m,&k);
//     dfsss(1,0);
//     cout<< l << endl;
//         // 防止黑框消失
//     char a, b;
//     printf("\n--------------------------\n");
//     scanf("%d", a);
//     return 0;
// }
/*
将 m 分解为 多个数的和， 且每个数， 不能被 k 整除。
详情看 img_Q1

回溯法
*/

void dfsss(int cur,int add){
    if(add == m){
        l++; return;
    }
    if(cur > m) return;
    if(cur % k != 0) dfsss(cur+1,add+cur);
    dfsss(cur+1,add);
}