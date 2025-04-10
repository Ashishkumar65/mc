import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    String bookingId;
    User user;
    Train train;
    BookingSegment segment;
    Seat seat;
    LocalDate date;
    BookingStatus status;

    Instant bookingTime;

    public Booking(String bookingId, User user, Train train, LocalDate date, Seat seat, BookingSegment segment) {
        this.bookingId = bookingId;
        this.user = user;
        this.train = train;
        this.date = date;
        this.seat = seat;
        this.segment = segment;
        this.status = BookingStatus.CONFIRMED;
        this.bookingTime = Instant.now();
    }


}
