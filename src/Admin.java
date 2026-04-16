package src;
public class Admin extends User {
    public Admin(String userId, String name, String password){
        super(userId, name, password);
    }


    @Override
    public String toCSV(){
        return ("Admin," + getUserId() + "," + getName() + "," + getPassword());
    }
}