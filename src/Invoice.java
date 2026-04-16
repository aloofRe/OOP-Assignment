package src;
import java.time.LocalDate;


public class Invoice {
    private String invoiceId;
    private String bookingId;
    private String userId;
    private String plateNo;
    private LocalDate invoiceDate;
    private double fuelFee;
    private double lateFee;
    private double damageFee;
    private double baseTotal;
    private double extraTotal;
    private double finalTotal;
    

    public Invoice(String invoiceId, String bookingId, String userId, String plateNo, LocalDate invoiceDate,
         double fuelFee, double lateFee, double damageFee, double baseTotal, double extraTotal, double finalTotal) {
        this.invoiceId = invoiceId;
        this.bookingId = bookingId;
        this.userId = userId;
        this.plateNo = plateNo;
        this.invoiceDate = invoiceDate;
        this.fuelFee = fuelFee;
        this.lateFee = lateFee;
        this.damageFee = damageFee;
        this.baseTotal = baseTotal;
        this.extraTotal = extraTotal;
        this.finalTotal = finalTotal;
    }
    

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
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

    
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
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


    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-10s | %-10s | %-12s | RM%10.2f", invoiceId, bookingId,
             userId, plateNo, invoiceDate, finalTotal);
    }

    public String toCSV() {
        return (invoiceId + "," + bookingId + "," + userId + "," + plateNo + "," + invoiceDate + ","
             + fuelFee + "," + lateFee + "," + damageFee + "," + baseTotal + "," + extraTotal + "," + finalTotal);
    }
}