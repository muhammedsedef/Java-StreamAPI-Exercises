package exercise1;

import com.example.Department;
import com.example.Employee;
import com.example.Gender;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercises {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("1" , "Muhammed", "Sedef" , 1998, 25_000 , Gender.MALE, Department.IT),
                new Employee("2" , "Aynur", "Sedef" , 1957, 15_000 ,Gender.FEMALE, Department.HR),
                new Employee("3" , "Aslı", "Sedef" , 1987, 12_000 ,Gender.FEMALE, Department.FINANCE),
                new Employee("4" , "Ismail", "Sedef" , 1954, 18_000 ,Gender.MALE, Department.MARKETING),
                new Employee("5" , "Ali", "Sedef" , 1965, 22_000 ,Gender.MALE, Department.MARKETING),
                new Employee("6" , "Ahmet", "Sedef" , 1985, 21_000 ,Gender.MALE, Department.SALES)
        );

        // ***** Total salary of all employees *****
        double sumOfSalary = employees.stream()
                .map(employee -> employee.getSalary())
                .reduce(0.0, (sum, salary) -> sum + salary);
        System.out.println("Total salary is " + sumOfSalary);

        /* Method reference
            employees.stream()
                .map(Employee::getSalary)
               .reduce(0.0, Double::sum);
        */

        // ***** Marketing department count *****
        long countOfMarketingDept = employees.stream()
                .filter(employee -> employee.getDepartment() == Department.MARKETING)
                .count();
        System.out.println("# of employees in Marketing department is " + countOfMarketingDept);

        /*
        employees.stream()
               .map(Employee::getDepertment)
               .filter(Depertment.MARKETING::equals)
                .count();
        */

        // *****    # of employees of the each departments *****
        Map<Department, Long> dept =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment, Collectors.counting()
                        ));
        dept.entrySet().forEach(
                employee -> System.out.println(employee.getKey() + " " + employee.getValue())
        );

        // ***** Sort employee by salary descending order *****
        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);

        // ***** Get highest paid employee *****
        employees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(employee -> System.out.println("\nThe highest paid employee is " + employee));

        // ***** lowest paid employee *****
        employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .ifPresent(employee -> System.out.println("\nThe lowest paid employee is " + employee + "\n"));

        // ***** Get higher paid employee by gender
        Comparator<Employee> compareBySalary = Comparator.comparing(Employee::getSalary);
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender , Collectors.maxBy(compareBySalary)))
                .forEach((gender, empl) -> System.out.println(gender + " : " + empl.get()));
    }
}
