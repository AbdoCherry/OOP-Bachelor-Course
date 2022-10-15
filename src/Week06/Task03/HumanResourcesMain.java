package Week06.Task03;

import java.util.List;

public class HumanResourcesMain {
    public static void main(String[] args) {

        List<Department> departments = Department.loadDepartments();
        Department.displayAll(departments);


    }
}
