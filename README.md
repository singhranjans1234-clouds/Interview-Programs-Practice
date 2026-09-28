# Java Interview Solutions (30 problems)

Each file in `src/` is a self-contained, runnable solution with a `main` method. Requires JDK 17+ (tested on 21).

    ./run_all.sh                 # run everything
    java src/Q09_KadaneMaxSubarray.java   # run one (no javac step needed)

| # | Problem | Approach | Time / Space |
|---|---------|----------|--------------|
| 1 | First non-repeating char | LinkedHashMap / indexOf==lastIndexOf stream | O(n) / O(k) |
| 2 | Group anagrams | sorted-chars key + groupingBy | O(n·k log k) |
| 3 | Even/odd separation | partitioningBy; two threads with wait/notify | O(n) |
| 4 | Second highest | distinct+sorted+skip(1); single pass | O(n) |
| 5 | Singleton | volatile DCL, readResolve, clone/reflection guards | O(1) |
| 6 | Duplicates (no Set) | IntStream + indexOf | O(n²) |
| 7 | Longest substring no repeat | sliding window + last-index map | O(n) |
| 8 | Frequency count | groupingBy + counting | O(n) |
| 9 | Kadane | running best ending here | O(n) / O(1) |
| 10 | Reverse words | stream/loops | O(n) |
| 11 | Employee grouping | groupingBy + averagingDouble | O(n) |
| 12 | Move zeros | write-pointer | O(n) / O(1) |
| 13 | Reverse letters only | two pointers | O(n) |
| 14 | Immutable class | final class, defensive copies | - |
| 15 | Max product subarray | track max and min | O(n) / O(1) |
| 16 | Product except self | prefix × suffix | O(n) |
| 17 | Two sum | HashMap | O(n) |
| 18 | Stack via queue | rotate on push | push O(n) |
| 19 | Reverse linked list | iterative + recursive | O(n) |
| 20 | Valid parentheses | stack | O(n) |
| 21 | String rotation | (a+a).contains(b) | O(n) |
| 22 | Missing number | sum / XOR / stream | O(n) |
| 23 | Rotate array | three reversals, k % n | O(n) / O(1) |
| 24 | Min size subarray sum | sliding window | O(n) |
| 25 | Dutch National Flag | 3 pointers | O(n) / O(1) |
| 26 | Next greater element | monotonic stack | O(n) |
| 27 | Merge sorted arrays | two-pointer + in-place gap method | O(n+m) / O(1) |
| 28 | Transaction aggregation | custom Collector -> LinkedHashMap | O(n) |
| 29 | Producer-consumer | BlockingQueue + wait/notify | - |
| 30 | Cycle detection | Floyd + cycle start | O(n) / O(1) |

Notes:
- Q1 is case-insensitive so "Swiss" returns 'w' as in the example.
- Q27: the strict in-place merge uses the gap method; the O(n+m) version returns a new array.
