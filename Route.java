public class Route {
    private int routeId;
    private String origin;
    private String destination;
    private double distanceKm;
    private String airline;
    private int flightCount;

    public Route(int routeId, String origin, String destination, double distanceKm, String airline) {
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.airline = airline;
        this.flightCount = 0;
    }

    public int getRouteId() { return routeId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public double getDistanceKm() { return distanceKm; }
    public String getAirline() { return airline; }
    public int getFlightCount() { return flightCount; }
    public void incrementFlightCount() { flightCount++; }

    @Override
    public String toString() {
        return String.format("| %-4d | %-12s | %-12s | %8.0f km | %-15s | %3d flights |",
            routeId, origin, destination, distanceKm, airline, flightCount);
    }
}
