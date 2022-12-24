package Week10.Task02.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Week10.Task02.Abstract.Employee;
import Week10.Task02.Auxiliary.FileDialog;

public class Manager extends Employee<Manager> {

    private final Scanner scannerManager = new Scanner(System.in);

    private double budget;
    private int staffSize;

    public Manager() {
    }

    public Manager(int empID, String firstName, String lastName, double salary, String department, double budget,
                   int staffSize) {
        super(empID, firstName, lastName, salary, department);
        this.budget = budget;
        this.staffSize = staffSize;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getStaffSize() {
        return staffSize;
    }

    public static Map<Integer, String> departments(List<Manager> managers) {
        // Extracting only the departments and the employee-id of the manager as their key
        /*
         * BEHOLD THAT USING THE ID OF EMPLOYEE AS ID FOR DEPARTMENT IS NOT A BEST PRACTICE
         */
        return managers.stream().collect(Collectors.toMap(Manager::getEmpID, Manager::getDepartment));
    }


    public boolean approveRequest(Clerk c, double newSalary) {

        System.out.println("\nPlease check the request of your clerk and approve or reject salary raise");
        System.out.printf("\n\033[1m%-5s%-15s%-30s%-15s%-15s%-5s\033[0m\n", "|", "Employee-ID", "Name", "Actual Salary",
                "Req. Salary", "|");
        System.out.println("|--------------------------------------------------------------------|");
        System.out.printf("%-5s%-15d%-30s%-15.2f%-15.2f%-5s\n", "|", c.getEmpID(),
                c.getFirstName() + " " + c.getLastName(), c.getSalary(), newSalary, "|");

        System.out.println("[Approve = 'A'/'a']\t[Reject = 'R'/'r']");
        System.out.print("Answer: ");

        return Character.toUpperCase(scannerManager.next().charAt(0)) == 'A';

    }

    @Override
    public void findEmp() {

        if (this != null) {
            super.findEmp();
            System.out.println("Budget: " + this.budget + " $");
            System.out.println("Staff size: " + this.staffSize);
            System.out.println("-----------------------------------");
        }
    }

    @Override
    public Manager singleObject(List<Manager> list) {
        // TODO Auto-generated method stub

        String[] fullName = name();

        Manager manager = list.stream().filter(
                        m -> m.getFirstName().equalsIgnoreCase(fullName[0]) && m.getLastName().equalsIgnoreCase(fullName[1]))
                .findFirst().orElse(null);

        if (manager == null) {
            manager = new Manager();
            manager.setEmpID(0);
            manager.setFirstName(fullName[0]);
            manager.setLastName(fullName[1]);
        }
        return manager;
    }

    public void updateManager(List<Clerk> clerks) {

        int staffSizeBefore = this.staffSize;

        Map<Integer, Long> groupedByManager = clerks.stream()
                .collect(Collectors.groupingBy(Clerk::getManagerID, Collectors.counting()));

        this.staffSize = Integer.parseInt(String.valueOf(groupedByManager.get(this.getEmpID())));

        System.out.println(staffSizeBefore < this.staffSize ? "\nStaff update successfully to Manager" + this.toString()
                : "Staff update failed to Manager\n");
    }

    @Override
    public void displayAll(List<Manager> list) {
        // TODO Auto-generated method stub

        list.sort(Comparator.comparing(Manager::getEmpID));
        System.out.printf("\n\033[1m%-5s%-15s%-30s%-15s%-40s%-15s%-10s%-5s\033[0m\n", "|", "Emp-ID", "Name", "Salary",
                "Department", "Budget", "Staff-Size", "|");
        System.out.println(
                "|-------------------------------------------------------------------------------------------------------------------------------------|");
        list.forEach(m -> System.out.printf("%-5s%-15s%-30s%-15.2f%-40s%-15.2f%-10d%-5s\n", "|", m.getEmpID(),
                m.getFirstName() + " " + m.getLastName(), m.getSalary(), m.getDepartment(), m.getBudget(),
                m.getStaffSize(), "|"));
    }

    public String toString() {

        return "\n[Employee-ID: '" + this.getEmpID() + "' - Name: '" + this.getFirstName() + " " + this.getLastName()
                + "' - Salary: '" + this.getSalary() + "' - Department: '" + this.getDepartment() + "' - Staff Size: '"
                + this.getStaffSize() + "']";
    }

    @Override
    public List<Manager> readCSV() {
        // TODO Auto-generated method stub

        String line;
        List<Manager> managers = new ArrayList<>();

        try {
            BufferedReader readCSVReader = new BufferedReader(
                    new FileReader(FileDialog.FileChooser(this.getClass().getSimpleName())));
            readCSVReader.readLine();

            while ((line = readCSVReader.readLine()) != null) {
                String[] values = line.split(";");
                managers.add(new Manager(Integer.parseInt(values[0]), values[1], values[2],
                        Double.parseDouble(values[3]), values[4], Double.parseDouble(values[5]), Integer.parseInt(values[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(managers.size() > 0 ? "================ IMPORT MANAGER SUCCESSFULLY ================"
                : "================ IMPORT MANAGER FAILED ================");

        return managers;
    }

    @Override
    public void writeCSV(List<Manager> managers) {

        try {
            PrintWriter writeCSV = new PrintWriter(FileDialog.FileChooser(this.getClass().getSimpleName()));
            StringBuilder sbManager = new StringBuilder();

            sbManager.append("EmployeeID");
            sbManager.append(";");
            sbManager.append("FirstName");
            sbManager.append(";");
            sbManager.append("LastName");
            sbManager.append(";");
            sbManager.append("Salary");
            sbManager.append(";");
            sbManager.append("Department");
            sbManager.append(";");
            sbManager.append("Budget");
            sbManager.append(";");
            sbManager.append("StaffSize");
            sbManager.append("\n");

            for (Manager m : managers) {
                sbManager.append(m.getEmpID());
                sbManager.append(";");
                sbManager.append(m.getFirstName());
                sbManager.append(";");
                sbManager.append(m.getLastName());
                sbManager.append(";");
                sbManager.append(m.getSalary());
                sbManager.append(";");
                sbManager.append(m.getDepartment());
                sbManager.append(";");
                sbManager.append(m.getBudget());
                sbManager.append(";");
                sbManager.append(m.getStaffSize());
                sbManager.append("\n");
            }

            writeCSV.write(sbManager.toString());
            writeCSV.close();
            System.out.println("================== EXPORT MANAGER SUCCESSFULLY ==================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
