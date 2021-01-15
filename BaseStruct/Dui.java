package BaseStruct;

/*
堆的两个 主要方法就是 ： 向上调整 up 和 向下 调整 dowm。

up : 将堆末尾元素向上调整。
dowm ： 将堆顶元素向下调整。

入队 :
    1. 若有空间，追加到堆尾部，然后 up ,向上调整。
    2. 若无空间，和堆顶比较，小于堆顶则放弃，否则，覆盖堆顶，dowm将堆顶元素向下调整

出队 : 返回堆顶， 然后使用堆尾，覆盖堆顶，然后dowm将堆顶元素向下调整


/*
一个固定大小的堆。
*/
class Dui{
    int[] dui;
    int curlen;
    public Dui(int k){
        dui = new int[k];
        curlen = 0;
    }
    // 调整数组最后一个元素到合适的位置。  写好了。
    public void downtoup(){
        if(curlen <= 1) return;
        int cur = curlen - 1;
        int key = dui[cur];
        while(cur > 0){
            int parent = (cur - 1) >>> 1;
            if(key >= dui[parent]) break;
            dui[cur] = dui[parent]; // 父亲下调，儿子才能上调。
            cur = parent;
        }
        dui[cur] = key;
    }
    public void uptodown(){
        if(curlen <= 1) return;
        int cur = 0;
        int key = dui[cur];
        int half = curlen >>> 1;
        while(cur < half){
            int child = (cur << 1) + 1;
            int right = child + 1;
            if(right < curlen && dui[child] > dui[right]) 
                child = right;
            if(key <= dui[child]) break;
            dui[cur] = dui[child];
            cur = child;
        }
        dui[cur] = key;
    }
    // 向堆内添加元素
    public void offer(int key){
        if(curlen < dui.length){  // 堆还有空闲 空间
            dui[curlen++] = key;
            // 自下向上调整。
            downtoup();
        }else{
            if(key > dui[0]){ // 只要新元素大于 堆顶时，才会加入该元素。
                dui[0] = key;
                uptodown();   // 自上向下调整。
            }
        }
    }
    public int poll(){
        if(curlen == 0) return Integer.MIN_VALUE;  // error 
        if(curlen == 1) return dui[--curlen];
        int res = dui[0];
        dui[0] = dui[--curlen];
        uptodown();
        return res;
    }
}