
import DataAccessObject.BooksData;

import java.util.InputMismatchException;
import java.util.Scanner;


import DataAccessObject.UserData;
import Services.UserServices;
import Services.AdminServices;

public class Main {
    public Scanner sc;
    String isUser;
    UserServices userOp;
    int select,reg_lo;
    AdminServices admin;

    public void register(){
        userOp.addUser();
//        sc.nextLine();
        newUser();
    }
    public void login(){
        System.out.println("Login...........");
        boolean bool  = userOp.validateUser();
        if(bool){
            isUser = userOp.getRole(userOp.username);
            System.out.println(isUser);
            System.out.println("Successfully login...");
            if(isUser.equalsIgnoreCase("user")){
                userOp.books();
            }
            else{
                System.out.println("Hello Admin......");
                admin.performOperation();
            }
        }else{
            System.out.println("Incorrect username/password");
            login();
        }
    }

    public void newUser(){
        try{
            System.out.println("""
                    
                    1.Register.
                    2.Login
                    """);
            reg_lo = sc.nextInt();
            if(reg_lo ==1 ){
                register();
            }else if(reg_lo == 2){
                login();
            }else{
                System.out.println("Select 1 or 2");
                sc.next();
                newUser();
            }
        }catch(InputMismatchException e){
            System.out.println("Invalid input");
            sc.next();
            newUser();
        }
    }

    public Main(Scanner sc){
        this.sc = sc;
    }
    public void createDummyData(){
        BooksData books = new BooksData();
        UserData users = new UserData();
        users.createUsers();
        books.addTempBooks();
    }

    public static void main(String [] args){
        Scanner scan= new Scanner(System.in);
        Main bookStore = new Main(scan);
        bookStore.createDummyData();
        bookStore.userOp = new UserServices(scan);
        bookStore.admin = new AdminServices(scan);
        while(true) {
            bookStore.newUser();
        }
    }
}
