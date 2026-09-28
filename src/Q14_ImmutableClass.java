import java.util.*;

/** Q14: Fully immutable class holding mutable references (Date, List, custom object). */
public class Q14_ImmutableClass {

    static class Address {                       // mutable helper
        private String city;
        Address(String city) { this.city = city; }
        Address(Address other) { this.city = other.city; } // copy constructor
        String getCity() { return city; }
        void setCity(String city) { this.city = city; }
        @Override public String toString() { return "Address(" + city + ")"; }
    }

    // 1) final class  2) private final fields  3) no setters
    // 4) deep copy in constructor  5) deep copy / unmodifiable view in getters
    static final class Employee {
        private final int id;
        private final String name;
        private final Date joined;
        private final List<String> skills;
        private final Address address;

        Employee(int id, String name, Date joined, List<String> skills, Address address) {
            this.id = id;
            this.name = name;
            this.joined = new Date(joined.getTime());
            this.skills = List.copyOf(skills);          // immutable copy (also rejects nulls)
            this.address = new Address(address);
        }

        int getId() { return id; }
        String getName() { return name; }
        Date getJoined() { return new Date(joined.getTime()); }
        List<String> getSkills() { return skills; }     // already unmodifiable
        Address getAddress() { return new Address(address); }

        @Override public String toString() { return id + " " + name + " " + joined.getTime() + " " + skills + " " + address; }
    }

    public static void main(String[] args) {
        Date d = new Date(0);
        List<String> skills = new ArrayList<>(List.of("Java", "SQL"));
        Address addr = new Address("Bengaluru");
        Employee e = new Employee(1, "Asha", d, skills, addr);

        // Try to tamper from outside
        d.setTime(999); skills.add("Hack"); addr.setCity("Hacked");
        e.getJoined().setTime(555); e.getAddress().setCity("Hacked2");
        try { e.getSkills().add("x"); } catch (UnsupportedOperationException ex) { System.out.println("skills list is read-only"); }
        System.out.println(e); // unchanged
    }
}
