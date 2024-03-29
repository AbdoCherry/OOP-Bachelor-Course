package Week06.Task03.IOFile;

import Week06.Task03.Model.Department;
import Week06.Task03.Model.Employee;

import javax.swing.*;
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

        String pathDep = "src/Week06/Task03/Data/Department.csv", pathEmp = "src/Week06/Task03/Data/Employee.csv";

        try {
            PrintWriter writeDepCSV = new PrintWriter((pathConverter(pathDep)));
            PrintWriter writeEmpCSV = new PrintWriter((pathConverter(pathEmp)));

            StringBuilder sbDep = new StringBuilder();
            StringBuilder sbEmp = new StringBuilder();

            // Headers for department csv
            sbDep.append("Department");
            sbDep.append(";");
            sbDep.append("TeamLead");
            sbDep.append(";");
            sbDep.append("Budget");
            sbDep.append("\n");

            // Headers for employee csv
            sbEmp.append("Employee ID");
            sbEmp.append(";");
            sbEmp.append("Name");
            sbEmp.append(";");
            sbEmp.append("Department Name");
            sbEmp.append("\n");

            for (Department d : departments) {
                sbDep.append(d.getDepartment());
                sbDep.append(";");
                sbDep.append(d.getTeamLead());
                sbDep.append(";");
                String formattedBudget = df.format(d.getBudget());
                sbDep.append(formattedBudget);
                sbDep.append("\n");

                List<Employee> empList = new ArrayList<>(d.getStaff());
                empList.sort(Comparator.comparing(Employee::getEmpID));

                for (Employee e : empList) {
                    sbEmp.append(e.getEmpID());
                    sbEmp.append(";");
                    sbEmp.append(e.getFirstName()).append(" ").append(e.getLastName());
                    sbEmp.append(";");
                    sbEmp.append(d.getDepartment());
                    sbEmp.append("\n");
                }

            }

            writeDepCSV.write(sbDep.toString());
            writeEmpCSV.write(sbEmp.toString());
            JOptionPane.showMessageDialog(null, "CSV Saved");

            writeDepCSV.close();
            writeEmpCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
