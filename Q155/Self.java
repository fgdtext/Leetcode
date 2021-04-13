package Q155;

import java.util.*;

public class Self {
    
}

class Self1 {
    
    LinkedList<Integer> stack;
    PriorityQueue<Integer> queue;

    /** initialize your data structure here. */
    public Self1() {
        stack = new LinkedList<>();
        queue = new PriorityQueue<>();
    }
    
    public void push(int x) {
        stack.addFirst(x);
        queue.add(x);
    }
    // 不返回。
    public void pop() {
        if(!stack.isEmpty()){
            queue.remove(stack.pop());
        }
    }
    
    public int top() {
        if(!stack.isEmpty()){
            return stack.peek();
        }
        return -1;
    }
    
    public int getMin() {
        return queue.peek();
    }
}


/*
辅助栈用法  ：  添加时; 都添加。
minStack： 单调栈  存入的数 > minStack.top 时 再存一个minStack.top
这样删除 xStack栈顶时， 删除一个minStack：栈顶。不会导致删除之前最小的。

xStack  ： -3  6  9  1  0  4  2  8  3
minStack： -3 -3 -3 -3 -3 -3 -3 -3 -3

xStack  ： 3  6  9  1  0  4  2  8  3
minStack： 3  3  3  1  0  0  0  0  0  ----》

xStack  ： 3  6  9  1  0  假设已经删除到该位置
minStack： 3  3  3  1  0  
此时 再删除 0， 则 最小值就会发生变化 
xStack  ： 3  6  9  1 
minStack： 3  3  3  1  

即；xStack每时每刻的栈顶， 都有一个与之对应的最小值元素。
*/


class MinStack2 {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack2() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }
    
    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }
    
    public void pop() {
        xStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return xStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}


class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        xStack.push(x);
        if(minStack.isEmpty() || minStack.peek() >= x) minStack.push(x);
    }
    
    public void pop() {
       int x =  xStack.pop();
       if(!minStack.isEmpty() && minStack.peek() == x) minStack.pop();
    }
    
    public int top() {
        return xStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}