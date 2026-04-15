import java.util.Scanner;
import java.time.LocalDate;


public class LoginMenu extends Menu {
    public LoginMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
        clearScreen();
        
        System.out.println(".------------------------------.");
        System.out.println("|          LOGIN MENU          |");
        System.out.println("'------------------------------'");
        System.out.print("\n1. Login\n2. Register\n3. Switch System Date\n4. Quit\n: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                loginUser();
                break;
            case 2:
                registerUser();
                break;
            case 3:
                switchDate();
                break;
            case 4:
                mainManager.shutdown();
                break;
            default:
                return;
        }
    }

    public void loginUser() {
        User user = null;

        while(user == null) {
            clearScreen();

            System.out.print("Please enter your User ID (e.g. C0001) : ");
            String userId = scanner.nextLine();

            System.out.print("Please enter your Password (Max 20 characters) : ");
            String password = scanner.nextLine();

            if(password.length() > 20) {
                password = null;
            }

            user = mainManager.getLoginManager().authenticateUser(userId, password);

            if(user == null) {
                clearScreen();
                notify("Login Failed. Please Check Your (User ID) Or (Password).");

                System.out.println("Would you like to retry?");
                System.out.print("1. Yes\n2. No\n: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 1:
                        continue;
                    case 2:
                        return;
                    default:
                        return;
                }
            }
        }

        if(user != null) {
            clearScreen();
            notify("Login Successful! Welcome, " + user.getName() + "!");

            mainManager.setSessionUser(user);
            return;
        }
    }

    public void registerUser() {
        boolean registered = false;
        User newUser = null;

        while(!registered) {
            clearScreen();

            int userListSize = mainManager.getRentalData().getAllUsers().size();
            String nextUserId = "C" + ((userListSize > 0) ? String.format("%04d", Integer.parseInt(mainManager.getRentalData()
                .getAllUsers().get(userListSize - 1).getUserId().substring(1)) + 1) : "0001");
            
            System.out.print("Please enter your Name (Max 50 characters) : ");
            String name = scanner.nextLine();

            System.out.print("Please enter your Password (Max 20 characters) : ");
            String password = scanner.nextLine();

            newUser = new Customer(nextUserId, name, password);
            registered = mainManager.getLoginManager().registerNewUser(newUser);

            if(!registered) {
                clearScreen();
                notify("Register Failed. Please Check Your (Name) Or (Password).");

                System.out.println("Would you like to retry?");
                System.out.print("1. Yes\n2. No\n: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 1:
                        break;
                    case 2:
                        return;
                    default:
                        return;
                }
            }
        }

        if(registered) {
            clearScreen();
            notify("Register Successful! Your User ID Is : " + newUser.getUserId());
            return;
        }
    }

    public void switchDate() {
        clearScreen();

        System.out.print("Current System Date : ");
        System.out.println(mainManager.getRentalData().getSystemDate());

        System.out.println("\nWould you like to change the date?");
        System.out.print("1. Yes\n2. No\n: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                System.out.print("What would you like as the new date?\n(YYYY-MM-DD) Format : ");
                
                LocalDate newDate = mainManager.getRentalData().getSystemDate();
                try {
                    newDate = LocalDate.parse(scanner.nextLine());
                } catch(Exception e) {
                    notify("Invalid Date. Please Ensure A Valid Date.");
                    break;
                }

                if(!newDate.isAfter(mainManager.getRentalData().getSystemDate())) {
                    notify("Invalid Date. Please Pick A Date In The Future.");
                    break;
                }

                mainManager.getRentalData().setSystemDate(newDate);
                break;
            case 2:
                return;
            default:
                return;
        }
    }
}