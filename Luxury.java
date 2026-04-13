public class Luxury extends Vehicle {
    private double serviceFee;
    private double insuranceRate;


    public Luxury(String plateNo, String brand, String model, String transmission, int engineCap, int seatingCap,
         double mileage, double curFuelLevel, double maxFuelLevel, double dailyRate, boolean isAvailable,
         boolean isDamaged, int rentalCount, double serviceFee, double insuranceRate) {
        super(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel,
             dailyRate, isAvailable, isDamaged, rentalCount);
        this.serviceFee = serviceFee;
        this.insuranceRate = insuranceRate;
    }


    public double getServiceFee() {
        return serviceFee;
    }

    public double getInsuranceRate() {
        return insuranceRate;
    }


    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setInsuranceRate(double insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    
    public double calculateBasePrice(long duration) {
        return (duration * getDailyRate() + duration * insuranceRate + serviceFee);
    }

    @Override
    public String toCSV() {
        return ("Luxury," + getPlateNo() + "," + getBrand() + "," + getModel() + "," + getTransmission() + ","
             + getEngineCap() + "," + getSeatingCap() + "," + getMileage() + "," + getCurFuelLevel() + ","
             + getMaxFuelLevel() + "," + getDailyRate() + "," + getIsAvailable() + "," + getIsDamaged() + ","
             + getRentalCount() + "," + getServiceFee() + "," + getInsuranceRate());
    }
}