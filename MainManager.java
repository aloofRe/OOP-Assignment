import java.util.Scanner;


public class MainManager {
    private RentalData rentalData;
    private DataManager dataManager;
    private MenuManager menuManager;
    private LoginManager loginManager;
    private BookingManager bookingManager;
    private VehicleManager vehicleManager;
    private User sessionUser;
    private Scanner scanner;
    private boolean systemRunning;


    public MainManager() {
        this.rentalData = new RentalData();
        this.dataManager = new DataManager(rentalData);
        this.scanner = new Scanner(System.in);
        this.menuManager = new MenuManager(this, scanner);
        this.loginManager = new LoginManager(this);
        this.bookingManager = new BookingManager(this);
        this.vehicleManager = new VehicleManager(this);
    }


    public RentalData getRentalData() {
        return rentalData;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public VehicleManager getVehicleManager() {
        return vehicleManager;
    }

    public User getSessionUser() {
        return sessionUser;
    }


    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
    

    public static void main(String[] args) {
        MainManager mainManager = new MainManager();

        mainManager.start();
    }

    public void start() {
        systemRunning = true;
        dataManager.loadData();

        while(systemRunning) {
            while(sessionUser == null) {
                menuManager.showLogin();
            }

            while(sessionUser != null) {
                menuManager.showUserMenus();
            }
        }
    }

    public void logout() {
        sessionUser = null;
    }

    public void shutdown() {
        systemRunning = false;
        dataManager.saveData();

        scanner.close();

        System.exit(0);
    }
}