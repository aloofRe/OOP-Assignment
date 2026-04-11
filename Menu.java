import java.util.Scanner;


public abstract class Menu {
    protected MainManager mainManager;
    protected Scanner scanner;


    public Menu(MainManager mainManager, Scanner scanner) {
        this.mainManager = mainManager;
        this.scanner = scanner;
    }

    public abstract void start();
}
