package Week06.Task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Department {
    private String depName, teamLead;
    private Set<Employee> employees;
    private double budget;

    public Department() {
    }

    public Department(String depName, String teamLead, Set<Employee> employees, double budget) {
        this.depName = depName;
        this.teamLead = teamLead;
        this.employees = employees;
        this.budget = budget;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public static List<Department> loadDepartments() {

        List<Department> departments = new ArrayList<>();
        Set<Employee> employees = new HashSet<>();

        String pathDepMac = "src/Week06/Task03/Departments.csv";
        String pathDepWin = "src\\Week06\\Task03\\Departments.csv";
        String pathDep, lineDep;

        String pathEmpMac = "src/Week06/Task03/Employees.csv";
        String pathEmpWin = "src\\Week06\\Task03\\Employees.csv";
        String pathEmp, lineEmp;

        if (System.getProperty("os.name").startsWith("Mac")) {
            pathDep = pathDepMac;
            pathEmp = pathEmpMac;
        } else if (System.getProperty("os.name").startsWith("Win")) {
            pathDep = pathDepWin;
            pathEmp = pathEmpWin;
        } else {
            pathDep = null;
            pathEmp = null;
            System.out.println("\nError: Unknown OS: " + System.getProperty("os.name") + " not supported." +
                    "\nPlease restart program or use it from different Operating System");
            System.exit(1);
        }

        try {
            BufferedReader readerDep = new BufferedReader(new FileReader(pathDep));
            BufferedReader readerEmp = new BufferedReader(new FileReader(pathEmp));

            readerDep.readLine();
            readerEmp.readLine();

            while ((lineDep = readerDep.readLine()) != null) {
                String[] valuesDep = lineDep.split(";");

                departments.add(new Department(valuesDep[0],
                        valuesDep[1],
                        null,
                        Double.parseDouble(valuesDep[2])));
            }
            readerDep.close();

            for (int i = 0; i < departments.size(); i++) {
                while ((lineEmp = readerEmp.readLine()) != null) {
                    String[] valuesEmp = lineEmp.split(";");

                    if (valuesEmp[0].equals(departments.get(i).getDepName())) {
                        employees.add(new Employee(
                                Integer.parseInt(valuesEmp[1]),
                                valuesEmp[2],
                                valuesEmp[3]
                        ));
                    }
                }
                departments.get(i).setEmployees(employees);
            }

            readerEmp.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(
                departments.size() > 0 ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return departments;
    }

    public static void displayAll(List<Department> departments) {

        departments.forEach(d -> {
            System.out.println("\n|===========================================================================================================================|");
            System.out.printf("%-2s%-10s%-40s%-2s%-10s%-20s%-2s%-10s%-25.2f%-2s\n", "|", "Department: ", d.getDepName(), "|", "Team Lead: ", d.getTeamLead(), "|", "Budget: ", d.getBudget(), "|");
            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
            d.getEmployees().forEach(e -> {
                System.out.printf("%-2s%-8s%-5d%-2s%-10s%-10s%-2s%-10s%-10s%-2s\n", "|", "Emp-ID: ", e.getEmpID(), "|", "First Name: ", e.getFirstName(), "|", "Last Name: ", e.getLastName(), "|");

            });
        });

    }
}
