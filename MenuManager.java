import java.util.Scanner;


public class MenuManager {
    private MainManager mainManager;
    private Scanner scanner;
    

    public MenuManager(MainManager mainManager, Scanner scanner) {
        this.mainManager = mainManager;
        this.scanner = scanner;
    }


    public void showLogin() {
        LoginMenu loginMenu = new LoginMenu(mainManager, scanner);

        loginMenu.start();
    }
    
    public void showUserMenus() {
        User sessionUser = mainManager.getSessionUser();
        
        if(sessionUser instanceof Customer) {
            new CustomerMenu(mainManager, scanner).start();
        }

        else if(sessionUser instanceof Staff) {
            new StaffMenu(mainManager, scanner).start();
        }

        else if(sessionUser instanceof Admin) {
            new AdminMenu(mainManager, scanner).start();
        }
    }
}