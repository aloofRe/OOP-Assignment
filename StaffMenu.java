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

            //TODO:
            //staff title whatever
            //options 1 returnmenu 2 logout

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

            //TODO:
            //retrned bookings from customers title menu
            //display current bookings which have already been dropped off but not checked by staff yet
            //table header stuff bookingId | userId | plateno | pickupdate | dropoffdate | returneddate |
            for(int i = 0; i < returnedBookings.size(); i++) {
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.print(returnedBookings.get(i).toString());
                //TODO:
                //Close the table with another vertical line "|"
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask if want staff want complete a booking
            //1 yes 2 return options
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which booking to pick
                    int bookingChoice = scanner.nextInt();
                    scanner.nextLine();

                    if(bookingChoice >= 1 && bookingChoice <= returnedBookings.size()) {
                        clearScreen();
                        Booking selectedBooking = returnedBookings.get(bookingChoice - 1);

                        //TODO:
                        //Ask staff how much fuel is left in the vehicle out of this vehicle's (maxfuel)
                        double curFuelLevel = scanner.nextDouble();
                        scanner.nextLine();
                        mainManager.getVehicleManager().setVehicleFuel(selectedBooking.getVehicle().getPlateNo(), curFuelLevel);

                        //TODO:
                        //Ask staff how much mileage to add to vehicle now
                        double addMileage = scanner.nextDouble();
                        scanner.nextLine();
                        mainManager.getVehicleManager().setVehicleMileage(selectedBooking.getVehicle().getPlateNo(),
                             selectedBooking.getVehicle().getMileage() + addMileage);

                        //TODO:
                        //Ask staff if vehicle is damaged
                        //Options 1 yes 2 no
                        int damageChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch(damageChoice) {
                            case 1:
                                //TODO:
                                //Ask staff how much damage fees for the vehicle
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