import java.util.LinkedList;
import java.util.Queue;

/**
 * BookingRequestQueue - Handles incoming booking requests using FIFO
 * 
 * @version 6.0
 */
public class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Request added for: "
                + reservation.getGuestName() + " (" + reservation.getRoomType() + ")");
    }

    public Reservation pollRequest() {
        return requestQueue.poll();
    }
}