import java.util.*;
import java.util.stream.*;

/** Q6: Duplicates using streams, with no user-declared Set/helper collection. */
public class Q06_FindDuplicates {

    // An element is a duplicate if its first index differs from the current index.
    static List<Integer> duplicates(List<Integer> list) {
        return IntStream.range(0, list.size())
                .filter(i -> list.indexOf(list.get(i)) != i)
                .mapToObj(list::get)
                .distinct()
                .collect(Collectors.toList());
    }

    // Alternative using frequency.
    static List<Integer> duplicates2(List<Integer> list) {
        return list.stream().filter(x -> Collections.frequency(list, x) > 1).distinct().toList();
    }

    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 2, 4, 5, 1, 6, 1);
        System.out.println(duplicates(l) + " " + duplicates2(l));
    }
}
