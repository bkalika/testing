package it.discovery.repository;

import it.discovery.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static it.discovery.model.util.TestModelUtils.getBook;
import static org.junit.jupiter.api.Assertions.*;

class DBBookRepositoryTest {

    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new DBBookRepository();
    }

    @Test
    void saveBookValidNewBookSaved() {
        var book = getBook();
        bookRepository.saveBook(book);

        assertTrue(book.getId() > 0);
        Book book1 = bookRepository.findBookById(book.getId());
        assertNotNull(book1);

        assertEquals(book, book1);
    }

    @Test
    void findBookById() {
    }

    @Test
    void findBooks() {
    }
}