package Week01.Task04;

public class Calculate2 {
    public static void main(String[] args) {

        final int RANGE = 125000; // Kilomenters

        // Car 1: Gazoline transmission vehicle
        int fixCosts1 = 20000, tax1 = 500;
        double consumption1 = 8.6 / 100, gazPrice1 = 1.35;

        // Car 2: Diesel powered vehicle
        int fixCosts2 = 25000, tax2 = 1240;
        double consumption2 = 5.4 / 100, dieselPrice = 1.12;

        // Calculation

        double totalPriceGaz = fixCosts1 + tax1 + (RANGE * consumption1) * gazPrice1;
        double totalPriceDiesel = fixCosts2 + tax2 + (RANGE * consumption2) * dieselPrice;

        // A shorter way to define an if-else expression => ternary operator
        System.out.println((totalPriceGaz < totalPriceDiesel) ? "\nGazoline transmissioned vehicle is cheaper in total"
                : "\nDiesel powered vehicle is cheaper in total");

        System.out.printf("Diesel Car: %.2f $ \n", totalPriceDiesel);
        System.out.printf("Gazoline Car: %.2f $ ", totalPriceGaz);
        System.out.println(" ");

    }

}
