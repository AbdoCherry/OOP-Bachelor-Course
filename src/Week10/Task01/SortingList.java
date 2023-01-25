package Week10.Task01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SortingList {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter random numbers into command line\n");

        List<Integer> myInput = new ArrayList<>();
        Random random = new Random();

        int randomInt = random.nextInt((10) + 1);

        while (myInput.size() < randomInt) {
            System.out.print("Input: ");
            myInput.add(scanner.nextInt());
        }

        System.out.println("\nBefore sorting ArrayList of integer values");
        for (int i = 0; i < myInput.size(); i++) {
            System.out.println("At position " + (i + 1) + ": " + myInput.get(i));
        }

        System.out.println("\nAfter sorting ArrayList of integer values");
        Collections.sort(myInput);

        for (int i = 0; i < myInput.size(); i++) {
            System.out.println("At position " + (i + 1) + ": " + myInput.get(i));
        }

        scanner.close();

    }

}
