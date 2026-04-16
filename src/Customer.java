package src;
public class Customer extends User {
    private String licenseNo;
    private String contactNo;


    public Customer(String userId, String name, String password) {
        super(userId, name, password);
        this.licenseNo = "";
        this.contactNo = "";
    }

    public Customer(String userId, String name, String password, String licenseNo, String contactNo) {
        super(userId, name, password);
        this.licenseNo = licenseNo;
        this.contactNo = contactNo;
    }


    public String getLicenseNo() {
        return licenseNo;
    }

    public String getContactNo() {
        return contactNo;
    }


    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }


    @Override
    public String toCSV() {
        return ("Customer," + getUserId() + "," + getName() + "," + getPassword() + ","
             + getLicenseNo() + "," + getContactNo());
    }
}