package Week09.Task02.BusinessRules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateConverter {

    private static final Scanner scanner = new Scanner(System.in);
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
            myDate = null;
        }

        return myDate;
    }

    public static boolean dateChecker(String maturityDate) {

        boolean valid = false;

        Date date = new Date();

        try {
            Date maturity = sdfParser.parse(maturityDate);

            valid = !maturity.after(date);
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
                inputUser = scanner.next().charAt(0);
                scanner.nextLine();

                if (inputUser == 'T' || inputUser == 't') {
                    createDate = sdfParser.format(today);
                } else if (inputUser == 'M' || inputUser == 'm') {
                    System.out.println(
                            "\nEnter desired Date you want to open your Account -> Format {dd.MM.yyyy} => e.g.: 21.10.2020");
                    System.out.print("Date: ");
                    try {
                        Date dateInput = sdfParser.parse(scanner.nextLine());
                        createDate = sdfParser.format(dateInput);
                    } catch (ParseException e) {
                        createDate = null;
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
                inputUser = scanner.next().charAt(0);
                scanner.nextLine();

                if (inputUser == 'T' || inputUser == 't') {
                    createDate = sdfParser.format(today);
                } else if (inputUser == 'M' || inputUser == 'm') {
                    System.out.println(
                            "\nEnter desired Date you want to start your investment -> Format {dd.MM.yyyy} => e.g.: 21.10.2020");
                    System.out.print("Date: ");
                    String dateInput = scanner.nextLine();
                    try {
                        Date dateInputDate = sdfParser.parse(dateInput);
                        createDate = sdfParser.format(dateInputDate);

                    } catch (ParseException e) {
                        createDate = null;
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

    public static String dateCalc(String investmentDate, String maturityDate, char controller) {

        String result = null;

        if (controller == 'P' || controller == 'p') {

            try {
                Date investmentDateToDate = sdfParser.parse(investmentDate);
                Calendar c = Calendar.getInstance();
                c.setTime(investmentDateToDate);

                c.add(Calendar.DATE, Integer.parseInt(maturityDate));
                Date resultDate = c.getTime();
                result = sdfParser.format(resultDate);
            } catch (ParseException e) {
                result = null;
            }

        } else if (controller == 'M' || controller == 'm') {
            try {
                Date investmentDateToDate = sdfParser.parse(investmentDate);
                Date maturityDateToDate = sdfParser.parse(maturityDate);
                long diff = (maturityDateToDate.getTime() - investmentDateToDate.getTime()) / 86400000;

                result = String.valueOf(diff);
            } catch (ParseException e) {
                result = null;
            }
        }

        return result;

    }

}
