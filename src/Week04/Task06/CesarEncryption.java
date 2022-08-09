package Week04.Task06;

import java.util.Scanner;

public class CesarEncryption {

    /**
     * @param plaintext
     * @param key
     * @return String
     */
    // Encrypt plaintext with the key
    public static String code(String plaintext, int key) {
        String ciphertext = "";
        // plaintext = plaintext.toLowerCase(); // Convert all to lowercase
        for (int i = 0; i < plaintext.length(); i++) {
            // Move letters and attach to ciphertext
            char next = plaintext.charAt(i);
            ciphertext = ciphertext + shift(next, key);
        }
        return ciphertext;
    }

    /**
     * @param letter
     * @param shift
     * @return char
     */
    // Moves the letter by the specified shift (key)
    public static char shift(char letter, int shift) {
        // Buchstaben als Zahl behandeln
        if (letter >= 'a' && letter <= 'z') { // Do not change special characters
            letter += shift;
            while (letter > 'z') {
                letter -= 26;
            }
        }
        return letter;
    }

    /**
     * @param ciphertext
     * @param key
     * @return String
     */
    // Decrypts the ciphertext with the given key
    public static String decode(String ciphertext, int key) {
        String decoded = "";
        // ciphertext = ciphertext.toLowerCase(); // Convert all to lowercase
        for (int i = 0; i < ciphertext.length(); i++) {
            // Shift each letter by (26-key) digits and append to decrypted text
            char next = ciphertext.charAt(i);
            decoded = decoded + shift(next, 26 - key);
        }
        return decoded;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Plaintext: ");
        String plaintext = scanner.nextLine();
        System.out.print("Strength of encryption: ");
        int key = scanner.nextInt();

        String ciphertext = code(plaintext, key);
        System.out.println("Plain text: " + plaintext + " ---> " + ciphertext);

        String decoded = decode(ciphertext, key);
        System.out.println("Ciphertext: " + ciphertext + " ---> " + decoded);

        scanner.close();

    }

}
