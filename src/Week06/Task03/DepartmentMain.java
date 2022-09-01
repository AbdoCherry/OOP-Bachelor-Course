package Week06.Task03;

import java.util.List;

public class DepartmentMain {
    public static void main(String[] args) {

        List<Department> departments = Department.readDepartmentsCSV();

        for (Department d : departments) {
            System.out.println(d.getDepName());
        }
    }
}
