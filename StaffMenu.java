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
            System.out.print("\nEnter choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

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
            System.out.print("\nEnter choice : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("\nEnter Booking number : ");
                    int bookingChoice = scanner.nextInt();
                    scanner.nextLine();

                    if(bookingChoice >= 1 && bookingChoice <= returnedBookings.size()) {
                        clearScreen();
                        Booking selectedBooking = returnedBookings.get(bookingChoice - 1);

                        System.out.print("\nEnter the current fuel level (Max : " + selectedBooking.getVehicle().getMaxFuelLevel() + ") : ");
                        double curFuelLevel = scanner.nextDouble();
                        scanner.nextLine();
                        mainManager.getVehicleManager().setVehicleFuel(selectedBooking.getVehicle().getPlateNo(), curFuelLevel);
                        
                        System.out.print("Enter new mileage to be added :  ");
                        double addMileage = scanner.nextDouble();
                        scanner.nextLine();
                        mainManager.getVehicleManager().setVehicleMileage(selectedBooking.getVehicle().getPlateNo(),
                             selectedBooking.getVehicle().getMileage() + addMileage);

                        System.out.println("Is the vehicle damaged?");
                        System.out.println("\n1. Yes");
                        System.out.println("2. No");
                        System.out.print("\nEnter choice : ");
                        int damageChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch(damageChoice) {
                            case 1:
                                System.out.print("\nEnter the damage fee : ");
                                double damageFee = scanner.nextDouble();
                                scanner.nextLine();

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