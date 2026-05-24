import java.util.*;

public class FlightRouteTracker {

    static FlightManager manager = new FlightManager();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        while (true) {
            printMainMenu();
            int choice = readInt("choose the option ");
            switch (choice) {
                case 1 -> viewAllFlights();
                case 2 -> addNewFlight();
                case 3 -> searchFlights();
                case 4 -> updateFlightStatus();
                case 5 -> bookOrCancelSeat();
                case 6 -> deleteFlight();
                case 7 -> viewRoutes();
                case 8 -> addNewRoute();
                case 9 -> viewDashboard();
                case 0 -> { System.out.println("\n  thank you! Flight Route Tracker cloosing...\n"); return; }
                default -> System.out.println("  [!] Invalid choice. try again.");
            }
        }
    }

    static void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║        FLIGHT ROUTE TRACKER - Java Project       ║");
        System.out.println("  ║          Made with Java | Mini Project           ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    static void printMainMenu() {
        System.out.println("\n  ┌─────────────── MAIN MENU ───────────────┐");
        System.out.println("  │  1. view all flight                      │");
        System.out.println("  │  2. add new flight                       │");
        System.out.println("  │  3. search flight                       │");
        System.out.println("  │  4. Chabge Flight Status                │");
        System.out.println("  │  5. Seat Book / Cancel                  │");
        System.out.println("  │  6. Flight Delete                       │");
        System.out.println("  │  7. view all Routes                     │");
        System.out.println("  │  8. New Route Add                       │");
        System.out.println("  │  9. Dashboard / Summary                 │");
        System.out.println("  │  0. Exit                                │");
        System.out.println("  └─────────────────────────────────────────┘");
    }

    static void viewAllFlights() {
        System.out.println("\n  ══ ALL  FLIGHTS ══");
        List<Flight> list = manager.getAllFlights();
        if (list.isEmpty()) { System.out.println("  not fount ."); return; }
        printFlightHeader();
        for (Flight f : list) System.out.println("  " + f);
        printFlightFooter();
    }

    static void printFlightHeader() {
        System.out.println("  +------+----------+--------------+--------------+----------+----------+-------------+---------+");
        System.out.println("  | ID   | Flight   | Origin       | Destination  | Dep Time | Arr Time | Status      | Seats   |");
        System.out.println("  +------+----------+--------------+--------------+----------+----------+-------------+---------+");
    }

    static void printFlightFooter() {
        System.out.println("  +------+----------+--------------+--------------+----------+----------+-------------+---------+");
    }

    static void addNewFlight() {
        System.out.println("\n  ══ add new flight ══");
        System.out.print("  Flight No. (e.g. AI-201): "); String num = sc.nextLine().trim();
        System.out.print("  Origin City: "); String from = sc.nextLine().trim();
        System.out.print("  Destination City: "); String to = sc.nextLine().trim();
        System.out.print("  Departure Time (HH:MM): "); String dep = sc.nextLine().trim();
        System.out.print("  Arrival Time (HH:MM): "); String arr = sc.nextLine().trim();
        System.out.print("  Total Seats: "); int seats = readInt("");
        System.out.print("  Distance (km): "); double dist = readDouble("");
        System.out.println("  Status: 1.Scheduled  2.Active  3.Delayed");
        int s = readInt("  Choose: ");
        String status = switch(s) { case 2 -> "Active"; case 3 -> "Delayed"; default -> "Scheduled"; };
        manager.addFlight(num, from, to, dep, arr, status, seats, dist);
        System.out.println("\n  [OK] Flight '" + num + "' successfully added  !");
    }

    static void searchFlights() {
        System.out.println("\n  ══ FLIGHT SEARCH ══");
        System.out.println("  1. Route  search (Origin → Destination)");
        System.out.println("  2. Status  search");
        int ch = readInt("  Choice: ");
        List<Flight> result;
        if (ch == 1) {
            System.out.print("  Origin: "); String from = sc.nextLine().trim();
            System.out.print("  Destination: "); String to = sc.nextLine().trim();
            result = manager.searchByRoute(from, to);
        } else {
            System.out.println("  Status: 1.Active  2.Scheduled  3.Delayed  4.Landed  5.Cancelled");
            int s = readInt("  Choice: ");
            String[] statuses = {"Active","Scheduled","Delayed","Landed","Cancelled"};
            String status = (s >= 1 && s <= 5) ? statuses[s-1] : "Scheduled";
            result = manager.searchByStatus(status);
        }
        if (result.isEmpty()) { System.out.println("\n  [!] flight not fount ."); return; }
        System.out.println("\n  " + result.size() + " flight(s) mili:");
        printFlightHeader();
        for (Flight f : result) System.out.println("  " + f);
        printFlightFooter();
    }

    static void updateFlightStatus() {
        System.out.println("\n  ══ STATUS UPDATE ══");
        viewAllFlights();
        int id = readInt("\n  Flight ID enter : ");
        Flight f = manager.findFlightById(id);
        if (f == null) { System.out.println("  [!] Flight not found."); return; }
        System.out.println("  Current status: " + f.getStatus());
        System.out.println("  new  Status: 1.Scheduled  2.Active  3.Delayed  4.Landed  5.Cancelled");
        int s = readInt("  Choice: ");
        String[] statuses = {"Scheduled","Active","Delayed","Landed","Cancelled"};
        if (s < 1 || s > 5) { System.out.println("  [!] Invalid choice."); return; }
        manager.updateStatus(id, statuses[s-1]);
        System.out.println("  [OK] " + f.getFlightNo() + "  status '" + statuses[s-1] + "' done!");
    }

    static void bookOrCancelSeat() {
        System.out.println("\n  ══ SEAT BOOKING / CANCELLATION ══");
        viewAllFlights();
        int id = readInt("\n  Flight ID: ");
        Flight f = manager.findFlightById(id);
        if (f == null) { System.out.println("  [!] Flight not found."); return; }
        System.out.printf("  %s | Available: %d | Booked: %d%n",
            f.getFlightNo(), f.getAvailableSeats(), f.getBookedSeats());
        System.out.println("  1. book seat   2. Seat Cancel ");
        int ch = readInt("  Choice: ");
        if (ch == 1) {
            if (manager.bookSeat(id)) System.out.println("  [OK] Seat successfully booked!");
            else System.out.println("  [!] Koi seat available nahi hai.");
        } else if (ch == 2) {
            if (manager.cancelSeat(id)) System.out.println("  [OK] Seat cancelled.");
            else System.out.println("  [!] not booked seat available for cancel.");
        }
    }

    static void deleteFlight() {
        System.out.println("\n  ══ FLIGHT DELETE ══");
        viewAllFlights();
        int id = readInt("\n  enter flight id for delete: ");
        Flight f = manager.findFlightById(id);
        if (f == null) { System.out.println("  [!] Flight not found."); return; }
        System.out.print("  '" + f.getFlightNo() + "' wnat to delete ? (y/n): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            manager.deleteFlight(id);
            System.out.println("  [OK] Flight is deleted ");
        } else System.out.println("  Delete cancel.");
    }

    static void viewRoutes() {
        System.out.println("\n  ══ all ROUTES ══");
        List<Route> list = manager.getAllRoutes();
        if (list.isEmpty()) { System.out.println("  not route found ."); return; }
        System.out.println("  +------+--------------+--------------+------------+-----------------+------------+");
        System.out.println("  | ID   | Origin       | Destination  | Distance   | Airline         | Flights    |");
        System.out.println("  +------+--------------+--------------+------------+-----------------+------------+");
        for (Route r : list) System.out.println("  " + r);
        System.out.println("  +------+--------------+--------------+------------+-----------------+------------+");
    }

    static void addNewRoute() {
        System.out.println("\n  ══ add new route  ══");
        System.out.print("  Origin City: "); String from = sc.nextLine().trim();
        System.out.print("  Destination City: "); String to = sc.nextLine().trim();
        System.out.print("  Distance (km): "); double dist = readDouble("");
        System.out.print("  Airline Name: "); String airline = sc.nextLine().trim();
        manager.addRoute(from, to, dist, airline);
        System.out.println("  [OK] Route '" + from + " → " + to + "' added!");
    }

    static void viewDashboard() {
        System.out.println("\n  ╔══════════════════════════════════════╗");
        System.out.println("  ║         FLIGHT DASHBOARD             ║");
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.printf("  Total Flights  : %d%n", manager.getAllFlights().size());
        System.out.printf("  Total Routes   : %d%n", manager.getAllRoutes().size());
        Map<String, Integer> summary = manager.getStatusSummary();
        System.out.println("\n  Status Breakdown:");
        for (Map.Entry<String, Integer> e : summary.entrySet())
            System.out.printf("    %-12s: %d%n", e.getKey(), e.getValue());
        int totalSeats = manager.getAllFlights().stream().mapToInt(Flight::getTotalSeats).sum();
        int bookedSeats = manager.getAllFlights().stream().mapToInt(Flight::getBookedSeats).sum();
        System.out.println("\n  Seat Summary:");
        System.out.printf("    Total Seats   : %d%n", totalSeats);
        System.out.printf("    Booked Seats  : %d%n", bookedSeats);
        System.out.printf("    Available     : %d%n", totalSeats - bookedSeats);
        if (totalSeats > 0)
            System.out.printf("    Occupancy     : %.1f%%%n", (bookedSeats * 100.0 / totalSeats));
        System.out.println();
    }

    static int readInt(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            try {
                String line = sc.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  [!] please enter valid number.");
            }
        }
    }

    static double readDouble(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            try {
                String line = sc.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  [!] please enter valid number.");
            }
        }
    }
}
