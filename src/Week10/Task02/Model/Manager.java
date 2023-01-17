package Week10.Task02.Model;

import Week10.Task02.Auxiliary.FileDialog;
import Week10.Task02.Generic.Employee;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Manager extends Employee<Manager> {

    Scanner scannerManager = new Scanner(System.in);

    private String depName;
    private double depBudget;
    private Set<Clerk> staffSize;

    public Manager() {
    }

    public Manager(int empId, String firstName, String lastName, String ssn, double salary, String depName, double depBudget, Set<Clerk> staffSize) {
        super(empId, firstName, lastName, ssn, salary);
        this.depName = depName;
        this.depBudget = depBudget;
        this.staffSize = staffSize;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public double getDepBudget() {
        return depBudget;
    }

    public void setDepBudget(double depBudget) {
        this.depBudget = depBudget;
    }

    public Set<Clerk> getStaffSize() {
        return staffSize;
    }

    public void setStaffSize(Set<Clerk> staffSize) {
        this.staffSize = staffSize;
    }

    public String createDepartment(@NotNull List<Manager> managers) {
        System.out.println("\nPlease enter name of department");
        String newDepartment;
        boolean approval;

        // Creation of department
        do {
            System.out.print("Department: ");
            scannerManager.nextLine();
            String inputDepartment = scannerManager.nextLine();
            approval = managers.stream()
                    .anyMatch(m -> m.getDepName().equals(inputDepartment));

            if (approval) {
                System.out.println("\nDepartment already exists. Please retry entering department name");
                newDepartment = null;
            } else {
                System.out.println("\nThank you for entering valid department name");
                newDepartment = inputDepartment;
                approval = false;
            }

        } while (approval);

        return newDepartment;
    }

    public double createDepBudget(@NotNull List<Manager> managers) {
        // Creation of department budget
        boolean approval = false;
        double newDepBudget = 0;
        System.out.println("\nPlease enter budget for department");
        do {
            System.out.print("Budget: ");
            newDepBudget = scannerEmp.nextDouble();
            double avgDepBudget = managers.stream()
                    .mapToDouble(Manager::getDepBudget).average().orElse(.0);
            double maxDepBudget = avgDepBudget * 1.5;

            if (newDepBudget > maxDepBudget) {
                System.out.println("\nWe cannot approve this budget.\nPlease enter new one");
                approval = true;
            }

        } while (approval);

        return newDepBudget;
    }

    public void addManager(@NotNull List<Manager> managers, List<Clerk> clerks) {
        int sizeBefore = managers.size();
        Manager manager = super.createEmp(managers);

        System.out.println("\nPlease choose existing department or type '0' to create a new one\n");
        System.out.printf("%-5s%-10s%-50s%-5s\n", "|", "#", "Department", "|");
        System.out.println("|==========================================================|");
        for (int i = 0; i < managers.size(); i++) {
            System.out.printf("%-5s%-10d%-50s%-5s\n", "|", (i + 1), managers.get(i).getDepName(), "|");
        }
        System.out.print("\nDepartment - or new one '0': ");
        int depSelection = scannerManager.nextInt();
        String department = null;

        if (depSelection == 0) {
            manager.setDepName(createDepartment(managers));
            manager.setDepBudget(createDepBudget(managers));
        } else {
            manager.setDepName(managers.get(depSelection).getDepName());
            manager.setDepBudget(managers.get(depSelection).getDepBudget());
        }

        manager.setStaffSize(Clerk.selectClerks(clerks));
        managers.add(manager);

        System.out.println(sizeBefore < managers.size() ?
                "Manager added successfully: " + manager.toString() + "\n"
                : "Manager not added successfully\n");
    }

    public void removeManager(List<Manager> managers, List<Clerk> clerks) {
        super.removeEmp(managers);
        updateLists(managers, clerks);
    }

    public void findManager(List<Manager> managers) {
        super.findEmployee(managers);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return super.toString() +
                ", depName = '" + depName + '\'' +
                ", depBudget = " + df.format(depBudget) + " $" +
                ", staffSize = " + staffSize.size() +
                "} ";
    }

    @Override
    public List<Manager> readCSV() {
        List<Manager> managers = new ArrayList<>();
        String line = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Week10/Task02/Data/Manager.csv")); // FileDialog.FileChooser(this.getClass().getSimpleName())
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                managers.add(new Manager(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        Double.parseDouble(values[4]),
                        values[5],
                        Double.parseDouble(values[6]),
                        null

                ));
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(managers.size() > 0 ? "=========== IMPORT MANAGER SUCCESSFULLY ===========" : "=========== IMPORT MANAGER FAILED ===========");
        return managers;
    }

    @Override
    public void writeCsv(List<Manager> managers) {

        try {
            PrintWriter writeCSV = new PrintWriter(FileDialog.FileChooser(this.getClass().getSimpleName()));
            StringBuilder sbManager = new StringBuilder();

            sbManager.append("EmployeeID");
            sbManager.append(";");
            sbManager.append("FirstName");
            sbManager.append(";");
            sbManager.append("LastName");
            sbManager.append(";");
            sbManager.append("SSN");
            sbManager.append(";");
            sbManager.append("Salary");
            sbManager.append(";");
            sbManager.append("Department");
            sbManager.append(";");
            sbManager.append("Budget");
            sbManager.append(";");
            sbManager.append("StaffSize");
            sbManager.append(";");
            sbManager.append("\n");

            for (Manager m : managers) {
                sbManager.append(String.valueOf(m.getEmpId()));
                sbManager.append(";");
                sbManager.append(m.getFirstName());
                sbManager.append(";");
                sbManager.append(m.getLastName());
                sbManager.append(";");
                sbManager.append(m.getSalary());
                sbManager.append(";");
                sbManager.append(m.getDepName());
                sbManager.append(";");
                sbManager.append(m.getDepBudget());
                sbManager.append(";");
                sbManager.append(m.getStaffSize().size());
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
