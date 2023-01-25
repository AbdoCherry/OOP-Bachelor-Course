package Week02.Task07;

public class CalculateMethods {

    static final int RANGE1 = 17000, RANGE2 = 25000;

    static void display(double gazRange1, double gazRange2, double dieselRange1, double dieselRange2) {


        System.out.printf("%-20s%-30s%-30s%-30s\n", "Vehichle", "Range = 17000", "Range = 25000", "More favorable");
        //System.out.println("\nVehicle\t\tRange = 17000 KM\tRange = 25000 KM\tMore favorable");
        String favorable1 = (gazRange1 < dieselRange1) ? "Gazoline" : "Diesel";
        String favorable2 = (gazRange2 < dieselRange2) ? "Gazoline" : "Diesel";
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s%-30s%-30s%-30s\n", "Gazoline", gazRange1, gazRange2, favorable1);
        System.out.printf("%-20s%-30s%-30s%-30s\n", "Diesel", dieselRange1, dieselRange2, favorable2);
    }

    static double calculator(int fixcosts, int tax, double consumption, double fuelPrice, int RANGE) {

        return fixcosts + tax * 4 + (RANGE * consumption) * fuelPrice;
    }

    public static void main(String[] args) {

        // Car 1: Gazoline transmission vehicle => Tax is per year, and we need for four
        // years
        int fixCosts1 = 20000, tax1 = 125;
        double consumption1 = 8.6 / 100, gazPrice = 1.31;

        // Car 2: Diesel powered vehicle => Tax is per year, and we need for four years
        int fixCosts2 = 25000, tax2 = 310;
        double consumption2 = 5.4 / 100, dieselPrice = 1.12;

        // Gazoline calculation
        double gazRange1 = calculator(fixCosts1, tax1, consumption1, gazPrice, RANGE1);
        double gazRange2 = calculator(fixCosts1, tax1, consumption1, gazPrice, RANGE2);

        // Diesel calculation
        double dieselRange1 = calculator(fixCosts2, tax2, consumption2, dieselPrice, RANGE1);
        double dieselRange2 = calculator(fixCosts2, tax2, consumption2, dieselPrice, RANGE2);

        display(gazRange1, gazRange2, dieselRange1, dieselRange2);
    }

}
