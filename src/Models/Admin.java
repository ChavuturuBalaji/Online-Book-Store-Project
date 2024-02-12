package Models;

public class Admin {
    private String name,role;
    final String username;
    final String password;
    // Other user details

    public Admin(String name, String username, String password) {
        this.name= name;
        this.username = username;
        this.password = password;
    }
    public String getName(){
        return name;
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){return password;}
}
