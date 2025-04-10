import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingSystemDemo {
    public static void main(String[] args){
        // Define stations
        Station A = new Station("A", "Alpha");
        Station B = new Station("B", "Beta");
        Station C = new Station("C", "Gamma");
        Station D = new Station("D", "Delta");
        Station E = new Station("E", "Epsilon");
        Station F = new Station("F", "Zeta");

        // Create train
        Train train = new Train("T1", "Express");
        train.route.add(new TrainRouteSegment(A, B, 1));
        train.route.add(new TrainRouteSegment(B, C, 2));
        train.route.add(new TrainRouteSegment(C, D, 3));
        train.route.add(new TrainRouteSegment(D, E, 4));
        train.route.add(new TrainRouteSegment(E, F, 5));

        train.seats.add(new Seat("S1", SeatClass.SLEEPER));
        train.seats.add(new Seat("S2", SeatClass.AC));
        train.seats.add(new Seat("S3", SeatClass.SLEEPER));

        // Create schedule
        LocalDate date = LocalDate.now();
        TrainSchedule schedule = new TrainSchedule(train, date);

        // Register schedules
        List<TrainSchedule> schedules = new ArrayList<>();
        schedules.add(schedule);
        TrainSearchService searchService = new TrainSearchService(schedules);

        // Create users
        User user1 = new User("U1", "John");
        User user2 = new User("U2", "Alice");
        User user3 = new User("U3", "Bob");
        User user4 = new User("U4", "Clara");

        // Bookings
        Booking booking1 = schedule.bookSeat(user1, A, C);
        Booking booking2 = schedule.bookSeat(user2, C, E);
        Booking booking3 = schedule.bookSeat(user3, B, D);

        // Attempt overlapping booking (should fail if all seats taken)
        Booking booking4 = schedule.bookSeat(user4, A, D);

        System.out.println("Booking1: " + (booking1 != null ? booking1.seat.seatNumber : "No seat available"));
        System.out.println("Booking2: " + (booking2 != null ? booking2.seat.seatNumber : "No seat available"));
        System.out.println("Booking3: " + (booking3 != null ? booking3.seat.seatNumber : "No seat available"));
        System.out.println("Booking4: " + (booking4 != null ? booking4.seat.seatNumber : "No seat available"));

        // Check available seats for a segment
        List<Seat> availableSeats1 = schedule.getAvailableSeats(A, F);
        System.out.println("Available seats from A to F:");
        for (Seat seat : availableSeats1) {
            System.out.println(" - Seat: " + seat.seatNumber + " Class: " + seat.seatClass);
        }

        // Search for trains from B to E
        System.out.println("Trains from B to E on " + date + ":");
        List<TrainSchedule> results = searchService.searchTrains(B, E, date);
        for (TrainSchedule ts : results) {
            System.out.println(" - Train ID: " + ts.train.getTrainId() + " Name: " + ts.train.getName());
        }

    }
}
