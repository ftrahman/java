public class LinkedList {
    LinkedListNode head;
    int length;

    public LinkedList() {
        this.length = 0;
        this.head = null;
    }

    public void addToHead(int data) {
        LinkedListNode newNode = new LinkedListNode(data);
        this.length++;
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    public void addToTail(int data) {
        this.length++;
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

    public int removeHead(boolean printVal) {
        this.length--;
        if (this.head == null)
            return -1;
        LinkedListNode cur = this.head;
        this.head = this.head.getNext();
        return cur.getVal();

    }

    public void removeHead() {
        this.length--;
        if (this.head == null)
            System.out.println("LL is empty!");
        else {
            this.head = this.head.getNext();
        }
    }

    public void swap(int data1, int data2) {
        LinkedListNode one = this.head;
        LinkedListNode two = this.head;
        LinkedListNode prevOne = null;
        LinkedListNode prevTwo = null;
        while (one != null) {
            if (one.getVal() == data1) {
                break;
            }
            prevOne = one;
            one = one.getNext();
        }
        while (two != null) {
            if (two.getVal() == data2) {
                break;
            }
            prevTwo = two;
            two = two.getNext();
        }
        if (prevOne == null)
            this.head = two;
        else
            prevOne.setNext(two);
        if (prevTwo == null)
            this.head = one;
        else
            prevTwo.setNext(one);
        LinkedListNode temp = one.getNext();
        one.setNext(two.getNext());
        two.setNext(temp);
    }

    public void printList() {
        if (this.head == null) {
            System.out.print("(LENGTH " + this.length + "): null ->\n");
        } else {
            LinkedListNode cur = this.head;
            System.out.print("(LENGTH " + this.length + "): ");
            while (cur.getNext() != null) {
                System.out.print(cur.getVal() + " -> ");
                cur = cur.getNext();
            }
            System.out.print(cur.getVal() + " -> null\n");
        }
    }
}