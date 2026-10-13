package com.hcl.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class EmployeeDirectory {
    private final Map<Integer, Employee> employeesById = new HashMap<>();

    public void add(Employee employee) {
        employeesById.put(employee.id(), employee);
    }

    // FR4: HashMap lookup by employee id.
    public Employee findById(int id) {
        return employeesById.get(id);
    }

    // FR5: TreeMap keeps department reports in sorted department order.
    public Map<String, List<Employee>> departmentReport() {
        return employeesById.values().stream().collect(Collectors.groupingBy(
                Employee::department, TreeMap::new, Collectors.toList()));
    }

    // FR6: Comparator chain sorts salary descending, then name ascending.
    public List<Employee> topFiveSalary() {
        Comparator<Employee> bySalaryDescending = Comparator.comparingDouble(Employee::salary).reversed();
        Comparator<Employee> bySalaryThenName = bySalaryDescending.thenComparing(Employee::name);
        return employeesById.values().stream().sorted(bySalaryThenName).limit(5).toList();
    }

    public static Map<String, Integer> countDestinations(List<String> destinations) {
        Map<String, Integer> frequency = new HashMap<>();
        destinations.forEach(destination -> frequency.merge(destination, 1, Integer::sum));
        return frequency;
    }

    public static void demonstrateEqualsHashCodeContract() {
        Employee first = new Employee(99, "Same ID", "Travel", 1.0);
        Employee second = new Employee(99, "Updated Name", "Travel", 2.0);
        Set<Employee> employees = new HashSet<>();
        employees.add(first);
        employees.add(second);
        System.out.println("equals/hashCode contract: equals=" + first.equals(second)
                + ", setSize=" + employees.size());
    }

    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();
        directory.add(new Employee(1, "Asha", "Travel", 85000));
        directory.add(new Employee(2, "Ravi", "Operations", 92000));
        directory.add(new Employee(3, "Meera", "Travel", 110000));
        directory.add(new Employee(4, "Kiran", "Finance", 98000));
        directory.add(new Employee(5, "Dev", "Operations", 105000));
        directory.add(new Employee(6, "Nila", "Travel", 76000));

        System.out.println("FR4 lookup id=3: " + directory.findById(3));
        System.out.println("FR5 sorted department report: " + directory.departmentReport());
        System.out.println("FR6 top five salaries: " + directory.topFiveSalary());
        System.out.println("Frequency merge: " + countDestinations(List.of("Ooty", "Goa", "Ooty", "Mysore")));
        demonstrateEqualsHashCodeContract();
    }
}
