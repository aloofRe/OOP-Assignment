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

            System.out.println(".------------------------------.");
            System.out.println("|          ADMIN MENU          |");
            System.out.println("'------------------------------'");
            System.out.println("\n1. Fleet Management");
            System.out.println("2. Reports");
            System.out.println("3. Logout");
            System.out.print("\nEnter choice : ");
		
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
            
            System.out.println("========== FLEET MENU ==========");

            System.out.println("-".repeat(103));

            System.out.printf("| %-3s | %-10s | %-12s %-12s | %-10s | %-15s | %-9s | %-9s |\n",
                 "No", "PlateNo", "Brand", "Model", "Mileage", "Fuel", "Available", "Damaged");

            System.out.println("-".repeat(103));
		
            for(int i = 0; i < allVehicles.size(); i++) {
                Vehicle vehicle = allVehicles.get(i);

                System.out.printf("| %-3d | %-10s | %-12s %-12s | %-10.2f | %-15s | %-9b | %-9b |\n", i + 1 , vehicle.getPlateNo(), vehicle.getBrand(),
                     vehicle.getModel(), vehicle.getMileage(), String.format("%.2f/%.2f", vehicle.getCurFuelLevel(), vehicle.getMaxFuelLevel()), vehicle.getIsAvailable(), vehicle.getIsDamaged());
            }
           
            System.out.println("-".repeat(103));
			System.out.println("\n1. Edit Vehicle");
			System.out.println("2. Add Vehicle");
			System.out.println("3. Remove Vehicle");
			System.out.println("4. Return");
			System.out.print("\nEnter choice : ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
					System.out.print("Enter vehicle number to pick : ");
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
					System.out.print("Enter vehicle number to remove : ");
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
            
            System.out.println("========== EDIT VEHICLE ==========");
            System.out.println("1. Mileage");
            System.out.println("2. Current Fuel Level");
            System.out.println("3. Daily Rate");
            System.out.println("4. Availability");
            System.out.println("5. Damage Status");
            System.out.println("6. Rental Count");
            System.out.println("7. Return");
            System.out.print("\nEnter choice : ");
				
            int editChoice = scanner.nextInt();
            scanner.nextLine();

            switch(editChoice) {
                case 1:
					System.out.print("Enter new mileage : ");
					
                    double mileage = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setMileage(mileage);
                    break;
                case 2:
					System.out.print("Enter new current fuel level (Max : " + vehicle.getMaxFuelLevel() + ") : ");

                    double curFuelLevel = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setCurFuelLevel(curFuelLevel);
                    break;
                case 3:
					System.out.print("Enter new daily rate : ");

                    double dailyRate = scanner.nextDouble();
                    scanner.nextLine();

                    vehicle.setDailyRate(dailyRate);
                    break;
                case 4:
                    System.out.println("Enter new availability");
					System.out.println("1 = Available\n2 = Not Available");
                    System.out.print("\nEnter choice : ");

                    int isAvailable = scanner.nextInt();
                    scanner.nextLine();

                    vehicle.setIsAvailable(isAvailable == 1);
                    break;
                case 5:
                    System.out.println("Enter new damage status");
					System.out.println("1 = Damaged\n2 = Not Damaged");
                    System.out.print("\nEnter choice : ");

                    int isDamaged = scanner.nextInt();
                    scanner.nextLine();

                    vehicle.setIsDamaged(isDamaged == 1);
                    break;
                case 6:
					System.out.print("Enter new rental count : ");
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

        System.out.println("========== ADD VEHICLE ==========");
        System.out.println("Select Vehicle Type");
        System.out.println("1. Economy");
        System.out.println("2. SUV");
        System.out.println("3. Luxury");
        System.out.print("\nEnter choice : ");
			
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("\nEnter Plate Number : ");
        String plateNo = scanner.nextLine();

        System.out.print("Enter Brand : ");
        String brand = scanner.nextLine();

        System.out.print("Enter Model : ");
        String model = scanner.nextLine();

        System.out.print("Enter Transmission : ");
        String transmission = scanner.nextLine();

        System.out.print("Enter Engine Capacity : ");
        int engineCap = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Seating Capacity : ");
        int seatingCap = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Mileage : ");
        double mileage = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Current Fuel Level : ");
        double curFuelLevel = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Max Fuel Level : ");
        double maxFuelLevel = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Daily Rate (RM) : ");
        double dailyRate = scanner.nextDouble();
        scanner.nextLine();

        switch(typeChoice) {
            case 1:
                System.out.print("Enter Fuel Discount (e.g. 0.1 for 10%) : ");
                double fuelDiscount = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("\nConfirm Add Vehicle?");
				System.out.println("1. Yes");
				System.out.println("2. No");
			    System.out.print("\nEnter choice : ");
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
                System.out.print("Enter Service Fee (RM) : ");
                
                double serviceFee = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("\nConfirm Add Vehicle?");
                System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.print("\nEnter choice : ");
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
                System.out.print("Enter Service Fee (RM) : ");
                
                double luxuryServiceFee = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Enter Insurance Rate (per day) : ");
                
                double insuranceRate = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("\nConfirm Add Vehicle?");
                System.out.println("1. Yes");
				System.out.println("2. No");
				System.out.print("\nEnter choice : ");
				
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
            
            System.out.println("========== REPORT MENU ==========");
			System.out.println("1. Most Rented Cars Report");
			System.out.println("2. Total Revenue Report");
			System.out.println("3. Return");
			System.out.print("\nEnter choice : ");
			
            int reportChoice = scanner.nextInt();
            scanner.nextLine();

            switch(reportChoice) {
                case 1:
                    clearScreen();
                    ArrayList<Vehicle> rentedVehicles = mainManager.getBookingManager().getMostRentedVehicles();

                    System.out.println("========== MOST RENTED VEHICLES ==========");
                    System.out.println("-".repeat(55));
					
                    for(int i = 0; i < rentedVehicles.size(); i++) {
                        Vehicle vehicle = rentedVehicles.get(i);

                        System.out.printf("| %-3d | %-10s | %-12s %-12s | %-4d |\n", i + 1, vehicle.getPlateNo(), vehicle.getBrand(),
                             vehicle.getModel(), vehicle.getRentalCount());
                    }
                    System.out.println("-".repeat(55));

                    notify("");
                    break;
                case 2:
                    clearScreen();
                    double totalRevenue = mainManager.getBookingManager().calculateTotalRevenue();

					System.out.println("========== TOTAL REVENUE ==========");
					System.out.printf("Total Revenue : RM%.2f\n", totalRevenue);
					
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