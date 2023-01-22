package Week09.Task03;

import java.util.Objects;
import java.util.Scanner;

public class InputChecker {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input1 = null, input2 = null;
        try (scanner) {
            System.out.println("\nPleae enter two inputs in the fields below");
            System.out.print("\nInput 1: ");
            input1 = scanner.nextLine();
            System.out.print("\nInput 2: ");
            input2 = scanner.nextLine();
            if (Objects.equals(input1, "") && Objects.equals(input2, "")) {
                throw new MissingParameter("\n**** Parameter missing ****");
            } else if (Objects.equals(input1, "") || Objects.equals(input2, "")) {
                throw new MissingParameter("\n**** One more parameter missing");
            }

        } catch (MissingParameter e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            System.out.println("\nString input 1: " + input1);
            System.out.println("String input 2: " + input2);
        }

    }

}
