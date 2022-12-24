package Week10.Task02.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Week10.Task02.Abstract.Employee;
import Week10.Task02.Auxiliary.FileDialog;

public class Clerk extends Employee<Clerk> {

    private final Scanner scannerClerk = new Scanner(System.in);

    private int managerID;

    public Clerk() {
    }

    public Clerk(int empID, String firstName, String lastName, double salary, String department, int managerID) {
        super(empID, firstName, lastName, salary, department);
        this.managerID = managerID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public int incEmpID(List<Clerk> clerks) {

        return clerks.stream().max(Comparator.comparing(Clerk::getEmpID)).orElseThrow(NoSuchElementException::new)
                .getEmpID() + 1;
    }

    private double averageSalary(List<Clerk> clerks, Clerk clerk) {

        return (clerks.stream().filter(c -> c.getDepartment().equals(clerk.getDepartment()))
                .max(Comparator.comparing(Clerk::getSalary)).orElseThrow(NoSuchElementException::new).getSalary()
                - clerks.stream().filter(c -> c.getDepartment().equals(clerk.getDepartment()))
                .min(Comparator.comparing(Clerk::getSalary)).orElseThrow(NoSuchElementException::new)
                .getSalary())
                / 2;
    }

    public void addClerk(List<Clerk> clerks, List<Manager> managers, Map<Integer, String> departments) {

        // Check if clerk are already in company
        Clerk newClerk = singleObject(clerks);

        if (newClerk.getEmpID() != 0) {
            System.out.println("\nEmployee does exist in our company");
            System.exit(1);
        }
        newClerk.setEmpID(incEmpID(clerks));

        System.out.println("\nPlease select department you want to join by ID\n");
        System.out.printf("\033[1m%-5s%-15s%-40s%-5s\033[0m\n", "|", "ID", "Department", "|");
        System.out.println("|-----------------------------------------------------------|");

        // Assign department to new clerk
        for (Map.Entry<Integer, String> entry : departments.entrySet()) {
            System.out.printf("%-5s%-15d%-40s%-5s\n", "|", entry.getKey(), entry.getValue(), "|");
        }
        System.out.print("\nSelection of department: ");
        int selection = scannerClerk.nextInt();
        Manager manager = managers.stream().filter(m -> m.getEmpID() == selection).findFirst().orElse(null);

        System.out.println("\nYour successfully joined department: " + departments.get(selection)
                + "\nYour manager is: " + manager.getFirstName() + " " + manager.getLastName() + "\n");
        newClerk.setDepartment(departments.get(selection));
        newClerk.setManagerID(manager.getEmpID());

        // Assign salary to new clerk
        System.out.println(
                "\nPlease enter your desired salary\nBehold, that we can only accept up to 20% of the average salary of your department");
        System.out.print("Input: ");
        double newSalary = scannerClerk.nextDouble();

        double avrgSalary = averageSalary(clerks, newClerk);
        if (newSalary > avrgSalary) {
            System.out.println(
                    "\nYour desired salary is higher than the average salary by more than 20%.\nYou will get assigned the average salary + 20% by default. For any salary raise please request this officially from your manager\n");
            newClerk.setSalary(avrgSalary);
        } else {
            newClerk.setSalary(newSalary);
        }

        // Update both lists
        newClerk.updateClerk(clerks, 'a');
        manager.updateManager(clerks);
    }

    public void removeClerk(List<Clerk> clerks, List<Manager> managers) {

        // Check if clerk exists
        Clerk rmClerk = singleObject(clerks);

        if (rmClerk.getEmpID() == 0) {
            System.out.println("\nClerk not existing in company\n");
            System.exit(1);
        }

        Manager myManager = managers.stream().filter(m -> m.getEmpID() == rmClerk.getManagerID()).findFirst()
                .orElse(null);
        rmClerk.updateClerk(clerks, 'r');
        myManager.updateManager(clerks);
    }

    public void requestPromotion(List<Clerk> clerks, List<Manager> managers) {

        //Check if clerk exists
        int indexOfClerk = clerks.indexOf(singleObject(clerks));
        if (indexOfClerk == -1) {
            System.out.println("\nClerk does not exist in company\n");
            System.exit(1);
        }

        double salaryBeforePromotion = clerks.get(indexOfClerk).getSalary();
        double salaryAfterPromotion;

        // Let clerk choose kind of promotion
        System.out.println("\nPlease choose kind of promotion");
        System.out.println("[Selection: 'S'/'s']\t[Entering = 'E'/'e']");
        System.out.print("Decision: ");
        char decision = Character.toUpperCase(scannerClerk.next().charAt(0));

        switch (decision) {
            case 'S':
                System.out.println("\nPlease select raise by percentage");
                System.out.println("[Raise by 5%  = 10]\t[Raise by 10% = 10]");
                System.out.println("[Raise by 15% = 15]\t[Raise by 20% = 20]");
                System.out.print("Select rate: ");
                int selection = scannerClerk.nextInt();
                salaryAfterPromotion = salaryBeforePromotion * (1 + (double) selection / 100);
                break;

            case 'E':
                System.out.println("\nPlease enter your desired salary");
                System.out.print("Enter new salary: ");
                double newSalary = scannerClerk.nextDouble();

                if (averageSalary(clerks, clerks.get(indexOfClerk)) < newSalary) {
                    Manager manager = managers.stream()
                            .filter(m -> m.getEmpID() == clerks.get(indexOfClerk).getManagerID()).findFirst().orElse(null);

                    System.out.println("\nDesired salary exceeds our policy and has to be approved by your manager " + manager.getFirstName() + " " + manager.getLastName());

                    if (manager.approveRequest(clerks.get(indexOfClerk), newSalary)) {
                        System.out.println("\nYour salary request was approved by your manager: " + manager.getFirstName() + " " + manager.getLastName());
                        salaryAfterPromotion = newSalary;
                    } else {
                        System.out.println("\nWe are sorry to inform you that your salary request was rejected by your manager: " + manager.getFirstName() + " " + manager.getLastName());
                        salaryAfterPromotion = averageSalary(clerks, clerks.get(indexOfClerk)) * 1.2;

                    }
                } else {
                    salaryAfterPromotion = newSalary;
                }
                break;
            default:
                System.out.println("\nError. Wrong entry\n");
                salaryAfterPromotion = 0;
                System.exit(1);
        }
        clerks.get(indexOfClerk).setSalary(salaryAfterPromotion);
        System.out.printf("\033[1m%-5s%-20s%-20s%-5s\033[0m\n", "|", "Salary before", "Salary after", "|");
        System.out.println("|--------------------------------------------|");
        System.out.printf("%-5s%-20.2f%-20.2f%-5s\n", "|", salaryBeforePromotion, salaryAfterPromotion, "|");
    }

    public void updateClerk(List<Clerk> clerks, char decision) {
        int sizeBefore = clerks.size();
        switch (decision) {
            case 'a':
                clerks.add(this);

                System.out.println(sizeBefore < clerks.size() ? "Clerk added successfully to company" + this.toString()
                        : "Employee not added successfully, please check source code\n");
                break;
            case 'r':
                clerks.remove(this);
                System.out.println(sizeBefore > clerks.size() ? "Clerk removed successfully to company\n " + this.toString()
                        : "Clerk not removed successfully, please check source code");
                break;

            default:
                System.out.println("\nError: '" + decision + "' decision not clear\n");
        }
    }

    public String toString() {

        return "\n[Employee-ID: '" + this.getEmpID() + "' - Name: '" + this.getFirstName() + " " + this.getLastName()
                + "' - Salary: '" + this.getSalary() + "'' - Department: '" + this.getDepartment() + "' - Manager-ID: '"
                + this.getManagerID() + "']";
    }

    @Override
    public void findEmp() {
        if (this != null) {
            super.findEmp();
            System.out.println("Manager-ID: " + this.managerID);
            System.out.println("-----------------------------------");
        }
    }

    @Override
    public Clerk singleObject(List<Clerk> list) {
        // TODO Auto-generated method stub

        String[] fullName = name();

        Clerk clerk = list.stream().filter(
                        c -> c.getFirstName().equalsIgnoreCase(fullName[0]) && c.getLastName().equalsIgnoreCase(fullName[1]))
                .findFirst().orElse(null);

        if (clerk == null) {
            clerk = new Clerk();
            clerk.setEmpID(0);
            clerk.setFirstName(fullName[0]);
            clerk.setLastName(fullName[1]);
        }
        return clerk;
    }

    @Override
    public void displayAll(List<Clerk> list) {
        list.sort(Comparator.comparing(Clerk::getEmpID));
        System.out.printf("\n\033[1m%-5s%-15s%-30s%-15s%-40s%-15s%-5s\033[0m\n", "|", "Emp-ID", "Name", "Salary",
                "Department", "Manager-ID", "|");
        System.out.println(
                "|---------------------------------------------------------------------------------------------------------------------------|");
        list.forEach(c -> System.out.printf("%-5s%-15d%-30s%-15.2f%-40s%-15d%-5s\n", "|", c.getEmpID(),
                c.getFirstName() + " " + c.getLastName(), c.getSalary(), c.getDepartment(), c.getManagerID(), "|"));

    }

    @Override
    public List<Clerk> readCSV() {
        // TODO Auto-generated method stub
        String line;
        List<Clerk> clerks = new ArrayList<>();

        try {
            BufferedReader readCSVReader = new BufferedReader(
                    new FileReader(FileDialog.FileChooser(this.getClass().getSimpleName())));
            readCSVReader.readLine();

            while ((line = readCSVReader.readLine()) != null) {
                String[] values = line.split(";");
                clerks.add(new Clerk(Integer.parseInt(values[0]), values[1], values[2], Double.parseDouble(values[3]),
                        values[4], Integer.parseInt(values[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(clerks.size() > 0 ? "================ IMPORT CLERK SUCCESSFULLY ================"
                : "================ IMPORT CLERK FAILED ================");

        return clerks;
    }

    @Override
    public void writeCSV(List<Clerk> clerks) {

        try {
            PrintWriter writeCSV = new PrintWriter(FileDialog.FileChooser(this.getClass().getSimpleName()));
            StringBuilder sbClerk = new StringBuilder();

            sbClerk.append("EmployeeID");
            sbClerk.append(";");
            sbClerk.append("FirstName");
            sbClerk.append(";");
            sbClerk.append("LastName");
            sbClerk.append(";");
            sbClerk.append("Salary");
            sbClerk.append(";");
            sbClerk.append("Department");
            sbClerk.append(";");
            sbClerk.append("Manager");
            sbClerk.append(";");
            sbClerk.append("\n");

            for (Clerk c : clerks) {
                sbClerk.append(c.getEmpID());
                sbClerk.append(";");
                sbClerk.append(c.getFirstName());
                sbClerk.append(";");
                sbClerk.append(c.getLastName());
                sbClerk.append(";");
                sbClerk.append(c.getSalary());
                sbClerk.append(";");
                sbClerk.append(c.getDepartment());
                sbClerk.append(";");
                sbClerk.append(c.getManagerID());
                sbClerk.append("\n");

            }

            writeCSV.write(sbClerk.toString());
            writeCSV.close();
            System.out.println("================== EXPORT CLERK SUCCESSFULLY ==================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

