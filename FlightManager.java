import java.util.*;

public class FlightManager {
    private List<Flight> flights = new ArrayList<>();
    private List<Route> routes = new ArrayList<>();
    private int nextFlightId = 1;
    private int nextRouteId = 1;

    public FlightManager() {
        loadSampleData();
    }

    private void loadSampleData() {
        addRoute("Mumbai", "Delhi", 1148, "Air India");
        addRoute("Delhi", "Bengaluru", 1740, "IndiGo");
        addRoute("Mumbai", "Chennai", 1338, "SpiceJet");
        addRoute("Kolkata", "Hyderabad", 1490, "Vistara");
        addRoute("Delhi", "Goa", 1900, "Air Asia");

        addFlight("AI-101", "Mumbai", "Delhi", "08:00", "10:15", "Active", 180, 1148);
        addFlight("6E-302", "Delhi", "Bengaluru", "11:30", "14:00", "Scheduled", 200, 1740);
        addFlight("SG-501", "Mumbai", "Chennai", "09:00", "11:10", "Landed", 150, 1338);
        addFlight("UK-890", "Kolkata", "Hyderabad", "13:45", "16:30", "Delayed", 160, 1490);
        addFlight("I5-202", "Delhi", "Goa", "07:00", "09:15", "Active", 170, 1900);
    }

    public void addFlight(String flightNo, String origin, String destination,
                          String dep, String arr, String status, int seats, double dist) {
        flights.add(new Flight(nextFlightId++, flightNo, origin, destination, dep, arr, status, seats, dist));
        for (Route r : routes) {
            if (r.getOrigin().equalsIgnoreCase(origin) && r.getDestination().equalsIgnoreCase(destination))
                r.incrementFlightCount();
        }
    }

    public void addRoute(String origin, String destination, double dist, String airline) {
        routes.add(new Route(nextRouteId++, origin, destination, dist, airline));
    }

    public List<Flight> getAllFlights() { return flights; }
    public List<Route> getAllRoutes() { return routes; }

    public Flight findFlightById(int id) {
        return flights.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public List<Flight> searchByRoute(String from, String to) {
        List<Flight> result = new ArrayList<>();
        for (Flight f : flights)
            if (f.getOrigin().equalsIgnoreCase(from) && f.getDestination().equalsIgnoreCase(to))
                result.add(f);
        return result;
    }

    public List<Flight> searchByStatus(String status) {
        List<Flight> result = new ArrayList<>();
        for (Flight f : flights)
            if (f.getStatus().equalsIgnoreCase(status))
                result.add(f);
        return result;
    }

    public boolean updateStatus(int id, String newStatus) {
        Flight f = findFlightById(id);
        if (f == null) return false;
        f.setStatus(newStatus);
        return true;
    }

    public boolean bookSeat(int id) {
        Flight f = findFlightById(id);
        if (f == null || f.getAvailableSeats() == 0) return false;
        f.setBookedSeats(f.getBookedSeats() + 1);
        return true;
    }

    public boolean cancelSeat(int id) {
        Flight f = findFlightById(id);
        if (f == null || f.getBookedSeats() == 0) return false;
        f.setBookedSeats(f.getBookedSeats() - 1);
        return true;
    }

    public boolean deleteFlight(int id) {
        return flights.removeIf(f -> f.getId() == id);
    }

    public Map<String, Integer> getStatusSummary() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Active", 0); map.put("Scheduled", 0);
        map.put("Delayed", 0); map.put("Landed", 0); map.put("Cancelled", 0);
        for (Flight f : flights)
            map.merge(f.getStatus(), 1, Integer::sum);
        return map;
    }
}
