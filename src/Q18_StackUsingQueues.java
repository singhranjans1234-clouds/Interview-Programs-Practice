import java.util.*;

/** Q18: LIFO stack using one FIFO queue. push is O(n), pop/top/empty are O(1). */
public class Q18_StackUsingQueues {

    static class MyStack {
        private final Queue<Integer> q = new LinkedList<>();

        void push(int x) {
            q.offer(x);
            for (int i = 0; i < q.size() - 1; i++) q.offer(q.poll()); // rotate so newest is at front
        }
        int pop()  { return q.remove(); }
        int top()  { return q.element(); }
        boolean empty() { return q.isEmpty(); }
    }

    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1); s.push(2); s.push(3);
        System.out.println(s.top());   // 3
        System.out.println(s.pop());   // 3
        System.out.println(s.pop());   // 2
        System.out.println(s.empty()); // false
        s.pop();
        System.out.println(s.empty()); // true
    }
}
