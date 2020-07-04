package robot;

import data.Point;

/**
 * 链式队列
 * front：指向的是链表的头节点
 * rear: 永远指向的是末尾节点
 * @param <T>
 */
public class LinkQueue<Point>{
    // 指向头节点(队头)
    private Entry<Point> front;
    // 指向尾节点(队尾)
    private Entry<Point> rear;
    // 记录队列节点的个数
    private int count;

    /**
     * 初始化，front和rear都指向头节点
     */
    public LinkQueue(){
        this.front = this.rear = new Entry<>(null, null);
    }

    /**
     * 入队操作
     * @param val
     */
    public void offer(Point val){
        Entry<Point> node = new Entry<>(val, null);
        this.rear.next = node;
        this.rear = node;
        this.count++;
    }

    /**
     * 出队操作
     * @return
     */
    public Point poll(){
    	Point val = null;
        if(this.front.next != null){
            val = this.front.next.data;
            this.front.next = this.front.next.next;
            // 删除队列最后一个元素，要把rear指向front，队列才能判空
            if(this.front.next == null){
                this.rear = this.front;
            }
            this.count--;
        }
        return val;
    }

    public Point peek(){
    	Point val = null;
        if(this.front.next != null){
            val = this.front.next.data;
        }
        return val;
    }

    /**
     * 判断队列空
     * @return
     */
    public boolean isEmpty(){
        return this.front == this.rear;
    }

    /**
     * 返回队列元素的个数
     * @return
     */
    public int size(){
        return this.count;
    }

    /**
     * 节点类型定义
     * @param <Point>
     */
    static class Entry<Point>{
    	Point data;
        Entry<Point> next;

        public Entry(Point data, Entry<Point> next) {
            this.data = data;
            this.next = next;
        }
    }
}