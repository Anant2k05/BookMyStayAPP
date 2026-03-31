/**
 * Reservation - Represents a booking request
 * 
 * @version 5.0
 */
public class Reservation {

    private String guestName;
    private RoomType roomType;

    public Reservation(String guestName, RoomType roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public RoomType getRoomType() {
        return roomType;
    }
}