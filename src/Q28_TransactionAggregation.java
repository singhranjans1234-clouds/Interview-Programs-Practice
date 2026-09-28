import java.util.*;
import java.util.stream.*;

/** Q28: Summarise credits/debits per account with a custom Collector into a LinkedHashMap; skip failed/null items. */
public class Q28_TransactionAggregation {

    enum Type { CREDIT, DEBIT }

    record Transaction(String account, Type type, String status, double amount) {}

    static class Summary {
        double credits, debits;
        Summary add(Transaction t) {
            if (t.type() == Type.CREDIT) credits += t.amount(); else debits += t.amount();
            return this;
        }
        @Override public String toString() {
            return String.format("credits=%.2f, debits=%.2f, net=%.2f", credits, debits, credits - debits);
        }
    }

    static Collector<Transaction, Map<String, Summary>, Map<String, Summary>> summarising() {
        return Collector.of(
                LinkedHashMap::new,
                (map, t) -> map.computeIfAbsent(t.account(), k -> new Summary()).add(t),
                (m1, m2) -> {                       // combiner for parallel streams
                    m2.forEach((k, v) -> {
                        Summary s = m1.computeIfAbsent(k, x -> new Summary());
                        s.credits += v.credits; s.debits += v.debits;
                    });
                    return m1;
                });
    }

    public static void main(String[] args) {
        List<Transaction> txns = Arrays.asList(
                new Transaction("ACC1", Type.CREDIT, "SUCCESS", 1000),
                new Transaction("ACC2", Type.CREDIT, "SUCCESS", 500),
                new Transaction("ACC1", Type.DEBIT, "SUCCESS", 300),
                new Transaction("ACC1", Type.DEBIT, "FAILED", 9999),   // ignored
                null,                                                   // ignored
                new Transaction("ACC2", Type.DEBIT, "SUCCESS", 100),
                new Transaction("ACC3", Type.CREDIT, "SUCCESS", 250));

        Map<String, Summary> result = txns.stream()
                .filter(Objects::nonNull)
                .filter(t -> "SUCCESS".equalsIgnoreCase(t.status()))
                .collect(summarising());
        result.forEach((acc, s) -> System.out.println(acc + " -> " + s));
    }
}
