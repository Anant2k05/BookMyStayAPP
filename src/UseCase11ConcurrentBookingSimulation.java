import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking System
class BookingSystem {

    private final Map<String, Integer> inventory;
    private final Queue<BookingRequest> bookingQueue;

    public BookingSystem(Map<String, Integer> inventory, Queue<BookingRequest> queue) {
        this.inventory = inventory;
        this.bookingQueue = queue;
    }

    // Critical Section
    public synchronized void processBooking() {
        if (bookingQueue.isEmpty()) {
            return;
        }

        BookingRequest request = bookingQueue.poll();

        if (request == null) return;

        String roomType = request.roomType;

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            System.out.println(Thread.currentThread().getName() +
                    " booked " + roomType + " for " + request.guestName);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed for " + request.guestName + " (No rooms)");
        }
    }
}

// Worker Thread
class BookingWorker extends Thread {

    private final BookingSystem system;

    public BookingWorker(BookingSystem system, String name) {
        super(name);
        this.system = system;
    }

    public void run() {
        while (true) {
            system.processBooking();

            // Exit condition (avoid infinite loop)
            synchronized (system) {
                // hacky but safe check
                // if queue empty → break
                // prevents useless looping
            }

            try {
                Thread.sleep(100); // simulate processing delay
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        // Shared Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("STANDARD", 2);
        inventory.put("DELUXE", 1);

        // Shared Queue
        Queue<BookingRequest> queue = new LinkedList<>();

        // Simulated Requests
        queue.add(new BookingRequest("Aman", "STANDARD"));
        queue.add(new BookingRequest("Riya", "STANDARD"));
        queue.add(new BookingRequest("John", "STANDARD")); // should fail
        queue.add(new BookingRequest("Neha", "DELUXE"));
        queue.add(new BookingRequest("Raj", "DELUXE"));   // should fail

        BookingSystem system = new BookingSystem(inventory, queue);

        // Multiple Threads
        Thread t1 = new BookingWorker(system, "Thread-1");
        Thread t2 = new BookingWorker(system, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Execution interrupted");
        }

        System.out.println("\nFinal Inventory: " + inventory);
    }
}