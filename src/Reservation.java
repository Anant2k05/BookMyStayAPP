import java.util.UUID;

/**
 * Reservation - Represents a booking request
 * 
 * @version 7.0
 */
public class Reservation {

    private String reservationId;
    private String guestName;
    private RoomType roomType;

    public Reservation(String guestName, RoomType roomType) {
        this.reservationId = UUID.randomUUID().toString().substring(0, 6);
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}