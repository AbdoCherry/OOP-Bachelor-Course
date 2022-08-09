package Week11.Script.InputOutput;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MyPrintStream {

    public static void main(String[] args) throws FileNotFoundException {

        PrintStream out = new PrintStream("src/Week11/Script/Buffered/output.txt");

        for (int i = 0; i < 10; i++) {
            out.println("e[" + i + "]=" + i + " ");
            out.println("**** Hello World ****");
            out.printf("\nValue %1$2d = %2$6.2f", 1, 47.11);

        }
        out.close();
        System.out.println("\nDone ...");
    }
}
