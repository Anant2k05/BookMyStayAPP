/**
 * UseCase6RoomAllocationService - Demonstrates booking confirmation and room allocation
 * 
 * @version 6.1
 */
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 6.1\n");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        // 🔥 Test overflow
        queue.addRequest(new Reservation("Alice", RoomType.SINGLE));
        queue.addRequest(new Reservation("Bob", RoomType.SINGLE));
        queue.addRequest(new Reservation("Charlie", RoomType.SINGLE)); // should be rejected
        queue.addRequest(new Reservation("David", RoomType.DOUBLE));

        RoomAllocationService allocationService = new RoomAllocationService(inventory);

        allocationService.processRequests(queue);
        allocationService.displayAllocations();
    }
}