import java.util.Scanner;


public class StaffMenu extends Menu {
    public StaffMenu(MainManager mainManager, Scanner scanner){
        super(mainManager, scanner);
    }


    @Override
    public void start() {
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
                mainManager.logout();
                return;
        }
    }

    public void returnMenu() {
    }

    public void inspectionMenu(Booking booking) {
    }
}