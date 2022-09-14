package Week06.Task03;

import java.util.List;


public class DepartmentsMain {
    public static void main(String[] args) {

        Department d = new Department();
        List<Department> departmeents = Department.readDepartments();

        d.createDepartment(departmeents);

    }
}
