package src;
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
        return (duration * getDailyRate() + serviceFee);
    }

    @Override
    public String toCSV() {
        return ("Suv," + getPlateNo() + "," + getBrand() + "," + getModel() + "," + getTransmission() + ","
             + getEngineCap() + "," + getSeatingCap() + "," + getMileage() + "," + getCurFuelLevel() + ","
             + getMaxFuelLevel() + "," + getDailyRate() + "," + getIsAvailable() + "," + getIsDamaged() + ","
             + getRentalCount() + "," + getServiceFee());
    }
}