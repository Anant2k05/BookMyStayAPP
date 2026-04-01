/**
 * UseCase7AddOnServiceSelection - Demonstrates add-on service selection
 * 
 * @version 7.1
 */
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 7.1\n");

        // Create a reservation
        Reservation reservation = new Reservation("Alice", RoomType.SINGLE);

        System.out.println("Reservation ID: " + reservation.getReservationId());

        // Create service manager
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Add services
        serviceManager.addService(reservation.getReservationId(), new Service("WiFi", 500));
        serviceManager.addService(reservation.getReservationId(), new Service("Breakfast", 1000));
        serviceManager.addService(reservation.getReservationId(), new Service("Spa", 2000));

        // Display services
        serviceManager.displayServices(reservation.getReservationId());

        // Calculate total cost
        double total = serviceManager.calculateTotalCost(reservation.getReservationId());

        System.out.println("\nTotal Add-On Cost: " + total);
    }
}