package DataAccessObject;

import Models.Books;


import java.util.HashMap;

public class BooksData{
    public static HashMap<Integer,Books> book = new HashMap<>();

    public void addTempBooks(){
        book.put(1,new Books("Emma","English",2500.00,5));
        book.put(2,new Books("Our Universe","Space",2500.00,5));
        book.put(3,new Books("Black Hole","Space",2500.00,5));
        book.put(4,new Books("Time Machine","Science",2500.00,5));
        book.put(5,new Books("Atoms","Science",2500.00,5));
        book.put(6,new Books("Java full Stack","Software",2500.00,5));
        book.put(7,new Books("1997","History",2500.00,5));
        book.put(8,new Books("Astronomy","Space",2500.00,5));
    }

}
