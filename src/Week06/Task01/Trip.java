package Week06.Task1;

public class Trip {

    private String destination;
    private Date travelDate;

    public Trip() {

    }

    public Trip(String destination, int day, int month) {
        this.destination = destination;
        this.travelDate = new Date(day, month);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

}
