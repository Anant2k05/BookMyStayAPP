import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BookingReportService - Generates reports from booking history
 * 
 * @version 8.0
 */
public class BookingReportService {

    // Total bookings
    public void generateTotalBookingsReport(List<Reservation> reservations) {

        System.out.println("\nTotal Bookings: " + reservations.size());
    }

    // Bookings by room type
    public void generateRoomTypeReport(List<Reservation> reservations) {

        Map<RoomType, Integer> countMap = new HashMap<>();

        for (Reservation r : reservations) {
            RoomType type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        System.out.println("\n---- Bookings by Room Type ----\n");

        for (RoomType type : countMap.keySet()) {
            System.out.println(type + ": " + countMap.get(type));
        }
    }
}