package Week06.Task01;

import java.text.ParseException;

public class TravelAgency {
    public static void main(String[] args) throws ParseException {

        Customer[] customer = new Customer[10];

        customer[0] = new Customer("Sheikh", "Yerbouti", "Bali", 12, 2, 10, 1); // Valid trip
        customer[1] = new Customer("Ben", "Dover", "Madrid", 11, 8, 10, 4); // Valid trip
        customer[2] = new Customer("Mike", "Hawk", "Tokyo", 01, 12, 30, 11); // Not valid trip
        customer[3] = new Customer("Maposi", "Nmakrak", "Krakow", 24, 6, 22, 6); // Not valid trip
        customer[4] = new Customer("Mike", "Litoris", "Bali", 04, 5, 28, 4); // Valid trip
        customer[5] = new Customer("Tess", "Tickles", "Hurghada", 11, 5, 1, 5); // Valid trip
        customer[6] = new Customer("Maja", "Hee", "Berlin", 15, 9, 21, 5); // Valid trip
        customer[7] = new Customer("Maja", "Huu", "Pitsburg", 11, 6, 19, 5); // Valid trip
        customer[8] = new Customer("Mohamed", "Ata", "New York City", 11, 9, 01, 9); // Valid trip
        customer[9] = new Customer("Ramit", "Inamashol", "Pune", 19, 5, 17, 5); // Not valid trip

        Customer.tripValidator(customer);

    }

}
