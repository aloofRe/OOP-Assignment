package src;
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
            
            System.out.println(".------------------------------.");
            System.out.println("|        CUSTOMER MENU         |");
            System.out.println("'------------------------------'");
            
            System.out.println("\n1. Browse Vehicles");
            System.out.println("2. Pickup Vehicles");
            System.out.println("3. Dropoff Vehicles");
            System.out.println("4. View Payments");
            System.out.println("5. Logout");

            int choice = getIntInput("\nEnter choice : ");

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

        System.out.println("========== Browse Vehicles ==========");

        System.out.print("Enter Pickup Date (YYYY-MM-DD) : ");
        LocalDate pickupDate;
        try{
            pickupDate = LocalDate.parse(scanner.nextLine());
        } catch(Exception e) {
            notify("Invalid Date Format. Please Use YYYY-MM-DD.");
            return;
        }

        if(pickupDate == null || pickupDate.isBefore(mainManager.getRentalData().getSystemDate())) {
            notify("Invalid Date. Please Enter A Valid Date");
            return;
        }

        System.out.print("Enter Dropoff Date (YYYY-MM-DD) : ");
        LocalDate dropoffDate;
        try{
            dropoffDate = LocalDate.parse(scanner.nextLine());
        } catch(Exception e) {
            notify("Invalid Date Format. Please Use YYYY-MM-DD.");
            return;
        }

        if(dropoffDate == null || dropoffDate.isBefore(pickupDate) || pickupDate.equals(dropoffDate)) {
            notify("Invalid Dates. Please Enter Valid Dates.");
            return;
        }

        int vehicleType = getIntInput("Enter Vehicle Type filter (0 = All, 1 = Economy, 2 = Suv, 3 = Luxury) : ");

        while(true) {
            clearScreen();
            ArrayList<Vehicle> availableVehicles = mainManager.getBookingManager().getAvailableVehicles(pickupDate, dropoffDate, vehicleType);
            
            System.out.println("========== Available Vehicles ==========");
            System.out.println("-".repeat(94));
            System.out.printf("| %-3s | %-10s | %-12s %-18s | %-12s | %8s | %-11s |\n",
                 "No", "PlateNo", "Brand", "Model", "Transmission", "Seats", "Daily Rate");
            System.out.println("-".repeat(94));
            
            for(int i = 0; i < availableVehicles.size(); i++) {
                System.out.printf("| %-3d | ", (i + 1));
                System.out.print(availableVehicles.get(i).toString() + " |\n");
            }
            
            System.out.println("-".repeat(94));

            System.out.println("\nWould you like to book a Vehicle?");
            System.out.println("\n1. Select Vehicle");
            System.out.println("2. Return");

            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    int pickedVehicle = getIntInput("\nEnter Vehicle number : ");

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

        System.out.println("========== Vehicle Details ==========");
        System.out.println("-".repeat(43));
        System.out.printf("| %-16s | %-20s |\n", "Plate Number", vehicle.getPlateNo());
        System.out.printf("| %-16s | %-20s |\n", "Brand", vehicle.getBrand());
        System.out.printf("| %-16s | %-20s |\n", "Model", vehicle.getModel());
        System.out.printf("| %-16s | %-20s |\n", "Transmission", vehicle.getTransmission());
        System.out.printf("| %-16s | %-20d |\n", "Engine Capacity", vehicle.getEngineCap());
        System.out.printf("| %-16s | %-20d |\n", "Seating Capacity", vehicle.getSeatingCap());
        System.out.printf("| %-16s | %-20.2f |\n", "Mileage", vehicle.getMileage());
        System.out.printf("| %-16s | %-20.2f |\n", "Current Fuel", vehicle.getCurFuelLevel());
        System.out.printf("| %-16s | %-20.2f |\n", "Max Fuel", vehicle.getMaxFuelLevel());
        System.out.printf("| %-16s | %-20.2f |\n", "Daily Rate", vehicle.getDailyRate());

        if(vehicle instanceof Economy economy) {
            System.out.printf("| %-16s | %-20.2f |\n", "Fuel Discount(%)", economy.getFuelDiscount());
        }
        else if(vehicle instanceof Suv suv) {
            System.out.printf("| %-16s | %-20.2f |\n", "Service Fee", suv.getServiceFee());
        }
        else if(vehicle instanceof Luxury luxury) {
            System.out.printf("| %-16s | %-20.2f |\n", "Service Fee", luxury.getServiceFee());
            System.out.printf("| %-16s | %-20.2f |\n", "Insurance Rate", luxury.getInsuranceRate());
        }

        System.out.printf("| %-16s | %-20d |\n", "Rental Count", vehicle.getRentalCount());
        System.out.println("-".repeat(43));
        
        double baseTotal = mainManager.getBookingManager().calculateBaseTotal(vehicle, pickupDate, dropoffDate);

        System.out.printf("\nEstimated Base Total : RM%-10.2f\n", baseTotal);
        
        System.out.println("\n1. Confirm Booking");
        System.out.println("2. Return");
        
        int bookingChoice = getIntInput("\nEnter choice : ");

        switch(bookingChoice) {
            case 1:
                if(mainManager.getSessionUser() instanceof Customer customer) {
                    if(customer.getLicenseNo().equals("null") || customer.getContactNo().equals("null")) {
                        System.out.print("Enter License No : ");
                        String licenseNo = scanner.nextLine();
                        customer.setLicenseNo(licenseNo);

                        System.out.print("Enter Contact No : ");
                        String contactNo = scanner.nextLine();
                        customer.setContactNo(contactNo);
                    }

                    boolean bookingStatus = mainManager.getBookingManager().addBooking(customer, vehicle, pickupDate, dropoffDate);
                    
                    if(bookingStatus) {
                        mainManager.getRentalData().getAllBookings().get(mainManager.getRentalData().getAllBookings().size() - 1).setBaseTotal(baseTotal);
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

            System.out.println("========== Pickup Vehicles ==========");
            System.out.println("-".repeat(91));
            System.out.printf("| %-3s | %-10s | %-10s | %-10s | %-12s | %-12s | %-12s |\n",
                 "No", "BookingID", "UserID", "PlateNo", "PickupDate", "DropoffDate", "ReturnedDate");
            System.out.println("-".repeat(91));
            
            for(int i = 0; i < pickupBookings.size(); i++) {
                System.out.printf("| %-3d | ", (i + 1));
                System.out.print(pickupBookings.get(i).toString() + " |\n");
                
            }
            System.out.println("-".repeat(91));
            
            System.out.println("\n1. Pickup a Vehicle");
            System.out.println("2. Return");

            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    int bookingChoice = getIntInput("\nEnter Booking number : ");

                    LocalDate currentDate = mainManager.getRentalData().getSystemDate();

                    if(bookingChoice >= 1 && bookingChoice <= pickupBookings.size()
                         && (currentDate.isEqual(pickupBookings.get(bookingChoice - 1).getPickupDate())
                         || currentDate.isAfter(pickupBookings.get(bookingChoice - 1).getPickupDate()))) {
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

            System.out.println("========== Dropoff Vehicles ===========");
            System.out.println("-".repeat(91));
            System.out.printf("| %-3s | %-10s | %-10s | %-10s | %-12s | %-12s | %-12s |\n",
                 "No", "BookingID", "UserID", "PlateNo", "PickupDate", "DropoffDate", "ReturnedDate");
            System.out.println("-".repeat(91));

            for(int i = 0; i < dropoffBookings.size(); i++) {
                System.out.printf("| %-3d | ", (i + 1));
                System.out.printf(dropoffBookings.get(i).toString() + " |\n");
            }
            System.out.println("-".repeat(91));

            System.out.println("\n1. Dropoff Vehicle");
            System.out.println("2. Return");

            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    int bookingChoice = getIntInput("\nEnter Booking number : ");

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

            System.out.println("========== Invoice List ==========");
            System.out.println("-".repeat(89));
            System.out.printf("| %-3s | %-10s | %-10s | %-10s | %-10s | %-12s | %-12s |\n",
                 "No", "InvoiceID", "BookingID", "UserID", "PlateNo", "InvoiceDate", "Final Total");
            System.out.println("-".repeat(89));
            
            for(int i = 0; i < invoices.size(); i++) {
                System.out.printf("| %-3d | ", (i + 1));
                System.out.print(invoices.get(i).toString() + " |\n");
            }
            System.out.println("-".repeat(89));

            System.out.println("\n1. View Details");
            System.out.println("2. Return");

            int choice = getIntInput("\nEnter choice : ");

            switch(choice) {
                case 1:
                    int invoiceChoice = getIntInput("\nEnter Invoice number : ");

                    if(invoiceChoice >= 1 && invoiceChoice <= invoices.size()) {
                        clearScreen();
                        Invoice selectedInvoice = invoices.get(invoiceChoice - 1);

                        System.out.println("========== Invoice Details ==========");
                        System.out.println("-".repeat(43));
                        System.out.println("|                 INVOICE                 |");
                        System.out.println("-".repeat(43));

                        System.out.printf("| %-16s : %-20s |\n", "Invoice ID", selectedInvoice.getInvoiceId());
                        System.out.printf("| %-16s : %-20s |\n", "Booking ID", selectedInvoice.getBookingId());
                        System.out.printf("| %-16s : %-20s |\n", "User ID", selectedInvoice.getUserId());
                        System.out.printf("| %-16s : %-20s |\n", "Plate No", selectedInvoice.getPlateNo());
                        System.out.printf("| %-16s : %-20s |\n", "Invoice Date", selectedInvoice.getInvoiceDate());
                        System.out.println("-".repeat(43));

                        System.out.printf("| %-16s : RM%-18.2f |\n", "Base Total", selectedInvoice.getBaseTotal());
                        System.out.printf("| %-16s : RM%-18.2f |\n", "Fuel Fee", selectedInvoice.getFuelFee());
                        System.out.printf("| %-16s : RM%-18.2f |\n", "Late Fee", selectedInvoice.getLateFee());
                        System.out.printf("| %-16s : RM%-18.2f |\n", "Damage Fee", selectedInvoice.getDamageFee());
                        System.out.println("-".repeat(43));

                        System.out.printf("| %-16s : RM%-18.2f |\n", "Extra Total", selectedInvoice.getExtraTotal());

                        System.out.println("=".repeat(43));
                        System.out.printf("| %-16s : RM%-18.2f |\n", "FINAL TOTAL", selectedInvoice.getFinalTotal());
                        System.out.println("=".repeat(43));

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