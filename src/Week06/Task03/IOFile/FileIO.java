package Week06.Task03.IOFile;

import Week06.Task03.Model.Department;
import Week06.Task03.Model.Employee;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


public class FileIO {

    static DecimalFormat df = new DecimalFormat("#.##");

    private static String pathConverter(String pathRaw) {

        if (System.getProperty("os.name").startsWith("Mac")) {
            return pathRaw.replace("\\", "/");
        } else if (System.getProperty("os.name").startsWith("Win")) {
            return pathRaw.replace("/", "\\");
        } else {
            // Just a fictional number, do not get stressed
            System.out.println("Error 99: Operating system " + System.getProperty("os.name") + " not known.\nPlease restart program on another computer or update your current os.\n");
            System.exit(1);
        }
        return null;
    }

    public static List<Department> readCSV() {

        List<Department> departments = new ArrayList<>();
        List<Employee> allEmployees = new ArrayList<>();

        String pathDepartment = "src/Week06/Task03/Data/Department.csv", pathEmployees = "src/Week06/Task03/Data/Employee.csv", line;

        try {
            // First reading the department csv
            BufferedReader readCSV = new BufferedReader(new FileReader(pathConverter(pathDepartment)));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");

                departments.add(new Department(
                        values[0],
                        values[1],
                        null,
                        Double.parseDouble(values[2])
                ));
            }

            readCSV.close();

            // Reading all Employees from employee csv
            line = null; // Resetting line for ignoring header row
            readCSV = new BufferedReader(new FileReader(pathConverter(pathEmployees)));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");
                String[] name = values[1].split(" ");
                String firstName, lastName;

                switch (name.length) {
                    case 3:
                        firstName = name[0] + " " + name[1];
                        lastName = name[2];
                        break;
                    case 2:
                        firstName = name[0];
                        lastName = name[1];
                        break;
                    default:
                        firstName = "Unknown";
                        lastName = "Unknown";
                }

                allEmployees.add(new Employee(
                        Integer.parseInt(values[0]),
                        firstName,
                        lastName,
                        values[2]
                ));
            }

            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sorting departments alphabetically ascending
        departments.sort(Comparator.comparing(Department::getDepartment));

        for (Department d : departments) {
            Set<Employee> staff = allEmployees.stream()
                    .filter(e -> e.getDepHelper().equals(d.getDepartment()))
                    .collect(Collectors.toSet());
            d.setStaff(staff);
        }

        System.out.println(
                departments.size() > 0 ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return departments;
    }

    public static void writeCSV(List<Department> departments) {

        String pathDepartment = "src/Week06/Task03/Data/Department.csv", pathEmployees = "src/Week06/Task03/Data/Employee.csv";

        try {
            PrintWriter writeDepartmentCSV = new PrintWriter(new File(pathConverter(pathDepartment)));
            PrintWriter writeEmployeeCSV = new PrintWriter(new File(pathConverter(pathEmployees)));

            StringBuilder dep = new StringBuilder();
            StringBuilder emp = new StringBuilder();

            // Defining header for Department
            dep.append("Department");
            dep.append(";");
            dep.append("Teamlead");
            dep.append(";");
            dep.append("Budget");
            dep.append("\n");

            // Defining header for Employee
            emp.append("EmployeeID");
            emp.append(";");
            emp.append("Name");
            emp.append("Department Name");
            dep.append("\n");

            for (Department d : departments) {
                dep.append(d.getDepartment());
                dep.append(";");
                dep.append(d.getTeamLead());
                dep.append(";");
                dep.append(df.format(d.getBudget()));
                dep.append("\n");
                for (Employee e : d.getStaff()) {
                    emp.append(String.valueOf(e.getEmpID()));
                    emp.append(";");
                    emp.append(e.getFirstName()).append(" ").append(e.getLastName());
                    emp.append(";");
                    emp.append(d.getDepartment());
                    emp.append("\n");
                }
            }

            writeDepartmentCSV.write(dep.toString());
            writeDepartmentCSV.close();
            writeEmployeeCSV.write(emp.toString());
            writeEmployeeCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
