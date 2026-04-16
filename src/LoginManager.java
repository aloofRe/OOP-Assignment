package src;
public class LoginManager {
    private MainManager mainManager;


    public LoginManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public User authenticateUser(String userId, String password) {
        User user = mainManager.getRentalData().getUserById(userId);
        
        if(user != null && !password.equals("null") && user.verify(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean registerNewUser(User user) {
        if(user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getName().trim().isEmpty() || user.getName().length() > 50 || user.getPassword().length() > 20) {
            return false;
        } else {
            mainManager.getRentalData().addUser(user);
            return true;
        }
    }
}