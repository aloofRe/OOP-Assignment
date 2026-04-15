import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;


public class CustomerMenu extends Menu {
    public CustomerMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
        while(true) {
            clearScreen();
            
            //TODO:
            //display customer menu title or something welcome welcome
            //options 1 browse 2 pickup 3 dropoff 4 payment 5 logout
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    browseMenu();
                    break;
                case 2:
                    pickupMenu();
                    break;
                case 3:
                    dropoffMenu();
                    break;
                case 4:
                    paymentMenu();
                    break;
                case 5:
                    mainManager.logout();
                    return;
                default:
                    break;
            }
        }
    }

    public void browseMenu() {
        clearScreen();

        //TODO:
        //browse menu title
        //Ask for wanted pickup date in YYYY-MM-DD format
        LocalDate pickupDate;
        try{
            pickupDate = LocalDate.parse(scanner.nextLine());
        } catch(Exception e) {
            notify("Invalid Date Format. Please Use YYYY-MM-DD.");
            return;
        }

        //TODO:
        //Ask for wanted dropoff date in YYYY-MM-DD format
        LocalDate dropoffDate;
        try{
            dropoffDate = LocalDate.parse(scanner.nextLine());
        } catch(Exception e) {
            notify("Invalid Date Format. Please Use YYYY-MM-DD.");
            return;
        }

        //TODO:
        //Ask for vehicle type filter
        //0 for just show all vehicles, 1 for economy, 2 suv, 3 luxury
        int vehicleType = scanner.nextInt();
        scanner.nextLine();

        while(true) {
            clearScreen();
            ArrayList<Vehicle> availableVehicles = mainManager.getBookingManager().getAvailableVehicles(pickupDate, dropoffDate, vehicleType);
            
            //TODO:
            //show browsemenu title in a table -----
            //show table headings PlateNo | Brand Model | Transmission | SeatingCap | DailyRate |
            //refer to vehicle.java in toString() for formatting sizes
            for(int i = 0; i < availableVehicles.size(); i++) {
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.print(availableVehicles.get(i).toString());
                //TODO:
                //Close the table with another vertical line "|"
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask whether want to reserve vehicle or return to main customer menu
            //options 1 select vehicle 2 return
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which vehicle number from the list to pick
                    int pickedVehicle = scanner.nextInt();
                    scanner.nextLine();

                    if(pickedVehicle >= 1 && pickedVehicle <= availableVehicles.size()) {
                        reservationMenu(availableVehicles.get(pickedVehicle - 1), pickupDate, dropoffDate);
                    } else {
                        notify("Invalid Vehicle Number. Please Input A Valid Number From The List.");
                    }
                    break;
                case 2:
                    return;
                default:
                    return;
            }
        }
    }

    public void reservationMenu(Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
        clearScreen();

        //TODO:
        //display all the info of this vehicle using getters
        //plateno, brand, model, transmission, enginecap, seatingcap, mileage, maxfuellevel, dailyrate

        double baseTotal = mainManager.getBookingManager().calculateBaseTotal(vehicle, pickupDate, dropoffDate);

        //TODO:
        //display the base total for this car

        //TODO:
        //clear one line space down then ask if customer wants to book the vehicle 1 yes 2 no
        int bookingChoice = scanner.nextInt();
        scanner.nextLine();

        switch(bookingChoice) {
            case 1:
                if(mainManager.getSessionUser() instanceof Customer customer) {
                    if(customer.getLicenseNo() == null || customer.getContactNo() == null) {
                        //TODO:
                        //ask to input license no
                        String licenseNo = scanner.nextLine();
                        customer.setLicenseNo(licenseNo);

                        //TODO:
                        //ask to input contact no
                        String contactNo = scanner.nextLine();
                        customer.setContactNo(contactNo);
                    }

                    boolean bookingStatus = mainManager.getBookingManager().addBooking(customer, vehicle, pickupDate, dropoffDate);
                    
                    if(bookingStatus) {
                        notify("Booking Successful! Your Booking Can Now Be Viewed In The Pickup Menu.");
                        return;
                    } else {
                        notify("Booking Failed. Please Check Your Booking Dates");
                        return;
                    }
                }
                break;
            case 2:
                return;
            default:
                return;
        }
    }

    public void pickupMenu() {
        while(true) {
            clearScreen();
            ArrayList<Booking> pickupBookings = mainManager.getBookingManager().getPendingsByCustomer(mainManager.getSessionUser().getUserId());

            //TODO:
            //pickup menu title stuff
            //display current bookings not yet picked for this customer table refer Booking.java for format in toString()
            //table header stuff bookingId | userId | plateno | pickupdate | dropoffdate | returneddate |
            for(int i = 0; i < pickupBookings.size(); i++) {
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.print(pickupBookings.get(i).toString());
                //TODO:
                //Close the table with another vertical line "|"
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask if want pickup a car from the bookings
            //1 yes 2 return options
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which car from the table to pick
                    int bookingChoice = scanner.nextInt();
                    scanner.nextLine();

                    if(bookingChoice >= 1 && bookingChoice <= pickupBookings.size()) {
                        mainManager.getBookingManager().setPickedup(pickupBookings.get(bookingChoice - 1), true);
                        notify("Vehicle Successfully Picked Up!");
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

    public void dropoffMenu() {
        while(true) {
            clearScreen();
            ArrayList<Booking> dropoffBookings = mainManager.getBookingManager().getPickupsByCustomer(mainManager.getSessionUser().getUserId());

            //TODO:
            //dropoff menu title stuff
            //display current bookings already picked but not dropped for this customer table refer Booking.java for format in toString()
            //table header stuff bookingId | userId | plateno | pickupdate | dropoffdate | returneddate |
            for(int i = 0; i < dropoffBookings.size(); i++) {
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.print(dropoffBookings.get(i).toString());
                //TODO:
                //Close the table with another vertical line "|"
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask if want dropoff a car from the bookings
            //1 yes 2 return options
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which car from the table to dropoff
                    int bookingChoice = scanner.nextInt();
                    scanner.nextLine();

                    if(bookingChoice >= 1 && bookingChoice <= dropoffBookings.size()) {
                        mainManager.getBookingManager().setDroppedoff(dropoffBookings.get(bookingChoice - 1), true);
                        notify("Vehicle Successfully Dropped Off!");
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

    public void paymentMenu() {
        while(true) {
            clearScreen();
            ArrayList<Invoice> invoices = mainManager.getBookingManager().getInvoicesByCustomer(mainManager.getSessionUser().getUserId());

            //TODO:
            //invoice menu title stuff
            //display current invoices for this customer refer Invoice.java for format in toString()
            //table header stuff invoiceId | bookingId | userId | plateNo | invoicedate | finalTotal |
            for(int i = 0; i < invoices.size(); i++) {
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.print(invoices.get(i).toString());
                //TODO:
                //Close the table with another vertical line "|"
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask if want view details of an invoice
            //1 yes 2 return options
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which invoice to check
                    int invoiceChoice = scanner.nextInt();
                    scanner.nextLine();

                    if(invoiceChoice >= 1 && invoiceChoice <= invoices.size()) {
                        clearScreen();
                        Invoice selectedInvoice = invoices.get(invoiceChoice - 1);

                        //TODO:
                        //display all the details of invoice with getters from selectedInvoice, if possible make it look like real invoice with like square and stuff
                        //invoice id, bookingid, userid, plateno, invoicedate, fuelfee, latefee, damagefee, basetotal,extra total,final total
                        notify("");
                    } else {
                        notify("Invalid Invoice Number. Please Input A Valid Number From The List.");
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