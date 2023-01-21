package Week11.Task03;

import Week11.Task02.Model.Article;
import Week11.Task02.Model.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IOStream {

    public static Set<Order> readOrders(String path) {
        File myFile = new File(path);

        if (!Files.exists(Path.of(path))) {
            System.out.println("\nPath does not exists: " + myFile.getName() + "\n");
            System.exit(1);
        }
        Set<Order> orders = new HashSet<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            orders = (HashSet<Order>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return orders;

    }

    public static Set<Article> readArticles(String path) {
        File myFile = new File(path);

        if (!Files.exists(Path.of(path))) {
            System.out.println("\nPath does not exists: " + myFile.getName() + "\n");
            System.exit(1);
        }
        Set<Article> articles = new HashSet<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            articles = (HashSet<Article>) ois.readObject();
            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return Collections.unmodifiableSet(articles);
    }
}