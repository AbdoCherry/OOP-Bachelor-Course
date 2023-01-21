package Week11.Task02.Model;

import java.io.Serializable;
import java.util.Scanner;

public class Residence implements Serializable {
    transient Scanner scannerResidence = new Scanner(System.in);

    private String street;
    private int houseNo;
    private String city;

    public Residence() {
    }

    public Residence(String street, int houseNo, String city) {
        this.street = street;
        this.houseNo = houseNo;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public String getCity() {
        return city;
    }

    public Residence createResidence() {

        System.out.println("\nPlease enter residence information in the fields below\n");

        System.out.print("Street: ");
        String street = scannerResidence.nextLine();
        System.out.print("House No: ");
        int houseNo = scannerResidence.nextInt();
        System.out.print("City: ");
        scannerResidence.nextLine();
        String city = scannerResidence.nextLine();

        return new Residence(street, houseNo, city);
    }
}
