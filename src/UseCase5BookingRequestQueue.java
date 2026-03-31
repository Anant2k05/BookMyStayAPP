/**
 * UseCase5BookingRequestQueue - Demonstrates FIFO booking request handling
 * 
 * @version 5.1
 */
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 5.1\n");

        // Create booking queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Simulate booking requests
        queue.addRequest(new Reservation("Alice", RoomType.SINGLE));
        queue.addRequest(new Reservation("Bob", RoomType.DOUBLE));
        queue.addRequest(new Reservation("Charlie", RoomType.SUITE));
        queue.addRequest(new Reservation("David", RoomType.SINGLE));

        // View all requests
        queue.viewRequests();
    }
}