import java.util.ArrayList;


public class VehicleManager {
    private MainManager mainManager;


    public VehicleManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }

    
    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    public boolean removeVehicle(String plateNo) {
        return false;
    }

    public Vehicle getVehicleByPlate(String plateNo) {
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return null;
    }

    public boolean setVehicleMileage(String plateNo, double mileage) {
        return false;
    }

    public boolean setVehicleFuel(String plateNo, double curFuelLevel) {
        return false;
    }

    public boolean setVehicleAvailability(String plateNo, boolean isAvailable) {
        return false;
    }

    public boolean setVehicleDamage(String plateNo, boolean isDamaged, double damageFee) {
        return false;
    }
}