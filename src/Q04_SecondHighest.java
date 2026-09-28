import java.util.*;
import java.util.stream.*;

/** Q4: Second highest number / salary (handles duplicates and nulls). */
public class Q04_SecondHighest {

    record Employee(int id, String name, Double salary) {}

    static Optional<Integer> secondHighest(int[] arr) {
        return Arrays.stream(arr).boxed().distinct()
                .sorted(Comparator.reverseOrder()).skip(1).findFirst();
    }

    static Optional<Employee> secondHighestPaid(List<Employee> list) {
        Optional<Double> second = list.stream()
                .filter(Objects::nonNull).map(Employee::salary).filter(Objects::nonNull)
                .distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        return second.flatMap(s -> list.stream()
                .filter(e -> e != null && s.equals(e.salary())).findFirst());
    }

    // Single-pass O(n), no sorting.
    static Integer secondHighestLoop(int[] a) {
        Integer first = null, second = null;
        for (int x : a) {
            if (first == null || x > first) { second = first; first = x; }
            else if (x < first && (second == null || x > second)) second = x;
        }
        return second;
    }

    public static void main(String[] args) {
        int[] a = {10, 50, 50, 20, 40};
        System.out.println("Stream: " + secondHighest(a).orElse(null) + ", loop: " + secondHighestLoop(a));
        List<Employee> emps = Arrays.asList(new Employee(1, "A", 5000.0), new Employee(2, "B", 9000.0),
                new Employee(3, "C", 9000.0), null, new Employee(4, "D", null), new Employee(5, "E", 7000.0));
        System.out.println(secondHighestPaid(emps).orElse(null));
    }
}
