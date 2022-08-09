package Week08.Task1.Auxilary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DateConverter {

    private static final Scanner scanner = new Scanner(System.in);

    public static SimpleDateFormat sdfParser = new SimpleDateFormat("dd.MM.yy");
    public static SimpleDateFormat sdfSpecial = new SimpleDateFormat("dd.MMM.yyyy");
    public static SimpleDateFormat sdfFormatter = new SimpleDateFormat("MMM yyyy", Locale.US);

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSdfParser() {
        return sdfParser;
    }

    /**
     * @param sdfParser
     */
    public static void setSdfParser(SimpleDateFormat sdfParser) {
        DateConverter.sdfParser = sdfParser;
    }

    /**
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSdfFormatter() {
        return sdfFormatter;
    }

    /**
     * @param sdfFormatter
     */
    public static void setSdfFormatter(SimpleDateFormat sdfFormatter) {
        DateConverter.sdfFormatter = sdfFormatter;
    }

    /**
     * @param day
     * @return String
     */
    public static String daySuffix(int day) {

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

    /**
     * @param date
     * @return String[]
     * @throws ParseException
     */
    public static String[] dateFormatter(String date) throws ParseException {

        String[] myDate = new String[2];

        Date convertDate;

        try {
            convertDate = sdfParser.parse(date);
        } catch (Exception e) {
            try {
                convertDate = sdfFormatter.parse(date);
            } catch (ParseException f) {
                String cleanedDate = date.charAt(0) + "." + date.substring(date.length() - 8, date.length() - 4) + "."
                        + date.substring(date.length() - 4);
                cleanedDate = cleanedDate.replace(" ", "");
                convertDate = sdfSpecial.parse(cleanedDate);
            }

        }

        Calendar c = Calendar.getInstance();
        c.setTime(convertDate);

        myDate[0] = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String rest = sdfFormatter.format(convertDate);
        myDate[1] = rest;
        return myDate;
    }

    /**
     * @param controller
     * @return String
     * @throws ParseException
     */
    public static String createDate(char controller) throws ParseException {

        Date myDate;
        String date = null;
        char createDate;
        switch (controller) {

            case 'C':
            case 'c':

                System.out.println("\nDo you want to open your deposit today or on a desired date?");
                System.out.print("[Today = \"T\" | \"t\"] - [Manually = \"M\" | \"m\"]: ");
                createDate = scanner.next().charAt(0);
                scanner.nextLine();

                if (createDate == 'T' || createDate == 't') {
                    // Date myDate = new Date();
                    myDate = new Date();
                    date = sdfFormatter.format(myDate);
                } else if (createDate == 'M' || createDate == 'm') {
                    System.out.print("\nEnter date -> Format {dd.MM.yyyy} => e.g.: 10.10.2020: ");
                    myDate = sdfParser.parse(scanner.nextLine());
                    date = sdfFormatter.format(myDate);
                } else {
                    date = null;
                }

                break;

            case 'I':
            case 'i':

                System.out.println("\nDo you want to start the investment date today?");
                System.out.print("[Yes = \"T\" | \"t\"] - [No = \"N\" | \"n\"]: ");
                createDate = scanner.next().charAt(0);
                scanner.nextLine();

                if (createDate == 'T' || createDate == 't') {
                    myDate = new Date();
                    date = sdfFormatter.format(myDate);
                } else if (createDate == 'N' || createDate == 'n') {
                    System.out.print("\nEnter date -> Format {dd.MM.yyyy} => e.g.: 10.10.2020: ");
                    myDate = sdfParser.parse(scanner.nextLine());
                    date = sdfFormatter.format(myDate);
                } else {
                    date = null;
                }

                break;

            default:

                date = null;

        }

        return date;
    }

    /**
     * @param investmentDay
     * @return String
     * @throws ParseException
     */
    public static String maternityDay(String[] investmentDay) throws ParseException {

        System.out.println("\nHow long do you want to hold the investment?");

        SimpleDateFormat singleFormatter = new SimpleDateFormat("dMMM yyyy");
        Date myDate;
        String date = investmentDay[0] + investmentDay[1];
        int decade = 10;
        for (int i = 1; i <= 5; i++) {
            String year;
            String decadeText = "Years";

            if (i == 1) {
                year = "Year ";
            } else {
                year = "Years";
            }

            System.out.println(
                    "[" + i + " " + year + " = " + i + "]\t\t[" + decade + " " + decadeText + " = " + decade + "]");
            decade += 10;

        }
        System.out.print(
                "\nSelect one option from above or type custom date -> Format {dd.MM.yyy} => e.g.: 01.01.2042: ");
        System.out.print("Your Input: ");
        String input = scanner.nextLine();
        try {
            Date maturityDate = new SimpleDateFormat("dd.MM.yyyy").parse(input);
            myDate = singleFormatter.parse(date);
            if (maturityDate.after(myDate)) {
                date = sdfFormatter.format(maturityDate);
            } else {
                date = null;
                System.out.println(
                        "\nUnfortunately the maturity date is invalid because it´s before the investment date\n");
                System.out.println("Please restart program again\n");
                System.exit(0);
            }
        } catch (ParseException e) {

            int maturityDate = Integer.parseInt(input);
            myDate = singleFormatter.parse(date);

            Calendar c = Calendar.getInstance();

            c.add(Calendar.YEAR, maturityDate);
            myDate = c.getTime();
            date = sdfFormatter.format(myDate);
        }
        return date;
    }

    /**
     * @param maturityDate
     * @return boolean
     * @throws ParseException
     */
    public static boolean checkDate(String maturityDate) throws ParseException {

        boolean valid = false;
        Date today = new Date();
        Date maturityDateToDate;

        try {
            maturityDateToDate = sdfParser.parse(maturityDate);
        } catch (ParseException e) {
            try {
                maturityDateToDate = sdfSpecial.parse(maturityDate);
            } catch (Exception f) {

                String maturityDateClean = maturityDate.charAt(0) + "."
                        + maturityDate.substring(maturityDate.length() - 8, maturityDate.length() - 4) + "."
                        + maturityDate.substring(maturityDate.length() - 4);
                maturityDateClean = maturityDateClean.replace(" ", "");
                maturityDateToDate = sdfSpecial.parse(maturityDateClean);
            }

        }

        // Count the days in between today and maturity date of time deposit account
        long difference = (maturityDateToDate.getTime() - today.getTime()) / 86400000; // Divide by no. of ms per day

        Math.abs(difference);

        valid = difference <= 0;

        return valid;

    }

}
