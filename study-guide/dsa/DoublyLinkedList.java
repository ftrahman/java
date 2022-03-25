import java.util.LinkedList;

public class DoublyLinkedList {
    public LinkedListNode head;
    public LinkedListNode tail;
    public int length;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public void addToHead(int data) {
        this.length++;
        LinkedListNode newNode = new LinkedListNode(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.head.setPrev(newNode);
            newNode.setNext(this.head);
            this.head = newNode;
        }
    }

    public void addToTail(int data) {
        this.length++;
        LinkedListNode newNode = new LinkedListNode(data);
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
        }
    }

    public int removeHead() {
        if (this.head == null) {
            return -1;
        }
        LinkedListNode remove = this.head;
        this.head = this.head.getNext();
        this.length--;
        if (this.head != null)
            this.head.setPrev(null);
        if (remove == this.tail)
            removeTail();
        return remove.getVal();
    }

    public int removeTail() {
        if (this.tail == null) {
            return -1;
        }
        LinkedListNode remove = this.tail;
        this.tail = this.tail.getPrev();
        this.length--;
        if (this.tail != null)
            this.tail.setNext(null);
        if (remove == this.head) {
            removeHead();
        }
        return remove.getVal();
    }

    public int removeByData(int data) {
        LinkedListNode cur = this.head;
        if (cur == null)
            return -1;
        while (cur != null) {
            if (cur.getVal() == data) {
                break;
            }
            cur = cur.getNext();
        }
        if (cur == null)
            return -1;
        if (cur == this.head)
            removeHead();
        if (cur == this.tail)
            removeTail();
        else {
            this.length--;
            LinkedListNode prev = cur.getPrev();
            LinkedListNode next = cur.getNext();
            prev.setNext(next);
            next.setPrev(prev);
        }
        return cur.getVal();
    }

    public void printList() {
        if (this.head == null) {
            System.out.print("(LENGTH " + this.length + "): null <->\n");
        } else {
            LinkedListNode cur = this.head;
            System.out.print("(LENGTH " + this.length + "): ");
            while (cur.getNext() != null) {
                System.out.print(cur.getVal() + " <-> ");
                cur = cur.getNext();
            }
            System.out.print(cur.getVal() + " <-> null\n");
        }
    }
}
