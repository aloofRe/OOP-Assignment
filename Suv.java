public class Suv extends Vehicle {
    private double serviceFee;


    public Suv(String plateNo, String brand, String model, String transmission, int engineCap, int seatingCap,
         double mileage, double curFuelLevel, double maxFuelLevel, double dailyRate, boolean isAvailable,
         boolean isDamaged, int rentalCount, double serviceFee) {
        super(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel,
             dailyRate, isAvailable, isDamaged, rentalCount);
        this.serviceFee = serviceFee;
    }


    public double getServiceFee() {
        return serviceFee;
    }


    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    
    public double calculateBasePrice(long duration) {
        return 0.0;
    }

    public String toString() {
        return "";
    }

    public String toCSV() {
        return "";
    }
}