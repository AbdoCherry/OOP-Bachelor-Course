package Week06.Task03;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentMain {
    public static void main(String[] args) {

        Map<String, Set<Employee>> employees = Employee.readEmployees();

        for (Map.Entry<String, Set<Employee>> entry : employees.entrySet()) {
            System.out.print("\n\n| ------------------------------------------------------------------- |");
            System.out.printf("\n\033[1m%-5s%-15s%-12s%-38s%-5s\033[0m\n", "|", "", "Department: ", entry.getKey(), "|");
            System.out.printf("%-5s%-15s%-25s%-25s%-5s\n", "|", "ID", "First Name", "Last Name", "|");
            System.out.println("| ------------------------------------------------------------------- |");

            List<Employee> employeesSorted = new ArrayList<Employee>(entry.getValue());
            employeesSorted.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));

            for (Employee e : employeesSorted) {
                System.out.printf("%-5s%-15s%-25s%-25s%-5s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
            }
        }


    }
}
