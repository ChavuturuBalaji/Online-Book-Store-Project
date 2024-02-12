package Models;

public class User {
    private String name,role;
    final String username;
    final String password;

    public User(String name, String username, String password, String role) {
        this.name= name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public String getName(){
        return name;
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){return password;}
    public String getRole(){return role;}

}
