import java.util.ArrayList;
import java.util.List;

/**
 * BookingHistory - Stores confirmed reservations
 * 
 * @version 8.0
 */
public class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Get all bookings
    public List<Reservation> getAllReservations() {
        return history;
    }

    // Display history
    public void displayHistory() {
        System.out.println("\n---- Booking History ----\n");

        for (Reservation r : history) {
            System.out.println("ID: " + r.getReservationId() +
                    ", Guest: " + r.getGuestName() +
                    ", Room: " + r.getRoomType());
        }
    }
}