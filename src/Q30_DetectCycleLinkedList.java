/** Q30: Detect a cycle with Floyd's tortoise and hare: O(n) time, O(1) space. Also finds the cycle start. */
public class Q30_DetectCycleLinkedList {

    static class Node {
        int val; Node next;
        Node(int v) { val = v; }
    }

    static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    static Node cycleStart(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;                       // distance head->start == meeting->start
                while (slow != fast) { slow = slow.next; fast = fast.next; }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1), n2 = new Node(2), n3 = new Node(3), n4 = new Node(4), n5 = new Node(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        System.out.println("No cycle: " + hasCycle(n1));
        n5.next = n3;
        System.out.println("With cycle: " + hasCycle(n1) + ", starts at node " + cycleStart(n1).val);
    }
}
