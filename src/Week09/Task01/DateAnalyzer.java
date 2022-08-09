package Week09.Task01;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DateAnalyzer {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("\nPlease enter desired date below -> Format {dd.MM.yyyy} => e.g.: 31.12.2020");
        System.out.print("Your Date input: ");
        String dateInput = scanner.nextLine();

        LocalDate firstDate = LocalDate.parse(dateInput, parser);

        boolean leapYear = firstDate.isLeapYear(); // Check if year is leap year
        String leap = (leapYear ? "is leap year" : "is not a leap year");
        int monthLength = firstDate.getMonth().length(leapYear); //
        LocalDate secondDate = LocalDate.of(firstDate.getYear(), Month.MAY, 1); // Initialize variable with 2nd date

        // Count range between 1st day of given year and first of may
        long daysInBetween = Math.abs(ChronoUnit.DAYS.between(firstDate, secondDate));

        // Output of our results
        System.out.printf("\nThe year %tY %s \n", firstDate, leap);
        System.out.printf("The month %s contains in total %d days\n", firstDate.getMonth(), monthLength);
        System.out.printf("There´re %d days in between the %te.%tm.%tY and the %te.%tm.%tY\n", daysInBetween,
                firstDate, firstDate, firstDate, secondDate, secondDate, secondDate);
        scanner.close();
    }

}
