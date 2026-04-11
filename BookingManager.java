import java.time.LocalDate;
import java.util.ArrayList;


public class BookingManager {
    private MainManager mainManager;


    public BookingManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public boolean addBooking(Customer customer, Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        return false;
    }

    public boolean removeBooking(String bookingId) {
        return false;
    }

    public String getBooking(String bookingId) {
        return null;
    }

    public ArrayList<Vehicle> getAvailableVehicles(LocalDate pickupDate, LocalDate dropoffDate, int vehicleType) {
        return null;
    }

    public boolean checkAvailability(Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        return false;
    }

    public ArrayList<Booking> getPendingsByCustomer(String userId) {
        return null;
    }

    public ArrayList<Booking> getPickupsByCustomer(String userId) {
        return null;
    }

    public ArrayList<Booking> getDroppedoffBookings() {
        return null;
    }

    public ArrayList<Invoice> getInvoicesByCustomer(String userId) {
        return null;
    }

    public boolean setPickedup(String pickedup, boolean value) {
        return false;
    }

    public boolean setDroppedoff(String isDroppedoff, boolean value) {
        return false;
    }

    public double calculateBaseTotal(Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        return 0.0;
    }

    public double calculateExtraTotal(Booking booking) {
        return 0.0;
    }

    public void finalizeBooking(String bookingId) {
    }

    public Invoice generateInvoice(Booking booking) {
        return null;
    }

    public ArrayList<Vehicle> getMostRentedVehicles() {
        return null;
    }

    public double calculateTotalRevenue() {
        return 0.0;
    }
}