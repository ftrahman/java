public class LinkedListNode {
    LinkedListNode prev;
    LinkedListNode next;
    int value;

    public LinkedListNode(int value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }

    public void setNext(LinkedListNode node) {
        this.next = node;
    }

    public LinkedListNode getNext() {
        return this.next;
    }

    public void setPrev(LinkedListNode node) {
        this.prev = node;
    }

    public LinkedListNode getPrev() {
        return this.prev;
    }
}
