package Week06.Task03;

import java.util.List;
import java.util.Map;

public class HumanResourcesMain {

    public static void main(String[] args) {
        Map<String, List<Employee>> assignedEmployees = Employee.readCSV();
        List<Department> departments = Department.readCSV(assignedEmployees);

        Department.display(departments);


    }


}
