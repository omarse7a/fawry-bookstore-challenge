import book.Book;
import book.DemoBook;
import book.EBook;
import book.PaperBook;
import delivery.MailService;
import delivery.ShippingService;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create inventory with a HashMap implementation
        Inventory inventory = new Inventory();

        // Create delivery service
        ShippingService shippingService = new ShippingService();

        // Create BookStore with inventory and delivery service
        BookStore bookStore = new BookStore(inventory, shippingService);

        System.out.println("===== Adding Books to Inventory =====");

        // Paper books
        PaperBook paperBook1 = new PaperBook("978-1234567890", "Java Programming", 2020, 29.99, 10);
        PaperBook paperBook2 = new PaperBook("978-0987654321", "Design Patterns", 2018, 39.99, 5);
        PaperBook oldBook = new PaperBook("978-1122334455", "Ancient Programming", 1960, 10.99, 2);

        // E-books
        EBook eBook1 = new EBook("978-2233445566", "Modern Java", 2022, 19.99, "PDF");
        EBook eBook2 = new EBook("978-3344556677", "Web Development", 2023, 24.99, "EPUB");

        // Add demo book
        DemoBook demoBook = new DemoBook("978-4455667788", "Programming Preview", 2025, 0.0);

        // Add books to store
        bookStore.addBook(paperBook1);
        bookStore.addBook(paperBook2);
        bookStore.addBook(oldBook);
        bookStore.addBook(eBook1);
        bookStore.addBook(eBook2);
        bookStore.addBook(demoBook);

        System.out.println("Added 3 paper books, 2 e-books, and 1 demo book to inventory");

        // Test selling books
        System.out.println("\n===== Testing Book Sales =====");

        // Sell paper book
        System.out.println("\nAttempting to sell 2 copies of \"" + paperBook1.getTitle() + "\"");
        double paperSale = bookStore.sellBook(paperBook1.getISBN(), 2, "test@example.com", "123 Main St, City");
        System.out.println("Sale complete: $" + paperSale);
        System.out.println("Remaining stock: " + paperBook1.getStock());

        // Sell e-book
        System.out.println("\nAttempting to sell an e-book \"" + eBook1.getTitle() + "\"");
        double eSale = bookStore.sellBook(eBook1.getISBN(), 1, "test@example.com", "");
        System.out.println("Sale complete: $" + eSale);

        // Try to sell demo book (should fail)
        System.out.println("\nAttempting to sell demo book \"" + demoBook.getTitle() + "\"");
        double demoSale = bookStore.sellBook(demoBook.getISBN(), 1, "test@example.com", "");
        System.out.println("Sale result: $" + demoSale);

        // Try to sell more copies than in stock (should fail)
        System.out.println("\nAttempting to sell more copies than available in stock");
        double overSale = bookStore.sellBook(paperBook2.getISBN(), 10, "test@example.com", "456 Main St, City");
        System.out.println("Sale result: $" + overSale);

        // Test removing outdated books
        System.out.println("\n===== Testing Removing Outdated Books =====");
        List<Book> removedBooks = bookStore.removeOutdatedBooks();
        System.out.println("Removed " + removedBooks.size() + " outdated books");
        for (Book book : removedBooks) {
            System.out.println("Removed: " + book.getTitle() + " (" + book.getYear() + ")");
        }

        // Change delivery service
        System.out.println("\n===== Testing Changing Delivery Service =====");
        bookStore.setDeliveryService(new MailService());
        System.out.println("Changed delivery service to Mail Service");

        // Sell book with new delivery service
        System.out.println("\nAttempting to sell paper book with Mail Service");
        double newSale = bookStore.sellBook(paperBook1.getISBN(), 1, "test@example.com", "789 Main St, City");
        System.out.println("Sale complete: $" + newSale);
        System.out.println("Note: Delivery service should have automatically switched to Shipping Service");

        System.out.println("\n===== BookStore Tests Complete =====");
    }
}