import java.util.stream.*;

/** Q22: Missing number in 1..n (array has n-1 items). */
public class Q22_MissingNumber {

    static int bySum(int[] a) {
        int n = a.length + 1;
        return n * (n + 1) / 2 - IntStream.of(a).sum();
    }

    static int byXor(int[] a) {          // avoids overflow for large n
        int x = 0;
        for (int i = 1; i <= a.length + 1; i++) x ^= i;
        for (int v : a) x ^= v;
        return x;
    }

    static int byStream(int[] a) {
        int n = a.length + 1;
        return IntStream.rangeClosed(1, n).filter(i -> IntStream.of(a).noneMatch(v -> v == i)).findFirst().getAsInt();
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 5, 6};
        System.out.println(bySum(a) + " " + byXor(a) + " " + byStream(a)); // 3 3 3
    }
}
