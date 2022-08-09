package Week10.Task2.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import Week10.Task2.Abstract.Employee;

public class Manager extends Employee {

    public Manager() {

    }

    public Manager(int employeeID, String name, String ssn, double salary, String depName) {
        super(employeeID, name, ssn, salary, depName);

    }

    public Manager(int employeeID, String name, String ssn, double salary, String depName,
            boolean staff) {
        super(employeeID, name, ssn, salary, depName);

    }

    public void setStaff(boolean staff) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Employee> T employee(List<? extends Employee> list) {

        System.out.println(
                "\nPlease enter the necessary information in the fields below for Manager");
        System.out.print("First Name: ");
        String firstName = Employee.getScanner().nextLine();
        System.out.print("Last Name: ");
        String lastName = Employee.getScanner().nextLine();
        String name = firstName + " " + lastName;

        T employee = (T) list.stream().filter(t -> t.getName().equals(name)).findFirst()
                .orElse(null);

        return employee;
    }

    public void findEmployee(List<Manager> manager, List<Clerk> clerk) {

        Manager ma = employee(manager);

        if (ma != null) {
            List<Clerk> myClerks = clerk.stream()
                    .filter(c -> c.getDepName().equals(ma.getDepName())).toList();

            if (myClerks.size() > 0) {
                System.out.println("\n\t\t\033[1m**** " + myClerks.size()
                        + " EMPLOYEES IN STAFF OF " + ma.getName() + " FOUND *****\033[0m\n");

                System.out.printf("\033[1m%-30s%-30s%-30s%-30s\033[0m\n", "Employee ID", "Name",
                        "Department", "Salary");
                System.out.println(
                        "---------------------------------------------------------------------------------------------------");

                for (Clerk c : myClerks) {
                    System.out.printf("%-30s%-30s%-30s%.2f$\n", "ID: " + c.getEmployeeID(),
                            c.getName(), c.getDepName(), c.getSalary());
                }

            } else {
                System.out.println("\nNo employees under your staff " + ma.getName() + "\n");
            }

        } else {
            System.out.println(
                    "\nManager couldn´t be found by the system. Please check your entered values and restart program again\n");
        }

    }

