import java.time.LocalDate;


public class Booking {
    private String bookingId;
    private User customer;
    private Vehicle vehicle;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private LocalDate returnedDate;
    private double fuelFee;
    private double lateFee;
    private double damageFee;
    private double baseTotal;
    private double extraTotal;
    private double finalTotal;
    private boolean isPickedup;
    private boolean isDroppedoff;
    private boolean isComplete;
    

    public Booking(String bookingId, User customer, Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
    }

    public Booking(String bookingId, User customer, Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate,
         LocalDate returnedDate, double fuelFee, double lateFee, double damageFee, double baseTotal,
         double extraTotal, double finalTotal, boolean isPickedup, boolean isDroppedoff, boolean isComplete) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
        this.dropoffDate = dropoffDate;
        this.returnedDate = returnedDate;
        this.fuelFee = fuelFee;
        this.lateFee = lateFee;
        this.damageFee = damageFee;
        this.baseTotal = baseTotal;
        this.extraTotal = extraTotal;
        this.finalTotal = finalTotal;
        this.isPickedup = isPickedup;
        this.isDroppedoff = isDroppedoff;
        this.isComplete = isComplete;
    }


    public String getBookingId() {
        return bookingId;
    }

    public User getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getPickUpDate() {
        return pickupDate;
    }

    public LocalDate getDropOffDate() {
        return dropoffDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public double getFuelFee() {
        return fuelFee;
    }

    public double getLateFee() {
        return lateFee;
    }

    public double getDamageFee() {
        return damageFee;
    }

    public double getBaseTotal() {
        return baseTotal;
    }

    public double getExtraTotal() {
        return extraTotal;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public boolean getPickedUp() {
        return isPickedup;
    }
    
    public boolean getDroppedOff() {
        return isDroppedoff;
    }

    public boolean getCompleted() {
        return isComplete;
    }


	public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setPickUpDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setDropOffDate(LocalDate dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public void setFuelFee(double fuelFee) {
        this.fuelFee = fuelFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public void setDamageFee(double damageFee) {
        this.damageFee = damageFee;
    }

    public void setBaseTotal(double baseTotal) {
        this.baseTotal = baseTotal;
    }

    public void setExtraTotal(double extraTotal) {
        this.extraTotal = extraTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public void setPickedUp(boolean isPickedup) {
        this.isPickedup = isPickedup;
    }

    public void setDroppedOff(boolean isDroppedoff) {
        this.isDroppedoff = isDroppedoff;
    }

    public void setCompleted(boolean isComplete) {
        this.isComplete = isComplete;
    }


    @Override
    public String toString() {
        return "";
    }

    public String toCSV() {
        return "";
    }      
}