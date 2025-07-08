import Exceptions.BookNotFoundException;
import Exceptions.InsufficientStockException;
import Exceptions.UnsellableBookException;
import book.Book;
import book.DemoBook;
import book.EBook;
import book.PaperBook;
import delivery.DeliveryService;
import delivery.MailService;
import delivery.ShippingService;

import java.util.ArrayList;
import java.util.List;

// Implemented strategy pattern to be able to change strategy in runtime
public class BookStore {
    private Inventory inventory;
    private DeliveryService deliveryService;

    public BookStore(Inventory inv, DeliveryService service) {
        this.inventory = inv;
        this.deliveryService = service;
    }

    public void addBook(Book book) {
        inventory.add(book);
    }

    public List<Book> removeOutdatedBooks() {
        return new ArrayList<>(inventory.removeOutdated());
    }


    public double sellBook(String isbn, int quantity, String email, String address) {
        try {
            Book book = inventory.get(isbn);
            if(book instanceof DemoBook) {
                throw new UnsellableBookException("This book is for showcase proposes only");
            }
            if(book instanceof PaperBook) {
                PaperBook pBook = (PaperBook)book;
                pBook.decreaseStock(quantity);
                if(!(deliveryService instanceof ShippingService))
                    this.setDeliveryService(new ShippingService());
            }
            else if (book instanceof EBook) {
                if(!(deliveryService instanceof MailService))
                    this.setDeliveryService(new MailService());
            }
            deliveryService.deliver(book, quantity, email, address);
            return book.getPrice() * quantity;
        }
        catch (BookNotFoundException | InsufficientStockException | UnsellableBookException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0;
    }

    public void setDeliveryService(DeliveryService service) {
        this.deliveryService = service;
    }

}
