package Services;



import Models.Books;
import Services.Actions.BookActions;
import DataAccessObject.BooksData;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminServices extends BooksData implements BookActions{
    public static Scanner sc;
    int id,quantity,op,nextOp;
    String tittle,category;
    double price;
    boolean bool = false;

    public AdminServices(Scanner sc){
        this.sc = sc;
    }
    public void nextOperation(){
        try {
            System.out.println("Enter 1 for next operation and 0 to log-off");
            nextOp = sc.nextInt();
            if(nextOp == 1){
                performOperation();
            } else if(nextOp == 0){
                System.out.println("Log-off............");
            }
            else{
                System.out.println("please select 1 or 0");
                sc.next();
                nextOperation();
            }
        }catch(InputMismatchException e){
            System.out.println("Exception " + e);
            sc.next();
            nextOperation();
        }
    }


    public void performOperation(){

        try{
            System.out.println("""
                    Select.....
                    1. Add New Book.
                    2. Update the book.
                    3. Get details of single book.
                    4. Get all books.
                    5. Delete the book.
                    6. Log-off.
                    """);
            op = sc.nextInt();;
            switch (op){
                case 6:
                    break;
                case 1:
                    addBook();
                    nextOperation();
                    break;
                case 2:
                    updateBook();
                    nextOperation();
                    break;
                case 3:
                    getOneBook();
                    nextOperation();
                    break;
                case 4:
                    getAllBooks();
                    nextOperation();
                    break;
                case 5 :
                    deleteBook();
                    nextOperation();
                    break;
                default:
                    System.out.println("Select valid number");
                    performOperation();
            }
        }catch(InputMismatchException e){
            System.out.println("Input mismatch please enter valid a number");
            sc.next();
            performOperation();
        }
    }

    @Override
    public boolean checkKey(int id) {
        return book.containsKey(id);
    }

    @Override
    public void addBook() {
        try{
            System.out.println("Enter Book Id");
            id = sc.nextInt();
            bool = checkKey(id);
            if(bool){
                System.out.println("Id is already used for another book please enter another book id");
                addBook();
            }
            else{
                System.out.println("Enter Book tittle");
                tittle = sc.next();
                System.out.println("Enter Book category");
                category = sc.next();
                System.out.println("Enter Book price");
                price = sc.nextDouble();
                System.out.println("Enter available stock");
                quantity = sc.nextInt();
                Books newBook = new Books(tittle,category,price,quantity);
                book.put(id,newBook);
                System.out.println("Book is added to store");
            }
        } catch(InputMismatchException e){
            System.out.println("Exception " +e);
            sc.next();
            addBook();
        }

    }

    @Override
    public void deleteBook() {
        try{
            System.out.println("Enter book id to delete");
            id = sc.nextInt();
            bool = checkKey(id);
            if(bool){
                book.remove(id);
                System.out.println("Book is deleted");
            }
            else{
                System.out.println("Book not found to delete");
            }
        } catch(InputMismatchException e){
            System.out.println("Exception " +e);
            sc.next();
            deleteBook();
        }

    }

    @Override
    public void updateBook() {
        try{
            System.out.println("please enter the book id to update");
            id = sc.nextInt();
            bool = checkKey(id);
            if(bool){
                System.out.println("Enter new tittle");
                tittle = sc.next();
                System.out.println("Enter new category");
                category = sc.next();
                System.out.println("Enter new price");
                price = sc.nextDouble();
                System.out.println("Enter available stocks");
                quantity = sc.nextInt();
                book.put(id,new Books(tittle,category,price,quantity));
                System.out.println("Book is updated");
            } else{
                System.out.println("Book not found!...");
                updateBook();
            }
        }catch(InputMismatchException e){
            System.out.println("Exception " +e);
            sc.next();
            updateBook();
        }
    }

    @Override
    public void getOneBook() {
        try{
            System.out.println("please enter book id to fetch details");
            id = sc.nextInt();
            bool = checkKey(id);
            if(bool){
                System.out.println("Book Name:- " + book.get(id).getTitle() + "\n" +"Book Category:- " + book.get(id).getCategory() + "\n" + "Book Price:- " + book.get(id).getPrice() + "\n" + "Stock left:- " + book.get(id).getQuantity());
            }
            else{
                System.out.println("Book not found!");
                sc.next();
                getOneBook();
            }
        }catch (InputMismatchException e){
            System.out.println("Exception " +e);
            sc.next();
            getOneBook();
        }

    }

    @Override
    public void getAllBooks() {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-25s | %-10s | %-10s | %-10s%n", "Book Name", "Category","Price","Stock Left");
        System.out.println("-------------------------------------------------------------------");
        book.keySet().stream().forEach(i -> System.out.printf("%-25s | %-10s | %-10s | %-10s%n", book.get(i).getTitle(), book.get(i).getCategory(), book.get(i).getPrice(), book.get(i).getQuantity()));
        System.out.println("--------------------------------------------------------------------");
    }
}

