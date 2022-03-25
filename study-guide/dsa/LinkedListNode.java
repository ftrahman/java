public class LinkedListNode {
    private LinkedListNode prev;
    private LinkedListNode next;
    private int value;
    private String key;

    public LinkedListNode(int value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }

    public LinkedListNode(String key, int value) {
        this.next = null;
        this.prev = null;
        this.value = value;
        this.key = key;
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

    public int getVal() {
        return this.value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKeyVal(String key, int val) {
        this.key = key;
        this.value = val;
    }
}
