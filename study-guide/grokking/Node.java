public class Node {
    // Helper class for files dealing with LLs
    public Node prev;
    public Node next;
    public int value;

    public Node(int value) {
        this.next = null;
        this.prev = null;
        this.value = value;
    }
}
