package src;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;


public class BookingManager {
    private MainManager mainManager;


    public BookingManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public boolean addBooking(Customer customer, Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        if(pickupDate == null || dropoffDate == null || pickupDate.isBefore(mainManager.getRentalData().getSystemDate())
             || dropoffDate.isBefore(pickupDate) || pickupDate.equals(dropoffDate)) {
            return false;
        }

        int bookingListSize = mainManager.getRentalData().getAllBookings().size();
        String nextBookingId = "B" + ((bookingListSize > 0) ? String.format("%04d", Integer.parseInt(mainManager.getRentalData()
                .getAllBookings().get(bookingListSize - 1).getBookingId().substring(1)) + 1) : "0001");

        mainManager.getRentalData().addBooking(new Booking(nextBookingId, customer, vehicle, pickupDate, dropoffDate));
        return true;
    }

    public Booking getBooking(String bookingId) {
        return mainManager.getRentalData().getBookingById(bookingId);
    }

    public ArrayList<Vehicle> getAvailableVehicles(LocalDate pickupDate, LocalDate dropoffDate, int vehicleType) {
        ArrayList<Vehicle> availableVehicles = new ArrayList<>();

        for(Vehicle vehicle : mainManager.getRentalData().getAllVehicles()) {
            if(vehicleType == 0 || (vehicleType == 1 && vehicle instanceof Economy)
                 || (vehicleType == 2 && vehicle instanceof Suv)
                 || (vehicleType == 3 && vehicle instanceof Luxury)) {
                if(vehicle.getIsAvailable()) {
                    availableVehicles.add(vehicle);
                }
            }
        }

        for(Booking booking : mainManager.getRentalData().getAllBookings()) {
            if(availableVehicles.contains(booking.getVehicle())) {
                if(pickupDate.isBefore(booking.getDropoffDate()) && dropoffDate.isAfter(booking.getPickupDate())) {
                    availableVehicles.remove(booking.getVehicle());
                }
            }
        }

        return availableVehicles;
    }

    public ArrayList<Booking> getPendingsByCustomer(String userId) {
        ArrayList<Booking> pendingBookings = new ArrayList<>();

        for(Booking booking : mainManager.getRentalData().getAllBookings()) {
            if(booking.getCustomer().getUserId().equals(userId) && !booking.getPickedup()) {
                pendingBookings.add(booking);
            }
        }

        return pendingBookings;
    }

    public ArrayList<Booking> getPickupsByCustomer(String userId) {
        ArrayList<Booking> pickedupBookings = new ArrayList<>();

        for(Booking booking : mainManager.getRentalData().getAllBookings()) {
            if(booking.getCustomer().getUserId().equals(userId) && booking.getPickedup() && !booking.getDroppedoff()) {
                pickedupBookings.add(booking);
            }
        }

        return pickedupBookings;
    }

    public ArrayList<Booking> getDroppedoffBookings() {
        ArrayList<Booking> droppedoffBookings = new ArrayList<>();

        for(Booking booking : mainManager.getRentalData().getAllBookings()) {
            if(booking.getDroppedoff() && !booking.getCompleted()) {
                droppedoffBookings.add(booking);
            }
        }

        return droppedoffBookings;
    }

    public ArrayList<Invoice> getInvoicesByCustomer(String userId) {
        ArrayList<Invoice> invoices = new ArrayList<>();

        for(Invoice invoice : mainManager.getRentalData().getAllInvoices()) {
            if(invoice.getUserId().equals(userId)) {
                invoices.add(invoice);
            }
        }

        return invoices;
    }

    public boolean setPickedup(Booking booking, boolean isPickedup) {
        if(booking == null || booking.getDroppedoff() || booking.getCompleted()) {
            return false;
        }
        
        booking.setPickedup(isPickedup);

        if(isPickedup) {
            booking.getVehicle().setIsAvailable(false);
        }

        return true;
    }

    public boolean setDroppedoff(Booking booking, boolean isDroppedoff) {
        if(booking == null || !booking.getPickedup() || booking.getCompleted()) {
            return false;
        }
        
        booking.setDroppedoff(isDroppedoff);
        booking.setReturnedDate(mainManager.getRentalData().getSystemDate());

        return true;
    }

    public double calculateBaseTotal(Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        if(pickupDate == null || dropoffDate == null || dropoffDate.isBefore(pickupDate)) {
            return 0.0;
        }

        long duration = pickupDate.until(dropoffDate, ChronoUnit.DAYS);
        
        return vehicle.calculateBasePrice(duration);
    }

    public double calculateExtraTotal(Booking booking) {
        double fuelFee = booking.getVehicle().calculateFuelFees(mainManager.getRentalData().getFuelPrice());
        booking.setFuelFee(fuelFee);

        double lateFee = 0.0;
        if(booking.getReturnedDate().isAfter(booking.getDropoffDate())) {
            lateFee = booking.getDropoffDate().until(booking.getReturnedDate(), ChronoUnit.DAYS)
                 * (booking.getBaseTotal() * 0.1);
        }
        booking.setLateFee(lateFee);

        double damageFee = 0.0;
        if(booking.getVehicle().getIsDamaged()) {
            damageFee = booking.getDamageFee();
        }

        double extraTotal = fuelFee + lateFee + damageFee;
        booking.setExtraTotal(extraTotal);
        return extraTotal;
    }

    public void finalizeBooking(String bookingId) {
        Booking booking = mainManager.getRentalData().getBookingById(bookingId);

        double extraTotal = calculateExtraTotal(booking);

        booking.setFinalTotal(booking.getBaseTotal() + extraTotal);

        booking.getVehicle().incRentalCount();
        booking.getVehicle().setIsAvailable(true);
        booking.getVehicle().setIsDamaged(false);
        booking.setCompleted(true);

        mainManager.getRentalData().addInvoice(generateInvoice(booking));
    }

    public Invoice generateInvoice(Booking booking) {
        int invoiceListSize = mainManager.getRentalData().getAllInvoices().size();
        String nextInvoiceId = "I" + ((invoiceListSize > 0) ? String.format("%04d", Integer.parseInt(mainManager.getRentalData()
                .getAllInvoices().get(invoiceListSize - 1).getInvoiceId().substring(1)) + 1) : "0001");

        return new Invoice(nextInvoiceId, booking.getBookingId(), booking.getCustomer().getUserId(),
             booking.getVehicle().getPlateNo(), mainManager.getRentalData().getSystemDate(), booking.getFuelFee(),
             booking.getLateFee(), booking.getDamageFee(), booking.getBaseTotal(), booking.getExtraTotal(),
             booking.getFinalTotal());
    }

    public ArrayList<Vehicle> getMostRentedVehicles() {
        ArrayList<Vehicle> sortingList = new ArrayList<>(mainManager.getRentalData().getAllVehicles());
        ArrayList<Vehicle> sortedList = new ArrayList<>();

        while(!sortingList.isEmpty()) {
            Vehicle topVehicle = sortingList.get(0);

            for(Vehicle vehicle : sortingList) {
                if(vehicle.getRentalCount() > topVehicle.getRentalCount()) {
                    topVehicle = vehicle;
                }
            }

            sortedList.add(topVehicle);
            sortingList.remove(topVehicle);
        }
        
        return sortedList;
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;

        for(Invoice invoice : mainManager.getRentalData().getAllInvoices()) {
            totalRevenue += invoice.getFinalTotal();
        }

        return totalRevenue;
    }
}