public class Flight {
    private int id;
    private String flightNo;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private String status;
    private int totalSeats;
    private int bookedSeats;
    private double distanceKm;

    public Flight(int id, String flightNo, String origin, String destination,
                  String departureTime, String arrivalTime, String status,
                  int totalSeats, double distanceKm) {
        this.id = id;
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
        this.distanceKm = distanceKm;
    }

    public int getId() { return id; }
    public String getFlightNo() { return flightNo; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTotalSeats() { return totalSeats; }
    public int getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(int b) { this.bookedSeats = b; }
    public int getAvailableSeats() { return totalSeats - bookedSeats; }
    public double getDistanceKm() { return distanceKm; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-8s | %-12s | %-12s | %-8s | %-8s | %-11s | %3d/%-3d |",
            id, flightNo, origin, destination, departureTime, arrivalTime, status, bookedSeats, totalSeats);
    }
}
