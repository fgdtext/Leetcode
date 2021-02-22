package Q765;

/*  
    人  0 1 2 3 4 5   正常 01 23 45 正好三对。且队伍序号应为 0,1,2   (0,1)/2 = (0,0) 向下取整是队伍名
    打乱 0 5 3 4 1 2  (0,5)/2 = (0,2) 正好把两个点连接在一起。
    在这里 一对情侣恰好扮演边的角色。可以连接两个集合。


    其实每次交换只要能成全一堆情侣，总交换次数不变。因此可以考虑贪心。
*/
class Solution {
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int N = len /2;
        UnionSet set = new UnionSet(N);
        for(int i = 0; i < len; i+=2){
            set.union(row[i]/2, row[i+1]/2);
        }
        return N - set.getCount();
    }
    class UnionSet{
        int[] parent;
        int count;
        public UnionSet(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
            count = n;
        }
        public int find(int x){
            if(x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public int getCount(){
            return count;
        }
        public void union(int x, int y){
            int xx = find(x);
            int yy = find(y);
            if(xx != yy){
                parent[xx] = yy;
                count--;
            }
        }
    }
}