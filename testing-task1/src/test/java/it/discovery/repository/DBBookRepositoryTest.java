package it.discovery.repository;

import it.discovery.model.Book;
import it.discovery.util.TestModelUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBBookRepositoryTest {

    BookRepository repository;

    @BeforeEach
    void setUp() {
        repository = new DBBookRepository();
    }

    @Test
    @DisplayName("""
            When we call saveBook and pass new book then
             1. Identifier value is generate
             2. Book is saved and available in repository""")
    void saveBook_newBook_saved() {
        var book = TestModelUtils.createBook();
        repository.saveBook(book);

        assertTrue(book.getId() > 0);
        Book book1 = repository.findBookById(book.getId());
        assertNotNull(book1);
        // Compare state of book and book1
        assertEquals(book, book1);
    }

    @Test
    void findBookById() {
    }

    @Test
    void findBooks() {
    }
}