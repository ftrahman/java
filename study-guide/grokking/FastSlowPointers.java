public class FastSlowPointers {

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
            if (slow == fast) { // instead of returning a boolean here, in order to detect the length of the
                                // cycle
                Node cur = slow; // initiate a new node equal to slow
                do {
                    length++; // increment length and set cur equal to the next node
                    cur = cur.next; // while slow is not equal to the cur node because
                } while (slow != cur); // when cur == slow, cur has done a full cycle length
            }
        }
        return length;
    }

    public static Node findCycleStart(Node head) {
        Node fast = head;
        Node slow = head;
        int length = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                Node cur = slow;
                do {
                    length++;
                    cur = cur.next;
                } while (slow != cur); // this algorithm is also similar to the previous one, however to detect the
                                       // start of the cycle
                Node p1 = head; // we initialize two new pointers
                Node p2 = head;
                while (length != 0) { // and move the first pointer up the list length or K node times, all the while
                                      // decremeting from length
                    p1 = p1.next;
                    length--;
                }
                while (p1 != p2) { // then increment each pointer up one step until they run into each other,
                                   // because then p1 will have completed one whole loop through the cycle
                    p1 = p1.next;
                    p2 = p2.next;
                }
                return p1;
            }
        }
        return null;
    }
}