package Week10.Task03;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Translator {

    static <K, V> K getKey(Map<K, V> map, V value) {

        return map.entrySet().stream().filter(entry -> value.equals(entry.getValue())).findFirst()
                .map(Map.Entry::getKey).orElse(null);
    }

    public static void printDictionary(Map<String, String> map) {

        map = new TreeMap<>(map);
        int counter = 0;
        System.out.printf("\033[1m%-5s%-15s%-15s\033[0m\n", "ID", "English", "Deutsch");
        System.out.println("------------------------------");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.printf("%-5d%-15s%-15s\n", counter, entry.getKey(), entry.getValue());
            counter++;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, String> translator = new HashMap<>();

        // Translation from english to german
        translator.put("Boat", "Boot");
        translator.put("Angel", "Engel");
        translator.put("Car", "Auto");
        translator.put("Butter", "Butter");
        translator.put("Boot", "Stiefel");
        translator.put("Bald", "Kahl");
        translator.put("Rat", "Ratte");
        translator.put("Soon", "Bald");
        translator.put("Bicycle", "Fahrrad");
        translator.put("Bad", "Schlecht");
        translator.put("Bath", "Bad");
        translator.put("Fishing Rod", "Angel");
        translator.put("Street", "Strasse");
        translator.put("Letter", "Brief");

        System.out.println("\nPlease enter a word: ");
        String word = scanner.nextLine();

        if (translator.containsKey(word) && getKey(translator, word) != null) {
            System.out.println("\nThe german word for " + word + " is => " + translator.get(word)
                    + " in english");
            System.out.println("\nThe english word for " + word + " is => "
                    + getKey(translator, word) + " in german\n");

        } else if (getKey(translator, word) != null) {
            System.out.println("\nThe german word for " + word + " is => "
                    + getKey(translator, word) + " in english\n");

        } else if (translator.containsKey(word)) {
            System.out.println("\nThe german word for " + word + " is => " + translator.get(word)
                    + " in english\n");

        } else {
            System.out.println("\nWord is not available in dictionary\n");
        }

        printDictionary(translator);

        scanner.close();

    }
}
