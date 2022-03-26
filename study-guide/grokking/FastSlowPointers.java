public class FastSlowPointers {

    public static boolean detectCycle(Node head) {
        // Time: O(n), Space: O(1)
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
        // Time: O(n), Space: O(1)
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
        // Time: O(n), Space: O(1)
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

    public static boolean findHappyNumber(int num) {
        // Time: O(log(n)), Space: O(1)
        int slow = num;
        int fast = num;
        do {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast)); // repeat the function twice on the same number to be ahead of slow
        } while (fast != slow); // eventually, we know that the sequence of numbers will repeat the cycle
                                // because we end up at 1 or slow and fast meet up
        return slow == 1;
    }

    public static int squareSum(int num) {
        // This is a helper function connected to the above function
        int digit = 0;
        int sum = 0;
        while (num != 0) {
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static Node findMiddle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static boolean isPalindrome(Node head) {
        // Time: O(n), Space: O(1)
        // can use the below commented lines but don't have to
        // if (head == null || head.next == null)
        // return true;
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) { // find the middle of the LL
            fast = fast.next.next;
            slow = slow.next;
        }
        Node reverse = reverse(slow); // once found, we want to reverse the second half of the LL
        Node reverseHead = reverse; // save the head of the reversedList
        while (reverse != null && head != null) { // iterate and compare our original list and
            if (reverse.value != head.value) { // if the values aren't the same, return false
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        reverse(reverseHead); // unreverse the the list to return the original list
        if (head == null || reverse == null) // if the heads of either list are null, return true
            return true;
        return false;
    }

    public static Node reverse(Node head) {
        // Helper code to reverse a linked list
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}