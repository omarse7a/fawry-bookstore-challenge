package book;

import Exceptions.InsufficientStockException;

public class PaperBook extends Book {
    private int stock;

    public PaperBook(String ISBN, String title, int year, double price, int stock) {
        super(ISBN, title, year, price);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity > stock) {
            throw new InsufficientStockException("Insufficient stock for book " + title + " (ISBN: " + ISBN + ")");
        }
        stock -= quantity;
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}
