package Week02.Task2;

import java.util.Scanner;

public class VacationEntitlement {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the duration of your company affiliation");
        System.out.print("Years: ");
        int years = scanner.nextInt();

        switch (years) {

            case 0:

                System.out.println("\nWe´re sorry. But your entry wasn´t recognized\n");

                break;

            case 1:

                System.out.println("\nYou are entitled to 15 days of vacation in the year\n");

                break;

            case 2:
            case 3:

                System.out.println("\nYou are entitled to 20 days of vacation in the year\n");

                break;

            case 4:
            case 5:

                System.out.println("\nYou are entitled to 25 days of vacation in the year\n");

                break;

            default:

                System.out.println("\nYou are entitled to 30 days of vacation in the year\n");

        }
        scanner.close();
    }

}
