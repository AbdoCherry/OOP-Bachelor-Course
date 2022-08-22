package Week06.Task03;

import java.util.List;
import java.util.Set;

public class DepartmentMain {
    public static void main(String[] args) {

        Department d = new Department();

        Set<Employee> employees = Employee.readEmployees();
        List<Department> departments = Department.readDepartments(employees);

        d.displayAll(departments);


    }
}
