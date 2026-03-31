/**
 * UseCase2RoomInitialization - Demonstrates room creation and static availability
 * 
 * @author Anant
 * @version 2.1
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Welcome to BookMyStay Application");
        System.out.println("Version: 2.1\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability (intentionally simple)
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("---- Room Details ----\n");

        single.displayDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        doub.displayDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable + "\n");
    }
}