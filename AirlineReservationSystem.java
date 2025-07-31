import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {
    private List<Flight> flights;
    private List<Reservation> reservations;
    private Scanner scanner;

    public AirlineReservationSystem() {
        flights = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeFlights(); 
    }

    private void initializeFlights() {
        flights.add(new Flight("AA123", "Delhi", "Bengaluru", "08:00", "12:00", 2));
        flights.add(new Flight("BA456", "Chennai", "Gujarat", "09:00", "11:30", 150));
        flights.add(new Flight("CA789", "Beijing", "Tokyo", "10:30", "14:30", 200));
    }

    public void start() {
        System.out.println("Welcome to the Airline Reservation System");
        while (true) {
            System.out.println("\n1. View Flights");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewFlights();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the Airline Reservation System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    private void makeReservation() {
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your passport number: ");
        String passportNumber = scanner.nextLine();
        Passenger passenger = new Passenger(name, passportNumber);

        System.out.print("Enter departure city: ");
        String departureCity = scanner.nextLine();
        System.out.print("Enter arrival city: ");
        String arrivalCity = scanner.nextLine();

        Flight flight = getFlightByCities(departureCity, arrivalCity);

        if (flight != null) {
            if (flight.addPassenger(passenger)) {
                reservations.add(new Reservation(flight, passenger));
                System.out.println("Reservation successful.");
            } else {
                System.out.println("Sorry, the flight is fully booked.");
            }
        } else {
            System.out.println("Flight not found.");
        }
    }

    private Flight getFlightByCities(String departureCity, String arrivalCity) {
        for (Flight flight : flights) {
            if (flight.getDepartureCity().equalsIgnoreCase(departureCity) &&
                flight.getArrivalCity().equalsIgnoreCase(arrivalCity)) {
                return flight;
            }
        }
        return null;
    }

    private void viewReservations() {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        new AirlineReservationSystem().start();
    }
}
