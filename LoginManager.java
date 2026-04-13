public class LoginManager {
    private MainManager mainManager;


    public LoginManager(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public User authenticateUser(String userId, String password) {
        User user = mainManager.getRentalData().getUserById(userId);
        
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean registerNewUser(User user) {
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        } else {
            mainManager.getRentalData().addUser(user);
            return true;
        }
    }
}