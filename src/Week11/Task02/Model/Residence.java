package Week11.Task02.Model;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.Set;

public class Residence {

    Scanner scannerResidence = new Scanner(System.in);

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

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Residence createResidence(@NotNull Set<Customer> customers) {

        Residence newResidence = new Residence();

        System.out.print("Street: ");
        newResidence.setCity(scannerResidence.nextLine());

        System.out.print("HouseNo: ");
        newResidence.setHouseNo(scannerResidence.nextInt());

        System.out.print("City: ");
        newResidence.setCity(scannerResidence.nextLine());

        boolean exists = customers.stream()
                .anyMatch(c -> c.getResidence().equals(newResidence));

        return newResidence;
    }
}
