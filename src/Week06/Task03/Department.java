package Week06.Task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Department {
    private String department, teamLead;
    private Set<Employee> staff;
    private double budget;

    public Department() {
    }

    public Department(String department, String teamLead, Set<Employee> staff, double budget) {
        this.department = department;
        this.teamLead = teamLead;
        this.budget = budget;
        this.staff = staff;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public Set<Employee> getStaff() {
        return staff;
    }

    public void setStaff(Set<Employee> staff) {
        this.staff = staff;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public static List<Department> readCSV(Map<String, List<Employee>> assignedEmployees) {

        List<Department> departments = new ArrayList<Department>();

        String path, pathMacOS = "src/Week06/Task03/Departments.csv", pathWin = "src\\Week06Task03\\Departments.csv",
                line;

        if (System.getProperty("os.name").startsWith("Mac")) {
            path = pathMacOS;
        } else if (System.getProperty("os.name").startsWith("Win")) {
            path = pathWin;
        } else {
            path = null;
        }

        try {
            BufferedReader readDeps = new BufferedReader(new FileReader(path));
            readDeps.readLine();

            while ((line = readDeps.readLine()) != null) {
                String[] depVals = line.split(";");

                departments.add(new Department(depVals[0], depVals[1], null, Double.parseDouble(depVals[2])));
            }
            readDeps.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Department d : departments) {
            for (Map.Entry<String, List<Employee>> e : assignedEmployees.entrySet()) {
                if (d.getDepartment().equals(e.getKey())) {
                    Set<Employee> createStaff = new HashSet<Employee>(e.getValue());
                    d.setStaff(createStaff);
                }
            }
        }

        return departments;
    }

    public void createDepartment(List<Department> departments) {

        Scanner createDepScanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary informations in the fields below");
        Department newDepartment = new Department();

        System.out.print("\nName of Department to create: ");
        String newDepName = createDepScanner.nextLine();

        Boolean depExists = false;
        for (Department d : departments) {
            if (d.getDepartment().equalsIgnoreCase(newDepName)) {
                depExists = true;
                break;
            }
        }

        if (!depExists) {
            System.out.println(
                    "\nPlease select Team lead for this new department\nChoose from the list below by entering employee number");

            int min = 1, max = 10, indexCounter = 0;
            ;
            int[] randomSelection = new int[10];

            while (randomSelection[9] == 0) {

                randomSelection[indexCounter] = ThreadLocalRandom.current().nextInt(min, max + 1);
                indexCounter++;
            }

        }

    }

    public static void display(List<Department> departments) {

        System.out.println("\nAll departments and employees");

        departments.forEach(d -> {
            System.out.println("\nDepartment: " + d.getDepartment() + "\tTeamlead: " + d.getTeamLead() + "\tBudget: "
                    + d.getBudget() + " $");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            d.getStaff().forEach(e -> System.out
                    .println("EmployeeID: " + e.getEmpID() + "\tName: " + e.getFirstName() + " " + e.getLastName()));
        });
    }

    @Override
    public String toString() {
        return "Department {" + " department = '" + department + '\'' + ", teamLead = '" + teamLead + '\''
                + ", budget = " + budget + '}';
    }
}