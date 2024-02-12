package Services;

import DataAccessObject.BooksData;
import Services.Actions.UserActions;
import DataAccessObject.UserData;
import Models.User;
import Models.Books;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class UserServices extends UserData implements UserActions {

    public  Scanner sc;
    public String username;
    String pass,bookName,category,abc;
    public HashMap<Integer, Books> book = BooksData.book;
    boolean bool= false;
    int count,op,nextOp;
    double totalPrice;
    Optional<Double> basePrice;
    long credit_DebitNumber;

    public String getRole(String name){
        Optional<String> role = userData.values()
                .stream()
                .filter(i -> i.getUserName().equalsIgnoreCase(name))
                .map(User::getRole)
                .findFirst();
        return role.get();
    }

    @Override
    public void payment(){
        System.out.print("Please enter credit / debit card number= ");
        credit_DebitNumber = 0;
        try{
            credit_DebitNumber = sc.nextLong();
            if(Long.toString(credit_DebitNumber).length()!=16){
                System.out.println("Credit/Debit card number must 16 digits");
                payment();
            }
            else{
                System.out.println("""
                    
                    payment success
                    Oder Placed....
                    Thank you for purchasing the book in our store
                    Have a good day
                    
                    """);
            }

        }
        catch(InputMismatchException e){
            System.out.println("please enter valid number");
            sc.next();
            payment();
        }
    }
    @Override
    public void buy(){
        try{
            System.out.println("Enter book name to buy");
            sc.nextLine();
            bookName = sc.nextLine();
            bool =  book.values().stream().anyMatch(i -> i.getTitle().equalsIgnoreCase(bookName));
            if(bool){
                System.out.println("Enter how many books you want");
                count = sc.nextInt();

                basePrice = book.values().stream().filter(i -> i.getTitle().equalsIgnoreCase(bookName)).map(Books::getPrice).findFirst();
                totalPrice = basePrice.get() * count;
                System.out.println("Total Price:  " + totalPrice);
                payment();
            }else{
                System.out.println("Book not found! try again.....");

                buy();
            }
        }catch (Exception e){
            System.out.println("Exception "+e);
//
            buy();
        }
    }
    @Override
    public void searchByBookName() {
        try {
            sc.nextLine();
            System.out.println("Enter book name");
            String bookName = sc.nextLine();
            bool = book.values().stream().anyMatch(i -> i.getTitle().equalsIgnoreCase(bookName));
            if (bool) {
                System.out.println("------------------------------------------------------------------");
                System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Book Name", "Category", "Price", "Stock Left");
                System.out.println("-------------------------------------------------------------------");
                book.entrySet().stream()
                        .filter(entry -> entry.getValue().getTitle().equalsIgnoreCase(bookName))
                        .forEach(entry -> System.out.printf("%-25s | %-10s | %-10s | %-10s%n",
                                entry.getValue().getTitle(), entry.getValue().getCategory(),
                                entry.getValue().getPrice(), entry.getValue().getQuantity()));
                System.out.println("-------------------------------------------------------------------");
            } else {
                System.out.println("Book not found! try again.....");
                searchByBookName();
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
            sc.next();
            searchByBookName();
        }
    }
    @Override
    public void searchByBookCategory() {
        try {
            System.out.println("Enter book Category");
            category = sc.next();
            bool = book.values().stream().anyMatch(i -> i.getCategory().equalsIgnoreCase(category));
            if (bool) {
                System.out.println("------------------------------------------------------------------");
                System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Book Name", "Category", "Price", "Stock Left");
                System.out.println("-------------------------------------------------------------------");
                book.entrySet().stream()
                        .filter(entry -> entry.getValue().getCategory().equalsIgnoreCase(category))
                        .forEach(entry -> System.out.printf("%-25s | %-10s | %-10s | %-10s%n",
                                entry.getValue().getTitle(), entry.getValue().getCategory(),
                                entry.getValue().getPrice(), entry.getValue().getQuantity()));
                System.out.println("-------------------------------------------------------------------");
            } else {
                System.out.println("Book not found! try again.....");
                sc.next();
                searchByBookCategory();
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);
            sc.next();
            searchByBookCategory();
        }
    }


    public void printBooks() {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Book Name", "Category","Price","Stock Left");
        System.out.println("-------------------------------------------------------------------");
        book.keySet().stream().forEach(i -> System.out.printf("%-25s | %-10s | %-10s | %-10s%n", book.get(i).getTitle(), book.get(i).getCategory(), book.get(i).getPrice(), book.get(i).getQuantity()));
        System.out.println("-------------------------------------------------------------------");
    }
    public UserServices(Scanner sc){
        this.sc = sc;
    }

    @Override
    public boolean checkUserName(String userName) {
        return userData.values().stream().anyMatch(i -> i.getUserName().equals(userName));
    }

    @Override
    public void addUser() {
        try{
            System.out.println("create username= ");
            username = sc.next();
            if(checkUserName(username)){
                System.out.println("user name already exist");
                addUser();
                sc.next();
            } else{
                System.out.println("Enter name= ");
                String name = sc.next();
                System.out.println("create password= ");
                pass = sc.next();
                int id = userData.size();
                userData.put((id+1),new User(name,username,pass,"user"));
                System.out.println("Successfully Registered.......");
            }

        } catch (Exception e){
            System.out.println("Exception "+ e);
            sc.nextLine();
            addUser();
        }
    }

    @Override
    public boolean validateUser() {
        try{
            System.out.println("Enter username");
            username = sc.next();
            System.out.println("Enter password ");
            pass = sc.next();
            return userData.values().stream().anyMatch(i -> i.getUserName().equals(username) && i.getPassword().equals(pass));
        }catch (Exception e){
            System.out.println("Exception "+e);
            sc.next();
            validateUser();
        }
        return false;
    }

    public void isNextOrder(){
        try{
            System.out.println("""
                    1. next order.
                    2. log-off.
                    """);
            nextOp = sc.nextInt();
            if(nextOp == 1){
                performAction();
            } else if(nextOp == 2){
                System.out.println("Log-off............");
            }
            else{
                System.out.println("please select 1 or 2");
                sc.next();
                isNextOrder();
            }
        }catch (InputMismatchException e){
            System.out.println("Invalid input");
            sc.next();
            isNextOrder();
        }
    }
    public  void performAction(){
        try{
            System.out.println("""
                1.Search by Category.
                2.Search by book name.
                3. Buy
                4.Log-off
                """);
            op = sc.nextInt();
            switch(op){
                case 1:
                    searchByBookCategory();
                    performAction();
                    break;
                case 2:
                    searchByBookName();
                    performAction();
                    break;
                case 3:
                    buy();
                    isNextOrder();
                    break;
                case 4:
                    System.out.println("Log-Off..........");
                    break;
                default:
                    System.out.println("Select valid number");
                    sc.next();
                    performAction();;
            }
        }catch(Exception e){
            System.out.println("Invalid input");
            sc.next();
            performAction();
        }
    }

    public void books(){
        System.out.println("\n" +"Hello Mr." +userData.values().stream().filter(i -> i.getUserName().equalsIgnoreCase(username)).map(User::getName).findFirst().get());
        System.out.println("These are the books available in store");
        printBooks();
        sc.nextLine();
        performAction();
    }
}
