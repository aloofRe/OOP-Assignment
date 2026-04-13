import java.util.Scanner;


public class LoginMenu extends Menu {
    public LoginMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
        //TODO:
        //display system logo and maybe title
        //display options
        //Please select an option? login/register/quit
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
                mainManager.shutdown();
                break;
            default:
                return;
        }
    }

    public void loginUser() {
        clearScreen();
        User user = null;

        while(user == null) {
            //TODO:
            //display ask for id eg "C0001"
            String userId = scanner.nextLine();

            //TODO:
            //display ask for password
            String password = scanner.nextLine();

            user = mainManager.getLoginManager().authenticateUser(userId, password);

            if(user == null) {
                clearScreen();
                notify("Login Failed. Please Check Your (User ID) Or (Password)");

                //TODO:
                //display want to retry? 1 yes 2 no
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
        clearScreen();
        boolean registered = false;
        User newUser = null;

        while(!registered) {
            int userListSize = mainManager.getRentalData().getAllUsers().size();
            String nextUserId = "C" + ((userListSize > 0) ? String.format("%04d", Integer.parseInt(mainManager.getRentalData()
                .getAllUsers().get(userListSize - 1).getUserId().substring(1)) + 1) : "0001");
            
            //TODO:
            //display ask for name
            String name = scanner.nextLine();

            //TODO:
            //display ask for password
            String password = scanner.nextLine();

            newUser = new Customer(nextUserId, name, password);
            registered = mainManager.getLoginManager().registerNewUser(newUser);

            if(!registered) {
                clearScreen();
                notify("Register Failed. Please Check Your (Name) Or (Password)");

                //TODO:
                //display want to retry? 1 yes 2 no
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

        if(registered) {
            clearScreen();
            notify("Register Successful! Your User ID Is : " + newUser.getUserId());
            return;
        }
    }
}