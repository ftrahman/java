public class Main {
    public static void main(String[] args) {
        LinkedList LL = new LinkedList();
        LL.addToHead(2);
        LL.addToHead(8);
        LL.addToHead(6);
        LL.printList();
        LL.addToTail(4);
        LL.addToTail(7);
        LL.printList();
        LL.addToHead(2);
        LL.printList();
        LL.removeHead();
        LL.removeHead();
        LL.removeHead();
        LL.printList();
    }
}
