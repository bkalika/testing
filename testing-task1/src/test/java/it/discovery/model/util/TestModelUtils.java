package it.discovery.model.util;

import it.discovery.model.Book;

public class TestModelUtils {

    public static Book getBook() {
        Book book = new Book();
        book.setId(1);
        book.setName("Automation testing");
        book.setPages(200);
        book.setPrice(400);
        return book;
    }
}
