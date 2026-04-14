import java.util.ArrayList;


public class VehicleManager {
    private MainManager mainManager;


    public VehicleManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }

    
    public boolean addVehicle(Vehicle vehicle) {
        if(vehicle == null) {
            return false;
        }

        mainManager.getRentalData().addVehicle(vehicle);
        return true;
    }

    public boolean removeVehicle(String plateNo) {
        Vehicle vehicle = getVehicleByPlate(plateNo);

        if(vehicle == null) {
            return false;
        }

        mainManager.getRentalData().removeVehicle(vehicle);
        return true;
    }

    public Vehicle getVehicleByPlate(String plateNo) {
        return mainManager.getRentalData().getVehicleByPlate(plateNo);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return mainManager.getRentalData().getAllVehicles();
    }

    public boolean setVehicleMileage(String plateNo, double mileage) {
        Vehicle vehicle = getVehicleByPlate(plateNo);

        if(vehicle == null) {
            return false;
        }

        vehicle.setMileage(mileage);
        return true;
    }

    public boolean setVehicleFuel(String plateNo, double curFuelLevel) {
        Vehicle vehicle = getVehicleByPlate(plateNo);

        if(vehicle == null) {
            return false;
        }

        vehicle.setCurFuelLevel(curFuelLevel);
        return true;
    }

    public boolean setVehicleAvailability(String plateNo, boolean isAvailable) {
        Vehicle vehicle = getVehicleByPlate(plateNo);

        if(vehicle == null) {
            return false;
        }

        vehicle.setIsAvailable(isAvailable);
        return true;
    }

    public boolean setVehicleDamage(Booking booking, boolean isDamaged, double damageFee) {
        Vehicle vehicle = booking.getVehicle();

        if(vehicle == null) {
            return false;
        }

        vehicle.setIsDamaged(isDamaged);
        booking.setDamageFee(damageFee);
        return true;
    }
}