package Services.Actions;



public interface UserActions {
    boolean checkUserName(String userName);
    void addUser();
    boolean validateUser();
    void payment();
    void buy();
    void searchByBookName();
    void searchByBookCategory();
}

