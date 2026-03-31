import java.util.LinkedList;
import java.util.Queue;

/**
 * BookingRequestQueue - Handles incoming booking requests using FIFO
 * 
 * @version 5.0
 */
public class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request added for: " 
            + reservation.getGuestName() + " (" + reservation.getRoomType() + ")");
    }

    // View all requests (without removing)
    public void viewRequests() {
        System.out.println("\n---- Booking Requests ----\n");

        for (Reservation r : requestQueue) {
            System.out.println("Guest: " + r.getGuestName() + 
                               ", Room Type: " + r.getRoomType());
        }
    }
}