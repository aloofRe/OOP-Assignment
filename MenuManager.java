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
        
    }
}