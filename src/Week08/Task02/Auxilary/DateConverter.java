package Week08.Task2.Auxilary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Week08.Task2.Abstract.Account;

public class DateConverter {

    public static SimpleDateFormat sdfParser = new SimpleDateFormat("dd.MM.yyyy");
    public static SimpleDateFormat sdfFormatter = new SimpleDateFormat("MMM yyyy");

    public static String daySuffix(String dayInput) {

        int day = Integer.parseInt(dayInput);

        if (day >= 11 && day <= 13) {
            return "th";
        } else {

            switch (day % 10) {
                case 1:
                    return "st";
                case 2:
                    return "nd";
                case 3:
                    return "rd";
                default:
                    return "th";
            }
        }
    }

    public static String dateFormatter(String dateInput) {

        String dayWithSuffix = null, monthYearString = null;
        String myDate = null;

        try {
            Date date = sdfParser.parse(dateInput);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            dayWithSuffix = c.get(Calendar.DAY_OF_MONTH)
                    + daySuffix(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));

            monthYearString = sdfFormatter.format(date);
            myDate = dayWithSuffix + " of " + monthYearString;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return myDate;
    }

    public static boolean dateChecker(String expiration) {

        boolean valid = false;

        Date date = new Date();

        try {
            Date expirationDate = sdfParser.parse(expiration);

            valid = !expirationDate.after(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return valid;
    }

    public static String createDate(char controller) {

        String createDate = null;
        Date today = new Date();
        char inputUser;

        switch (controller) {

            case 'C':
            case 'c':

                System.out.print(
                        "\nDo you want to open your Account today or on desired date in future?\t[Today = \"T\"/\"t\"] - [Manually = \"M\"/\"m\"]: ");
                inputUser = Account.scanner.next().charAt(0);
                Account.scanner.nextLine();

                if (inputUser == 'T' || inputUser == 't') {
                    createDate = sdfParser.format(today);
                } else if (inputUser == 'M' || inputUser == 'm') {
                    System.out.println(
                            "\nEnter desired Date you want to open your Account -> Format {dd.MM.yyyy} => e.g.: 21.10.2020");
                    System.out.print("Date: ");
                    try {
                        Date dateInput = sdfParser.parse(Account.scanner.nextLine());
                        createDate = sdfParser.format(dateInput);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("\nWrong Entry. Creation date of your account will be setted to today");
                    createDate = sdfFormatter.format(today);
                }

                break;

            case 'I':
            case 'i':

                System.out.print(
                        "\nDo you want to start your Investment Date today?\t[Today = \"T\"/\"t\"] - [Manual  = \"M\"/\"m\"]: ");
                inputUser = Account.scanner.next().charAt(0);
                Account.scanner.nextLine();

                if (inputUser == 'T' || inputUser == 't') {
                    createDate = sdfParser.format(today);
                } else if (inputUser == 'M' || inputUser == 'm') {
                    System.out.println(
                            "\nEnter desired Date you want to start your investment -> Format {dd.MM.yyyy} => e.g.: 21.10.2020");
                    System.out.print("Date: ");
                    String dateInput = Account.scanner.nextLine();
                    try {
                        Date dateInputDate = sdfParser.parse(dateInput);
                        createDate = sdfParser.format(dateInputDate);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out
                            .println("\nWrong Entry. Investment date of your deposit Account will be setted to today");
                    createDate = sdfParser.format(today);
                }

                break;

            default:

                System.out.println("\nEntry isn´t recognized. Please cancel and restart program later\n");
        }

        return createDate;
    }

    public static String expirationDate(String investmentDate) {

        String expirationDateString = null;
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdfParser.parse(investmentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(
                "\nChoose period from options below you want to hold investment: ");

        // Making period selection more realistic
        System.out.println("\nChoose amount you want to withdraw or enter manually");
        System.out.println("1 Year  <-- 1\t 10 --> Years 10");
        System.out.println("2 Years <-- 2\t 20 --> Years 20");
        System.out.println("3 Years <-- 3\t 30 --> Years 30");
        System.out.println("4 Years <-- 4\t 40 --> Years 40");
        System.out.println("5 Years <-- 5\t 50 --> Years 50");

        System.out.print("\nEnter period from above or type manually -> Format {dd.MM.yyyy} => e.g.: 21.12.2025: ");
        String inputYear = Account.scanner.nextLine();

        try {
            if (inputYear.contains(".")) {
                Date dateInput = sdfParser.parse(inputYear);
                expirationDateString = sdfParser.format(dateInput);
            } else {
                c.add(Calendar.YEAR, Integer.parseInt(inputYear));
                expirationDateString = sdfParser.format(c.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return expirationDateString;

    }

}
