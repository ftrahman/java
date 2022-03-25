public class FastSlowPointers {

    private static Object Node;

    public static boolean detectCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                return true; // found the cycle
        }
        return false;
    }

    public static int detectCycleLength(Node head) {
        Node slow = head;
        Node fast = head;
        int length = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                Node cur = slow;
                do {
                    length++;
                    cur = cur.next;
                } while (slow != cur);
            }
        }
        return length;
    }
}