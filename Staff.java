public class Staff extends User {
    public Staff(String userId, String name, String password) {
        super(userId, name, password);
    }


    @Override
    public String toCSV() {
        return ("Staff," + getUserId() + "," + getName() + "," + getPassword());
    }
}