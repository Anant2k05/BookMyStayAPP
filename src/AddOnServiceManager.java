import java.util.*;

/**
 * AddOnServiceManager - Manages services attached to reservations
 * 
 * @version 7.0
 */
public class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<Service>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to a reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service: " + service.getName() +
                " to Reservation: " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        System.out.println("\n---- Services for Reservation " + reservationId + " ----");

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services added.");
            return;
        }

        for (Service s : services) {
            System.out.println(s.getName() + " - " + s.getPrice());
        }
    }

    // Calculate total cost of services
    public double calculateTotalCost(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (Service s : services) {
            total += s.getPrice();
        }

        return total;
    }
}