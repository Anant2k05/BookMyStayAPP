/**
 * UseCase8BookingHistoryReport - Demonstrates booking history and reporting
 * 
 * @version 8.1
 */
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 8.1\n");

        // Setup
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService(inventory);
        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Add booking requests
        queue.addRequest(new Reservation("Alice", RoomType.SINGLE));
        queue.addRequest(new Reservation("Bob", RoomType.SINGLE));
        queue.addRequest(new Reservation("Charlie", RoomType.SINGLE)); // should be rejected
        queue.addRequest(new Reservation("David", RoomType.DOUBLE));

        System.out.println("\n---- Processing Bookings ----");

        // Process requests manually to capture confirmed ones
        while (true) {

            Reservation request = queue.pollRequest();
            if (request == null) break;

            RoomType type = request.getRoomType();
            int available = inventory.getAvailability(type.name());

            if (available > 0) {

                // Confirm booking
                inventory.updateAvailability(type.name(), available - 1);

                System.out.println("Confirmed: " + request.getGuestName());

                // 🔥 Add to history
                history.addReservation(request);

            } else {
                System.out.println("Rejected: " + request.getGuestName());
            }
        }

        // Display history
        history.displayHistory();

        // Generate reports
        reportService.generateTotalBookingsReport(history.getAllReservations());
        reportService.generateRoomTypeReport(history.getAllReservations());
    }
}