import java.util.*;
import java.util.stream.*;

/** Q11: Group by department, average salary per department, department with highest average. */
public class Q11_EmployeeGrouping {

    record Employee(int id, String name, String department, double salary) {}

    public static void main(String[] args) {
        List<Employee> emps = List.of(
                new Employee(1, "Asha", "IT", 90000), new Employee(2, "Ravi", "IT", 110000),
                new Employee(3, "Meera", "HR", 60000), new Employee(4, "John", "HR", 65000),
                new Employee(5, "Kiran", "Finance", 120000), new Employee(6, "Sara", "Finance", 100000));

        Map<String, List<Employee>> byDept = emps.stream().collect(Collectors.groupingBy(Employee::department));
        System.out.println("Grouped: " + byDept.keySet());

        Map<String, Double> avg = emps.stream().collect(
                Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));
        System.out.println("Average salary: " + avg);

        avg.entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("Highest avg dept: " + e.getKey() + " = " + e.getValue()));
    }
}
