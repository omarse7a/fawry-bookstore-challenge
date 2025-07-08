import Exceptions.BookNotFoundException;
import book.Book;
import book.PaperBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Book> books;

    public Inventory() {
        books = new HashMap<>();
    }

    public void add(Book book) {
//        if(books.containsKey(book.getISBN()) && book instanceof PaperBook) {
//            PaperBook pBook = (PaperBook)book;
//            pBook.increaseStock(1);
//            books.put(book.getISBN(), book);
//        }
        books.put(book.getISBN(), book);
    }
    public Book get(String isbn) {
        Book book = books.get(isbn);
        if(book == null)
            throw new BookNotFoundException("Book " + isbn + " is not found in the inventory");
        return book;
    }

    public List<Book> removeOutdated() {
        List<Book> outdated = new ArrayList<>();
        List<String> toRemove = new ArrayList<>();
        for (Book b : books.values()) {
            if (b.isOutdated()) {
                toRemove.add(b.getISBN());
                outdated.add(b);
            }
        }
        for (String isbn : toRemove) {
            books.remove(isbn);
        }
        return outdated;
    }
}
