package Week11.Task05;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicTextPlaceholder {


    static String readMessages() {
        final String messageFile = "src/Week11/Task05/Data/Messages.txt";
        String message, line = null;


        try {
            BufferedReader reader = new BufferedReader(new FileReader(messageFile));
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                line.trim();

                sb.append(line).append("\n");

            }
            reader.close();
            message = sb.toString();
        } catch (IOException e) {
            message = null;
            System.out.println("\nText could not be read appropriately\n " + e.getMessage());
        }

        return message;
    }

    static List<AddressList> readAddresses() {
        final String addressFile = "src/Week11/Task05/Data/AddressList.txt";
        List<AddressList> addresses = new ArrayList<>();
        String line = null;

        try {
            BufferedReader readAddressess = new BufferedReader(new FileReader(addressFile));
            while ((line = readAddressess.readLine()) != null) {
                String[] values = line.split(" ");
                addresses.add(new AddressList(
                        values[0],
                        Integer.parseInt(values[1])
                ));
            }
            readAddressess.close();

        } catch (IOException e) {
            addresses = null;
            System.out.println("\nAddresses could not be read appropriately\n" + e.getMessage());
        } finally {
            System.out.println(addresses.size() > 0 ?
                    "\n=============== ADDRESSES SUCCESSFULLY IMPORTED ===============\n" :
                    "\n=============== ADDRESSES NOT SUCCESSFULLY IMPORTED ===============\n");
        }
        return addresses;
    }

    static void writeReplacedText(String message, List<AddressList> addresses) {

        String name = "<first name>", age = "<age>";
        int yearToday = Year.now().getValue();
        String replaced = null;

        final String nameFile = "src/Week11/Task05/Data";

        try {
            for (AddressList a : addresses) {
                File newFile = new File(nameFile + "/<" + a.getName() + ">.txt");
                replaced = message.replace(name, a.getName());
                replaced = replaced.replace(age, String.valueOf(yearToday - a.getYear()));

                System.out.println("\n----------------------------------------------------------------");
                System.out.println("Replaced values in message:\n" + replaced);

                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
                writer.write(replaced);
                writer.close();

            }

        } catch (IOException e) {
            System.out.println("\nError occured: " + e.getMessage());
        }


    }

    static void writeText() {


    }


    public static void main(String[] args) {

        String message = readMessages();
        List<AddressList> addresses = readAddresses();
        writeReplacedText(message, addresses);

        System.out.println("----------------------------------------------------------------\n");
        System.out.println("Message:\n" + message);
        System.out.println("----------------------------------------------------------------\n");
        addresses.forEach(a -> System.out.println("Name: " + a.getName() + "\nYear: " + a.getYear() + "\n"));
    }
}
