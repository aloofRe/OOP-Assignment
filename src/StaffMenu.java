package src;
import java.util.ArrayList;
import java.util.Scanner;


public class StaffMenu extends Menu {
    public StaffMenu(MainManager mainManager, Scanner scanner){
        super(mainManager, scanner);
    }


    @Override
    public void start() {
        while(true) {
            clearScreen();
            System.out.println(".------------------------------.");
            System.out.println("|          STAFF MENU          |");
            System.out.println("'------------------------------'");
            System.out.println("\n1. Returned Bookings");
            System.out.println("2. Logout");
            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    returnMenu();
                    break;
                case 2:
                    mainManager.logout();
                    return;
                default:
                    break;
            }
        }
    }

    public void returnMenu() {
        while(true) {
            clearScreen();
            ArrayList<Booking> returnedBookings = mainManager.getBookingManager().getDroppedoffBookings();

            System.out.println("========== RETURN MENU ==========");
            System.out.println("-".repeat(91));
            System.out.printf("| %-3s | %-10s | %-10s | %-10s | %-12s | %-12s | %-12s |\n",
                 "No", "BookingID", "UserID", "PlateNo", "PickupDate", "DropoffDate", "ReturnedDate");
            System.out.println("-".repeat(91));

            for(int i = 0; i < returnedBookings.size(); i++) {
                System.out.printf("| %-3d | ", (i + 1));
                System.out.print(returnedBookings.get(i).toString());
                System.out.println(" |");
            }
            System.out.println("-".repeat(91));
            
            System.out.println("\nWould you like to complete a Booking?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    int bookingChoice = getIntInput("\nEnter Booking number : ");

                    if(bookingChoice >= 1 && bookingChoice <= returnedBookings.size()) {
                        clearScreen();
                        Booking selectedBooking = returnedBookings.get(bookingChoice - 1);

                        double curFuelLevel = getDoubleInput("\nEnter the current fuel level (Max : " + selectedBooking.getVehicle().getMaxFuelLevel() + ") : ");
                        mainManager.getVehicleManager().setVehicleFuel(selectedBooking.getVehicle().getPlateNo(), curFuelLevel);
                        
                        double addMileage = getDoubleInput("Enter new mileage to be added : ");
                        mainManager.getVehicleManager().setVehicleMileage(selectedBooking.getVehicle().getPlateNo(),
                             selectedBooking.getVehicle().getMileage() + addMileage);

                        System.out.println("Is the vehicle damaged?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        int damageChoice = getIntInput("\nEnter choice : ");

                        switch(damageChoice) {
                            case 1:
                                double damageFee = getDoubleInput("\nEnter the damage fee : ");

                                mainManager.getVehicleManager().setVehicleDamage(selectedBooking, true, damageFee);
                                break;
                            case 2:
                                break;
                            default:
                                break;
                        }

                        mainManager.getBookingManager().finalizeBooking(selectedBooking.getBookingId());
                        notify("Booking Successfully Finalized!");
                    } else {
                        notify("Invalid Booking Number. Please Input A Valid Number From The List.");
                    }
                    break;
                case 2:
                    return;
                default:
                    return;
            }
        }
    }
}