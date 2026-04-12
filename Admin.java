public class Admin extends User {
    public Admin(String userId, String name, String password){
        super(userId, name, password);
    }


    @Override
    public String toString(){
        return "";
    }

    @Override
    public String toCSV(){
        return "";
    }
}