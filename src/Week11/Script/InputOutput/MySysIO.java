package Week11.Script.InputOutput;

import java.io.IOException;

public class MySysIO {
    public static void main(String[] args) throws IOException {
        // Declaration of variables
        byte[] line = new byte[128];
        int counter;

        System.out.println("\nEnter text und submit with \'Enter\'");

        // Read line
        counter = System.in.read(line);

        // Print line
        System.out.println("Entered text: \'" + new String(line, 0, counter) + "\'");
    }
}
