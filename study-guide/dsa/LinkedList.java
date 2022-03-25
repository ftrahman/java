public class LinkedList {
    LinkedListNode head;
    int length;

    public LinkedList() {
        this.length = 0;
        this.head = null;
    }

    public void addToHead(int data) {
        LinkedListNode newNode = new LinkedListNode(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    public void addToTail(int data) {
        LinkedListNode tail = this.head;
        if (this.head == null) {
            this.head = new LinkedListNode(data);
        } else {
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(new LinkedListNode(data));
        }
    }

    public int removeHead() {
        if (this.head == null)
            return -1;
        LinkedListNode cur = this.head;
        this.head = this.head.getNext();
        return cur.getVal();

    }

    public void printList() {
        if (this.head == null) {
            System.out.println("null ->");
        } else {
            LinkedListNode cur = this.head;
            while (cur.getNext() != null) {
                System.out.print(cur.getVal() + " -> ");
                cur = cur.getNext();
            }
            System.out.print(cur.getVal() + " -> null\n");
        }
    }
}