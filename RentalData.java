import java.time.LocalDate;
import java.util.ArrayList;


public class RentalData {
    private LocalDate systemDate;
    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<Vehicle> vehiclesList = new ArrayList<>();
    private ArrayList<Booking> bookingsList = new ArrayList<>();
    private ArrayList<Invoice> invoicesList = new ArrayList<>();
            

    public RentalData() {
        
    }


    public LocalDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDate systemDate) {
        this.systemDate = systemDate;
    }


    public void addUser(User user) {
        usersList.add(user);
    }

    public void addVehicle(Vehicle vehicle) {
        vehiclesList.add(vehicle);
    }

    public void addBooking(Booking booking) {
        bookingsList.add(booking);
    }

    public void addInvoice(Invoice invoice) {
        invoicesList.add(invoice);
    }

    public void removeVehicle(Vehicle vehicle) {
        vehiclesList.remove(vehicle);
    }

    public void removeBooking(Booking booking) {
        bookingsList.remove(booking);
    }

    public ArrayList<User> getAllUsers() {
        return usersList;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehiclesList;
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingsList;
    }

    public ArrayList<Invoice> getAllInvoices() {
        return invoicesList;
    }

    public User getUserById(String userId) {
        for(User user : usersList) {
            if(user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public Vehicle getVehicleByPlate(String plateNo) {
        for(Vehicle vehicle : vehiclesList) {
            //if(vehicle.getPlateNo().equals(plateNo)) {
                //return vehicle;
            //}
        }

        return null;
    }

    public Booking getBookingById(String bookingId) {
        for(Booking booking : bookingsList) {
            if(booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }

        return null;
    }
}