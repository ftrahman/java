public class Queue {
    private int size;
    private LinkedList queue;
    private int maxSize;

    public Queue(int size) {
        this.queue = new LinkedList();
        this.size = 0;
        this.maxSize = size;
    }

    public Queue() {
        this.queue = new LinkedList();
        this.size = 0;
        this.maxSize = Integer.MAX_VALUE;
    }

    public void enqueue(int data) {
        this.size++;
        this.queue.addToTail(data);
    }

    public int dequeue() {
        this.size--;
        return this.queue.removeHead();
    }

    public int peek() {
        return this.queue.head.getVal();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean hasSpace() {
        return this.size < this.maxSize;
    }
}
