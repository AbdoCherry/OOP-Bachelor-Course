package Week06.Task03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String depName;
    private String teamLead;
    private Employee[] employees;
    private double budget;

    public Department() {
    }

    public Department(String depName, String teamLead, Employee[] employees, double budget) {
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

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public static List<Department> readDepartmentsCSV() {

        List<Department> departments = new ArrayList<Department>();

        String pathMacOs = "src/Week06/Task03/DepartmentsList.csv";
        String pathWindows = "src\\Week06\\Task03\\DepartmentsList.csv";
        String path = null, line = null;

        // Determin OS for correct path selection
        if (System.getProperty("os.name").startsWith("Mac")) {
            path = pathMacOs;
        } else if (System.getProperty("os.name").startsWith("Win")) {
            path = pathWindows;
        } else {
            System.out.println("\nError: Unknown OS\nPlease restart program");
            System.exit(1);
        }

        // Read CSV file into ArrayList
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            csvReader.readLine();

            while ((line = csvReader.readLine()) != null) {
                String[] values = line.split(";");
                String[] nameSplitter = values[1].split(" ");
                String firstName = null, lastName = null;

                // Preprocess name field into first- and lastname
                if (nameSplitter.length == 3) {
                    firstName = nameSplitter[0];
                    lastName = nameSplitter[1] + " " + nameSplitter[2];
                } else if (nameSplitter.length == 2) {
                    firstName = nameSplitter[0];
                    lastName = nameSplitter[1];
                }

                if (Boolean.parseBoolean(values[5])) {
                    departments.add(new Department(
                                    values[4],
                                    values[1],
                                    null,
                                    Double.parseDouble(values[6])
                            )
                    );
                    
                } else {
                    for (Department d : departments) {
                        if (d.getDepName().equals(values[4])) {
                            for (Employee e : d.getEmployees()) {
                                if (e != null) {
                                    e = new Employee(Integer.parseInt(values[0]), firstName, lastName);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(departments.size() > 0 ? "\n================================ IMPORT SUCCESSFUL ================================\n" :
                    "\n================================ IMPORT FAILED ================================\n");
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return departments;
    }
}
