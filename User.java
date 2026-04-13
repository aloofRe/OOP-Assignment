public abstract class User {
    private String userId;
    private String name;
    private String password;


    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


	public boolean verify(String password) {
    	return this.password.equals(password);
	}

    public abstract String toCSV();
}