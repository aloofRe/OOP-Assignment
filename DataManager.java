import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.io.PrintWriter;
import java.io.IOException;


public class DataManager {
    private final String SYSTEM_FILE = "data/system.txt";
    private final String USER_FILE = "data/users.txt";
    private final String VEHICLE_FILE = "data/vehicles.txt";
    private final String BOOKING_FILE = "data/bookings.txt";
    private final String INVOICE_FILE = "data/invoices.txt";
    private RentalData rentalData;
    

    public DataManager(RentalData rentalData) {
        this.rentalData = rentalData;
    }


    //Reads each line in each file, then parses them into their respective objects and stores in RentalData
    public void loadData() {
        try(Scanner lineReader = new Scanner(new File(SYSTEM_FILE))) {
            if(lineReader.hasNextLine()) {
                rentalData.setSystemDate(LocalDate.parse(lineReader.nextLine()));
            }
        } catch(FileNotFoundException e) {
            System.out.println("An Error Occurred! File Not Found : " + SYSTEM_FILE);
        }

        try(Scanner lineReader = new Scanner(new File(USER_FILE))) {
            while(lineReader.hasNextLine()) {
                String line = lineReader.nextLine();
                User parsedUser = parseUser(line);

                if(parsedUser != null) {
                    rentalData.addUser(parsedUser);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("An Error Occurred! File Not Found : " + USER_FILE);
        }

        try(Scanner lineReader = new Scanner(new File(VEHICLE_FILE))) {
            while(lineReader.hasNextLine()) {
                String line = lineReader.nextLine();
                Vehicle parsedVehicle = parseVehicle(line);

                if(parsedVehicle != null) {
                    rentalData.addVehicle(parsedVehicle);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("An Error Occurred! File Not Found : " + VEHICLE_FILE);
        }

        try(Scanner lineReader = new Scanner(new File(BOOKING_FILE))) {
            while(lineReader.hasNextLine()) {
                String line = lineReader.nextLine();
                Booking parsedBooking = parseBooking(line);

                if(parsedBooking != null) {
                    rentalData.addBooking(parsedBooking);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("An Error Occurred! File Not Found : " + BOOKING_FILE);
        }

        try(Scanner lineReader = new Scanner(new File(INVOICE_FILE))) {
            while(lineReader.hasNextLine()) {
                String line = lineReader.nextLine();
                Invoice parsedInvoice = parseInvoice(line);

                if(parsedInvoice != null) {
                    rentalData.addInvoice(parsedInvoice);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("An Error Occurred! File Not Found : " + INVOICE_FILE);
        }
    }

    //Gets all objects in RentalData and overwrites all files with their new CSV data
    public void saveData() {
        try(PrintWriter writer = new PrintWriter(new File(SYSTEM_FILE))) {
            writer.println(rentalData.getSystemDate().toString());
        } catch(IOException e) {
            System.out.println("An Error Occurred! Could Not Save To : " + SYSTEM_FILE);
        }

        try(PrintWriter writer = new PrintWriter(new File(USER_FILE))) {
            for(User user : rentalData.getAllUsers()) {
                writer.println(user.toCSV());
            }
        } catch(IOException e) {
            System.out.println("An Error Occurred! Could Not Save To : " + USER_FILE);
        }

        try(PrintWriter writer = new PrintWriter(new File(VEHICLE_FILE))) {
            for(Vehicle vehicle : rentalData.getAllVehicles()) {
                // writer.println(vehicle.toCSV());
            }
        } catch(IOException e) {
            System.out.println("An Error Occurred! Could Not Save To : " + VEHICLE_FILE);
        }

        try(PrintWriter writer = new PrintWriter(new File(BOOKING_FILE))) {
            for(Booking booking : rentalData.getAllBookings()) {
                writer.println(booking.toCSV());
            }
        } catch(IOException e) {
            System.out.println("An Error Occurred! Could Not Save To : " + BOOKING_FILE);
        }

        try(PrintWriter writer = new PrintWriter(new File(INVOICE_FILE))) {
            for(Invoice invoice : rentalData.getAllInvoices()) {
                writer.println(invoice.toCSV());
            }
        } catch(IOException e) {
            System.out.println("An Error Occurred! Could Not Save To : " + INVOICE_FILE);
        }
    }

    //Splits User CSVs into their respective users
    private User parseUser(String line) {
        if(line.trim().isEmpty()) {
            return null;
        }

        String[] userInfo = line.split(",");

        String userType = userInfo[0];
        String userId = userInfo[1];
        String name = userInfo[2];
        String password = userInfo[3];

        if(userType.equals("Customer")) {
            String licenseNo = userInfo[4];
            String contactNo = userInfo[5];

            return new Customer(userId, name, password, licenseNo, contactNo);
        }

        else if(userType.equals("Staff")) {
            return new Staff(userId, name, password);
        }
        
        else if(userType.equals("Admin")) {
            return new Admin(userId, name, password);
        }

        return null;
    }
    
    //Splits Vehicle CSVs into their respective vehicles
    private Vehicle parseVehicle(String line) {
        if(line.trim().isEmpty()) {
            return null;
        }

        String[] vehicleInfo = line.split(",");

        String vehicleType = vehicleInfo[0];
        String plateNo = vehicleInfo[1];
        String brand = vehicleInfo[2];
        String model = vehicleInfo[3];
        String transmission = vehicleInfo[4];
        int engineCap = Integer.parseInt(vehicleInfo[5]);
        int seatingCap = Integer.parseInt(vehicleInfo[6]);
        double mileage = Double.parseDouble(vehicleInfo[7]);
        double curFuelLevel = Double.parseDouble(vehicleInfo[8]);
        double maxFuelLevel = Double.parseDouble(vehicleInfo[9]);
        double dailyRate = Double.parseDouble(vehicleInfo[10]);
        boolean isAvailable = Boolean.parseBoolean(vehicleInfo[11]);
        boolean isDamaged = Boolean.parseBoolean(vehicleInfo[12]);
        int rentalCount = Integer.parseInt(vehicleInfo[13]);

        if(vehicleType.equals("Economy")) {
            double fuelDiscount = Double.parseDouble(vehicleInfo[14]);

            return null; //new Economy(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, isAvailable, isDamaged, rentalCount, fuelDiscount);
        }

        else if(vehicleType.equals("Suv")) {
            double serviceFee = Double.parseDouble(vehicleInfo[14]);

            return null; //new Suv(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, isAvailable, isDamaged, rentalCount, serviceFee);
        }
        
        else if(vehicleType.equals("Luxury")) {
            double serviceFee = Double.parseDouble(vehicleInfo[14]);
            double insuranceRate = Double.parseDouble(vehicleInfo[15]);

            return null; //new Luxury(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, isAvailable, isDamaged, rentalCount, serviceFee, insuranceRate);
        }

        return null;
    }
    
    //Splits Booking CSVs into individual bookings
    private Booking parseBooking(String line) {
        if(line.trim().isEmpty()) {
            return null;
        }

        String[] bookingInfo = line.split(",");

        String bookingId = bookingInfo[0];
        User customer = rentalData.getUserById(bookingInfo[1]);
        Vehicle vehicle = rentalData.getVehicleByPlate(bookingInfo[2]);
        LocalDate pickupDate = bookingInfo[3].equals("null") ? null : LocalDate.parse(bookingInfo[3]);
        LocalDate dropoffDate = bookingInfo[4].equals("null") ? null : LocalDate.parse(bookingInfo[4]);
        LocalDate returnedDate = bookingInfo[5].equals("null") ? null : LocalDate.parse(bookingInfo[5]);
        double fuelFee = Double.parseDouble(bookingInfo[6]);
        double lateFee = Double.parseDouble(bookingInfo[7]);
        double damageFee = Double.parseDouble(bookingInfo[8]);
        double baseTotal = Double.parseDouble(bookingInfo[9]);
        double extraTotal = Double.parseDouble(bookingInfo[10]);
        double finalTotal = Double.parseDouble(bookingInfo[11]);
        boolean isPickedup = Boolean.parseBoolean(bookingInfo[12]);
        boolean isDroppedoff = Boolean.parseBoolean(bookingInfo[13]);
        boolean isComplete = Boolean.parseBoolean(bookingInfo[14]);

        return new Booking(bookingId, customer, vehicle, pickupDate, dropoffDate, returnedDate, fuelFee, lateFee, damageFee, baseTotal, extraTotal, finalTotal, isPickedup, isDroppedoff, isComplete);
    }

    //Splits Invoice CSVs into individual invoices
    private Invoice parseInvoice(String line) {
        if(line.trim().isEmpty()) {
            return null;
        }

        String[] invoiceInfo = line.split(",");

        String invoiceId = invoiceInfo[0];
        String bookingId = invoiceInfo[1];
        String customerName = invoiceInfo[2];
        String plateNo = invoiceInfo[3];
        LocalDate invoiceDate = invoiceInfo[4].equals("null") ? null : LocalDate.parse(invoiceInfo[4]);
        double fuelFee = Double.parseDouble(invoiceInfo[5]);
        double lateFee = Double.parseDouble(invoiceInfo[6]);
        double damageFee = Double.parseDouble(invoiceInfo[7]);
        double baseTotal = Double.parseDouble(invoiceInfo[8]);
        double extraTotal = Double.parseDouble(invoiceInfo[9]);
        double finalTotal = Double.parseDouble(invoiceInfo[10]);

        return new Invoice(invoiceId, bookingId, customerName, plateNo, invoiceDate, fuelFee, lateFee, damageFee, baseTotal, extraTotal, finalTotal);
    }
}