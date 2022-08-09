package Week07.Task03;
import java.util.Scanner;

public class HR {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Employee[] employees = new Employee[10];

        employees[0] = new Engineer(101, "012-34-5678", "Jane Schmidt", 120345.67);
        employees[1] = new Engineer(102, "012-34-5679", "Shaddap Yakkant", 110500.99);
        employees[2] = new Admin(304, "108-23-6509", "Bill Moser", 75002.34);
        employees[3] = new Admin(305, "108-23-6510", "Ben Dover", 90300.50);
        employees[4] = new Manager(207, "054-12-2367", "Barbara Janson", 109501.36, "IT-Service US");
        employees[5] = new Manager(208, "054-12-2368", "Tess Tickles", 150993.20, "Compliance & Regulatory");
        employees[6] = new Director(12, "099-45-2340", "Susanne Weiler", 120567.36, "Global Marketing", 1000000.00);
        employees[7] = new Director(13, "099-45-2341", "Dickins Cyder", 200400.55, "Technology & Innovations",
                500250000.00);

        System.out.println(
                "\n---------------------------------------------------------------- Human Resource Pool ----------------------------------------------------------------\n");

        char menu;
        int input = 3;
        String name = null;
        String nameNew = null;
        double salaryNew = 0.0;
        do {

            System.out.println("\nPlease choose on of the following options");
            System.out.print("[Request salary raise = 1]\t[Request name editing = 2]\t[Cancel menu = 3]: ");
            input = scanner.nextInt();

            switch (input) {

                case 1:

                    System.out
                            .println(
                                    "\n---------------------------------------------------------------- Payraise ----------------------------------------------------------------\n");
                    System.out.println("\nPlease enter the necessary information in the fields below");
                    System.out.print("\nName: ");
                    scanner.nextLine();
                    name = scanner.nextLine();

                    for (Employee e : employees) {
                        if (e != null) {
                            if (e.getName().equals(name)) {
                                System.out.print("New salary: ");
                                // scanner.nextLine();
                                salaryNew = scanner.nextDouble();
                                e.increaseSalary(salaryNew);
                                break;
                            }
                        }
                    }

                    break;

                case 2:
                    System.out.println("\nPlease enter the necessary information in the fields below");
                    System.out.print("\nName: ");
                    scanner.nextLine();
                    name = scanner.nextLine();

                    for (Employee e : employees) {
                        if (e != null) {
                            if (e.getName().equals(name)) {
                                System.out.print("New name: ");
                                nameNew = scanner.nextLine();
                                e.editName(nameNew);
                                break;
                            }
                        }
                    }

                    break;

                case 3:

                    System.out.println("\nOperation cancelled\n");

                    break;

                default:

                    System.out.println("\nWrong entry. Please close or restart program later");
            }

            System.out.print("\nDo you want to restart the program again?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            menu = scanner.next().charAt(0);
        } while (menu == 'Y' || menu == 'y');

        for (Employee e : employees) {
            if (e != null) {
                e.displayEmployees();
            }
        }

        Employee.stockOptions(employees);
        System.out.println(" ");

        scanner.close();
    }
}