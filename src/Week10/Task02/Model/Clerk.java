package Week10.Task02.Model;

import Week10.Task02.Auxiliary.FileDialog;
import Week10.Task02.Generic.Employee;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Clerk extends Employee<Clerk> {

    static Scanner scannerClerk = new Scanner(System.in);

    private int manId;

    public Clerk() {
    }

    public Clerk(int empId, String firstName, String lastName, String ssn, double salary, int manId) {
        super(empId, firstName, lastName, ssn, salary);
        this.manId = manId;
    }

    public int getManId() {
        return manId;
    }

    public void setManId(int manId) {
        this.manId = manId;
    }

    private int chooseManager(@NotNull List<Manager> managers) {
        System.out.println("\nPlease select Manager by his ID");

        System.out.printf("%-5s%-10s%-30s%-5s\n", "|", "ID", "Manager", "|");
        managers.forEach(m -> {
            System.out.printf("%-5s%-10d%-30s%-5s\n", "|", m.getEmpId(), m.getFirstName() + " " + m.getLastName(), "|");
        });

        boolean declined = false;
        int managerID = 0;

        do {
            System.out.print("\nID: ");
            managerID = scannerClerk.nextInt();

            int finalManagerID = managerID;
            if (managers.stream().filter(m -> m.getEmpId() == finalManagerID).findFirst().isEmpty()) {
                System.out.println("\nID not found in manager staff\nPlease retry\n");
                declined = true;
            }
            declined = false;

        } while (declined);

        return managerID;
    }

    public void addClerk(List<Clerk> clerks, List<Manager> managers) {
        Clerk newClerk = addEmp(clerks);
        newClerk.setManId(chooseManager(managers));
        clerks.add(newClerk);
        updateLists(managers, clerks);

        System.out.println("\n" + newClerk.toString());

    }

    public void removeClerk(List<Clerk> clerks, List<Manager> managers) {
        super.removeEmp(clerks);
        updateLists(managers, clerks);
    }

    public static Set<Clerk> selectClerks(List<Clerk> clerks) {
        System.out.println("\nPlease select ID from clerks below. You can add up to 10");

        System.out.printf("%-5s%-10s%-30s%-5s\n", "|", "ID", "Name", "|");
        System.out.println("|============================================|");
        clerks.forEach(c -> {
            System.out.printf("%-5s%-10d%-30s%-5s\n", "|", c.getEmpId(), c.getFirstName() + " " + c.getLastName(), "|");
        });

        Set<Clerk> selectedClerks = new HashSet<>();
        char continueSelection = 'Y';
        int i = 0;

        do {
            System.out.print("\nID: ");
            int selection = scannerClerk.nextInt();
            Clerk selectClerk = clerks.stream()
                    .filter(c -> c.getEmpId() == selection).findFirst()
                    .orElse(null);

            if (selectClerk == null) {
                System.out.println("\nClerk not found");
                i--;
            } else if (selectedClerks.contains(selectClerk)) {
                System.out.println("\nClerk already selected");
                i--;
            } else {
                selectedClerks.add(selectClerk);
                i++;
            }

            System.out.print("\nContinue selection?\n[Yes = 'Y'/'y']\t[No = 'N'/'n']: ");
            continueSelection = Character.toUpperCase(scannerClerk.next().charAt(0));
        } while (continueSelection == 'Y' && (i < 10));

        return selectedClerks;
    }

    public void findClerk(List<Clerk> clerks) {
        super.findEmployee(clerks);
    }

    @Override
    public String toString() {

        return super.toString()
                + " manId = " + manId +
                "} ";
    }

    @Override
    public void writeCsv(List<Clerk> clerks) {
        try {
            PrintWriter writeCSV = new PrintWriter(FileDialog.FileChooser(this.getClass().getSimpleName()));
            StringBuilder sbClerk = new StringBuilder();

            sbClerk.append("EmployeeID");
            sbClerk.append(";");
            sbClerk.append("FirstName");
            sbClerk.append(";");
            sbClerk.append("LastName");
            sbClerk.append(";");
            sbClerk.append("SSN");
            sbClerk.append(";");
            sbClerk.append("Salary");
            sbClerk.append(";");
            sbClerk.append("ManagerID");
            sbClerk.append("\n");

            for (Clerk c : clerks) {
                sbClerk.append(String.valueOf(c.getEmpId()));
                sbClerk.append(";");
                sbClerk.append(c.getFirstName());
                sbClerk.append(";");
                sbClerk.append(c.getLastName());
                sbClerk.append(";");
                sbClerk.append(c.getSsn());
                sbClerk.append(";");
                sbClerk.append(String.valueOf(c.getSalary()));
                sbClerk.append(";");
                sbClerk.append(String.valueOf(c.getManId()));
                sbClerk.append("\n");

            }

            writeCSV.write(sbClerk.toString());
            writeCSV.close();
            System.out.println("================== EXPORT MANAGER SUCCESSFULLY ==================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clerk> readCSV() {

        List<Clerk> clerks = new ArrayList<>();
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileDialog.FileChooser(this.getClass().getSimpleName())));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                clerks.add(new Clerk(
                        Integer.parseInt(values[0]),
                        values[1],
                        values[2],
                        values[3],
                        Double.parseDouble(values[4]),
                        Integer.parseInt(values[5])
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(clerks.size() > 0 ? "=========== IMPORT CLERK SUCCESSFULLY ===========" : "=========== IMPORT CLERK FAILED ===========");
        return clerks;
    }
}
