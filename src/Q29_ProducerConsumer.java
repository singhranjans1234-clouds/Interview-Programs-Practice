import java.util.*;
import java.util.concurrent.*;

/** Q29: Producer-Consumer, (a) with BlockingQueue, (b) with wait()/notify(). */
public class Q29_ProducerConsumer {

    // (a) BlockingQueue: simplest and preferred.
    static void withBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(3);
        final int POISON = -1;
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) { q.put(i); System.out.println("[BQ] produced " + i); }
                q.put(POISON);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int v; (v = q.take()) != POISON; ) System.out.println("[BQ]   consumed " + v);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        producer.start(); consumer.start(); producer.join(); consumer.join();
    }

    // (b) wait/notify: bounded buffer.
    static class Buffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;
        Buffer(int capacity) { this.capacity = capacity; }

        synchronized void produce(int v) throws InterruptedException {
            while (queue.size() == capacity) wait();   // loop guards against spurious wakeups
            queue.add(v);
            System.out.println("[WN] produced " + v);
            notifyAll();
        }
        synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) wait();
            int v = queue.poll();
            System.out.println("[WN]   consumed " + v);
            notifyAll();
            return v;
        }
    }

    static void withWaitNotify() throws InterruptedException {
        Buffer b = new Buffer(2);
        Thread p = new Thread(() -> { try { for (int i = 1; i <= 5; i++) b.produce(i); } catch (InterruptedException ignored) {} });
        Thread c = new Thread(() -> { try { for (int i = 1; i <= 5; i++) b.consume(); } catch (InterruptedException ignored) {} });
        p.start(); c.start(); p.join(); c.join();
    }

    public static void main(String[] args) throws Exception {
        withBlockingQueue();
        withWaitNotify();
    }
}
