package Week06.Task03;


import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DepartmentsMain {
    public static void main(String[] args) {

        Department d = new Department();
        List<Department> departmeents = Department.readDepartments();

        d.createDepartment(departmeents);

    }
}
