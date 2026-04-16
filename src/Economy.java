package src;
public class Economy extends Vehicle {
    private double fuelDiscount;


    public Economy(String plateNo, String brand, String model, String transmission, int engineCap, int seatingCap,
         double mileage, double curFuelLevel, double maxFuelLevel, double dailyRate, boolean isAvailable,
         boolean isDamaged, int rentalCount, double fuelDiscount) {
        super(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel,
             dailyRate, isAvailable, isDamaged, rentalCount);
        this.fuelDiscount = fuelDiscount;
    }


    public double getFuelDiscount() {
        return fuelDiscount;
    }


    public void setFuelDiscount(double fuelDiscount) {
        this.fuelDiscount = fuelDiscount;
    }
    

    public double calculateFuelFees(double fuelPrice) {
        return ((getMaxFuelLevel() - getCurFuelLevel()) * fuelPrice * (1 - fuelDiscount));
    }

    @Override
    public String toCSV() {
        return ("Economy," + getPlateNo() + "," + getBrand() + "," + getModel() + "," + getTransmission() + ","
             + getEngineCap() + "," + getSeatingCap() + "," + getMileage() + "," + getCurFuelLevel() + ","
             + getMaxFuelLevel() + "," + getDailyRate() + "," + getIsAvailable() + "," + getIsDamaged() + ","
             + getRentalCount() + "," + getFuelDiscount());
    }
}