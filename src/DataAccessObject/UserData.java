package DataAccessObject;

import java.util.HashMap;
import Models.User;
public class UserData {
    public static HashMap<Integer, User> userData = new HashMap<>();
    public void createUsers(){
        userData.put(1,new User("Tony","tony@123","Tony@2001","user"));
        userData.put(2,new User("Steve","steve@123","Steve@2001","user"));
        userData.put(3,new User("Banner","banner@123","Banner@2001","user"));
        userData.put(4,new User("clint","clint@123","Clint@2001","admin"));
        userData.put(5,new User("balaji","balaji@123","Balaji@2001","admin"));
    }
}
