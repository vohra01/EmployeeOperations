import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@NoArgsConstructor
public class EmployeeService {


    static Employee employee = new Employee("Kunal", new Double(1.0), "", 5);


    static List<Employee> employees = new ArrayList<Employee>(asList(employee,
            new Employee("Kunal", new Double(1.0), "IT", 25),
            new Employee("Kunal", new Double(2.0), "IT", 25),
            new Employee("Kunal", new Double(3.0), "ECE", 25),
            new Employee("Kunal", new Double(4.0), "ME", 25),
            new Employee("Kunal", new Double(5.0), "CS", 25),
            new Employee("Kunal", new Double(6.0), "CS", 25),
            new Employee("Kunal", new Double(7.1), "123", 25),
            new Employee("Kunal", new Double(6.0), "abc", 25)
    ));

    public static void main(String[] args) {
        changeName();
        getAverageSalaryByDepartment();
        staticalSummary();
        sortEmployeesBySalary();
        System.out.println("-----Eliminate Department \"CS\"------");
        eliminateDataOfSpecifiedDept("CS");
    }

    private static void eliminateDataOfSpecifiedDept(String toBeEliminatedDept) {
        employees.stream()
                .filter(employee -> !employee.getDepartment().endsWith(toBeEliminatedDept))
                .forEach(System.out::println);
        System.out.println();
    }

    private static void sortEmployeesBySalary() {
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .collect(Collectors.toList())
                .stream()
                .forEach(System.out::println);
        System.out.println();
    }

    private static void staticalSummary() {
        DoubleSummaryStatistics statistics = employees.stream()
                .mapToDouble(x -> x.getSalary())
                .summaryStatistics();

        System.out.printf("Average Salary of All Employees :: %s %n", statistics.getAverage());
        System.out.printf("Max Salary among All Employees :: %s %n", statistics.getMax());
        System.out.printf("Min Salary among All Employees :: %s %n", statistics.getMin());
        System.out.printf("Sum of Salary of All Employees :: %s %n", statistics.getSum());
        System.out.printf("Count of All Employees :: %s %n", statistics.getCount());
        System.out.println();

    }

    private static void getAverageSalaryByDepartment() {
        Map<String, Double> averageSalary =
                employees.stream()
                        .collect(groupingBy(Employee::getDepartment, summingDouble(Employee::getSalary)));

        System.out.println(averageSalary);
        System.out.println();
    }

    private static void changeName() {
        employees.stream()
                .filter(Objects::nonNull)
                .forEach(employee -> {
                            employee.setName("Sudhanshu");
                            System.out.printf("New Name is :: %s %n", employee.getName());
                        }
                );
    }

}
