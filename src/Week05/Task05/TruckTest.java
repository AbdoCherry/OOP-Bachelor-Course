package Week05.Task05;

public class TruckTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Truck[] truck = new Truck[10];

        truck[0] = new Truck("Magnet", "FF-AS-2020", 12.9);
        truck[1] = new Truck("Checker", "SX-MA-2010", 12.9);
        truck[2] = new Truck("Butcher", "FS-RR-2095", 12.9);
        truck[3] = new Truck("Beast", "ZZ-YY-0953", 50.0);
        truck[4] = new Truck("Judgement Day", "FF-AS-2020", 12.9);
        truck[5] = new Truck("Bulldozer", "FF-AS-2020", 12.9);
        truck[6] = new Truck("Carmageddon", "FF-AS-2020", 12.9);
        truck[7] = new Truck("Conqueftador", "FF-AS-2020", 12.9);
        truck[8] = new Truck("Dark Knight", "TR-FS-1100", 22.7);
        truck[9] = new Truck("Magnet", "SO-HO-9211", 18.3);

        for (Truck t : truck) {
            t.display();
        }

        Truck.maxVol(truck);

    }

}