    public void addEmployee(List<Manager> manager, List<Clerk> clerk) {

        Manager ma = employee(manager);

        // Check if manager exists
        if (ma != null) {
            Clerk newClerk = new Clerk();
            newClerk = newClerk.employee(clerk);

            char shiftDepartment;

            // Check if clerk exist
            if (newClerk.getSsn() != null) {

                // Check if Clerk is already in same department like Manager
                if (newClerk.getDepName().equals(ma.getDepName())) {
                    System.out.println("\n\t\t\033[1mEmployee " + newClerk.getName()
                            + " is already in your staff\033[0m");
                    System.out.println(
                            "---------------------------------------------------------------------------------------------------");
                    System.out.printf("%-30s%-30d\n", "ID: ", newClerk.getEmployeeID());
                    System.out.printf("%-30s%-30s\n", "Name: ", newClerk.getName());
                    System.out.printf("%-30s%-30s\n", "Department: ", newClerk.getDepName());
                    System.out.printf("%-30s%.2f$\n", "Salary: ", newClerk.getSalary());
                    shiftDepartment = 'Y';

                    // If Clerk isn´t in same department like Manager
                } else {

                    // Ask manager if he want´s to add existing employee to staff
                    System.out.print("\nDo you want to add Employee " + newClerk.getName()
                            + " to your staff?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
                    shiftDepartment = Employee.getScanner().next().charAt(0);
                }

            } else {
                shiftDepartment = 'N';

            }

            if (Character.toUpperCase(shiftDepartment) == 'Y') {

                // Add employee to staff of Manager by changing his department to same department like Manager
                String departmentBefore = null, departmentAfter = ma.getDepName();
                for (Clerk c : clerk) {

                    if (!c.getDepName().equals(departmentAfter)) {
                        if (c.getEmployeeID() == newClerk.getEmployeeID()) {
                            departmentBefore = c.getDepName();
                            c.setDepName(ma.getDepName());
                            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s\n\033[0m",
                                    "Employee ID", "Employee", "Department Before",
                                    "Department After");
                            System.out.println(
                                    "-----------------------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-30s%-30s%-30s%-30s\n", "ID: " + c.getEmployeeID(),
                                    c.getName(), departmentBefore, departmentAfter);
                            break;
                        }
                    }
                }
            } else if (Character.toUpperCase(shiftDepartment) == 'N') {

                // Create completely new employee to staff of Manager
                int employeeID = newClerk.maxID(clerk);
                String name = newClerk.getName();
                String ssn = newClerk.createSsn(clerk, manager);
                String departmentName = ma.getDepName();
                System.out.println("Enter salary for new employee: ");
                double salary = Employee.getScanner().nextDouble();

                // Check if condition is hold in topic of salary assignment
                Clerk highestSalary = Collections.max(clerk, Comparator.comparing(c -> c.getSalary()));
                if(highestSalary.getSalary()< salary*1.1){
                    salary = newClerk.averageSalaray(clerk, departmentName);
                }

                newClerk = new Clerk(employeeID, name, ssn, salary, departmentName);

                clerk.add(newClerk);

                System.out.println("\nNew Clerk is added to staff of " + ma.getName() + "\n");
                System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s%-30s\033[0m\n", "Employee ID",
                        "Employee", "SSN", "Department", "Salary");
                System.out.println(
                        "--------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-30s%-30s%-30s%-30s%.2f$\n", "ID: " + newClerk.getEmployeeID(),
                        newClerk.getName(), newClerk.getSsn(), newClerk.getDepName(),
                        newClerk.getSalary());

            }

            // If Manager doesn´t exists at all
        } else {
            System.out.println(
                    "\nManager couldn´t be found by the system. Please check your entered values and restart program again\n");
        }
    }

    public void removeEmployee(List<Manager> manager, List<Clerk> clerk) {

        // Getting basic information about Manager
        Manager ma = employee(manager);

        if (ma != null) {

            List<Clerk> myClerks = clerk.stream()
                    .filter(c -> c.getDepName().equals(ma.getDepName()))
                    .collect(Collectors.toList());

            // Collecting information about later evaluation
            int sizeBefore = clerk.size();

            System.out.println(
                    "\nPlease choose employee you want to remove from staff by his ID or Name\n");
            System.out.printf("\033[1m%-30s%-30s%-30s%-30s%-30s\033[0m\n", "Employee ID",
                    "Employee", "SSN", "Department", "Salary");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------");
            for (Clerk c : myClerks) {
                System.out.printf("%-30s%-30s%-30s%-30s%.2f$\n", "ID: " + c.getEmployeeID(),
                        c.getName(), c.getSsn(), c.getDepName(), c.getSalary());
            }

            System.out.print("\nID or Name: ");
            String inputUser = Employee.getScanner().nextLine();

            if (inputUser.contains(" ")) {
                clerk.removeIf((c -> c.getName().equals(inputUser)));
            } else {
                clerk.removeIf((c -> c.getEmployeeID() == Integer.parseInt(inputUser)));
            }
            System.out.println(
                    sizeBefore > clerk.size() ? "Removal successfull\n" : "Removal failed\n");

        } else {
            System.out.println(
                    "\nManager couldn´t be found by the system. Please check your entered values and restart program again\n");
        }

    }

    public void displayAllStaffs(List<Manager> manager, List<Clerk> clerk) {

        Map<String, List<Clerk>> myStaff = clerk.stream()
                .collect(Collectors.groupingBy(c -> c.getDepName()));

        for (Manager m : manager) {
            System.out.printf("\n\033[1m%-30s%-30s%-30s\033[0m\n", "Manager ID", "Manager",
                    "Salary");
            System.out.println(
                    "---------------------------------------------------------------------");
            System.out.printf("%-30s%-30s%.2f$\n", "ID: " + m.getEmployeeID(), m.getName(),
                    m.getSalary());
            for (Map.Entry<String, List<Clerk>> entry : myStaff.entrySet()) {
                if (m.getDepName().equals(entry.getKey())) {
                    System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s%-30s\033[0m\n", "Employee ID",
                            "Employee", "SSN", "Department", "Salary");
                    System.out.println(
                            "---------------------------------------------------------------------------------------------------------------------------------");
                    for (Clerk c : entry.getValue()) {
                        System.out.printf("%-30s%-30s%-30s%-30s%.2f$\n", "ID: " + c.getEmployeeID(),
                                c.getName(), c.getSsn(), c.getDepName(), c.getSalary());
                    }
                    System.out.println("\n");
                }
            }
        }
    }

    public void displayStaff(List<Clerk> clerk) {
        Map<String, List<Clerk>> myStaff = clerk.stream()
                .filter(c -> c.getDepName().equals(this.getDepName()))
                .collect(Collectors.groupingBy(c -> c.getDepName()));

        System.out.printf("\n\033[1m%-30s%-30s%-30s\033[0m\n", "Manager ID", "Manager", "Salary");
        for (Map.Entry<String, List<Clerk>> entry : myStaff.entrySet()) {
            System.out.println(
                    "---------------------------------------------------------------------");
            System.out.printf("%-30s%-30s%.2f$\n", "ID: " + this.getEmployeeID(), this.getName(),
                    this.getSalary());
            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s%-30s\033[0m\n", "Employee ID",
                    "Employee", "SSN", "Department", "Salary");
            System.out.println(
                    "---------------------------------------------------------------------------------------------------------------------------------");
            for (Clerk c : entry.getValue()) {

                System.out.printf("%-30s%-30s%-30s%-30s%.2f$\n", "ID: " + c.getEmployeeID(),
                        c.getName(), c.getSsn(), c.getDepName(), c.getSalary());

            }
            System.out.println("\n");
        }
    }

    @Override
    public List<Manager> readCSV() {

        List<Manager> manager = new ArrayList<Manager>();
        String line = null;

        try {
            BufferedReader readCSVManager = new BufferedReader(new FileReader(super.pathImport()));
            readCSVManager.readLine();

            while ((line = readCSVManager.readLine()) != null) {
                String[] valMan = line.split(";");
                if (Boolean.parseBoolean(valMan[5])) {
                    manager.add(new Manager(Integer.parseInt(valMan[0]), valMan[1], valMan[2],
                            Double.parseDouble(valMan[3]), valMan[4],
                            Boolean.parseBoolean(valMan[5])));
                }
            }

            readCSVManager.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(manager.size() > 0
                ? "\n================================ \033[1mIMPORT MANAGER SUCCESSFUL\033[0m ================================"
                : "\n================================ \033[1mIMPORT MANAGER FAILED\033[0m ================================");

        return manager;
    }

}
