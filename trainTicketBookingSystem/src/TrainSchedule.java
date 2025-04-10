import java.time.LocalDate;
import java.util.*;

public class TrainSchedule {
    Train train;
    LocalDate date;
    Map<String, List<BookingSegment>> seatBookings = new HashMap<>();

    public TrainSchedule(Train train, LocalDate date) {
        this.train = train;
        this.date = date;
        for (Seat seat : train.seats) {
            seatBookings.put(seat.seatNumber, new ArrayList<>());
        }
    }

    public synchronized Seat findAvailableSeat(Station from, Station to) {
        for (Seat seat : train.seats) {
            List<BookingSegment> segments = seatBookings.get(seat.seatNumber);
            boolean isAvailable = true;
            for (BookingSegment booked : segments) {
                if (overlaps(booked, new BookingSegment(from, to))) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) return seat;
        }
        return null;
    }

    public synchronized Booking bookSeat(User user, Station from, Station to) {
        Seat seat = findAvailableSeat(from, to);
        if (seat == null) return null;
        Booking booking = new Booking(UUID.randomUUID().toString(), user, train, date, seat, new BookingSegment(from, to));
        seatBookings.get(seat.seatNumber).add(booking.segment);
        return booking;
    }

    public List<Seat> getAvailableSeats(Station from, Station to) {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : train.seats) {
            List<BookingSegment> segments = seatBookings.get(seat.seatNumber);
            boolean isAvailable = true;
            for (BookingSegment booked : segments) {
                if (overlaps(booked, new BookingSegment(from, to))) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) availableSeats.add(seat);
        }
        return availableSeats;
    }

    private boolean overlaps(BookingSegment a, BookingSegment b) {
        int aFrom = getStationIndex(a.fromStation);
        int aTo = getStationIndex(a.toStation);
        int bFrom = getStationIndex(b.fromStation);
        int bTo = getStationIndex(b.toStation);
        return aFrom < bTo && bFrom < aTo;
    }

    private int getStationIndex(Station station) {
        for (int i = 0; i < train.route.size(); i++) {
            if (train.route.get(i).getFromStation().equals(station)) return i;
        }
        return -1;
    }
}
