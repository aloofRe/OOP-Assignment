package src;
import java.util.Scanner;
import java.util.InputMismatchException;


public abstract class Menu {
    protected MainManager mainManager;
    protected Scanner scanner;


    public Menu(MainManager mainManager, Scanner scanner) {
        this.mainManager = mainManager;
        this.scanner = scanner;
    }

    public abstract void start();

    protected void notify(String message) {
        System.out.println(message + "\nPress Enter To Continue...");
        scanner.nextLine();
    }

    protected void clearScreen() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                //Try run the native "cls" command
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                //For other OS environments
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            //Fallback to pushing screen
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    protected int getIntInput(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                notify("Invalid Input. Please try again.");
            }
        }
    }

    protected double getDoubleInput(String prompt) {
        while(true) {
            System.out.print(prompt);
            try {
                double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                notify("Invalid Input. Please try again.");
            }
        }
    }
}
