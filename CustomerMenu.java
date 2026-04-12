import java.util.Scanner;
import java.time.LocalDate;


public class CustomerMenu extends Menu {
    public CustomerMenu(MainManager mainManager, Scanner scanner) {
        super(mainManager, scanner);
    }


    @Override
    public void start() {
    }

    public void BrowseMenu() {
    }

    public void ReservationMenu(Vehicle vehicle, LocalDate pickupDate, LocalDate dropoffDate) {
    }

    public void PickupMenu() {
    }

    public void DropoffMenu() {
    }

    public void PaymentMenu() {
    }
}