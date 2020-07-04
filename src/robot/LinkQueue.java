package robot;

/**
 * ��ʽ����
 * front��ָ����������ͷ�ڵ�
 * rear: ��Զָ�����ĩβ�ڵ�
 * @param <T>
 */
public class LinkQueue<T>{
    // ָ��ͷ�ڵ�(��ͷ)
    private Entry<T> front;
    // ָ��β�ڵ�(��β)
    private Entry<T> rear;
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
    public void offer(T val){
        Entry<T> node = new Entry<>(val, null);
        this.rear.next = node;
        this.rear = node;
        this.count++;
    }

    /**
     * ���Ӳ���
     * @return
     */
    public T poll(){
        T val = null;
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

    public T peek(){
        T val = null;
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
     * @param <T>
     */
    static class Entry<T>{
        T data;
        Entry<T> next;

        public Entry(T data, Entry<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}