package Week03.Task06;

public class Ascii {

    public static void main(String[] args) {

        System.out.println("\n");

        for (int i = 0; i < 127; i++) {
            System.out.println("The ASCII - Value of " + (char) i + " equals = " + i);
        }

        System.out.println("\n---------- Only capital letters ----------\n");
        for (int i = 65; i < 91; i++) {
            System.out.println("The ASCII - Value of " + (char) i + " equals = " + i);
        }
    }

}
