package Services.Actions;

public interface BookActions {
    boolean checkKey(int id);
    void addBook();
    void deleteBook();
    void updateBook();
    void getOneBook();
    void getAllBooks();
}