import java.util.Scanner;


public class AdminMenu extends Menu {
    public AdminMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
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
                mainManager.logout();
                return;
        }
    }

    public void fleetMenu() {
    }

    public void managementVehicleMenu() {
    }

    public void reportMenu() {
    }
}