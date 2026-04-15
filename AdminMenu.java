import java.util.ArrayList;
import java.util.Scanner;


public class AdminMenu extends Menu {
    public AdminMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
        while(true) {
            clearScreen();

            //TODO:
            //admin title whatever
            //options 1 fleetmenu 2 report menu 3 logout

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    fleetMenu();
                    break;
                case 2:
                    reportMenu();
                    break;
                case 3:
                    mainManager.logout();
                    return;
                default:
                    break;
            }
        }
    }

    public void fleetMenu() {
        while(true) {
            clearScreen();
            ArrayList<Vehicle> allVehicles = mainManager.getRentalData().getAllVehicles();
            
            //TODO:
            //show fleetmenu title in a table -----
            //show table headings PlateNo | Brand Model | mileage | curfuellevel/maxfuellevel | isavailable | isDamaged
            for(int i = 0; i < allVehicles.size(); i++) {
                Vehicle vehicle = allVehicles.get(i);
                //TODO:
                //display like the start of the table "| i+1 | ""
                System.out.printf("%-10s | %-12s %-12s | %-8.2f | %5.2f/%5.2f | %-5b | %-5b", vehicle.getPlateNo(), vehicle.getBrand(),
                 vehicle.getModel(), vehicle.getMileage(), vehicle.getCurFuelLevel(), vehicle.getMaxFuelLevel(), vehicle.getIsAvailable(), vehicle.getIsDamaged());
                //TODO:
                //Close the table with another vertical line "|" and clear line
            }
            //TODO:
            //close table with bottom ------ and clear one line of space

            //TODO:
            //ask whether want to edit existing vehicle or add new vehicle or return to main customer menu
            //options 1 edit vehicle 2 add vehicle 3 remove vehicle 4 return
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    //TODO:
                    //ask which vehicle number from the list to pick
                    int pickedVehicle = scanner.nextInt();
                    scanner.nextLine();

                    if(pickedVehicle >= 1 && pickedVehicle <= allVehicles.size()) {
                        managementMenu(allVehicles.get(pickedVehicle - 1));
                    } else {
                        notify("Invalid Vehicle Number. Please Input A Valid Number From The List.");
                    }
                    break;
                case 2:
                    createVehicle();
                    break;
                case 3:
                    //TODO:
                    //ask which vehicle number from the list to remove
                    int removeVehicle = scanner.nextInt();
                    scanner.nextLine();

                    if(removeVehicle >= 1 && removeVehicle <= allVehicles.size()) {
                        mainManager.getVehicleManager().removeVehicle(allVehicles.get(removeVehicle - 1).getPlateNo());
                    } else {
                        notify("Invalid Vehicle Number. Please Input A Valid Number From The List.");
                    }
                    break;
                case 4:
                    return;
                default:
                    return;
            }
        }
    }

    public void managementMenu(Vehicle vehicle) {
        while(true) {
            clearScreen();

            //ask what the admin wants to edit
            //1 mileage 2 curfuellevel 3 dailyrate 4 availability 5 damaged 6 rentalcount 7 return
            int editChoice = scanner.nextInt();
            scanner.nextLine();

            switch(editChoice) {
                case 1:
                    //ask what mileage to set car
                    double mileage = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setMileage(mileage);
                    break;
                case 2:
                    //ask what curfuellevel to set car, also show maxfuellevel if can
                    double curFuelLevel = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setCurFuelLevel(curFuelLevel);
                    break;
                case 3:
                    //ask what dailyrate to set car
                    double dailyRate = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setDailyRate(dailyRate);
                    break;
                case 4:
                    //ask what availability 1 true or 2 false to set car
                    int isAvailable = scanner.nextInt();
                    scanner.nextLine();

                    vehicle.setIsAvailable(isAvailable == 1);
                    break;
                case 5:
                    //ask if damaged car 1 true 2 false
                    int isDamaged = scanner.nextInt();
                    scanner.nextLine();

                    vehicle.setIsDamaged(isDamaged == 1);
                    break;
                case 6:
                    //ask what rentalcount to set car
                    int rentalCount = scanner.nextInt();
                    scanner.nextLine();

                    vehicle.setRentalCount(rentalCount);
                    break;
                case 7:
                    return;
                default:
                    return;
            }
        }
    }

    public void createVehicle() {
        clearScreen();

        //TODO:
        //ask for what type of vehicle 1 economy 2 suv 3 luxury
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        //ask for new vehicle plateno
        String plateNo = scanner.nextLine();

        //ask for brand
        String brand = scanner.nextLine();

        //ask for model
        String model = scanner.nextLine();

        //ask for transmission
        String transmission = scanner.nextLine();

        //ask for enginecap
        int engineCap = scanner.nextInt();
        scanner.nextLine();

        //ask for seatingCap
        int seatingCap = scanner.nextInt();
        scanner.nextLine();

        //ask for mileage
        double mileage = scanner.nextDouble();
        scanner.nextLine();

        //ask for current fuel level of vehicle
        double curFuelLevel = scanner.nextDouble();
        scanner.nextLine();

        //ask for max fuel level of this vehicle
        double maxFuelLevel = scanner.nextDouble();
        scanner.nextLine();

        //ask for rate per day in RM
        double dailyRate = scanner.nextDouble();
        scanner.nextLine();

        switch(typeChoice) {
            case 1:
                //ask for fueldiscount in % decimal so like 0.15 is 15% discount
                double fuelDiscount = scanner.nextDouble();
                scanner.nextLine();

                //ask whether the admin is satisfied with these values and wants to add the vehicle
                //1 yes 2 no
                int economyChoice = scanner.nextInt();
                scanner.nextLine();

                switch(economyChoice) {
                    case 1:
                        mainManager.getVehicleManager().addVehicle(new Economy(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, true, false, 0, fuelDiscount));
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                //ask for service fee that is paid with base total before booking
                double serviceFee = scanner.nextDouble();
                scanner.nextLine();

                //ask whether the admin is satisfied with these values and wants to add the vehicle
                //1 yes 2 no
                int suvChoice = scanner.nextInt();
                scanner.nextLine();

                switch(suvChoice) {
                    case 1:
                        mainManager.getVehicleManager().addVehicle(new Suv(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, true, false, 0, serviceFee));
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                //ask for service fee that is paid with base total before booking
                double luxuryServiceFee = scanner.nextDouble();
                scanner.nextLine();

                //ask for insurance rate per day that is paid before booking
                double insuranceRate = scanner.nextDouble();
                scanner.nextLine();

                //ask whether the admin is satisfied with these values and wants to add the vehicle
                //1 yes 2 no
                int luxuryChoice = scanner.nextInt();
                scanner.nextLine();

                switch(luxuryChoice) {
                    case 1:
                        mainManager.getVehicleManager().addVehicle(new Luxury(plateNo, brand, model, transmission, engineCap, seatingCap, mileage, curFuelLevel, maxFuelLevel, dailyRate, true, false, 0, luxuryServiceFee, insuranceRate));
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void reportMenu() {
        while(true) {
            clearScreen();
            
            //TODO:
            //ask what report admin wants
            //1 most rented cars 2 total revenue 3 return
            int reportChoice = scanner.nextInt();
            scanner.nextLine();

            switch(reportChoice) {
                case 1:
                    clearScreen();
                    ArrayList<Vehicle> rentedVehicles = mainManager.getBookingManager().getMostRentedVehicles();

                    //TODO:
                    //show cool report-like title for top rented vehicles
                    for(int i = 0; i < rentedVehicles.size(); i++) {
                        Vehicle vehicle = rentedVehicles.get(i);

                        //TODO:
                        //display like number "1. "
                        System.out.printf("%-10s | %-12s %-12s | %-4d\n", vehicle.getPlateNo(), vehicle.getBrand(),
                             vehicle.getModel(), vehicle.getRentalCount());
                    }
                    //display closing ----- for report

                    notify("");
                    break;
                case 2:
                    clearScreen();
                    double totalRevenue = mainManager.getBookingManager().calculateTotalRevenue();

                    //just display that totalRevenue is the revenue made so far

                    notify("");
                    break;
                case 3:
                    return;
                default:
                    return;
            }
        }
    }
}