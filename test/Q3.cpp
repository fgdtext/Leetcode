#include<bits/stdc++.h>
using namespace std;


/*
回溯。dfs 搜索 全图 中  起点， 到  终点，  有多少种走法。
回溯。dfs
*/


const int direct[][2] = {{-1,0},{1,0},{0,1},{0,-1}};
int G[1000][1000];
int vis[1000][1000];
int x, y;
int x11,y11;
int x22,y22;

int ans;
void dfs(int i, int j,int count);
// int main(){

//     ans = 0;
//     // scanf("%d%d", &x,&y);
//     // scanf("%d%d", &x1,&y1);
//     // scanf("%d%d", &x2,&y2);

//     x = 4; y = 4;
//     x11 = 3; y11 = 0;
//     x22 = 3; y22 = 3;
//     memset(G,1,sizeof(G));
//     dfs(x11,y11,1);
//     printf("ans is : %d",ans);

//     // 防止黑框消失
//     int a;
//     printf("\n--------------------------\n");
//     scanf("%d", a);
//     return 0;
// }
// dfs
void dfs(int i, int j,int count){
    // 来到出口，且走完全部房间
    if(count == y*x && i == x22 && j == y22){
        ans++;
        return;
    }
    // 走完全部房间，但是没有停在出口。
    if(count == y*x)   return; 
    // 在出口，但是没有走完全部房间，直接返回
    if(i == x22 && j == y22)    return;

    //开始dfs, 四个方向

    G[i][j] = 0;

    for(int k = 0; k < 4; k++){
        int new_i = i + direct[k][0];
        int new_j = j + direct[k][1];
        // 不越界， 且 没有访问过。
        if(new_i < x && new_i >=0 && new_j >= 0 && new_j < y && G[new_i][new_j]){
            dfs(new_i,new_j,count+1);
        }
    }
    G[i][j] = 1;
}
