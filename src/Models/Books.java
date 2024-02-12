package Models;

public class Books {
    private String title;
    private String category;
    private double price;
    private int quantity;

    public Books(String title, String category, double price, int quantity) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }


    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changePrice(double price) {
        this.price = price;
    }

    public void changeName(String name) {
        this.title = name;
    }

    public void changeGenre(String genre) {
        this.category = genre;
    }
}
