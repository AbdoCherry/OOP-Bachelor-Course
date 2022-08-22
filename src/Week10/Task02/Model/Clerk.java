package Week10.Task02.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import Week10.Task02.Abstract.Employee;

public class Clerk extends Employee {

    public Clerk() {

    }

    public Clerk(int employeeID, String name, String ssn, double salary, String depName) {
        super(employeeID, name, ssn, salary, depName);
    }

    public static void countEmployees(List<Clerk> staff) {

        Map<String, Long> countEmployees = staff.stream()
                .collect(Collectors.groupingBy((Employee::getDepName), Collectors.counting()));

        System.out.printf("\n\033[1m%-30s%-20s%-30s\033[0m\n", "Department", "|", "Employees");
        System.out.println("---------------------------------------------------------------");
        for (Map.Entry<String, Long> entry : countEmployees.entrySet()) {
            System.out.printf("%-30s%-20s%-30d\n", entry.getKey(), "|", entry.getValue());

        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Employee> T employee(List<? extends Employee> list) {

        System.out.println("\nPlease enter the necessary information in the fields below for Clerk");
        System.out.print("First Name: ");
        String firstName = Employee.getScanner().nextLine();
        System.out.print("Last Name: ");
        String lastName = Employee.getScanner().nextLine();
        String name = firstName + " " + lastName;

        T employee = (T) list.stream().filter(t -> t.getName().equals(name)).findFirst()
                .orElse(null);

        if (employee == null) {
            employee = (T) new Clerk(0, name, null, 0.0, null);
        }

        return employee;
    }

    public int maxID(List<Clerk> clerk) {

        int maxID = 0;

        for (Clerk c : clerk) {
            if (maxID < c.getEmployeeID()) {
                maxID = c.getEmployeeID();
            }
        }

        return maxID + 1;
    }

    public double averageSalaray(List<Clerk> clerk, String department) {

        System.out.println("\nApparently the desired salary exceeds our policy of actual salaries");
        System.out.println("No exceeding of 10% than the highest salary");

        List<Clerk> myClerks = clerk.stream().filter(c -> c.getDepName().equals(department)).toList();

        Double avgDep = (myClerks.stream().mapToDouble(Clerk::getSalary)).sum() / myClerks.size();

        if (avgDep.isNaN()) {
            System.out.println(
                    "\nPlease enter a new salary again (per year before tax) for new employee. Otherwise he will be assigned with average salary");
            System.out.print("Salary: ");
            double salaryInput = Employee.getScanner().nextDouble();
            avgDep = (clerk.stream().mapToDouble(Clerk::getSalary)).sum() / clerk.size();

            if (salaryInput > avgDep) {
                System.out.println("\nEmployee will get the average salary assigned");
            } else {
                avgDep = salaryInput;
            }
        }

        return avgDep;

    }

    public String createSsn(List<Clerk> clerk, List<Manager> manager) {

        // Creating an ordered set of all ssn of the clerks
        TreeSet<Integer> mySsn = new TreeSet<Integer>();

        for (Manager m : manager) {
            mySsn.add(Integer.parseInt(m.getSsn()));
        }
        for (Clerk c : clerk) {
            mySsn.add(Integer.parseInt(c.getSsn()));
        }

        String randomSsn = String.valueOf(mySsn.last() + 1);
        return randomSsn;
    }

    @Override
    public List<Clerk> readCSV() {

        List<Clerk> staff = new ArrayList<Clerk>();
        String line = null;

        try {
            BufferedReader readCSVStaff = new BufferedReader(new FileReader(super.pathImport()));
            readCSVStaff.readLine();

            while ((line = readCSVStaff.readLine()) != null) {
                String[] valStaff = line.split(";");

                if (!Boolean.parseBoolean(valStaff[5])) {
                    staff.add(new Clerk(Integer.parseInt(valStaff[0]), valStaff[1], valStaff[2],
                            Double.parseDouble(valStaff[3]), valStaff[4]));
                }
            }

            readCSVStaff.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(staff.size() > 0
                ? "\n================================ \033[1mIMPORT CLERK SUCCESSFUL\033[0m ================================\n"
                : "\n================================ \033[1mIMPORT CLERK FAILED\033[0m ================================\n");

        return staff;
    }

}
