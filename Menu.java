import java.util.Scanner;


public abstract class Menu {
    protected MainManager mainManager;
    protected Scanner scanner;


    public Menu(MainManager mainManager, Scanner scanner) {
        this.mainManager = mainManager;
        this.scanner = scanner;
    }

    public abstract void start();

    public void notify(String message) {
        System.out.println(message + "\nPress Enter To Continue...");
        scanner.nextLine();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
