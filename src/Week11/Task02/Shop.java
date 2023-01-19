package Week11.Task02;

import Week11.Task02.Model.Article;
import Week11.Task02.Model.Customer;
import Week11.Task02.Model.Order;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.Set;

public abstract class Shop<T> {

    Scanner scannerShop = new Scanner(System.in);
    private int id;
    private String name;

    public Shop() {
    }

    public Shop(int id) {
        this.id = id;
    }

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Increment maxId by 1
    public int maxIdIncr(@NotNull Set<T> t) {
        return t.stream().mapToInt(g -> getId()).max().getAsInt() + 1;
    }

    // Get my Object -> Useful for creation and finding methods
    public T getObj(@NotNull Set<? extends Shop<T>> t) {

        String typeName = null;
        Shop<T> obj = null;

        if (t instanceof Order) {
            obj = (Shop<T>) new Order();
            typeName = obj.getClass().getSimpleName();
        } else if (t instanceof Customer) {
            obj = (Shop<T>) new Customer();
            typeName = obj.getClass().getSimpleName();
        } else if (t instanceof Article) {
            obj = (Shop<T>) new Article();
            typeName = obj.getClass().getSimpleName();
        } else System.out.println("\nObject not assigned to known type\n");

        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("Name of " + typeName + ": ");
        String name = scannerShop.nextLine();

        obj = t.stream()
                .filter(o -> o.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        return (T) obj;
    }

    @Override
    public String toString() {

        String typeName = null;
        if (this instanceof Customer) {
            typeName = "Customer";
        } else if (this instanceof Order) {
            typeName = "Order";
        } else if (this instanceof Article) {
            typeName = "Article";
        }

        return "\"" + typeName + "\"" +
                ", id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
