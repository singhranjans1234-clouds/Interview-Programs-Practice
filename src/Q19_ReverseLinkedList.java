/** Q19: Reverse a singly linked list, iterative O(1) space and recursive O(n) stack. */
public class Q19_ReverseLinkedList {

    static class Node {
        int val; Node next;
        Node(int v) { val = v; }
    }

    static Node reverseIterative(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static Node reverseRecursive(Node head) {
        if (head == null || head.next == null) return head;
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static Node build(int... v) {
        Node dummy = new Node(0), t = dummy;
        for (int x : v) { t.next = new Node(x); t = t.next; }
        return dummy.next;
    }

    static String str(Node n) {
        StringBuilder sb = new StringBuilder();
        for (; n != null; n = n.next) sb.append(n.val).append(n.next != null ? " -> " : "");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(str(reverseIterative(build(1, 2, 3, 4, 5))));
        System.out.println(str(reverseRecursive(build(1, 2, 3, 4, 5))));
    }
}
