package robot;

import data.Point;

/**
 * ��ʽ����
 * front��ָ����������ͷ�ڵ�
 * rear: ��Զָ�����ĩβ�ڵ�
 * @param <T>
 */
public class LinkQueue<Point>{
    // ָ��ͷ�ڵ�(��ͷ)
    private Entry<Point> front;
    // ָ��β�ڵ�(��β)
    private Entry<Point> rear;
    // ��¼���нڵ�ĸ���
    private int count;

    /**
     * ��ʼ����front��rear��ָ��ͷ�ڵ�
     */
    public LinkQueue(){
        this.front = this.rear = new Entry<>(null, null);
    }

    /**
     * ��Ӳ���
     * @param val
     */
    public void offer(Point val){
        Entry<Point> node = new Entry<>(val, null);
        this.rear.next = node;
        this.rear = node;
        this.count++;
    }

    /**
     * ���Ӳ���
     * @return
     */
    public Point poll(){
    	Point val = null;
        if(this.front.next != null){
            val = this.front.next.data;
            this.front.next = this.front.next.next;
            // ɾ���������һ��Ԫ�أ�Ҫ��rearָ��front�����в����п�
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
     * �ж϶��п�
     * @return
     */
    public boolean isEmpty(){
        return this.front == this.rear;
    }

    /**
     * ���ض���Ԫ�صĸ���
     * @return
     */
    public int size(){
        return this.count;
    }

    /**
     * �ڵ����Ͷ���
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