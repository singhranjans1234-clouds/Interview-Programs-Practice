import java.util.*;
import java.util.stream.*;

/** Q3: Separate even/odd with streams, and print alternately with two threads. */
public class Q03_EvenOddSeparation {

    static Map<Boolean, List<Integer>> partition(List<Integer> nums) {
        return nums.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    // Two threads alternately print 1..max using wait()/notify().
    static class Printer {
        private int current = 1;
        private final int max;
        Printer(int max) { this.max = max; }

        synchronized void print(boolean wantEven) throws InterruptedException {
            while (current <= max) {
                if ((current % 2 == 0) == wantEven) {
                    System.out.println(Thread.currentThread().getName() + ": " + current++);
                    notifyAll();
                } else {
                    wait();
                }
            }
            notifyAll(); // release the other thread once we are done
        }
    }

    public static void main(String[] args) throws Exception {
        Map<Boolean, List<Integer>> p = partition(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Even: " + p.get(true) + "  Odd: " + p.get(false));

        Printer printer = new Printer(10);
        Thread odd = new Thread(() -> { try { printer.print(false); } catch (InterruptedException ignored) {} }, "ODD ");
        Thread even = new Thread(() -> { try { printer.print(true); } catch (InterruptedException ignored) {} }, "EVEN");
        odd.start(); even.start();
        odd.join(); even.join();
    }
}
