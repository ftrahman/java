public class Stack {
    private int maxSize;
    private LinkedList stack;
    private int size;

    public Stack(int size) {
        this.stack = new LinkedList();
        this.maxSize = size;
        this.size = 0;
    }

    public Stack() {
        this.stack = new LinkedList();
        this.maxSize = Integer.MAX_VALUE;
        this.size = 0;
    }

    public void push(int data) {
        this.size++;
        this.stack.addToHead(data);
    }

    public int pop() {
        this.size--;
        return this.stack.removeHead();
    }

    public int peek() {
        return this.stack.head.getVal();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean hasSpace() {
        return this.size < this.maxSize;
    }
}
