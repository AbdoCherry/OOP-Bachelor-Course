package Week05.Task05;

public class Truck {

    private int truckID;
    private String truckName, plate;
    private double loadVolume;
    private static int counter;

    public Truck() {
    }

    public Truck(String truckName, String plate, double loadVolume) {
        counter++;
        this.truckID = counter;
        this.truckName = truckName;
        this.plate = plate;
        this.loadVolume = loadVolume;
    }

    public String getTruckName() {
        return truckName;
    }

    public String getPlate() {
        return plate;
    }

    public double getLoadVolume() {
        return loadVolume;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Truck.counter = counter;
    }

    public static void maxVol(Truck[] truck) {
        double max = 0;
        Truck truckMax = new Truck();
        for (Truck t : truck) {
            if (max < t.getLoadVolume()) {
                max = t.getLoadVolume();
                truckMax = new Truck(t.getTruckName(), t.getPlate(), max);
            }
        }

        System.out.println("\nHighest Volume available is " + max + " t\n>>> " + truckMax.getTruckName() + " | "
                + truckMax.getPlate() + " <<<");
    }

    public void display() {
        System.out.println("\nTruck ID: " + this.truckID);
        System.out.println("Truck Name: " + this.truckName);
        System.out.println("Truck Plate: " + this.plate);
        System.out.println("Truck Volume: " + this.loadVolume + " t");
    }

}
