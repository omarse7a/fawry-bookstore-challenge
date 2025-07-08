package delivery;

import book.Book;

public interface DeliveryService {
    void deliver(Book book, int quantity, String email, String address);
}
