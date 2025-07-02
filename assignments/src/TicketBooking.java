import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BookingTask implements Runnable {
    private final int bookingId;

    public BookingTask(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public void run() {
        System.out.println("Booking " + bookingId + " received");
        simulateProcessing("Ticket confirmed");
    }

    private void simulateProcessing(String message) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(message + " for booking " + bookingId);
    }
}

 class TicketBookingSystem {
    public static void main(String[] args) {
        int numberOfThreads = 3;  // Limit concurrency to 3
        int numberOfBookings = 5;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 1; i <= numberOfBookings; i++) {
            executor.submit(new BookingTask(i));
        }
        executor.shutdown();

    }
}
