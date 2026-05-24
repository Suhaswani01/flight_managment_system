✈️ Flight Route Tracker
A console-based Java Mini Project for managing flights, routes, and seat bookings — like a mini airport management system running in your terminal.

📌 Features
#FeatureDescription1View All FlightsSee all flights in a clean table format2Add New FlightAdd flight number, route, time, seats3Search FlightsFilter by route or status4Update StatusScheduled → Active → Landed / Delayed / Cancelled5Seat BookingBook or cancel a seat, occupancy is tracked6Delete FlightRemove any flight from the system7View RoutesSee city-to-city routes and distances8Add New RouteAdd a new airline route9DashboardSummary — total flights, status breakdown, occupancy %

🗂️ Project Structure
FlightRouteTracker/
│
├── Flight.java              # Flight data model (getters, setters)
├── Route.java               # Route data model
├── FlightManager.java       # Business logic — add, search, update, delete
└── FlightRouteTracker.java  # Main class — menu & user interaction

🚀 How to Run
Requirements: Java 14 or higher (JDK)
bash# Step 1: Compile
javac *.java

# Step 2: Run
java FlightRouteTracker

🧠 Java Concepts Used

OOP — Classes, Objects, Encapsulation
Collections — ArrayList, HashMap, LinkedHashMap
Stream API — filtering, mapping, aggregation
Scanner — console input handling
Switch Expressions — Java 14+ syntax
Method design — helper methods, input validation


📊 Sample Data (Preloaded)
FlightRouteStatusAI-101Mumbai → DelhiActive6E-302Delhi → BengaluruScheduledSG-501Mumbai → ChennaiLandedUK-890Kolkata → HyderabadDelayedI5-202Delhi → GoaActive

📁 Class Responsibilities
Flight.java — Represents a single flight with flight number, origin, destination, departure/arrival time, status, total seats, and booked seats.
Route.java — Represents a city-to-city route with origin, destination, distance in km, airline name, and flight count on that route.
FlightManager.java — Contains all the core logic: add, search, update status, book/cancel seats, delete flights, and generate dashboard summary.
FlightRouteTracker.java — Main class that prints the menu, reads user input, and calls FlightManager methods accordingly.
