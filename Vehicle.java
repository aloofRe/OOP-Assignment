public abstract class Vehicle {
    private String plateNo;
    private String brand;
    private String model;
    private String transmission;
    private int engineCap;
    private int seatingCap;
    private double mileage;
    private double curFuelLevel;
    private double maxFuelLevel;
    private double dailyRate;
    private boolean isAvailable;
    private boolean isDamaged;
    private int rentalCount;
    

    public Vehicle(String plateNo, String brand, String model, String transmission, int engineCap, int seatingCap, double mileage, double curFuelLevel, double maxFuelLevel, double dailyRate, boolean isAvailable, boolean isDamaged, int rentalCount) {
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.transmission = transmission;
        this.engineCap = engineCap;
        this.seatingCap = seatingCap;
        this.mileage = mileage;
        this.curFuelLevel = curFuelLevel;
        this.maxFuelLevel = maxFuelLevel;
        this.dailyRate = dailyRate;
        this.isAvailable = isAvailable;
        this.isDamaged = isDamaged;
        this.rentalCount = rentalCount;
    }
    

    public String getPlateNo() {
        return plateNo;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getTransmission() {
        return transmission;
    }
    
    public int getEngineCap() {
        return engineCap;
    }
    
    public int getSeatingCap() {
        return seatingCap;
    }
    
    public double getMileage() {
        return mileage;
    }
    
    public double getCurFuelLevel() {
        return curFuelLevel;
    }
    
    public double getMaxFuelLevel() {
        return maxFuelLevel;
    }
    
    public double getDailyRate() {
        return dailyRate;
    }
    
    public boolean getIsAvailable() {
        return isAvailable;
    }
    
    public boolean getIsDamaged() {
        return isDamaged;
    }
    
    public int getRentalCount() {
        return rentalCount;
    }
    

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    
    public void setEngineCap(int engineCap) {
        this.engineCap = engineCap;
    }
    
    public void setSeatingCap(int seatingCap) {
        this.seatingCap = seatingCap;
    }
    
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
    
    public void setCurFuelLevel(double curFuelLevel) {
        this.curFuelLevel = curFuelLevel;
    }
    
    public void setMaxFuelLevel(double maxFuelLevel) {
        this.maxFuelLevel = maxFuelLevel;
    }
    
    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }
    
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    public void setRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }
    
    
    public double calculateBasePrice(long duration) {
       return 0.0;
    }
    
    public double calculateFuelFees(double fuelPrice){
        return 0.0;
    }

    public void incRentalCount() {
        rentalCount++;
    }
    
    public String toString() {
        return "";
    }

    public String toCSV() {
        return "";
    }
}